package jh.spring.mvc.dao;

import jh.spring.mvc.vo.MemberVO;

public interface MemberDAO {

	int insertMember(MemberVO mvo);

	MemberVO selectOneMember(String memberId);

	int selectOneMember(MemberVO mvo);

}
