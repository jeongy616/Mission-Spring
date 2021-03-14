package kr.co.mlec;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.mlec.board.dao.BoardDAO;
import kr.co.mlec.board.vo.BoardVO;
import kr.co.mlec.service.BoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:config/spring/spring-mvc.xml"})
public class BoardTest {
	@Autowired
	private BoardDAO boardDAO;
	@Autowired
	private BoardService boardService;
	
	@Test
	public void 전체게시글리스트() throws Exception{
		List<BoardVO> boardList = boardDAO.select();
//		for(BoardVO board : boardList) {
//			System.out.println(board);
//		}
	}

}
