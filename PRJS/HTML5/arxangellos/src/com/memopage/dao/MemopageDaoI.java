package com.memopage.dao;

import java.util.List;

import com.memopage.dto.Memopage;
import com.memopage.dto.User;

public interface MemopageDaoI  {
	
	
	 List<Memopage>  getUserMemopages( User user );
	
	
	
	
	 Memopage getMemopage( int sid );
	 int insertMemopage (  Memopage mpage );
	 int updateMemopage (  Memopage mpage );
	 int deleteMemopage (  int sid );
	
	
	
	
}
