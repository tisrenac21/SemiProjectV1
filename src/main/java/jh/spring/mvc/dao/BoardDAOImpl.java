package jh.spring.mvc.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import jh.spring.mvc.vo.BoardVO;

@Repository("bdao")
public class BoardDAOImpl implements BoardDAO {
	
	private JdbcTemplate jdbcTemplate;
	@Override
	public int registerPost(BoardVO bvo) {
		String sql = "insert into board(title, content, member_id) values (?,?,?)";
		
		Object[] params = new Object[] {
				bvo.getTitle(), bvo.getContent(), bvo.getMemberId()
		};
		
		return jdbcTemplate.update(sql, params);
	}

}
