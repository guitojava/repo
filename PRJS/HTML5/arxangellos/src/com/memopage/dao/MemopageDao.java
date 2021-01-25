package com.memopage.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.memopage.context.AppContext;
import com.memopage.dto.Memopage;
import com.memopage.dto.MemopageCategory;
import com.memopage.dto.User;



public class MemopageDao  implements MemopageDaoI {
	
	private static Logger logger = Logger.getLogger(MemopageDao.class);
	private SimpleJdbcTemplate simpleJdbcTemplate; 
	
	ApplicationContext ctx = AppContext.getApplicationContext(); 
	MemopageCategoryDao memopageCategoryDao = (MemopageCategoryDao) ctx.getBean("memopageCategoryDao");   
	
	UserDao userDao = (UserDao) ctx.getBean("userDao");   
	
	
	public void setDataSource(DataSource dataSource) {
		this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	
	public MemopageDao() {
	}


	@Override
	public List<Memopage> getUserMemopages(User user) {
		
		List<Memopage> ret= null; 
		logger.debug("getUserMemopages ENTER ");
		
		String sql = 
			" SELECT  *  " +
			" FROM memopage " +
			" WHERE user_fk = ? ";  
		
		
		ParameterizedRowMapper<Memopage> mapper = new ParameterizedRowMapper<Memopage>() {
		        public Memopage mapRow(ResultSet rs, int rowNum) throws SQLException {
		        	Memopage m  = new Memopage();
		        	
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		        	
		        	m.setSid(  rs.getInt("sid" ) );
		        	
		        	
		        	User u = userDao.getUser(  rs.getInt("user_fk" )   );
		        	m.setUser( u  ); 
		        	
		        	m.setPageName( rs.getString("page_name") );
		        	m.setPageSummary(  rs.getString("page_summary")  );
		        	
		        	
		        	m.setPageType(  rs.getInt("page_type")  );
		          	MemopageCategory mc = memopageCategoryDao.getMemopageCategory( rs.getInt("page_type" ) );
		        	m.setPageCateg(  mc );
		        	
		        	
		        	try {
		        		
		        		m.setPageWhen(   sdf.parse(  rs.getString("page_when" ))  );
		        		m.setPageWhenEnd(   sdf.parse(  rs.getString("page_when_end" ))  );
		        		
					} catch (ParseException e) {
						logger.error("Date conversion Error in getUserMemopages MemopageDao");
						e.printStackTrace();
					}
		        	
		        	
		        	m.setPageAddress( rs.getString("page_address" ) );
		        	m.setPageLat( rs.getString("page_lat" ) );
		        	m.setPageLng( rs.getString("page_lng" ) );
		        	
		        	m.setMapZoom( rs.getString("map_zoom" ) );
		        	
		        	m.setCalendarCssClass(   rs.getString("calendar_css_class" ) );
		        	
		        	m.setPageContent(rs.getString("page_content" ));
		        	m.setPageKeywords(rs.getString("page_keywords" ));
		        	m.setPageLang(rs.getString("page_lang" ));
		        	m.setPageUrl(rs.getString("page_url" ));
		        	m.setIsListed(rs.getInt("is_listed" ));
		        	m.setStatus(rs.getInt("status" ));
		        	
		        	m.setHasComments(rs.getInt("has_comments" ));
		        	m.setCommentsAreReviewed(rs.getInt("comments_are_reviewed" ));
		        	m.setAreaCode( rs.getInt("area_code" ) );
		        	
		        		
		      
		           	
		           	
		           	
		        	m.setPageTel(rs.getString("page_tel" ));
		        	m.setPageEmail(rs.getString("page_email" ));
		        	m.setPageWebLink(rs.getString("page_web_link" ));
		        	
		        	
		        	m.setPriceEuros(rs.getString("price_euros" ));
		        	
		            return m;
		        }
		    };

		    ret =  simpleJdbcTemplate.query(sql, mapper, user.getSid() );		    
		    
		logger.debug("getGame EXIT ");
		return ret; 
	}


	

	@Override
	public Memopage getMemopage(int sid) {
		Memopage ret= null; 
		logger.debug("Memopage ENTER ");
		
		String sql = 
			" SELECT  * " +
			" FROM memopage " +
			" WHERE sid = ? ";  
		
		ParameterizedRowMapper<Memopage> mapper = new ParameterizedRowMapper<Memopage>() {
		        public Memopage mapRow(ResultSet rs, int rowNum) throws SQLException {
		        	Memopage m  = new Memopage();
		        	
		         	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		        	
		        	m.setSid(  rs.getInt("sid" ) );
		        	
		        	
		        	User u = userDao.getUser(  rs.getInt("user_fk" )   );
		        	m.setUser( u  ); 
		        	
		        	m.setPageName( rs.getString("page_name") );
		        	m.setPageSummary(  rs.getString("page_summary")  );
		        	m.setPageType(  rs.getInt("page_type")  );
		        	
		        	try {
		        		
		        		m.setPageWhen(   sdf.parse(  rs.getString("page_when" ))  );
		        		m.setPageWhenEnd(   sdf.parse(  rs.getString("page_when_end" ))  );
		        		
					} catch (ParseException e) {
						logger.error("Date conversion Error in getMemopage MemopageDao");
						e.printStackTrace();
					}
		        	
		        	
		        	m.setPageAddress( rs.getString("page_address" ) );
		        	m.setPageLat( rs.getString("page_lat" ) );
		        	m.setPageLng( rs.getString("page_lng" ) );
		        	
		        	m.setMapZoom( rs.getString("map_zoom" ) );
		        	
		         	m.setCalendarCssClass(   rs.getString("calendar_css_class" ) );
		        	
		        	m.setPageContent(rs.getString("page_content" ));
		        	m.setPageKeywords(rs.getString("page_keywords" ));
		        	m.setPageLang(rs.getString("page_lang" ));
		        	m.setPageUrl(rs.getString("page_url" ));
		        	m.setIsListed(rs.getInt("is_listed" ));
		        	m.setStatus(rs.getInt("status" ));
		        	
		        	m.setHasComments(rs.getInt("has_comments" ));
		        	m.setCommentsAreReviewed(rs.getInt("comments_are_reviewed" ));
		        	m.setAreaCode( rs.getInt("area_code" ) );
		        
		        	MemopageCategory mc = memopageCategoryDao.getMemopageCategory( rs.getInt("page_type" ) );
		        	m.setPageCateg(  mc );
		           	
		        	m.setPageTel(rs.getString("page_tel" ));
		        	m.setPageEmail(rs.getString("page_email" ));
		        	m.setPageWebLink(rs.getString("page_web_link" ));
		        	
		        	m.setPriceEuros(rs.getString("price_euros" ));
		         	
		            return m;
		        }
		    };

		    
			try { 
			    ret =  simpleJdbcTemplate.queryForObject(sql, mapper, sid );		    
			} catch (  EmptyResultDataAccessException ex ){
				// empty as its new memopage 
			}
		    
			
		logger.debug("Memopage EXIT ");
		return ret; 
	}


	@Override
	public int insertMemopage(Memopage mpage) {
		int ret= -1;
		logger.debug("insert ENTER ");
		
		String sql = "" 
			+"INSERT " 
			+"INTO   memopage " 
			+"       ( " 
			+"              sid         , " 
			+"              user_fk   , " 
			+"              page_name     , " 
			+"              page_summary    , " 
			+"              page_type, " 
			+"              page_when    , " 
			+"              page_when_end   , " 
			+"              page_address   , " 
			+"              page_lat   , " 
			+"              page_lng   , " 
			+"              map_zoom   , "
			+"           calendar_css_class ,"
			
			+"              page_content     , " 
			+"              page_keywords  , " 
			+"              page_lang   , "
			+"              page_url   , "
			+"              is_listed   , "
			+"              has_comments   , "
			+"              comments_are_reviewed   , "
			+"              area_code   , "
			
			+"              page_tel   , " 
			+"              page_email   , " 
			+"              page_web_link   , " 
			
			+"              price_euros  , " 
			
			
			+"              status " 
			+"       ) " 
			+"       VALUES " 
			+"       ( " 
			+"              ?  ," 
			+"              ?  ," 
			+"              ?  ," 
			+"              ?  ," 
			+"              ?  ," 
			+"              ?  ," 
			+"              ?  ," 
			+"              ?  ," 
			+"              ?  ," 
			+"              ?  ," 
			+"              ?  ," 
			+"              ?  ," 
			+"              ?  ,"
			+"              ?  ," 
			+"              ?  ," 
			+"              ?  ," 
			+"              ?  ," 
			+"              ?  ," 
			+"              ?  ," 
			+"              ?  ," 
			+"              ?  ," 
			+"              ?  ," 
			+"              ?  ," 
			+"              ?  ," 
			+"              ?  " 
			+"       );";
		
		logger.debug("sql = "+sql);
		ret = simpleJdbcTemplate.update(
				sql, 
				new Object[	] {
						mpage.getSid(),
						mpage.getUser().getSid(),
						mpage.getPageName(),
						mpage.getPageSummary(),
						mpage.getPageType(),
						mpage.getPageWhen(),
						mpage.getPageWhenEnd(),
						mpage.getPageAddress(),
						mpage.getPageLat(),
						mpage.getPageLng(),
						mpage.getMapZoom(),
						mpage.getCalendarCssClass(),
						
						mpage.getPageContent(),
						mpage.getPageKeywords() , // int in DB
						mpage.getPageLang(),
						mpage.getPageUrl(),
						mpage.getIsListed(),
						mpage.getHasComments(), 
						mpage.getCommentsAreReviewed(), 
						mpage.getAreaCode(), 
						mpage.getPageTel(), 
						mpage.getPageEmail(), 
						mpage.getPageWebLink(), 
						mpage.getPriceEuros(), 
						mpage.getStatus()
				});
		
		logger.debug("insert EXIT ");
		return ret; 
		
	}

	
	
	
	@Override
	public int updateMemopage(Memopage mpage) {
		int ret= -1;
		logger.debug("updateMemopage ENTER ");
		

		SimpleDateFormat mySqlDateFormatter = new SimpleDateFormat(	"yyyy-MM-dd HH:mm");
		
		
		String sql =
		" UPDATE memopage  SET " +
			"    user_fk = ? " +
			" ,  page_name = ? " +
			" ,  page_summary = ? " +
			" ,  page_type = ? " +
			" ,  page_when = ? " +
			" ,  page_when_end = ? " +
			" ,  page_address = ? " +
			" ,  page_lat = ? " +
			" ,  page_lng = ? " +
			
			" ,  map_zoom= ? " +
			" , calendar_css_class= ? "+
			
			" ,  page_content = ? " +
			" ,  page_keywords = ? " +
			" ,  page_lang = ? " +
			" ,  page_url = ? " +
			" ,  is_listed = ? "+
			" ,  has_comments = ? "+
			" ,  comments_are_reviewed = ? "+
			" ,  area_code = ? "+
			
			" ,  page_tel = ? "+
			" ,  page_email = ? "+
			" ,  page_web_link = ? "+
			" ,  price_euros = ? "+
			
			" ,  status = ? "+
			" WHERE 	sid = ? ";
		
		logger.debug("sql = "+sql);
		ret = simpleJdbcTemplate.update(
				sql, 
				new Object[] {				
						
						mpage.getUser().getSid(),
						mpage.getPageName(),
						mpage.getPageSummary(),
						mpage.getPageType(),
						mpage.getPageWhen(),
						mpage.getPageWhenEnd(),
						mpage.getPageAddress(),
						mpage.getPageLat(),
						mpage.getPageLng(),
						
						mpage.getMapZoom(),
						mpage.getCalendarCssClass(),
						
						mpage.getPageContent(),
						mpage.getPageKeywords() , 
						mpage.getPageLang(),
						mpage.getPageUrl(),
						mpage.getIsListed(),
						mpage.getHasComments(), 
						mpage.getCommentsAreReviewed(), 
						mpage.getAreaCode(), 
						
						mpage.getPageTel(),
						mpage.getPageEmail(),
						mpage.getPageWebLink(),
						mpage.getPriceEuros(),
						
						mpage.getStatus(),
						
						mpage.getSid()
						
						
				});
		
		
		
		
		logger.debug("updateMemopage EXIT ");
		return ret; 
		
	}
	
	@Override
	public int deleteMemopage( int sid ) {
		int ret= -1;
		logger.debug("deleteMemopage ENTER ");
		String sql =	"DELETE FROM memopage  WHERE sid = ? ";
		logger.debug("sql = "+sql);
		ret = simpleJdbcTemplate.update(
				sql, 
				new Object[] {
						sid
				});
		logger.debug("deleteMemopage EXIT ");
		return ret; 
		
	}

	
	
	
}
