package jh.spring.mvc.service;

import jh.spring.mvc.vo.BoardVO;

import java.util.List;

public interface BoardService {

	boolean register(BoardVO bvo);

	List<BoardVO> readBoard(String fkey, String fval, int snum);

	BoardVO readOneBoard(String boardNo);

    int readCountBoard(String fkey, String fval);
}
