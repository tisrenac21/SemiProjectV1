package jh.spring.mvc.controller;


import jh.spring.mvc.service.MemberService;
import jh.spring.mvc.vo.MemberVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService msrv;
	
	//로그 유형 : trace, debug, info, warn, error
	protected Logger LOGGER =
			LoggerFactory.getLogger(getClass());
	
	
	// 로그인 상태가 아니면 ->  join/join
	// 로그인 상태면 -> "join/myinfo
	@GetMapping("/join")
	public String join(HttpSession session) {
		String returnPage = "join/join";
		
		if(session.getAttribute("m") != null) {
			returnPage = "redirect:/myinfo";
		}
		return returnPage;
	}
	
	@PostMapping("/join")
	public String joinok(MemberVO mvo) {
		
		//회원정보 저장
		if(msrv.newMember(mvo))
			LOGGER.info("회원가입 성공");
		
		LOGGER.info("joinok 호출 {}", mvo);
		
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String login() {
		return "join/login";
	}
	
	@PostMapping("/login") // 로그인 처리
	public String loginSuc(MemberVO mvo, HttpSession session) {
		String returnPage = "join/loginFail";
		
		if(msrv.checkLogin(mvo)) {
			session.setAttribute("m", mvo); //회원정보를 세션에 저장.
			returnPage = "redirect:/myinfo";
		}
		
		return returnPage;
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	// 로그인 상태가 아니면 ->  redirect:/login
	// 로그인 상태면 -> "join/myinfo
	@GetMapping("/myinfo")
	public String myinfo(Model m, HttpSession session) {
		String returnPage = "join/myinfo";
		
		if(session.getAttribute("m") != null) {
			MemberVO mvo = (MemberVO) session.getAttribute("m");
			m.addAttribute("mbr", msrv.readOneMember(mvo.getMemberId()));
		}else {
			returnPage = "redirect:/login";
		}
		return returnPage;
	}
	
	// 아이디 중복검사 - REST api 이용
	@ResponseBody
	@GetMapping("/checkmid")
	public String checkmid(String mid){
		String result = "잘못된 방법으로 호출하였습니다.";

		if(mid != null || !mid.equals("")) {
			result = msrv.checkMid(mid);
		}

		return result;
	}

}
