package com.memopage.dao;

import java.util.List;

import com.memopage.dto.MemopageCategory;

public interface MemopageCategoryDaoI  {
	
	
	 List<MemopageCategory>  getCategories(  );
	
	 MemopageCategory getMemopageCategory( int sid );
	 
	 List<MemopageCategory>  getCategoriesThatAreUsed( int areaCode );
	 
	
}
