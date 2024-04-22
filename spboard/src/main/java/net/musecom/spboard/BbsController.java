package net.musecom.spboard;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.musecom.spboard.dao.SpBoardDao;
import net.musecom.spboard.service.SpGetContentService;
import net.musecom.spboard.service.SpGetListService;
import net.musecom.spboard.service.SpSetContentService;

@Controller
public class BbsController {
	
	@Autowired
	SpGetListService getList;
	@Autowired
	SpBoardDao dao;
	@Autowired
	SpGetContentService getContent;
	//insert
	@Autowired
	SpSetContentService setContent;
	@RequestMapping("/list")
	public String list(@RequestParam(value="cpg", defaultValue="1") int cpg,
					   @RequestParam(value="searchname", defaultValue="") String searchname,
					   @RequestParam(value="searchvalue", defaultValue="") String searchvalue,
					   Model model) { //cPage를 받아오겠다 기본값은 1으로 int형 cPage 변수로
	
		System.out.println("list() 실행됨");
		model.addAttribute("cpg", cpg); //model에 파라미터로 받아온 cpg 보내줌
		model.addAttribute("searchname", searchname);
		model.addAttribute("searchvalue", searchvalue);
		getList.excute(model); //model에 cpg를 위에서 담고 excute로 getList로 보낸다 -> SpGetListService로 보내짐.
		return "list";
	}
	/*
	@RequestMapping("/contents")
	public String contents(@RequestParam(value="id", defaultValue="1") int id,
						   @RequestParam(value="cpg", defaultValue="1") int cpg,
						   Model model) {
	 
		System.out.println("contents() 실행됨");
		//상세보기
		SpBoardDto dto = dao.selectDetail(id); // 해당 id에 맞는 dto 값을 뽑아줘서 contents내용을 보이게 한다.
		
		model.addAttribute("cpg", cpg);
	    model.addAttribute("contents", dto);
	    
		return "contents";
	}
	*/
	@RequestMapping("/contents")
	public String contents(HttpServletRequest request, HttpServletResponse response, Model model) { //cookie는 request로 받아야하기 때문에 HttpSErvletRequest를 썻다.
	 
		System.out.println("contents() 실행됨");
		
		  //조회수 증가 판단 (쿠키 생성)
	      boolean increaseHit = true;
	      Cookie[] cookies = request.getCookies();
	      if(cookies != null) {
	         for(Cookie cookie : cookies) {
	            if(cookie.getName().equals("addHit_" + request.getParameter("id")) && cookie.getValue().equals("hit")) {
	               increaseHit = false;
	               break;
	            }
	         }
	      }

	      if(increaseHit) {  //조회수 증가 로직
	         model.addAttribute("increaseHit", true);
	         Cookie hitCookie = new Cookie("addHit_" + request.getParameter("id"), "hit");
	         hitCookie.setMaxAge(60*60); //1시간  24*60*60 하루
	         hitCookie.setPath("/"); //모든 루트에서 쿠키가 유효 함.
	         response.addCookie(hitCookie); //쿠키 저장
	      }else {
	         model.addAttribute("increaseHit", false);
	      }

		
		//상세보기
		model.addAttribute("req", request);		
		getContent.excute(model);
	    
		return "contents";
	}
	
	//똑같은 글쓰기버튼이지만 form으로 보내는 post와 a태그를 눌러서 주소창으로 보내는 get과 다르게 설정한다 !!
	
	@GetMapping("/write") //주소창(get)으로 넘어가게
	public String write(Model model) {
	
		System.out.println("write() 실행됨");
		
		return "write";
	}
	
	@PostMapping("/write") //post로 넘김
	public String writeok(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		System.out.println("writeok() 실행됨");
		model.addAttribute("request", request);
		setContent.excute(model);
		//mapper 확인
		//dao 확인
		//SpSetWriteService implement SpService 생성
		
		
		return "redirect:list";  //본문 실행 후 리턴값으로 list로 보냄
	}
	
	@GetMapping("/edit")
	public String edit(Model model) {
	
		System.out.println("edit() 실행됨");
		//mapper 확인
		//request.getParameter("id") 확인
		//SpGetContentService 실행해서 form에 값을 넣어줌
		
		return "edit";
	}
	
	@PostMapping("/edit")
	public String editok(Model model) {
		
		System.out.println("edit() 실행됨");
		//mapper 확인
		//request값을 dto에 담아
		//SpGetEditService 실행해서 db에 값을 넣어줌
		//parameter로 page를 갖고 있다가 
		//return "redirect:list?cpg="+cpg; 이렇게 넘긴다.
		
		return "edit";
	}
	
	
}
