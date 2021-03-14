package kr.co.mlec.member.dao;

import java.util.List;
import java.util.Map;

import kr.co.mlec.account.vo.FileVO;
import kr.co.mlec.member.vo.MemberVO;
/*
 * 회원 데이터를 관리하는 기능 클래스
 * @author jeong-yeon
 */
public interface MemberDAO {
	/*
	 * 회원로그인 시 아이디 및 비밀번호 체크
	 * @return boolean
	 */
	MemberVO checkLogin(MemberVO member);
	void insertMember(MemberVO member);
	Map<String,Object> selectAccount(String account_no);
	Map<String,String> loadMypage(String customer_no);
	void updateInfo(Map<String,Object> info);
	
	List<FileVO> selectFile(String customer_no);
	
	
}
