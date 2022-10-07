package jh.spring.mvc.dao;

import jh.spring.mvc.vo.BoardVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("bdao")
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int registerPost(BoardVO bvo) {
		return sqlSession.insert("board.registerPost", bvo);
	}

	@Override
	public List<BoardVO> selectBoard(String fkey, String fval, int snum) {

		Map<String, Object> params = new HashMap<>();
		params.put("fkey",fkey);
		params.put("fval",fval);
		params.put("snum",snum);

		return sqlSession.selectList("board.selectBoard",params);
	}

	@Override
	public BoardVO selectOneBoard(String boardNo) {
		//조회수 증가
		sqlSession.update("board.viewBoard", boardNo);

		//본문글 가져오기
		return sqlSession.selectOne("board.selectOneBoard",boardNo);
	}

	@Override
	public int readCountBoard(String fkey, String fval) {
		Map<String, Object> params = new HashMap<>();
		params.put("fkey",fkey);
		params.put("fval",fval);

		return sqlSession.selectOne("board.selectCountBoard",params);
	}

	@Override
	public int deleteBoard(String boardNo) {
		return sqlSession.delete("board.deleteBoard",boardNo);
	}

	@Override
	public int updateBoard(BoardVO bvo) {
		return sqlSession.update("board.updateBoard",bvo);
	}
}
