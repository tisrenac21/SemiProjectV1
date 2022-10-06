package jh.spring.mvc.dao;

import jh.spring.mvc.vo.MemberVO;
import jh.spring.mvc.vo.Zipcode;

import java.util.List;

public interface MemberDAO {

	int insertMember(MemberVO mvo);

	MemberVO selectOneMember(String memberId);

	int selectOneMember(MemberVO mvo);

    int selectCountMemberId(String mid);

    List<Zipcode> selectZipcode(String dong);

}
