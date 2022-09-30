package jh.spring.mvc.service;

import java.util.List;

import jh.spring.mvc.vo.BoardVO;

public interface BoardService {

	boolean register(BoardVO bvo);

	List<BoardVO> readBoard();

}
