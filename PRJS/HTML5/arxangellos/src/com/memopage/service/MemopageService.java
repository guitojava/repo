package com.memopage.service;

import java.util.List;

import com.memopage.dto.Memopage;
import com.memopage.dto.User;
import com.memopage.view.MemopageBacking;


public interface MemopageService {
	
	public List<MemopageBacking>  getUserMemopages( User user  );
	

	
	
	 Memopage getMemopage( int sid );
	 MemopageBacking getMemopageBacking( int sid );
	 
	 int insertMemopage (  Memopage mpage );
	 int updateMemopage (  Memopage mpage );
	 int deleteMemopage (  int sid );
	
	
}
