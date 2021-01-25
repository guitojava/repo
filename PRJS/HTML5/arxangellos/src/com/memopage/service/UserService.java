package com.memopage.service;

import com.memopage.dto.User;





public interface UserService {
	
	
	
	public User getUser( String uname, String password);

	public User getUser( int sid) ;
	

	public User getUserWithPassword( int sid) ; 
	
	public User getUserByUName( String uname)  ;
	
	public User getUserByEmail( String email)  ;
	
	public int  craeteNewUser(User user);
	
	public int  updatePassword(User user);
	
	public int  updateEmail(User user);
	
	public int  updateUser(User user);
	
	public int  updateTel(User user);

	public int updateLastLogin( User user  ); 
	
	public int updateImage( User user  ); 
	
}
