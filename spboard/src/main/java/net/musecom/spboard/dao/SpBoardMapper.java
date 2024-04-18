package net.musecom.spboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.musecom.spboard.dto.SpBoardDto;

@Mapper
public interface SpBoardMapper {
	
	//mapper.xml에서 여기에 있는 메소드명이 id값이 된다.
	
	//insert
    public int insert(SpBoardDto dto);
    
    //update
    public int update(SpBoardDto dto);
    
    //delete
    public int delete(int selectId);
    
    //전체 게시글 수
    public int selectTotalCount();
    
    //상세보기
    public SpBoardDto selectDetail(int selectId);
    
    //목록보기
    public List<SpBoardDto> selectList(Map<String, Object> params);
    
    //조회수 증가
    public void increaseHit(int selectId);
}
