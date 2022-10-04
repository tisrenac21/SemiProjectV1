package jh.spring.mvc.dao;

import java.util.List;

import jh.spring.mvc.vo.BoardVO;

public interface BoardDAO {

	int registerPost(BoardVO bvo);

	List<BoardVO> selectBoard();

	BoardVO selectOneBoard(String boardNo);

}
