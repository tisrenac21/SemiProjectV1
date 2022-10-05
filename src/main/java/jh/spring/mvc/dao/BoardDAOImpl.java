package jh.spring.mvc.dao;

import jh.spring.mvc.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("bdao")
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert simpleInsert;
	private NamedParameterJdbcTemplate jdbcNamedTemplate;
	
	private RowMapper<BoardVO> boardMapper = BeanPropertyRowMapper.newInstance(BoardVO.class);
	
	public BoardDAOImpl(DataSource dataSource) {
		simpleInsert = new SimpleJdbcInsert(dataSource).withTableName("board").usingColumns("title","content","member_id");
		jdbcNamedTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	

	@Override
	public int registerPost(BoardVO bvo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(bvo);
		
		
		return simpleInsert.execute(params);
	}

	// 동적 질의문
	// 조건에 따라 실행할 질의문의 형태가 바뀌는 것
	// 제목으로 검색 : select * from board where title = ?
	// 작성자로 검색 : select * from board where member_id = ?
	// 내용으로 검색 : select * from board where content = ?
	// => select * from ? where ? = ? (실행 X : 테이블명과 컬럼명은 매개변수화 할 수 없다.)
	@Override
	public List<BoardVO> selectBoard(String fkey, String fval, int snum) {
		StringBuilder sql = new StringBuilder();
		sql.append("select board_no, title, member_id, reg_date, readcount from board ");

		if( fkey.equals("title") ) sql.append(" where title like :fval ");
		else if( fkey.equals("memberId") ) sql.append(" where member_id like :fval ");
		else if( fkey.equals("content") ) sql.append(" where content like :fval ");

		sql.append(" order by board_no desc limit :snum, 25");
		
		Map<String, Object> params = new HashMap<>();
		params.put("snum", snum);
		params.put("fval", "%"+fval+"%");
		
		return jdbcNamedTemplate.query(sql.toString(), params, boardMapper);
	}



	@Override
	public BoardVO selectOneBoard(String boardNo) {
		//조회수 증가
		String sql = "update board set readcount = readcount + 1 where board_no = ?";
		Object[] param = { boardNo };
		jdbcTemplate.update(sql, param);
		
		//본문글 가져오기
		sql = "select * from board where board_no = ?";
		return jdbcTemplate.queryForObject(sql, param, boardMapper);
	}

	@Override
	public int readCountBoard(String fkey, String fval) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT CEIL(COUNT(board_no)/25) pages from board ");

		if( fkey.equals("title") ) sql.append(" where title like :fval ");
		else if( fkey.equals("memberId") ) sql.append(" where member_id like :fval ");
		else if( fkey.equals("content") ) sql.append(" where content like :fval ");

		Map<String, Object> params = new HashMap<>();
		params.put("fval", "%"+fval+"%");

		return jdbcNamedTemplate.queryForObject(sql.toString(), params, Integer.class);
	}

	@Override
	public int deleteBoard(String boardNo) {
		String sql = "delete from board where board_no = ?";
		Object[] param = new Object[]{ boardNo };

		return jdbcTemplate.update(sql, param);
	}
}
