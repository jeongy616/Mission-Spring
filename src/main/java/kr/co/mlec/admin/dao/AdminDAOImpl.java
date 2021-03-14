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
	//한번에 보여질 수
	private static final int LIST_SIZE = 5;
	
	/*
	 * 해당 페이지의 고객 목록 
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
	 * 전체 고객 목록 카운트
	 * @return
	 */
	public int selectCustomerCount() {
		return session.selectOne("admin.dao.AdminDAO.selectCustomerCount");
	}
	/*
	 * 고객번호에 해당하는 최신 대출신청 파일 가져오기
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
	 * 전체 고객의 파일 수 
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
	 * COFIX 테이블 가져오기
	 * @param : 조회할 날짜 데이터
	 * @return : 해당 날짜의 cofix테이블 데이터
	 */
	public Map<String, Object> selectCofix(String date) {
		return session.selectOne("admin.dao.AdminDAO.selectCofix",date);
	}

	/*
	 * 서류 파일 상태 변경하기
	 * @param: map 형식의 파일 번호들
	 */
	public void updateFileStatus(Map<String, Integer> map) {
		for(String key : map.keySet()) {
			session.update("admin.dao.AdminDAO.updateFileStatus",(map.get(key)));
		}
	}
	
	/*
	 * 새 상품 추가하기 
	 */
	public void addItem(Map<String, String> map) {
		session.insert("admin.dao.AdminDAO.insertItem",map);
	}

	/*
	 * cofix 추가하기
	 */
	public void addCofix(Map<String, String> data) {
		session.insert("admin.dao.AdminDAO.insertCofix",data);
	}
	/*
	 * cofix 추가 전에 날짜 확인하기
	 */
	public int checkCofix(String date) {
		return session.selectOne("admin.dao.AdminDAO.selectCofixDate",date);
	}

}
