package kr.co.mlec.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.mlec.account.vo.FileVO;
import kr.co.mlec.common.LoginUtil;
import kr.co.mlec.member.dao.MemberDAO;
import kr.co.mlec.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberdao;
	
	public MemberVO checkLogin(MemberVO member) throws Exception {
		if((member.getPassword()).length() < 20) {
			String encode_password = LoginUtil.encryptPassword(member.getId(), member.getPassword());
			System.out.println(encode_password);
			member.setPassword(encode_password);
		}
		return memberdao.checkLogin(member);
	}

	public void insertMember(MemberVO member) throws Exception {
		String encode_password = LoginUtil.encryptPassword(member.getId(), member.getPassword());
		member.setPassword(encode_password);
		
		memberdao.insertMember(member);
	}

	public Map<String,Object> selectAccount(String account_no) {
		Map<String,Object> result = (Map<String,Object>)memberdao.selectAccount(account_no);
		return result;
	}

	@Override
	public Map<String, String> loadMypage(String customer_no) {
		return (Map<String,String>)memberdao.loadMypage(customer_no);
	}

	@Override
	public void updateInfo(Map<String, Object> info) {
		memberdao.updateInfo(info);
	}

	public List<FileVO> selectFile(String customer_no) {
		return memberdao.selectFile(customer_no);
	}


}
