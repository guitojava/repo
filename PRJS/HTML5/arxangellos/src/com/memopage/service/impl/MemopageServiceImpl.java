package com.memopage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import utils.DateUtils;

import com.memopage.Constants;
import com.memopage.context.AppContext;
import com.memopage.dao.*;
import com.memopage.dto.Memopage;
import com.memopage.dto.User;
import com.memopage.service.MemopageService;
import com.memopage.service.UserService;
import com.memopage.view.MemopageBacking;
import com.memopage.Transforms;



public class MemopageServiceImpl implements MemopageService {
	
	static Logger logger = Logger.getLogger(MemopageService.class);

   
	ApplicationContext ctx = AppContext.getApplicationContext(); 
	UserDao userDao = (UserDao) ctx.getBean("userDao");         
	SequenceDao sequenceDao =(SequenceDao) ctx.getBean("sequenceDao");   
	MemopageDao memopageDao =(MemopageDao) ctx.getBean("memopageDao");   
	
	public MemopageServiceImpl() {
		super();
	}

	
	
	
	@Override
	public List<MemopageBacking> getUserMemopages(User user) {
	
		List<MemopageBacking> ret = new ArrayList<MemopageBacking>();
		
		try {
			
			List<Memopage> mList =  memopageDao.getUserMemopages(user );
			for (Memopage mpage : mList) {
				ret.add(  Transforms.transformMemopageBacking(  mpage )  ); 
			}
		} catch (Exception e) {
			
			logger.error( " Can't   getUserMemopages ()   " );
			e.printStackTrace();
		} 
		return ret;
		
	}



	@Override
	public Memopage getMemopage(int sid) {
		Memopage ret = null; 
		try {
			ret = memopageDao.getMemopage(sid) ;
		} catch (Exception e) {
			ret = null; 
			logger.error( " Can't   getMemopage()   " );
			e.printStackTrace();
		} 
		return ret;
	}
	
	
	@Override
	public MemopageBacking getMemopageBacking (int sid) {
		MemopageBacking ret = null; 
		
		try {
			Memopage mpage = memopageDao.getMemopage( sid ) ;
			ret = Transforms.transformMemopageBacking(mpage);
		} catch (Exception e) {
			ret = null; 
			logger.error( " Can't   getMemopage()   " );
			e.printStackTrace();
		} 
		return ret;
	}
	

	@Override
	public int insertMemopage(Memopage mpage) {
		int ret=-1; 
		try {
			int sid  = sequenceDao.getNextId(Constants.MEMOPAGE_SEQ); 
			mpage.setSid( sid  );
			memopageDao.insertMemopage(mpage);
			ret = sid; 
		} catch (Exception e) {
			ret = -1;
			logger.error( " Can't   insertMemopage()   " );
			e.printStackTrace();
		} 
		return ret;
	}
	
	
	
	@Override
	public int updateMemopage(Memopage mpage) {
		int ret=-1; 
		try {
			ret = memopageDao.updateMemopage(mpage) ;
		} catch (Exception e) {
			ret = -1;
			logger.error( " Can't   updateMemopage()   " );
			e.printStackTrace();
		} 
		return ret;
		
	}
	
	@Override
	public int deleteMemopage(int sid) {
		
		int ret=-1; 
		try {
			ret = memopageDao.deleteMemopage(sid);
		} catch (Exception e) {
			ret = -1;
			logger.error( " Can't   deleteMemopage()   " );
			e.printStackTrace();
		} 
		return ret;
		
	}


	
	
}
