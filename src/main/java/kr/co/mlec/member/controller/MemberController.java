package kr.co.mlec.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.mlec.account.service.AccountService;
import kr.co.mlec.account.vo.AccountVO;
import kr.co.mlec.account.vo.FileVO;
import kr.co.mlec.member.service.MemberService;
import kr.co.mlec.member.vo.MemberVO;

@Controller
public class MemberController {
	@Autowired
	private MemberService service;
	@Autowired
	private AccountService service2;
	
	@RequestMapping("/login")
	public String login() {
		return "/member/login";
	}
	
	/*
	 * �α��ν� ���̵� ��й�ȣ Ȯ��
	 */
	@RequestMapping("/login/checkLogin")
	public String checkLogin(@ModelAttribute("member") MemberVO member
									  ,HttpServletRequest request,Model model) throws Exception {
		MemberVO result = service.checkLogin(member);
		if(result == null) {
			model.addAttribute("msg", "������� ID Ȥ�� �н����尡 ��ġ���� �ʽ��ϴ�.");
			return "redirect:/login";
		}else {
			request.getSession().setAttribute("loginInfo",result);
			request.getSession().setMaxInactiveInterval(60*30);//���������ð��� 30������ ����
			model.addAttribute("msg", "�α��εǾ����ϴ�.");
			return "redirect:/index";
		}
	}
	/*
	 * ȸ������2�ܰ�� ���� Ȯ��
	 */
	@RequestMapping("/register3")
	public String singUp3(@RequestParam("account_number") String account_no,Model model) {
		Map<String,Object> result = service.selectAccount(account_no);
		if(result != null) {
			if(result.get("ID") == null) {
				model.addAttribute("account",result);
				model.addAttribute("memberVO",new MemberVO());
				return "/member/signUp3";
			}else {
				model.addAttribute("msg", "�̹� �����Ͽ����ϴ�.\n�α��� �������� �̵��մϴ�.");
				return "redirect:/login";
			}
		}else {
			model.addAttribute("msg", "��ϵ� ���¹�ȣ�� �����ϴ�.\n�޴��������� �ʿ��մϴ�.");
			return "/member/signUp2";
		}
	}
	
	/*
	 * ȸ������ 2�ܰ�� �޴��� ����
	 */
	@RequestMapping("/sendMessage")
	public ModelAndView sendSMS(@RequestParam("phoneNum") String phoneNum, @RequestParam("verification") String verification) {
		
		System.out.println(phoneNum);
		System.out.println(verification);
		System.out.println("sendSMS");
		ModelAndView mav = new ModelAndView("/sms/smssend");
		mav.addObject("action","go");
		mav.addObject("phoneNum", phoneNum);
		mav.addObject("verification",verification);
		return mav;
	}
	
	/*
	 * ȸ������ 3�ܰ�� 
	 */
	@RequestMapping(value="/register/info", method = RequestMethod.GET)
	public ModelAndView insert(Model model,@RequestParam("account_number") String account_no) {
		ModelAndView mav = new ModelAndView("/member/signUp3");
		mav.addObject("accountNo",account_no);
		mav.addObject("memberVO",new MemberVO());
		return mav;
	}
	@RequestMapping(value="/register/info", method = RequestMethod.POST)
	public String insert(@ModelAttribute("memberVO") @Valid MemberVO member, BindingResult result
							,HttpServletRequest request,Model model) throws Exception {
		if(result.hasErrors()) {
			return "/member/signUp3";
		}
		service.insertMember(member);
		model.addAttribute("msg","ȸ�����ԵǾ����ϴ�. �α������ּ���.");
		return "redirect:/login";
	}
	
	/*
	 * �α׾ƿ�
	 */
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request)throws Exception {
		request.getSession().removeAttribute("loginInfo");
		ModelAndView mav = new ModelAndView("/etc/main");
		mav.addObject("msg","�α׾ƿ��Ǿ����ϴ�.");
		
		return mav;
	}
	
	/*
	 * ȸ������ ���� �������� �� �� 
	 * @param : ���� ������ ���� request
	 */
	@RequestMapping("/mypage")
	public ModelAndView loadMypage(HttpServletRequest request) {
		MemberVO customer = (MemberVO)request.getSession().getAttribute("loginInfo");
		Map<String,String> result = service.loadMypage(customer.getCustomerNo());
		List<AccountVO> accountlist = service2.selectAccount(customer);
		List<AccountVO> otherAccountlist = service2.selectOtherAccount(customer);
		List<FileVO> files = service.selectFile(customer.getCustomerNo());
		
		ModelAndView mav = new ModelAndView("/member/mypage");
		mav.addObject("list1", accountlist);
		mav.addObject("list2",otherAccountlist);
		mav.addObject("memberInfo",result);
		mav.addObject("files",files);
		return mav;
	}
	
	@RequestMapping("/mypage/update")
	public ModelAndView loadIncomeType(HttpServletRequest request) {
		Map<String,String> result = service.loadMypage(((MemberVO)request.getSession().getAttribute("loginInfo")).getCustomerNo());
		ModelAndView mav = new ModelAndView("/member/updateMypage");
		mav.addObject("memberInfo",result);
		System.out.println(result);
		return mav;
	}
	
	/*
	 * ȸ������ �����ϱ� ������ ��� 
	 */
	@PostMapping("/updateMyInfo")
	public String updateInfo(HttpServletRequest request,@RequestParam("email") String email
																		 ,@RequestParam("address") String address
																		 ,@RequestParam("phoneNo") String phoneNo
																		 ,@RequestParam("incomeType1") String incomeType1
																		 ,@RequestParam(value="incomeType2",required=false,defaultValue="0") String incomeType2
																		 ,@RequestParam(value="incomeType3",required=false,defaultValue="0") String incomeType3
																		 ,@RequestParam(value="pro",required=false,defaultValue="0") String incomeType4
																		 ,@RequestParam("incomeMoney") String incomeMoney
																		 ,@RequestParam(value="pro_etc",required=false,defaultValue="0") String proEtc) {
		Map<String,Object> param = new HashMap<>();
		param.put("customerNo",((MemberVO)request.getSession().getAttribute("loginInfo")).getCustomerNo());
		param.put("email",email);
		param.put("address",address);
		param.put("phoneNo",phoneNo);
		param.put("incomeMoney",incomeMoney);
		param.put("incomeType1",incomeType1);
		param.put("incomeType2",incomeType2);
		param.put("incomeType3",incomeType3);
		param.put("incomeType4",incomeType4);
		param.put("incomeMoney",incomeMoney);
		param.put("proEtc",proEtc);
		System.out.println(incomeType3);
		
		service.updateInfo(param);
		return "redirect:/mypage";
	}
}