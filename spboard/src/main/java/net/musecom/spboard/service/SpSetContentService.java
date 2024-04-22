package net.musecom.spboard.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import net.musecom.spboard.dao.SpBoardDao;
import net.musecom.spboard.dto.SpBoardDto;

@Service
public class SpSetContentService implements SpService {
   
	@Autowired
    SpBoardDao dao;	

    @Override
	public void excute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("request");
		
		SpBoardDto dto = new SpBoardDto();
		
		dto.setTitle(req.getParameter("title"));
		dto.setContent(req.getParameter("content"));
		dto.setWriter(req.getParameter("writer"));
		dto.setPass(req.getParameter("pass"));
		//dto.setImnum(System.currentTimeMillis() / 1000L); //unix 타임 스탬프 값
		dto.setUserid("GUEST");
		
		dao.insert(dto);
		dao.updateRefId(dto.getId());
		
	}

}
