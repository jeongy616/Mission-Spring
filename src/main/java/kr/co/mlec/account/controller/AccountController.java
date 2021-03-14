package kr.co.mlec.account.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.co.mlec.account.service.AccountService;
import kr.co.mlec.account.vo.AccountVO;
import kr.co.mlec.member.vo.MemberVO;

@Controller
public class AccountController {
	@Autowired
	AccountService service;
	@Autowired 
	ServletContext servletContext;
	
	/*
	 * �������� ������ �ε�� ���� ���ⵥ���� ��������
	 * 
	 */
	@GetMapping("/loan")
	public ModelAndView selectAccount(HttpServletRequest request){
		List<AccountVO> accountlist = service.selectAccount((MemberVO)request.getSession().getAttribute("loginInfo"));
		List<AccountVO> otherAccountlist = service.selectOtherAccount((MemberVO)request.getSession().getAttribute("loginInfo"));
		Map<String,Object> income = service.selectIncome(((MemberVO)request.getSession().getAttribute("loginInfo")).getCustomerNo());
		ModelAndView mav;
		
		if(income.get("INCOME_TYPE1") == null) {
			mav = new ModelAndView("redirect:/mypage/update");
			mav.addObject("msg","�ҵ������� �Է����� �ʾҽ��ϴ�.�Է� �� ���������� �����մϴ�.");
		}else{
			mav = new ModelAndView("/loan/checkLoanMyself");
			mav.addObject("accounts",accountlist);
			mav.addObject("income",income);
			mav.addObject("otherAccounts",otherAccountlist);
		}
		return mav;
	}

	@GetMapping("/myStatus/{no}")
	public ModelAndView test(@PathVariable("no") String no,HttpServletRequest request) {
		/*
		 * DSR���
		 */
		String customer_no = ((MemberVO)request.getSession().getAttribute("loginInfo")).getCustomerNo();
		List<List<Map<String, Object>>> accountlist  = service.selectTotalAccount(customer_no);
		if(no.equals("1")) {//���⿬��
			ModelAndView mav = new ModelAndView("/loan/myStatus");
			mav.addObject("list1", accountlist.get(0));
			mav.addObject("list2", accountlist.get(1));
			return mav;
		}else if(no.equals("2")){//����ű�
			ModelAndView mav = new ModelAndView("/loan/myStatusNew");
			mav.addObject("list1", accountlist.get(0));
			mav.addObject("list2", accountlist.get(1));
			return mav;
		}
		return null;
	}
	
	@PostMapping("/myStatus")
	public ModelAndView calculateMyLoneStatus(HttpServletRequest request) {
		String customer_no = ((MemberVO)request.getSession().getAttribute("loginInfo")).getCustomerNo();
		/*
		 * DSR���
		 */
		List<List<Map<String, Object>>> accountlist  = service.selectTotalAccount(customer_no);
		ModelAndView mav = new ModelAndView("loan/myStatus");
		mav.addObject("list1", accountlist.get(0));
		mav.addObject("list2", accountlist.get(1));
		return mav;
	}
	/*
	 * Ÿ���� ���� �߰��ϱ�
	 */
	@PostMapping("/addOtherLoan")
	public String addOtherLoan(AccountVO accountVO,HttpServletRequest request){
		String customer_no = ((MemberVO)request.getSession().getAttribute("loginInfo")).getCustomerNo();
		accountVO.setCustomerNo(customer_no);
		System.out.println(accountVO);
		service.calculateMyLoneStatus(accountVO);
		//�߰�
		return "redirect:/mypage";
	}
	/*
	 * Ÿ���� ���� �����ϱ�
	 */
	@GetMapping("/deleteMypage")
	public String deleteInfo(@RequestParam("no") String accountNo) {
		System.out.println(accountNo);
		service.deleteOtherBankInfo(accountNo);
		return "redirect:/mypage";
	}
	
	@PutMapping("/addApply")
	@ResponseBody
	public Map<String,Object> insertApply(HttpServletRequest request){
		String customerNo = ((MemberVO)request.getSession().getAttribute("loginInfo")).getCustomerNo();
		service.insertApply(customerNo);
		
		Map<String,Object> map = new HashMap<>();
	    map.put("data","true");
		return map;
	}
	/*
	 * ���� ���� ���� ���ε��ϱ�
	 */
	@PostMapping("/addFileUpload")
	public  String addFileUpload(MultipartHttpServletRequest mRequest
										,HttpServletRequest request) throws IllegalStateException, IOException{
		
		System.out.println(mRequest);
		String customerNo = ((MemberVO)request.getSession().getAttribute("loginInfo")).getCustomerNo();
		String name = ((MemberVO)request.getSession().getAttribute("loginInfo")).getName();
		String uploadDir = servletContext.getRealPath("/upload/");
		
	    Iterator<String> iter = mRequest.getFileNames();
	    
	    Map<String,Object> map = new HashMap<>();
	    int cnt = 0;
	    while (iter.hasNext()) {
	    	 cnt ++;
	         String formFileName = iter.next();
	
	         MultipartFile mFile = mRequest.getFile(formFileName);
	         if(mFile.getSize()!=0) {
	        	 String originFileName = "";
	        	 if(cnt == 1) {
	        		 originFileName = customerNo + name + "_��������_";
	        		 originFileName += mFile.getOriginalFilename();
	        		 map.put("originFileName1",originFileName);
		         }else if(cnt ==2){
		        	 originFileName = customerNo + name + "_��õ¡��_";
		        	 originFileName += mFile.getOriginalFilename();
		        	 map.put("originFileName2",originFileName);
	        	 }else if(cnt == 3) {
	        		 originFileName = customerNo + name + "_�����ٷμҵ濵����_";
	        		 originFileName += mFile.getOriginalFilename();
	        		 map.put("originFileName3",originFileName);
	        	 }
	        	 
		        if (originFileName != null && !originFileName.equals("")) {
		            String ext = "";
		            int index = originFileName.lastIndexOf(".");
		            if (index != -1) {
		               ext = originFileName.substring(index);
	            }
	            long fileSize = mFile.getSize();		// ���� ������
	            map.put("fileSize",fileSize);
	            
	            String saveFileName = "hana-" + UUID.randomUUID().toString() + ext;
	            if(cnt == 1) {
	            	map.put("saveFileName1",saveFileName);
	            }else if(cnt == 2) {
	            	map.put("saveFileName2",saveFileName);
	            }else if(cnt == 3) {
	            	map.put("saveFileName3",saveFileName);
	            }
	            mFile.transferTo(new File(uploadDir + saveFileName));
	            }
	         }
	      }
	    map.put("customerNo", customerNo);
        service.updateFile(map);
		return "redirect:/mypage";
	}
}
