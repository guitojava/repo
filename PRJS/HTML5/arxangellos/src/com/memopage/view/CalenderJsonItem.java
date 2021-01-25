package com.memopage.view;

import java.io.Serializable;





public class CalenderJsonItem  implements Serializable{

	

	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1210005565431009518L;
	private String id ;
	private String title;
	private String start;    // must be   SimpleDateFormat sdfCal = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");  
	
	private String end;    // must be   SimpleDateFormat sdfCal = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");  
	
	//private boolean allDay;			// optional 
	//private Date end; 				// optional 
	private String url; 			// opt 
	private String className; 		// optional 

	
	
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( " CalenderJsonItem: " );
		ret.append( "  sid=" + id );
		ret.append( "  title=" + title );
	
		return ret.toString(); 
	}
	
	
	public CalenderJsonItem() {
	}
	
	

	public String getEnd() {
		return end;
	}


	public void setEnd(String end) {
		this.end = end;
	}


	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String  getStart() {
		return start;
	}


	public void setStart(String  start) {
		this.start = start;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	
	
}
