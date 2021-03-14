package kr.co.mlec.member.service;

import java.util.List;
import java.util.Map;

import kr.co.mlec.account.vo.FileVO;
import kr.co.mlec.member.vo.MemberVO;

public interface MemberService {
	MemberVO checkLogin(MemberVO member) throws Exception;
	void insertMember(MemberVO member) throws Exception;
	Map<String,Object> selectAccount(String account_no);
	Map<String,String> loadMypage(String customer_no);
	void updateInfo(Map<String,Object> info);
	
	List<FileVO> selectFile(String customer_no);
}
