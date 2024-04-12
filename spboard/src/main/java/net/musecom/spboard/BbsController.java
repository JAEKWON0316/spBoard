package net.musecom.spboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BbsController {
	   
	@RequestMapping("/list")
	public String list(Model model) {
	
		System.out.println("list() 실행됨");
		
		return "list";
	}
	
	@RequestMapping("/contents")
	public String contents(Model model) {
	
		System.out.println("contents() 실행됨");
		
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
