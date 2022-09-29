package jh.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	@GetMapping("/join")
	public String join() {
		return "join/join";
	}
	
	@GetMapping("/login")
	public String login() {
		return "join/login";
	}
	
	@GetMapping("/myinfo")
	public String myinfo() {
		return "join/myinfo";
	}

}
