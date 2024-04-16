package net.musecom.spboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import net.musecom.spboard.dao.SpBoardDao;
import net.musecom.spboard.dto.SpBoardDto;
import net.musecom.spboard.util.Paging;

@Service
public class SpGetListService implements SpService {

	@Autowired
	Paging pg;
	@Autowired
	SpBoardDao dao;
	
	@Override
	public void excute(Model model) {
		Map<String, Object> map = model.asMap(); //모델을 쪼개서 쓸 수 있게해줌. (map.get으로 담긴 값을 쓸 수 있게됨.)
		int currentPage = (int) map.get("cpg");
		int postsPerPage = 10;
		int pagesPerBlock = 5;
		int totalPosts = dao.selectTotalCount();
		//paging에 담아서(setter) 안에 써준 getter로 뽑아주기위함.
		pg.setCurrentPage(currentPage);
		pg.setPagesPerBlock(pagesPerBlock);
		pg.setPostsPerPage(postsPerPage);
		pg.setTotalPosts(totalPosts);
		int limitCount = (currentPage - 1) * postsPerPage;
		
		map.put("currentPage", limitCount);
		map.put("listCount", postsPerPage);
			
		List<SpBoardDto> list = dao.selectList(map); // (0,10) (10,10) (20,10) (30,10) 한페이지에 목록수 10개씩 밑에 페이지는 5개씩 보임
		model.addAttribute("pg", pg);
		model.addAttribute("list", list);
		
	}

}
