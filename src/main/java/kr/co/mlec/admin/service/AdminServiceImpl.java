package kr.co.mlec.admin.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.mlec.admin.dao.AdminDAO;
import kr.co.mlec.member.dao.MemberDAO;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	AdminDAO admindao;
	@Autowired
	MemberDAO memberdao;
	
	/*
	 * �ش� �������� �� ��� 
	 * @param
	 * @return
	 */
	public List<Map<String,Object>> selectCustomer(int pageNo) {
	
		return admindao.selectCustomer(pageNo);
	}
	
	/*
	 * ��ü �� ��� ī��Ʈ
	 * @return
	 */
	public int selectCustomerCount() {
		return admindao.selectCustomerCount();
	}
	/*
	 * �ش� ���� ���� 
	 * @param �� ��ȣ
	 */
	public Map<String, Object> selectOneCustomer(String no) {
		Map<String,Object> map = new HashMap<>();
		map.put("info", memberdao.loadMypage(no));
		map.put("file", admindao.loadFile(no));
		
		return map;
	}
	
	public List<Map<String,Object>> selectFile(int pageNo) {
		return admindao.selectFile(pageNo);
	}
	/*
	 * ��ü ���� ���� ��
	 * @return
	 */
	public int selectFileCount() {
		return admindao.selectFileCount();
	}
	/*
	 * Ű����� �˻�
	 */
	public List<Map<String,Object>> selectFileByKeyword(int pageNo, String type, String keyword) {
		Map<String,String> map = new HashMap<>();
		map.put("pageNo",Integer.toString(pageNo));
		map.put("type",type);
		map.put("keyword",keyword);
		return admindao.selectFileByKeyword(map);
	}
	/*
	 * 
	 * @return
	 */
	public int selectFileByKeyword(String type,String keyword) {
		Map<String,String> map = new HashMap<>();
		map.put("type",type);
		map.put("keyword",keyword);
		return admindao.selectFileByKeywordCount(map);
	}

	
	/*
	 * COFIX ���̺� ��������
	 * @return : �ش� ��¥�� cofix���̺� ������
	 */
	public Map<String, Object> selectCofix(String date) {
		Map<String, Object> map = admindao.selectCofix(date);
		return map;
	}
	/*
	 * COFIX ���̺� ��ȸ�ϱ�
	 */
	public Map<String, Object> selectCofixByDate(String date) {
		Map<String, Object> map = admindao.selectCofix(date);
		return map;
	}

	/*
	 * ���� ���� ���� �����ϱ�
	 */
	public void updateFileStatus(Map<String, Integer> map) {
		admindao.updateFileStatus(map);
	}

	/*
	 * �� ��ǰ �߰��ϱ�
	 */
	public void addItem(Map<String, String> data) {
		admindao.addItem(data);
	}

	/*
	 * cofix �߰��ϱ�
	 */
	public String addCofix(Map<String, String> data) {
		Date today = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
		format.format(today);
		int count = admindao.checkCofix(today.toString());
		if(count > 0) {
			return "�̹� �߰��߽��ϴ�.";
		}else {
			admindao.addCofix(data);
		}
		return "";
	}

}
