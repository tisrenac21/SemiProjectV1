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
	public List<BoardVO> readBoard(String fkey, String fval, int snum) {
		
		return bdao.selectBoard(fkey, fval+"%", snum);
	}

	@Override
	public BoardVO readOneBoard(String boardNo) {
		return bdao.selectOneBoard(boardNo);
	}

	@Override
	public int readCountBoard(String fkey, String fval) {
		return bdao.readCountBoard(fkey, fval+"%");
	}

	@Override
	public boolean removeBoard(String boardNo) {
		boolean isDelete = false;

		if(bdao.deleteBoard(boardNo) > 0) isDelete = true;

		return isDelete;
	}

	@Override
	public boolean modifyBoard(BoardVO bvo) {
		boolean isUpdate = false;
		if(bdao.updateBoard(bvo) > 0) isUpdate = true;

		return isUpdate;
	}

}
