package kr.co.mlec.admin.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.mlec.account.vo.FileVO;
import kr.co.mlec.member.vo.MemberVO;

@Repository
public class AdminDAOImpl implements AdminDAO {
	@Autowired
	SqlSessionTemplate session;
	//�ѹ��� ������ ��
	private static final int LIST_SIZE = 5;
	
	/*
	 * �ش� �������� �� ��� 
	 * @param
	 * @return
	 */
	public List<Map<String,Object>> selectCustomer(int pageNo) {
		int start = (pageNo -1) * LIST_SIZE +1;
		int end = (pageNo) * LIST_SIZE ;
		Map<String,Integer> map = new HashMap<>();
		map.put("start",start);
		map.put("end",end);
		return session.selectList("admin.dao.AdminDAO.selectCustomer",map);
	}

	/*
	 * ��ü �� ��� ī��Ʈ
	 * @return
	 */
	public int selectCustomerCount() {
		return session.selectOne("admin.dao.AdminDAO.selectCustomerCount");
	}
	/*
	 * ����ȣ�� �ش��ϴ� �ֽ� �����û ���� ��������
	 */
	public List<FileVO> loadFile(String no) {
		return session.selectList("admin.dao.AdminDAO.selectCustomerFile",no);
	}
	
	
	public List<Map<String, Object>> selectFile(int pageNo) {
		int start = (pageNo -1) * LIST_SIZE +1;
		int end = (pageNo) * LIST_SIZE ;
		Map<String,Integer> map = new HashMap<>();
		map.put("start",start);
		map.put("end",end);
		return session.selectList("admin.dao.AdminDAO.selectFile",map);
	}
	
	/*
	 * ��ü ���� ���� �� 
	 */
	public int selectFileCount() {
		return session.selectOne("admin.dao.AdminDAO.selectFileCount");
	}

	public List<Map<String, Object>> selectFileByKeyword(Map<String,String> map) {
		int pageNo = Integer.parseInt(map.get("pageNo"));
		int start = (pageNo -1) * LIST_SIZE +1;
		int end = (pageNo) * LIST_SIZE ;
		map.put("start",Integer.toString(start));
		map.put("end",Integer.toString(end));
		System.out.println(map.get("type"));
		System.out.println(map.get("keyword"));
		return session.selectList("admin.dao.AdminDAO.selectCustomerByKeyword",map);
	}

	public int selectFileByKeywordCount(Map<String, String> map) {
		return session.selectOne("admin.dao.AdminDAO.selectCutomerCountByKeword",map);
	}
	
	/*
	 * COFIX ���̺� ��������
	 * @param : ��ȸ�� ��¥ ������
	 * @return : �ش� ��¥�� cofix���̺� ������
	 */
	public Map<String, Object> selectCofix(String date) {
		return session.selectOne("admin.dao.AdminDAO.selectCofix",date);
	}

	/*
	 * ���� ���� ���� �����ϱ�
	 * @param: map ������ ���� ��ȣ��
	 */
	public void updateFileStatus(Map<String, Integer> map) {
		for(String key : map.keySet()) {
			session.update("admin.dao.AdminDAO.updateFileStatus",(map.get(key)));
		}
	}
	
	/*
	 * �� ��ǰ �߰��ϱ� 
	 */
	public void addItem(Map<String, String> map) {
		session.insert("admin.dao.AdminDAO.insertItem",map);
	}

	/*
	 * cofix �߰��ϱ�
	 */
	public void addCofix(Map<String, String> data) {
		session.insert("admin.dao.AdminDAO.insertCofix",data);
	}
	/*
	 * cofix �߰� ���� ��¥ Ȯ���ϱ�
	 */
	public int checkCofix(String date) {
		return session.selectOne("admin.dao.AdminDAO.selectCofixDate",date);
	}

}
