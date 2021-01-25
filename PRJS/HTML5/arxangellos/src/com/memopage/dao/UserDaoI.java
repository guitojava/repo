package com.memopage.dao;

import com.memopage.dto.User;

public interface UserDaoI  {
	
	
	public User getUser( String uname, String pword );
	public User getUser( int sid );
	public User getUserWithPassword( int sid ); 
	
	public User getUserByUName( String uname );
	
	public int insert( User user  );
	public int updatePassword( User user  );
	public int updateEmail( User user  );
	public int updateTel( User user  );
	public int update( User user  );
	public int updateLastLogin( User user  );
	public int updateImage( User user  );
	
	
	
}
