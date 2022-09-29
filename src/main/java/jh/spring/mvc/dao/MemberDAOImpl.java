package jh.spring.mvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jh.spring.mvc.vo.MemberVO;

@Repository("mdao")
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int insertMember(MemberVO mvo) {
		String sql = "insert into member (member_id, password, member_name, email) values (?, ?, ?, ?)";
		
		Object[] params = new Object[] {
				mvo.getMemberId(), mvo.getPassword(),
				mvo.getMemberName(), mvo.getEmail()
		};
		
		
		return jdbcTemplate.update(sql, params);
	}

}