package com.memopage.dto;


public class MemopageCategory {

	int sid=-1;
	
	String categName ="";
	String categDescription ="";

	public MemopageCategory() {
	}

	
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getCategName() {
		return categName;
	}

	public void setCategName(String categName) {
		this.categName = categName;
	}

	public String getCategDescription() {
		return categDescription;
	}

	public void setCategDescription(String categDescription) {
		this.categDescription = categDescription;
	}

	
	
	
}
