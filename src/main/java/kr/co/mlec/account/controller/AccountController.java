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
	 * 대출진단 페이지 로드시 관련 대출데이터 가져오기
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
			mav.addObject("msg","소득정보를 입력하지 않았습니다.입력 후 대출진단이 가능합니다.");
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
		 * DSR계산
		 */
		String customer_no = ((MemberVO)request.getSession().getAttribute("loginInfo")).getCustomerNo();
		List<List<Map<String, Object>>> accountlist  = service.selectTotalAccount(customer_no);
		if(no.equals("1")) {//대출연장
			ModelAndView mav = new ModelAndView("/loan/myStatus");
			mav.addObject("list1", accountlist.get(0));
			mav.addObject("list2", accountlist.get(1));
			return mav;
		}else if(no.equals("2")){//대출신규
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
		 * DSR계산
		 */
		List<List<Map<String, Object>>> accountlist  = service.selectTotalAccount(customer_no);
		ModelAndView mav = new ModelAndView("loan/myStatus");
		mav.addObject("list1", accountlist.get(0));
		mav.addObject("list2", accountlist.get(1));
		return mav;
	}
	/*
	 * 타은행 정보 추가하기
	 */
	@PostMapping("/addOtherLoan")
	public String addOtherLoan(AccountVO accountVO,HttpServletRequest request){
		String customer_no = ((MemberVO)request.getSession().getAttribute("loginInfo")).getCustomerNo();
		accountVO.setCustomerNo(customer_no);
		System.out.println(accountVO);
		service.calculateMyLoneStatus(accountVO);
		//추가
		return "redirect:/mypage";
	}
	/*
	 * 타은행 정보 삭제하기
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
	 * 서류 관련 파일 업로드하기
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
	        		 originFileName = customerNo + name + "_재직증명서_";
	        		 originFileName += mFile.getOriginalFilename();
	        		 map.put("originFileName1",originFileName);
		         }else if(cnt ==2){
		        	 originFileName = customerNo + name + "_원천징수_";
		        	 originFileName += mFile.getOriginalFilename();
		        	 map.put("originFileName2",originFileName);
	        	 }else if(cnt == 3) {
	        		 originFileName = customerNo + name + "_갑종근로소득영수증_";
	        		 originFileName += mFile.getOriginalFilename();
	        		 map.put("originFileName3",originFileName);
	        	 }
	        	 
		        if (originFileName != null && !originFileName.equals("")) {
		            String ext = "";
		            int index = originFileName.lastIndexOf(".");
		            if (index != -1) {
		               ext = originFileName.substring(index);
	            }
	            long fileSize = mFile.getSize();		// 파일 사이즈
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
