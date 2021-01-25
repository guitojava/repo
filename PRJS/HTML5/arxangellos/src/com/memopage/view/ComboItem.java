package com.memopage.view;

import java.io.Serializable;




public class ComboItem implements Serializable{

	
	


	private static final long serialVersionUID = -48255326961598618L;
	
	private String key ;
	private String displayFld;
	
	
	
	
	
	public ComboItem() {
	}


	


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getKey() {
		return key;
	}





	public void setKey(String key) {
		this.key = key;
	}





	public String getDisplayFld() {
		return displayFld;
	}





	public void setDisplayFld(String displayFld) {
		this.displayFld = displayFld;
	}



	
	
}
