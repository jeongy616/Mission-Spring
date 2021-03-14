package kr.co.mlec.account.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.mlec.account.vo.AccountVO;
import kr.co.mlec.account.vo.FileVO;
import kr.co.mlec.member.vo.MemberVO;

@Repository
public class AccountDAOImpl implements AccountDAO {
	@Autowired
	private SqlSessionTemplate session;
	
	public List<AccountVO> selectAccount(MemberVO customer) {
		List<AccountVO> accountList = session.selectList("account.dao.AccountDAO.selectById",customer);
		return accountList;
	}
	@Override
	public List<AccountVO> selectOtherAccount(MemberVO customer) {
		List<AccountVO> accountList = session.selectList("account.dao.AccountDAO.selectById2",customer);
		return accountList;
	}
	@Override
	public String calculateMyLoneStatus(AccountVO list) {
		session.insert("account.dao.AccountDAO.insertOtherBank", list);
		return null;
	}

	@Override
	public Map<String, Object> selectIncome(String customer_no) {
		return session.selectOne("account.dao.AccountDAO.selectByCustomerNo",customer_no);
	}

	/*
	 * 
	 */
	public List<List<Map<String, Object>>> selectTotalAccount(String customer_no) {
		List<Map<String, Object>> list1 = session.selectList("account.dao.AccountDAO.selectTotalAccount",customer_no);
		List<Map<String, Object>> list2 = session.selectList("account.dao.AccountDAO.selectTotalAccount2",customer_no);
		
		List<List<Map<String, Object>>> list = new ArrayList<>();
		list.add(list1);
		list.add(list2);
		
		return list;
	}

	/*
	 * 마이페이지에서 고객이 직접 파일을 올릴 때
	 * @param 파일
	 */
	public void updateFile(Map<String,Object> file) {
		session.update("account.dao.AccountDAO.updateFile",file);
	}
	/*
	 * 타 은행 정보 삭제하기
	 * @param 타은행 번호
	 */
	public void deleteOtherBankInfo(String accountNo) {
		session.delete("account.dao.AccountDAO.deleteOtherAccount",accountNo);
	}
	/*
	 * 영업점 연결시 file 테이블에 공간 생성
	 * @param 고객 번호
	 */
	public void insertApply(String customer_no) {
		session.insert("account.dao.AccountDAO.insertApply1",customer_no);
	}
}
	
