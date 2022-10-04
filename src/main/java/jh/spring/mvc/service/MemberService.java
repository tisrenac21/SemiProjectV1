package jh.spring.mvc.service;

import jh.spring.mvc.vo.MemberVO;

public interface MemberService {

	boolean newMember(MemberVO mvo);

	MemberVO readOneMember(String memberId);

	boolean checkLogin(MemberVO mvo);

}
