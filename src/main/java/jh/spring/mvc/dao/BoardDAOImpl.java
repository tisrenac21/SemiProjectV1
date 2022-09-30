package jh.spring.mvc.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import jh.spring.mvc.vo.BoardVO;

@Repository("bdao")
public class BoardDAOImpl implements BoardDAO {
	
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert simpleInsert;
	
	public BoardDAOImpl(DataSource dataSource) {
		simpleInsert = new SimpleJdbcInsert(dataSource).withTableName("board").usingColumns("title","content","member_id");
	}
	

	@Override
	public int registerPost(BoardVO bvo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(bvo);
		
		
		return simpleInsert.execute(params);
	}

}
