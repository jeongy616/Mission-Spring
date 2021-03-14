package kr.co.mlec.member.dao;

import java.util.List;
import java.util.Map;

import kr.co.mlec.account.vo.FileVO;
import kr.co.mlec.member.vo.MemberVO;
/*
 * ȸ�� �����͸� �����ϴ� ��� Ŭ����
 * @author jeong-yeon
 */
public interface MemberDAO {
	/*
	 * ȸ���α��� �� ���̵� �� ��й�ȣ üũ
	 * @return boolean
	 */
	MemberVO checkLogin(MemberVO member);
	void insertMember(MemberVO member);
	Map<String,Object> selectAccount(String account_no);
	Map<String,String> loadMypage(String customer_no);
	void updateInfo(Map<String,Object> info);
	
	List<FileVO> selectFile(String customer_no);
	
	
}
