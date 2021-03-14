package kr.co.mlec.member.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.mlec.account.vo.FileVO;
import kr.co.mlec.member.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	private SqlSessionTemplate session;
	
	/*
	 * �α��ν� ���̵�� ��й�ȣ üũ
	 * @param : MemberVO
	 */
	public MemberVO checkLogin(MemberVO member) {
		return session.selectOne("member.dao.MemberDAO.selectById",member);
	}

	/*
	 * 
	 */
	public void insertMember(MemberVO member) {
		//ȸ�����̺� ȸ�����
		session.insert("member.dao.MemberDAO.insert",member);
	}
	/*
	 * �Է��� ���°� ��Ȯ���� ã��
	 */
	public Map<String,Object> selectAccount(String account_no) {
		Map<String,Object> result = session.selectOne("member.dao.MemberDAO.selectByAccount",account_no);
		System.out.println(result);
		return result;
	}

	/*
	 * ����ȣ�� �ش� ���� ��������
	 */
	public Map<String, String> loadMypage(String customer_no) {
		return session.selectOne("member.dao.MemberDAO.selectByCustomerNo",customer_no);
	}

	@Override
	public void updateInfo(Map<String, Object> info) {
		System.out.println(info);
		session.update("member.dao.MemberDAO.update", info);
		session.update("member.dao.MemberDAO.update2", info);
	}

	public List<FileVO> selectFile(String customer_no) {
		return session.selectList("member.dao.MemberDAO.selectFile",customer_no);
	}
}

