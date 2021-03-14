package kr.co.mlec.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.mlec.admin.service.AdminService;
import kr.co.mlec.common.PageVO;

@Controller
public class AdminController {
	@Autowired
	AdminService service;
	
	//한번에 보여질 회원 목록 수
	private static final int LIST_SIZE =5;
	
	/*
	 * 관리자 페이지에서 고객 목록을 관리
	 * @return
	 */
	@RequestMapping("/manageCustomer/{no}")
	public ModelAndView selectCustomer(@PathVariable("no") String no) {
		//현재 페이지 번호 저장 변수
		int pageNo = Integer.parseInt(no);
		
		// 해당 페이지의 게시글 목록
		List<Map<String,Object>> customerList = service.selectCustomer(pageNo);
		// 전체 게시글 카운트
		int totalCount = service.selectCustomerCount();
		// 마지막 페이지 구하기
		int lastPage = (totalCount % LIST_SIZE  == 0) ? totalCount /LIST_SIZE  
								                                    : totalCount / LIST_SIZE  + 1;		
		// 목록에 보여질 탭 사이즈
		int tabSize  = 5;
		// 현재 페이지에 해당하는 탭 위치 
		int currTab   = (pageNo  -1) / tabSize + 1;
		int beginPage = (currTab -1) * tabSize + 1;
		int endPage   = (currTab * tabSize < lastPage) ? currTab * tabSize 
					                                      : lastPage;
			
		PageVO page = new PageVO(pageNo,totalCount,lastPage,tabSize,currTab,beginPage,endPage);	
			// ======================================================================
		ModelAndView mav = new ModelAndView("/admin/manageCustomer");
		mav.addObject("customerList",customerList);
		mav.addObject("page",page);
		return mav;
	}
	
	/*
	 * 관리자 페이지에서 선택한 고객 목록으로 이동
	 */
	@RequestMapping("/manageOneCustomer/{no}")
	public ModelAndView selectOneCustomer(@PathVariable("no") String no) {
		/*
		 * 해당 고객번호의 기본정보와 서류 목록을 보여줌 
		 */
		ModelAndView mav = new ModelAndView("/admin/manageOneCustomer");
		mav.addObject("oneInfo",service.selectOneCustomer(no));
		return mav;
	}
	
	/*
	 * 관리자페이지에서 검색하기 기능
	 * @param 페이지번호
	 */
	@RequestMapping("/searchCustomer/{no}")
	public ModelAndView selectCustomerByKeyword(@PathVariable("no") String no
															,@RequestParam("keyword") String keyword
															,@RequestParam("type") String type){
		int pageNo = Integer.parseInt(no);
		List<Map<String,Object>> fileList = service.selectFileByKeyword(pageNo,type,keyword);
		System.out.println(fileList);
		int totalCount = service.selectFileByKeyword(type,keyword);
		int lastPage = (totalCount % LIST_SIZE  == 0) ? totalCount /LIST_SIZE  
								                                    : totalCount / LIST_SIZE  + 1;		
		int tabSize  = 5;
		int currTab   = (pageNo  -1) / tabSize + 1;
		int beginPage = (currTab -1) * tabSize + 1;
		int endPage   = (currTab * tabSize < lastPage) ? currTab * tabSize 
					                                      : lastPage;
			
		PageVO page = new PageVO(pageNo,totalCount,lastPage,tabSize,currTab,beginPage,endPage);	
		ModelAndView mav = new ModelAndView("/admin/manageCustomer");
		mav.addObject("customerList",fileList);
		mav.addObject("page",page);
		return mav;
	}
	
	
	/*
	 * COFIX 테이블 가져오기(관리자)
	 * @param : 데이터를 가져올 날짜
	 * @return : 데이터 관리 페이지로 이동
	 */
	@RequestMapping("/manageData/{date}")
	public String selectCofix(@PathVariable("date") String date,Model model) {
		Map<String, Object> map = service.selectCofix(date);
		model.addAttribute("cofix",map);
		return "/admin/manageData";
	}
	/*
	 * COFIX 테이블 가져오기(최신동향)
	 * @param : 데이터를 가져올 날짜
	 * @return : 최신동향 페이지로 이동
	 */
	@RequestMapping("/news/{date}")
	public String selectCofix2(@PathVariable("date") String date,Model model) {
		Map<String, Object> map = service.selectCofix(date);
		model.addAttribute("cofix",map);
		return "/etc/news";
	}
	/*
	 * COFIX 테이블 추가하기
	 */
	@PutMapping("/manageData/cofix/add")
	@ResponseBody
	public Map<String,Object> addCofix(@RequestBody Map<String, String> data){
	
		
		String result =service.addCofix(data);
		
		Map<String,Object> map = new HashMap<>();
		map.put("data","ture");
		return map;
	}
	/*
	 * COFIX 테이블 조회하기(관리자)
	 */
	@PutMapping("/manageData/cofix/select")
	@ResponseBody
	public Map<String,Map<String,Object>> selectCofix(@RequestBody Map<String, String> data){
	
		System.out.println(data);
		Map<String,Object> map =service.selectCofixByDate(data.get("cofix1"));
		Map<String,Map<String,Object>> map2 = new HashMap<String, Map<String,Object>>();
		map2.put("data", map);
		return map2;
	}
	/*
	 * COFIX 테이블 조회하기(최신동향)
	 */
	@PutMapping("/news/cofix/select")
	@ResponseBody
	public Map<String,Map<String,Object>> selectCofix2(@RequestBody Map<String, String> data){
	
		System.out.println(data);
		Map<String,Object> map =service.selectCofixByDate(data.get("cofix1"));
		Map<String,Map<String,Object>> map2 = new HashMap<String, Map<String,Object>>();
		map2.put("data", map);
		return map2;
	}
	/*
	 * 새 상품 추가하기 
	 */
	@RequestMapping("/manageData/item/add")
	@ResponseBody
	public Map<String,Object> addItem(@RequestBody Map<String,String> data){
		System.out.println(data);
		service.addItem(data);
		Map<String,Object> map = new HashMap<>();
		map.put("data","ture");
		return map;
	}
	/*
	 * 관리자가 서류 목록 체크하기
	 * 
	 */
	@RequestMapping("/manageData/file/check")
	public String updateFileStatus(HttpServletRequest request) {
		String[] checkName= request.getParameterValues("checkName");
		String customer_no = request.getParameter("customer_no");
		for(String str : checkName) {
			System.out.println(str);
		}
		System.out.println(customer_no);
		Map<String,Integer> map = new HashMap<>();
		for(int cnt = 0 ; cnt < checkName.length ; cnt++ ) {
			map.put(Integer.toString(cnt), Integer.valueOf(checkName[cnt]));
		}

		service.updateFileStatus(map);
		return "redirect:/manageOneCustomer/"+customer_no;
	}
}

