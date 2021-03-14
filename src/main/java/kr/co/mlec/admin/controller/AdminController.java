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
	
	//�ѹ��� ������ ȸ�� ��� ��
	private static final int LIST_SIZE =5;
	
	/*
	 * ������ ���������� �� ����� ����
	 * @return
	 */
	@RequestMapping("/manageCustomer/{no}")
	public ModelAndView selectCustomer(@PathVariable("no") String no) {
		//���� ������ ��ȣ ���� ����
		int pageNo = Integer.parseInt(no);
		
		// �ش� �������� �Խñ� ���
		List<Map<String,Object>> customerList = service.selectCustomer(pageNo);
		// ��ü �Խñ� ī��Ʈ
		int totalCount = service.selectCustomerCount();
		// ������ ������ ���ϱ�
		int lastPage = (totalCount % LIST_SIZE  == 0) ? totalCount /LIST_SIZE  
								                                    : totalCount / LIST_SIZE  + 1;		
		// ��Ͽ� ������ �� ������
		int tabSize  = 5;
		// ���� �������� �ش��ϴ� �� ��ġ 
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
	 * ������ ���������� ������ �� ������� �̵�
	 */
	@RequestMapping("/manageOneCustomer/{no}")
	public ModelAndView selectOneCustomer(@PathVariable("no") String no) {
		/*
		 * �ش� ����ȣ�� �⺻������ ���� ����� ������ 
		 */
		ModelAndView mav = new ModelAndView("/admin/manageOneCustomer");
		mav.addObject("oneInfo",service.selectOneCustomer(no));
		return mav;
	}
	
	/*
	 * ���������������� �˻��ϱ� ���
	 * @param ��������ȣ
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
	 * COFIX ���̺� ��������(������)
	 * @param : �����͸� ������ ��¥
	 * @return : ������ ���� �������� �̵�
	 */
	@RequestMapping("/manageData/{date}")
	public String selectCofix(@PathVariable("date") String date,Model model) {
		Map<String, Object> map = service.selectCofix(date);
		model.addAttribute("cofix",map);
		return "/admin/manageData";
	}
	/*
	 * COFIX ���̺� ��������(�ֽŵ���)
	 * @param : �����͸� ������ ��¥
	 * @return : �ֽŵ��� �������� �̵�
	 */
	@RequestMapping("/news/{date}")
	public String selectCofix2(@PathVariable("date") String date,Model model) {
		Map<String, Object> map = service.selectCofix(date);
		model.addAttribute("cofix",map);
		return "/etc/news";
	}
	/*
	 * COFIX ���̺� �߰��ϱ�
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
	 * COFIX ���̺� ��ȸ�ϱ�(������)
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
	 * COFIX ���̺� ��ȸ�ϱ�(�ֽŵ���)
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
	 * �� ��ǰ �߰��ϱ� 
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
	 * �����ڰ� ���� ��� üũ�ϱ�
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

