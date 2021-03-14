package kr.co.mlec;

import org.apache.commons.codec.binary.Base64;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.mlec.member.dao.MemberDAO;
import kr.co.mlec.member.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:config/spring/spring-mvc.xml"})
public class MemberTest {
	@Autowired
	private SqlSessionTemplate session;
	@Autowired
	private MemberDAO memberDAO;
	
	@Ignore
	@Test
	public void 아이디비밀번호체크테스트()throws Exception{
		MemberVO member = new MemberVO();
		member.setId("admin");
		member.setPassword("1111");
		MemberVO member2 = new MemberVO();
		member2 = session.selectOne("member.dao.MemberDAO.selectById",member);
		System.out.println("success!!");
	}
	
	@Ignore
	@Test
	public void 디코딩테스트() throws Exception{
		String text = "ddddddd김김김";
		byte[] encoded = Base64.encodeBase64(text.getBytes());
		byte[] decoded = Base64.decodeBase64(encoded);
		
		System.out.println(new String(encoded));
		System.out.println(new String(decoded));
	}
	
}
