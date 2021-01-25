package com.memopage.service.impl;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import utils.DateUtils;

import com.memopage.Constants;
import com.memopage.context.AppContext;
import com.memopage.dao.*;
import com.memopage.dto.User;
import com.memopage.service.UserService;



public class UserServiceImpl implements UserService {
	
	static Logger logger = Logger.getLogger(UserServiceImpl.class);

   
	ApplicationContext ctx = AppContext.getApplicationContext(); 
	UserDao userDao = (UserDao) ctx.getBean("userDao");         
	SequenceDao sequenceDao =(SequenceDao) ctx.getBean("sequenceDao");   
	 
	
	public UserServiceImpl() {
		super();
	}
	
	
	@Override
	@Transactional
	public User getUser( String uname, String password)  {
		User ret= null; 
		
		try {
			
			ret = userDao.getUser(uname, password);
			if (null != ret) 			logger.debug(  "  USER  = " + ret.toString() );
			
		} catch (Exception e) {
			logger.error( "Can't   getUser  ()   " );
			e.printStackTrace();
		} 
		
		return ret; 
	}
	
	
	@Override
	@Transactional
	public User getUser( int sid)  {
		User ret= null; 
		
		try {
			
			ret = userDao.getUser(sid);
			if (null != ret) 			logger.debug(  "  USER  = " + ret.toString() );
			
		} catch (Exception e) {
			logger.error( "Can't   getUser  ()   " );
			e.printStackTrace();
		} 
		
		return ret; 
	}
	
	
	@Override
	@Transactional
	public User getUserWithPassword( int sid)  {
		User ret= null; 
		
		try {
			
			ret = userDao.getUserWithPassword(sid);
			if (null != ret) 			logger.debug(  "  USER  = " + ret.toString() );
			
		} catch (Exception e) {
			logger.error( "Can't   getUserWithPassword  ()   " );
			e.printStackTrace();
		} 
		
		return ret; 
	}
	
	
	
	@Override
	@Transactional
	public User getUserByUName ( String uname)  {
		User ret= null; 
		
		try {
			
			ret = userDao.getUserByUName(uname);
			
			if (null != ret) 			logger.debug(  "  USER  = " + ret.toString() );
			
		} catch (Exception e) {
			logger.error( "Can't   getUser  ()   " );
			e.printStackTrace();
		} 
		
		return ret; 
	}
	
	
	@Override
	@Transactional
	public User getUserByEmail( String email)  {
		User ret= null; 
		
		try {
			
			ret = userDao.getUserByEmail(email);
			if (null != ret) 			logger.debug(  "  USER  = " + ret.toString() );
			
		} catch (Exception e) {
			logger.error( "Can't   getUser  ()   " );
			e.printStackTrace();
		} 
		
		return ret; 
	}
	
	
	
	
	@Override
	@Transactional
	public int craeteNewUser(User user)  {
		int ret=-1; 
		
		// set the next 
		try {
			
			// call to get next sid   
			
			int sid  = sequenceDao.getNextId(Constants.USER_SEQ); 
			
			user.setSid( sid  );
			ret = sid; 
			
			user.setCreateDt( DateUtils.nowAsJavaUtilDate());
			user.setLastLoginDt( DateUtils.nowAsJavaUtilDate());
			user.setStatus( Constants.USER_STATUS_ACTIVE   ); 
			
			userDao.insert(user);
			
			logger.debug(  "  USER  = " + user.toString() );
			
		} catch (Exception e) {
			logger.error( "Can't   craeteNewUser ()   " );
			e.printStackTrace();
		} 
		
		
		
		return ret; 
	}
	
	@Override
	@Transactional
	public int updatePassword(User user)  {
		int ret=-1; 
		
		// set the next 
		try {
			
			ret = userDao.updatePassword(user);
			
			logger.debug(  "  USER  = " + user.toString() );
		} catch (Exception e) {
			logger.error( "Can't   updatePassword  ()   " );
			e.printStackTrace();
		} 
		
		return ret; 
	}
	
	@Override
	@Transactional
	public int updateEmail(User user)  {
		int ret=-1; 
		
		// set the next 
		try {
			
			ret = userDao.updateEmail(user);
			
			if (null !=  user) 			logger.debug(  "  USER  = " + user.toString() );
		} catch (Exception e) {
			logger.error( "Can't   updateEmail  ()   " );
			e.printStackTrace();
		} 
		
		return ret; 
	}
	
	
	@Override
	@Transactional
	public int updateTel(User user)  {
		int ret=-1; 
		
		// set the next 
		try {
			
			ret = userDao.updateTel(user);
			
			if (null !=  user) 			logger.debug(  "  USER  = " + user.toString() );
		} catch (Exception e) {
			logger.error( "Can't   updateTel  ()   " );
			e.printStackTrace();
		} 
		
		return ret; 
	}
	
	@Override
	@Transactional
	public int updateImage(User user)  {
		int ret=-1; 
		
		// set the next 
		try {
			
			ret = userDao.updateImage(user);
			
			if (null !=  user) 			logger.debug(  "  USER  = " + user.toString() );
		} catch (Exception e) {
			logger.error( "Can't   updateImage  ()   " );
			e.printStackTrace();
		} 
		
		return ret; 
	}
	
	
	@Override
	@Transactional
	public int updateUser(User user)  {
		int ret=-1; 
		
		// set the next 
		try {
			
			ret = userDao.update(user);
			
			if (null !=  user) 			logger.debug(  "  USER  = " + user.toString() );
		} catch (Exception e) {
			logger.error( "Can't   updateUser  ()   " );
			e.printStackTrace();
		} 
		
		return ret; 
	}


	@Override
	public int updateLastLogin(User user) {
		int ret=-1; 
		
		// set the next 
		try {
			
			ret = userDao.updateLastLogin(user);
			
			if (null !=  user) 			logger.debug(  "  USER  = " + user.toString() );
		} catch (Exception e) {
			logger.error( "Can't   updateLastLogin   ()   " );
			e.printStackTrace();
		} 
		
		return ret; 
	}
	
	
	
}
