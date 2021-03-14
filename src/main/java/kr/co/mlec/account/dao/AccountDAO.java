package kr.co.mlec.account.dao;

import java.util.List;
import java.util.Map;

import kr.co.mlec.account.vo.AccountVO;
import kr.co.mlec.account.vo.FileVO;
import kr.co.mlec.member.vo.MemberVO;

public interface AccountDAO {
	List<AccountVO> selectAccount(MemberVO customer);
	List<AccountVO> selectOtherAccount(MemberVO customer);
	String calculateMyLoneStatus(AccountVO list);
	Map<String,Object> selectIncome(String customer_no);
	List<List<Map<String, Object>>> selectTotalAccount(String customer_no);
	
	void insertApply(String customer_no);
	
	void updateFile(Map<String,Object> file);
	void deleteOtherBankInfo(String accountNo);
}
