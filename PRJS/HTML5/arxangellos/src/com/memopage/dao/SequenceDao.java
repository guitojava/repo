package com.memopage.dao;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;



public class SequenceDao implements SequenceDaoI {

	static Logger logger = Logger.getLogger(SequenceDao.class);
	private SimpleJdbcTemplate simpleJdbcTemplate; 
	
	public void setDataSource(DataSource dataSource) {
		this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	
	public SequenceDao() {
		super();
	}




	public int getNextId( String tableName  ){
		int ret= -1;
		logger.debug("getNextId ENTER ");
		String sql;
		
		try {
		
		
		
		
		// get the next id to use
		sql = "Select next_val from sequence WHERE tableName='"+tableName+"'";	
		ret = simpleJdbcTemplate.queryForInt(sql);
		logger.debug("** NEW SID for tableName="+ tableName +" getNextId SID= "+ ret);
		
		// prepare for next time 
		sql = "UPDATE sequence SET next_val=@val:=next_val+1 WHERE tableName= ? ;";
		simpleJdbcTemplate.update(
											sql, 
				new Object[] {  			tableName
				});
		
		
		} catch (Exception e) {
			ret = -1 ;
			logger.error(" ERROR :  failed to get a SId  from a sequence "); 
		}
		
		logger.debug("getNextId EXIT ");
		return ret; 
		
	}
	
}
