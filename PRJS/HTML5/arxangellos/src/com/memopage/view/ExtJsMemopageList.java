package com.memopage.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ExtJsMemopageList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2655359995124519669L;
	private String total ="0"; 
	private List<MemopageBacking> results = new ArrayList<MemopageBacking>();
	
	
	public ExtJsMemopageList() {
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List<MemopageBacking> getResults() {
		return results;
	}

	public void setResults(List<MemopageBacking> results) {
		this.results = results;
	}
	
	
}
