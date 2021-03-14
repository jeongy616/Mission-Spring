package kr.co.mlec.account.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.mlec.account.dao.AccountDAO;
import kr.co.mlec.account.vo.AccountVO;
import kr.co.mlec.account.vo.FileVO;
import kr.co.mlec.member.vo.MemberVO;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountDAO accountdao;
	/*
	 * 
	 */
	public List<AccountVO> selectAccount(MemberVO customer) {
		List<AccountVO> accountList = accountdao.selectAccount(customer);
		return accountList;
	}
	/*
	 * 
	 */
	public List<AccountVO> selectOtherAccount(MemberVO customer) {
		List<AccountVO> otheraccountList = accountdao.selectOtherAccount(customer);
		return otheraccountList;
	}

	/*
	 * 
	 */
	public String calculateMyLoneStatus(AccountVO list) {
		accountdao.calculateMyLoneStatus(list);
		return null;
	}
	/*
	 * 
	 */
	public Map<String, Object> selectIncome(String customer_no) {
		return accountdao.selectIncome(customer_no);
	}
	
	/*
	 * 
	 */
	public List<List<Map<String, Object>>> selectTotalAccount(String customer_no) {
		return accountdao.selectTotalAccount(customer_no);
	}

	/*
	 * 
	 */
	public void updateFile(Map<String,Object> file) {
		accountdao.updateFile(file);
	}
	/*
	 * 타은행 정보 삭제하기
	 */
	public void deleteOtherBankInfo(String accountNo) {
		accountdao.deleteOtherBankInfo(accountNo);
	}
	
	/*
	 * 
	 */
	public void insertApply(String customer_no) {
		accountdao.insertApply(customer_no);
	}
}
