package com.memopage.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.memopage.context.AppContext;

import com.memopage.dto.MemopageCategory;



public class MemopageCategoryDao  implements MemopageCategoryDaoI {
	
	private static Logger logger = Logger.getLogger(MemopageDao.class);
	private SimpleJdbcTemplate simpleJdbcTemplate; 
	
	ApplicationContext ctx = AppContext.getApplicationContext(); 
	UserDao userDao = (UserDao) ctx.getBean("userDao");   
	
	public void setDataSource(DataSource dataSource) {
		this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	
	public MemopageCategoryDao() {
	}


	
	 public List<MemopageCategory>  getCategories(  ){
		 
		 
			List<MemopageCategory> ret= null; 
			logger.debug("getCategories ENTER ");
			
			String sql = 
				" SELECT  *  " +
				" FROM memopage_categ " ;
				
			
			
			ParameterizedRowMapper<MemopageCategory> mapper = new ParameterizedRowMapper<MemopageCategory>() {
			        public MemopageCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
			        	MemopageCategory m  = new MemopageCategory();
			        	
			        	
			        	m.setSid(  rs.getInt("sid" ) );
			        	m.setCategName( rs.getString("categ_name") );
			        	m.setCategDescription(  rs.getString("categ_descr")  );
			           	
			            return m;
			        }
			    };

			    ret =  simpleJdbcTemplate.query(sql, mapper );		    
			    
			logger.debug("getGame EXIT ");
			return ret; 
		 
		 
	 }
		
	 
	 public List<MemopageCategory>  getCategoriesThatAreUsed(  int areaCode ){
		 
		 
			List<MemopageCategory> ret= null; 
			logger.debug("getCategoriesThatAreUsed ENTER ");
			
			String sql = "";
			
			if  ( areaCode == 0 ){
			
			 sql = 
				"    SELECT * FROM memopage_categ WHERE sid IN "
				+ "  (  " +
						"  SELECT   page_type FROM memopage  where page_type > 0 "    +
					"); ";
			}
			else {
				 sql = 
						"    SELECT * FROM memopage_categ WHERE sid IN "
						+ "  (  " +
								"  SELECT   page_type FROM memopage  where page_type > 0  and area_code =  ? "    +
							"); ";
				
			}
			
			
			
			ParameterizedRowMapper<MemopageCategory> mapper = new ParameterizedRowMapper<MemopageCategory>() {
			        public MemopageCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
			        	MemopageCategory m  = new MemopageCategory();
			        	
			        	
			        	m.setSid(  rs.getInt("sid" ) );
			        	m.setCategName( rs.getString("categ_name") );
			        	m.setCategDescription(  rs.getString("categ_descr")  );
			           	
			            return m;
			        }
			    };

			    
			    if  ( areaCode == 0 ){
			    	ret =  simpleJdbcTemplate.query(sql, mapper  );		    
			    } else {
			    	 ret =  simpleJdbcTemplate.query(sql, mapper, areaCode );		
			    }
			    
			    
			    
			logger.debug("getCategoriesThatAreUsed EXIT ");
			return ret; 
		 
		 
	 }
	 
	 
	 public MemopageCategory getMemopageCategory( int sid ){
		 MemopageCategory ret= null; 
			logger.debug("MemopageCategory ENTER ");
			
			String sql = 
				" SELECT  * " +
				" FROM memopage_categ " +
				" WHERE sid = ? ";  
			
			ParameterizedRowMapper<MemopageCategory> mapper = new ParameterizedRowMapper<MemopageCategory>() {
		        public MemopageCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
		        	MemopageCategory m  = new MemopageCategory();
		        	
		        	
		        	m.setSid(  rs.getInt("sid" ) );
		        	m.setCategName( rs.getString("categ_name") );
		        	m.setCategDescription(  rs.getString("categ_descr")  );
		           	
		            return m;
		        }
		    };

			    
				try { 
				    ret =  simpleJdbcTemplate.queryForObject(sql, mapper, sid );		    
				} catch (  EmptyResultDataAccessException ex ){
					// empty as its new memopage 
				}
			    
				
			logger.debug("MemopageCategory EXIT ");
			return ret; 
		 
	 }
	
	
	

	
	
	
}
