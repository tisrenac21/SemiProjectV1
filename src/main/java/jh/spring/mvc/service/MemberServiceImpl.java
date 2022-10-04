package jh.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jh.spring.mvc.dao.MemberDAO;
import jh.spring.mvc.vo.MemberVO;

@Service("msrv")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO mdao;
	
	@Override
	public boolean newMember(MemberVO mvo) {
		boolean isIntert = false;
		
		
		//회원가입 성공 시 true값 return.
		if(mdao.insertMember(mvo) > 0) isIntert = true;
		
		return isIntert;
	}

	@Override
	public MemberVO readOneMember() {
		return mdao.selectOneMember();
	}

	@Override
	public boolean checkLogin(MemberVO mvo) {
		boolean loginResult = false;
		
		//회원이 존재한다면
		if((mdao.selectOneMember(mvo)) > 0) loginResult = true;
		
		
		return loginResult;
	}

}
