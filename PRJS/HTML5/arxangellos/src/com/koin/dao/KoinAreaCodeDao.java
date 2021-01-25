package com.koin.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.koin.dto.KoinAreaCode;



public class KoinAreaCodeDao implements KoinAreaCodeDaoI {
	
	


	private static Logger logger = Logger.getLogger(KoinAreaCodeDao.class);
	
	
	private SimpleJdbcTemplate simpleJdbcTemplate; 
	
	public void setDataSource(DataSource dataSource) {
		this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	public KoinAreaCodeDao() {
		super();
	}
	
	
	
	
	
	
	@Override
	public List<KoinAreaCode> getKoinAreaCodesThatAreUsedForMemopages() {
		
		
		logger.debug("getKoinAreaCodesThatAreUsedForMemopages ENTER ");
		List<KoinAreaCode> ret = null;
		
		String sql = 
			 " SELECT * FROM koin_area_table WHERE  sid > 0  AND  sid IN  "+
				" (  	 SELECT area_code FROM memopage    ) ";
				
		logger.debug(sql);
		
		ParameterizedRowMapper<KoinAreaCode> mapper = new ParameterizedRowMapper<KoinAreaCode>() {
	        public KoinAreaCode mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	KoinAreaCode per  = new KoinAreaCode();
	        		
	        	per.setId( rs.getInt("sid") );
	        		per.setDescription( rs.getString("STATEDESCR")  ) ;
	        		
	            return per;
	        }
	    };
		
	    ret =  simpleJdbcTemplate.query(sql, mapper );	
		logger.debug("getKoinAreaCodesThatAreUsedForMemopages EXIT ");
		return 	 ret;
		
		
		
	}
	
	
	
	
	
	
	public List<KoinAreaCode>  loadAreaCodes ( ){
		// State getState
		logger.debug("loadAreaCodes ENTER ");
		List<KoinAreaCode> ret = null;
		
		String sql = 
			"  SELECT * FROM koin_area_table WHERE sid > -1 AND sid IN " +
			" (  SELECT area_code FROM koin_building  " +
			" WHERE  sid > -1  and  length( address ) > 3 "+
			
			" )   " ;
		
	
				
		logger.debug(sql);
		
		ParameterizedRowMapper<KoinAreaCode> mapper = new ParameterizedRowMapper<KoinAreaCode>() {
	        public KoinAreaCode mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	KoinAreaCode per  = new KoinAreaCode();
	        		
	        	per.setId( rs.getInt("sid") );
	        		per.setDescription( rs.getString("STATEDESCR")  ) ;
	        		
	            return per;
	        }
	    };
		
	    ret =  simpleJdbcTemplate.query(sql, mapper );	
		logger.debug("loadAreaCodes EXIT ");
		return 	 ret;
	}
	
	
	
	// SELECT * FROM koin_area_table WHERE sid > 0 AND sid IN (  SELECT area_code FROM mf_field)  
	
	
	public List<KoinAreaCode>  loadAllAreaCodes ( ){
		// State getState
		logger.debug("loadAreaCodesThatHaveFields ENTER ");
		List<KoinAreaCode> ret = null;
		
		String sql =  			" SELECT * FROM koin_area_table where sid > -1  " ;
		logger.debug(sql);
		
		ParameterizedRowMapper<KoinAreaCode> mapper = new ParameterizedRowMapper<KoinAreaCode>() {
	        public KoinAreaCode mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	KoinAreaCode per  = new KoinAreaCode();
	        		
	        	per.setId( rs.getInt("sid") );
	        		per.setDescription( rs.getString("STATEDESCR")  ) ;
	        		
	            return per;
	        }
	    };
		
	    ret =  simpleJdbcTemplate.query(sql, mapper );	
		logger.debug("loadAreaCodes EXIT ");
		return 	 ret;
	}
	
	
	
	
	
	
	public KoinAreaCode  getKoinAreaCodeWithDesc ( String descr ){
		// State getState
		logger.debug("getKoinAreaCodeWithDesc ENTER ");
		// always have descr 
		String sql = "SELECT * FROM koin_area_table where  sid > -1 and  STATEDESCR='"+descr+"' ";
		logger.debug(sql);
		KoinAreaCode ret = new KoinAreaCode(); 
			
		ParameterizedRowMapper<KoinAreaCode> mapper = new ParameterizedRowMapper<KoinAreaCode>() {
	        public KoinAreaCode mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	KoinAreaCode per  = new KoinAreaCode();
	        		
	        	per.setId( rs.getInt("sid") );
	        	per.setDescription( rs.getString("STATEDESCR")  ) ;
	        		
	            return per;
	        }
	    };
		    
		    
		ret =  simpleJdbcTemplate.queryForObject(sql, mapper );				
		logger.debug("getKoinAreaCodeWithDesc EXIT ");
		return 	 ret;
	}
	
	
	public KoinAreaCode  getKoinAreaCodeWithSid ( int sid ){
		// State getState
		logger.debug("getKoinAreaCodeWithSid ENTER ");
		// always have descr 
		String sql = "SELECT * FROM koin_area_table where  sid > -1 and sid= ? ";
		
		logger.debug(sql);
		KoinAreaCode ret = new KoinAreaCode(); 
			
		ParameterizedRowMapper<KoinAreaCode> mapper = new ParameterizedRowMapper<KoinAreaCode>() {
	        public KoinAreaCode mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	KoinAreaCode per  = new KoinAreaCode();
	        		
	        	per.setId( rs.getInt("sid") );
	        	per.setDescription( rs.getString("STATEDESCR")  ) ;
	        		
	            return per;
	        }
	    };
		    
		
	    
	    try {
		ret =  simpleJdbcTemplate.queryForObject(sql, mapper, sid );	
	    } catch (Exception e) {
			
	    	ret = new KoinAreaCode(); 
	    	
		}    
		    
		    
		logger.debug("getKoinAreaCodeWithSid EXIT ");
		return 	 ret;
	}

	
	
	
	
	
	
}
