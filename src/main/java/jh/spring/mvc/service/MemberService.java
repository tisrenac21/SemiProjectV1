package jh.spring.mvc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jh.spring.mvc.vo.MemberVO;

public interface MemberService {

	boolean newMember(MemberVO mvo);

	MemberVO readOneMember(String memberId);

	boolean checkLogin(MemberVO mvo);

    String checkMid(String mid);

    String findZipcode(String dong) throws JsonProcessingException;

}
