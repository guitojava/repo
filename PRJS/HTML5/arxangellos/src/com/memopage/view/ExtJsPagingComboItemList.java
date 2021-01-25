package com.memopage.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class ExtJsPagingComboItemList implements Serializable{


	
	private static final long serialVersionUID = -8978194286964391665L;
	private String total ="0"; 
	private List<ComboItem> results = new ArrayList<ComboItem>();
	
	
	public ExtJsPagingComboItemList() {
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List<ComboItem> getResults() {
		return results;
	}

	public void setResults(List<ComboItem> results) {
		this.results = results;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	
}
