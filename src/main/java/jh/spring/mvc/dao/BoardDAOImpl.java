package jh.spring.mvc.dao;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import jh.spring.mvc.vo.BoardVO;

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


	@Override
	public List<BoardVO> selectBoard() {
		String sql = "select board_no, title, member_id, reg_date, readcount from board order by board_no desc";
		
		return jdbcNamedTemplate.query(sql, Collections.emptyMap(), boardMapper);
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

}
