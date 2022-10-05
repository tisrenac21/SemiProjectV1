package jh.spring.mvc.dao;

import jh.spring.mvc.vo.BoardVO;

import java.util.List;

public interface BoardDAO {

	int registerPost(BoardVO bvo);

	List<BoardVO> selectBoard(String fkey, String fval, int snum);

	BoardVO selectOneBoard(String boardNo);

    int readCountBoard(String fkey, String fval);
}
