package com.koin.dto;

import java.io.Serializable;

public class KoinAreaCode implements Serializable {

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	private static final long serialVersionUID = 5890131601814849243L;
	
	int  id ;
	String description ;
	
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
