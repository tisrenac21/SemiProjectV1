package jh.spring.mvc.controller;

import jh.spring.mvc.service.BoardService;
import jh.spring.mvc.vo.BoardVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class BoardController {
	
	protected Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BoardService bsrv;
	
	/* 페이징  처리 */
	/* 페이지 당 게시믈 수 perPage : 25 */
	/* 총 페이지 수 : 전체 게시글 / 페이지 당 게시믈 수 */
	/* 총 페이지 수 pages : ceil(getTotalPage / perPage) */
	/* 2 = 50 / 25. 3 = 51 / 25 */
	
	/* 페이지 별 읽어올 게시글 범위 */
	/* 총 게시글이 55건이라 할 때 */
	/* 1 page : 1번째 ~ 25번째 게시글 */
	/* 2 page : 26번째 ~ 50번째 게시글 */
	/* 3 page : 51번째 ~ 55번째 게시글 */
	
	/* i page : m번째 ~ n번째 */
	/* snum = (i - 1) * 25 + 1 */

	/* 현재 페이지에 따라서 보여줄 페이지 블럭 결정 */
	/* ex) 총 페이지 수가 27일 때 */
	/* cpg = 1 : 1 2 3 4 5 6 7 8 9 10 */
	/* cpg = 5 : 1 2 3 4 5 6 7 8 9 10 */
	/* cpg = 9 : 1 2 3 4 5 6 7 8 9 10 */
	/* cpg = 10 : 1 2 3 4 5 6 7 8 9 10 */
	/* cpg = 11 : 11 12 13 14 15 16 17 18 19 20 */
	/* cpg = 17 : 11 12 13 14 15 16 17 18 19 20 */
	/* cpg = 23 : 21 22 23 24 25 26 27 */
	/* cpg = 26 : 21 22 23 24 25 26 27 */
	/* cpg = n : ? ?+1 ?+2 ... ?+9 */
	/* stpgn = ((cpg - 1) / 10) * 10 + 1 */


	@GetMapping("/list")
	public String list(Model m, String cpg) {
		int perPage = 25;
		if (cpg == null || cpg.equals("")) cpg = "1";
		int cpage = Integer.parseInt(cpg);
		int snum = (cpage-1) * perPage;
		int stpgn = ((cpage - 1) / 10) * 10 + 1;
		
		m.addAttribute("bdlist", bsrv.readBoard(snum));
		m.addAttribute("stpgn", stpgn);
		/*m.addAttribute("cpg", Integer.parseInt(cpg));*/
		
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
