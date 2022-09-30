package jh.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jh.spring.mvc.dao.BoardDAO;
import jh.spring.mvc.vo.BoardVO;

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

}
