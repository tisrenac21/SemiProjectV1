package jh.spring.mvc.service;

import jh.spring.mvc.dao.BoardDAO;
import jh.spring.mvc.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bsrv")
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDAO bdao;

	@Override
	public boolean register(BoardVO bvo) {
		boolean result = false;
		if(bdao.registerPost(bvo) > 0) result = true;
		
		return result;
	}

	@Override
	public List<BoardVO> readBoard(int snum) {
		
		return bdao.selectBoard(snum);
	}

	@Override
	public BoardVO readOneBoard(String boardNo) {
		return bdao.selectOneBoard(boardNo);
	}

	@Override
	public int readCountBoard() {
		return bdao.readCountBoard();
	}

}
