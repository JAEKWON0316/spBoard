package net.musecom.spboard.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.musecom.spboard.dto.SpBoardDto;

@Repository //bean에다가 등록하는 법
public class SpBoardDao implements SpBoardMapper {

	@Autowired
	private SqlSession session;
	
	@Override
	public int insert(SpBoardDto dto) {
		
		return session.insert("insert", dto);
	}

	@Override
	public int update(SpBoardDto dto) {
		
		return 0;
	}

	@Override
	public int delete(int selectId) {
		
		return 0;
	}

	@Override
	public int selectTotalCount() {
		
		return session.selectOne("selectTotalCount"); //mapper에 해준 id값을 넣어줘야함.
	}

	@Override
	public SpBoardDto selectDetail(int selectId) {
		
		/*
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", selectId);
		System.out.println(selectId);
		return session.selectOne("selectDetail", map);  //map을 이용하여 하는법
		*/
		return session.selectOne("selectDetail", selectId);
	}

	@Override
	public List<SpBoardDto> selectList(Map<String, Object> params) {
		
		return session.selectList("selectList", params); //변수로 map을 보내서 selectList를 불러오겠다.
	}

	@Override
	public void increaseHit(int selectId) {
		session.update("increaseHit", selectId);
		
	}
	
	@Override
	public void updateRefId(int id) {
		session.update("updateRefId", id);		
	}

}
