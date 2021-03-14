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
	 * 해당 페이지의 고객 목록 
	 * @param
	 * @return
	 */
	public List<Map<String,Object>> selectCustomer(int pageNo) {
	
		return admindao.selectCustomer(pageNo);
	}
	
	/*
	 * 전체 고객 목록 카운트
	 * @return
	 */
	public int selectCustomerCount() {
		return admindao.selectCustomerCount();
	}
	/*
	 * 해당 고객의 정보 
	 * @param 고객 번호
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
	 * 전체 고객의 파일 수
	 * @return
	 */
	public int selectFileCount() {
		return admindao.selectFileCount();
	}
	/*
	 * 키워드로 검색
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
	 * COFIX 테이블 가져오기
	 * @return : 해당 날짜의 cofix테이블 데이터
	 */
	public Map<String, Object> selectCofix(String date) {
		Map<String, Object> map = admindao.selectCofix(date);
		return map;
	}
	/*
	 * COFIX 테이블 조회하기
	 */
	public Map<String, Object> selectCofixByDate(String date) {
		Map<String, Object> map = admindao.selectCofix(date);
		return map;
	}

	/*
	 * 서류 파일 상태 변경하기
	 */
	public void updateFileStatus(Map<String, Integer> map) {
		admindao.updateFileStatus(map);
	}

	/*
	 * 새 상품 추가하기
	 */
	public void addItem(Map<String, String> data) {
		admindao.addItem(data);
	}

	/*
	 * cofix 추가하기
	 */
	public String addCofix(Map<String, String> data) {
		Date today = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
		format.format(today);
		int count = admindao.checkCofix(today.toString());
		if(count > 0) {
			return "이미 추가했습니다.";
		}else {
			admindao.addCofix(data);
		}
		return "";
	}

}
