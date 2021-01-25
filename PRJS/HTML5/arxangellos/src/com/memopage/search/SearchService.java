package com.memopage.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.el.GreekAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.store.Directory;

import com.memopage.view.CalenderJsonItem;

public interface SearchService {

	
	
	//void indexMemopages(Directory directory, GreekAnalyzer analyzerGR  );

	//ArrayList<Document>  runSearch ( String searchText,  String fieldToSearch, Directory directory, GreekAnalyzer analyzerGR ); 
	
	
	public String    getSearchResults ( String searchTerms, String searchType ,  int nomosSid , int  pageTypeSid) ;
	
	public  List<CalenderJsonItem> getCalanderResultsForAUserSearch( String sid  ) ;
	
	
}
