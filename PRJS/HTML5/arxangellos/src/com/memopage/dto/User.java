package com.memopage.dto;

import java.util.Date;

public class User {
	



	private int sid;
	private String uname;
	private String pword;
	private String email;
	private String tel = "null";
	private Date createDt;
	private Date lastLoginDt;
	private int status;
	private String sessionId;
	
	private String gamesTot;// calculated not in DB 
	private String teamsTot;// calculated not in DB 
	
	private String roleType;  // governs what type of user we have 
	
	
	private String fieldCode=""; // if Field user set the field code to use in create game ... 
	
	private String profileImage="";
	
	
	
	
	
	
	public User() {
		super();
	}



	public int getSid() {
		return sid;
	}



	public void setSid(int sid) {
		this.sid = sid;
	}



	public String getSessionId() {
		return sessionId;
	}



	public String getGamesTot() {
		return gamesTot;
	}



	public void setGamesTot(String gamesTot) {
		this.gamesTot = gamesTot;
	}



	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}


	public String getUname() {
		return uname;
	}



	public void setUname(String uname) {
		this.uname = uname;
	}



	public String getPword() {
		return pword;
	}



	public void setPword(String pword) {
		this.pword = pword;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getRoleType() {
		return roleType;
	}



	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}



	public Date getCreateDt() {
		return createDt;
	}



	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}



	public Date getLastLoginDt() {
		return lastLoginDt;
	}



	public void setLastLoginDt(Date lastLoginDt) {
		this.lastLoginDt = lastLoginDt;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	
	public String getTeamsTot() {
		return teamsTot;
	}

	public void setTeamsTot(String teamsTot) {
		this.teamsTot = teamsTot;
	}



	public String getFieldCode() {
		return fieldCode;
	}



	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}

	
	
	public String getProfileImage() {
		return profileImage;
	}



	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.mf.dao.dto.MfUser: " );
		ret.append( "sid=" + sid );
		ret.append( ", uname=" + uname );
		ret.append( ", pword=" + pword );
		ret.append( ", email=" + email );
		ret.append( ", tel=" + tel );
		ret.append( ", roleType=" + roleType );
		ret.append( ", createDt=" + createDt );
		ret.append( ", lastLoginDt=" + lastLoginDt );
		ret.append( ", status=" + status );
		ret.append( ", fieldCode=" + fieldCode );
		return ret.toString();
	}
}
