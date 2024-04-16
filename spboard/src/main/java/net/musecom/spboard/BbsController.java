package net.musecom.spboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.musecom.spboard.dao.SpBoardDao;
import net.musecom.spboard.dto.SpBoardDto;
import net.musecom.spboard.service.SpGetListService;

@Controller
public class BbsController {
	
	@Autowired
	SpGetListService getList;
	@Autowired
	SpBoardDao dao;
	
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
	
	@RequestMapping("/write")
	public String write(Model model) {
	
		System.out.println("write() 실행됨");
		
		return "write";
	}
	
	@RequestMapping("/edit")
	public String edit(Model model) {
	
		System.out.println("edit() 실행됨");
		
		return "edit";
	}
	
}
