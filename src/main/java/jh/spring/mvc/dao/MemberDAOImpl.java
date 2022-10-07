package jh.spring.mvc.dao;

import jh.spring.mvc.vo.MemberVO;
import jh.spring.mvc.vo.Zipcode;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("mdao")
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insertMember(MemberVO mvo) {
		return sqlSession.insert("member.insertMember",mvo);
	}

	@Override
	public MemberVO selectOneMember(String memberId) {
		return sqlSession.selectOne("member.selectOneMember",memberId);
	      }

	@Override
	public int selectOneMember(MemberVO mvo) {

		return sqlSession.selectOne("member.selectCountMember", mvo);
	}

	@Override
	public int selectCountMemberId(String mid) {
		return sqlSession.selectOne("member.selectCountMemberId", mid);
	}

	@Override
	public List<Zipcode> selectZipcode(String dong) {
		return sqlSession.selectList("member.selectZipcode",dong);
	}



}