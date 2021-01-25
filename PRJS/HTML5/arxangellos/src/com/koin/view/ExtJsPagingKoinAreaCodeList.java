package com.koin.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.koin.dto.KoinAreaCode;



public class ExtJsPagingKoinAreaCodeList implements Serializable{


	
	public String getTotal() {
		return total;
	}


	public void setTotal(String total) {
		this.total = total;
	}


	public List<KoinAreaCode> getResults() {
		return results;
	}


	public void setResults(List<KoinAreaCode> results) {
		this.results = results;
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}


	private static final long serialVersionUID = -8978194286964391665L;
	private String total ="0"; 
	private List<KoinAreaCode> results = new ArrayList<KoinAreaCode>();
	
	
	public ExtJsPagingKoinAreaCodeList() {
	}

	

	
}
