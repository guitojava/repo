package com.koin.dao;



import java.util.List;

import com.koin.dto.KoinAreaCode;



public interface KoinAreaCodeDaoI  {
	
	public List<KoinAreaCode>  loadAreaCodes ( );
	public List<KoinAreaCode>  loadAllAreaCodes ( ); 
	public KoinAreaCode  getKoinAreaCodeWithDesc ( String descr );
	public KoinAreaCode  getKoinAreaCodeWithSid ( int sid );
	List<KoinAreaCode>  getKoinAreaCodesThatAreUsedForMemopages(  );
	
}
