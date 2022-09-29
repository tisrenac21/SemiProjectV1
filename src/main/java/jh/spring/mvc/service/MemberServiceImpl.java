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

}
