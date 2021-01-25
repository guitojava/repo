package com.memopage.dto;

import java.util.Calendar;
import java.util.Date;

public class Memopage {







	



	Calendar c1 = Calendar.getInstance();
	


	int sid=-1;
	
	User user = new User ();
	String pageName ="";
	String pageSummary ="";
	int pageType = -1;
	Date pageWhen   = new Date();
	Date pageWhenEnd = new Date();
	String pageAddress ="";
	
	String pageLat ="";
	String pageLng ="";
	String mapZoom ="";
	
	
	MemopageCategory pageCateg = new MemopageCategory();
	
	String pageContent ="";
	
	String pageKeywords ="";
	String pageLang ="";
	String pageUrl  ="";
	
	int isListed = 1;
	int status   = 1; 
	
	int hasComments = -1;
	int commentsAreReviewed = -1;

	int areaCode = -1;
	
	String pageTel ="";
	String pageEmail ="";
	String pageWebLink ="";
	
	String priceEuros = "";
	
	
	String calendarCssClass  ="";
	
	public String getCalendarCssClass() {
		return calendarCssClass;
	}


	public void setCalendarCssClass(String calendarCssClass) {
		this.calendarCssClass = calendarCssClass;
	}

	
	public Memopage() {
			super();
		
	//	  c1.setTime( pageWhen );
	//	  c1.add(Calendar.DAY_OF_MONTH, 30);
	//	  pageWhenEnd = c1.getTime();
	}


	public String getPageTel() {
		return pageTel;
	}




	public void setPageTel(String pageTel) {
		this.pageTel = pageTel;
	}



	public String getPriceEuros() {
		return priceEuros;
	}


	public void setPriceEuros(String priceEuros) {
		this.priceEuros = priceEuros;
	}




	public String getPageEmail() {
		return pageEmail;
	}




	public void setPageEmail(String pageEmail) {
		this.pageEmail = pageEmail;
	}


	public String getMapZoom() {
		return mapZoom;
	}


	public void setMapZoom(String mapZoom) {
		this.mapZoom = mapZoom;
	}




	public String getPageWebLink() {
		return pageWebLink;
	}




	public void setPageWebLink(String pageWebLink) {
		this.pageWebLink = pageWebLink;
	}

	
	public Calendar getC1() {
		return c1;
	}


	public void setC1(Calendar c1) {
		this.c1 = c1;
	}


	public MemopageCategory getPageCateg() {
		return pageCateg;
	}


	public void setPageCateg(MemopageCategory pageCateg) {
		this.pageCateg = pageCateg;
	}

	
	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	public int getSid() {
		return sid;
	}




	public void setSid(int sid) {
		this.sid = sid;
	}




	public String getPageName() {
		return pageName;
	}




	public void setPageName(String pageName) {
		this.pageName = pageName;
	}




	public String getPageSummary() {
		return pageSummary;
	}




	public void setPageSummary(String pageSummary) {
		this.pageSummary = pageSummary;
	}




	public int getPageType() {
		return pageType;
	}




	public void setPageType(int pageType) {
		this.pageType = pageType;
	}




	public Date getPageWhen() {
		return pageWhen;
	}




	public void setPageWhen(Date pageWhen) {
		this.pageWhen = pageWhen;
	}




	public Date getPageWhenEnd() {
		return pageWhenEnd;
	}




	public void setPageWhenEnd(Date pageWhenEnd) {
		this.pageWhenEnd = pageWhenEnd;
	}




	public String getPageAddress() {
		return pageAddress;
	}




	public void setPageAddress(String pageAddress) {
		this.pageAddress = pageAddress;
	}




	public String getPageLat() {
		return pageLat;
	}




	public void setPageLat(String pageLat) {
		this.pageLat = pageLat;
	}




	public String getPageLng() {
		return pageLng;
	}




	public void setPageLng(String pageLng) {
		this.pageLng = pageLng;
	}




	public String getPageContent() {
		return pageContent;
	}




	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}




	public String getPageKeywords() {
		return pageKeywords;
	}




	public void setPageKeywords(String pageKeywords) {
		this.pageKeywords = pageKeywords;
	}




	public String getPageLang() {
		return pageLang;
	}




	public void setPageLang(String pageLang) {
		this.pageLang = pageLang;
	}




	public String getPageUrl() {
		return pageUrl;
	}




	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}




	public int getIsListed() {
		return isListed;
	}




	public void setIsListed(int isListed) {
		this.isListed = isListed;
	}




	public int getStatus() {
		return status;
	}




	public void setStatus(int status) {
		this.status = status;
	}




	public int getHasComments() {
		return hasComments;
	}




	public void setHasComments(int hasComments) {
		this.hasComments = hasComments;
	}




	public int getCommentsAreReviewed() {
		return commentsAreReviewed;
	}




	public void setCommentsAreReviewed(int commentsAreReviewed) {
		this.commentsAreReviewed = commentsAreReviewed;
	}



	public int getAreaCode() {
		return areaCode;
	}




	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}


	
	
	
}
