package net.musecom.spboard.dao;

import java.sql.Connection;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import net.musecom.spboard.dto.SpBoardDto;

public class SpBoardDao implements DaoInterface {

	DataSource dataSource;
	Connection conn = null;
	
	public SpBoardDao() {
		Context context;
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/spboard");
			
			//커넥션
			
		} catch (NamingException e) {
		
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public ArrayList<SpBoardDto> list(int limit, int maxlist) {
		
		return null;
	}

	@Override
	public SpBoardDto select(int pg) {
		
		return null;
	}

	@Override
	public int del(int id) {
		
		return 0;
	}

	@Override
	public int update(int id) {
		
		return 0;
	}

}
