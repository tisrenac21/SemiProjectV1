package jh.spring.mvc.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import jh.spring.mvc.service.BoardService;
import jh.spring.mvc.vo.BoardVO;

@Controller
public class BoardController {
	
	protected Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BoardService bsrv;
	

	@GetMapping("/list")
	public String list(Model m) {
		m.addAttribute("bdlist", bsrv.readBoard());
		
		return "board/list";
	}
	
	@GetMapping("/view")
	public ModelAndView view(ModelAndView mv, String boardNo) {
		mv.setViewName("board/view");
		mv.addObject("bd", bsrv.readOneBoard(boardNo));
		
		return mv;
	}
	
	@GetMapping("/write")
	public String write(HttpSession session) {
		String returnPage = "redirect:/login";
		
		if(session.getAttribute("m") != null) {
			returnPage = "board/write";
		}
		
		return returnPage;
	}
	
	@PostMapping("/write")
	public String writeok(BoardVO bvo) {
		if(bsrv.register(bvo))
			LOGGER.info("게시글 등록 완료");
			
		LOGGER.info("writeok 호출 {}", bvo);

		
		return "redirect:/list";
	}
}
