package kr.co.mlec;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import javax.sql.DataSource;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.mlec.board.vo.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:config/spring/spring-mvc.xml"})
public class DBTest {
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void DB�׽�Ʈ() throws Exception{
		assertNotNull(dataSource);
	}
	
	
	@Autowired(required=false)
	private SqlSessionTemplate session;
	
	@Ignore
	@Test
	public void ��ü�Խñ���ȸ�׽�Ʈ() throws Exception{
		List<BoardVO> list = session.selectList("board.dao.BoardDAO.selectAll");
		for(BoardVO vo : list) {
			System.out.println(vo);
		}
	}
	
	@Ignore
	@Test
	public void sqlSession�׽�Ʈ() throws Exception{
		assertNotNull(session);
	}
}
