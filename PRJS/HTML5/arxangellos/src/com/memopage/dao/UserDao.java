package com.memopage.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.memopage.dto.User;



public class UserDao  implements UserDaoI {
	
	private static Logger logger = Logger.getLogger(UserDao.class);
	private SimpleJdbcTemplate simpleJdbcTemplate; 
	
	public void setDataSource(DataSource dataSource) {
		this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}
	
	
	public UserDao() {
	}

	
	
	public User getUser( String uname, String pword ){
		User ret= null; 
		logger.debug("getUser( String uname, String pword ) ENTER ");
		
		String sql ="SELECT * FROM user WHERE uname = ?   AND pword = ? ";
	
		ParameterizedRowMapper<User> mapper = new ParameterizedRowMapper<User>() {
			    
		        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		        	User user = new User();
		        	user.setSid(rs.getInt("sid"));
		        	user.setUname(rs.getString("uname"));
		        	
		        	user.setPword("dummy");// always set to dummy just for fun 
		        	
		        	user.setEmail(rs.getString("email"));
		        	user.setTel(rs.getString("tel"));
		        	user.setRoleType(rs.getString("role_type"));
		        	user.setCreateDt( rs.getDate("create_dt") ) ;
		        	user.setLastLoginDt( rs.getDate("last_login_dt") ) ;
		        	user.setStatus( rs.getInt("status") ) ;
		        	
		        	user.setProfileImage(rs.getString("profile_image"));
		        	
		        	// session set here  TODO   Need to Fix SessionId write and read properly from DB 
		        	user.setSessionId( user.getUname() + "-"+user.getSid() +"-"+ Calendar.getInstance().getTimeInMillis()  ) ;
		        	
		      //  	user.setGamesTot( "" + getTotalMyGamesCount( user.getSid() ) ); 
		        	
		      //  	user.setTeamsTot( "" + getTotalMyTeamsCount( user.getSid() ) ); 
		        	
		        	
		            return user;
		        }
		    };
		    
		    try {
		    	
		    	 ret =  simpleJdbcTemplate.queryForObject(sql, mapper, uname, pword);
		    	
		    } catch (EmptyResultDataAccessException e) {
				
		    	logger.info("No user   uname: " + uname + " pword: "+ pword   );
		    	
			}
		    
		   

		
		logger.debug("getUser( String uname, String pword ) EXIT ");
		return ret; 
		
	}
	
	public User getUser( int sid ){
		User ret= null; 
		logger.debug("getUser( int sid ) ENTER ");
		
		String sql ="SELECT * FROM user WHERE sid = ?   ";
	
		ParameterizedRowMapper<User> mapper = new ParameterizedRowMapper<User>() {
			    
		        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		        	User user = new User();
		        	user.setSid(rs.getInt("sid"));
		        	user.setUname(rs.getString("uname"));
		        	user.setPword("*****");// always set to dummy just for fun 
		        	user.setEmail(rs.getString("email"));
		        	user.setTel(rs.getString("tel"));
		        	user.setRoleType(rs.getString("role_type"));
		        	user.setCreateDt( rs.getDate("create_dt") ) ;
		        	user.setLastLoginDt( rs.getDate("last_login_dt") ) ;
		        	user.setStatus( rs.getInt("status") ) ;
		        	
		           	user.setProfileImage(rs.getString("profile_image"));
		        	
		        	user.setSessionId( user.getUname() + "-"+user.getSid() +"-"+ Calendar.getInstance().getTimeInMillis()  ) ;
		        	
		  //      	user.setGamesTot( "" + getTotalMyGamesCount( user.getSid() ) ); 
		        	
		//        	user.setTeamsTot( "" + getTotalMyTeamsCount( user.getSid() ) ); 
		        	
		            return user;
		        }
		    };

		   
		
		    try {
		    	
		    	 ret =  simpleJdbcTemplate.queryForObject(sql, mapper, sid );

		    	
		    } catch (EmptyResultDataAccessException e) {
				
		    	logger.info("No user   sid: " + sid );
		    	
			}
		    
		logger.debug("getUser( int sid ) EXIT ");
		return ret; 
		
	}
	
	
	
	
	
	public User getUserWithPassword( int sid ){
		User ret= null; 
		logger.debug("getUserWithPassword( int sid ) ENTER ");
		
		String sql ="SELECT *  FROM user WHERE sid = ?   ";
	
		ParameterizedRowMapper<User> mapper = new ParameterizedRowMapper<User>() {
			    
		        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		        	User user = new User();
		        	user.setSid(rs.getInt("sid"));
		        	user.setUname(rs.getString("uname"));
		        	user.setPword(  rs.getString("pword")  );
		        	user.setEmail(rs.getString("email"));
		        	user.setTel(rs.getString("tel"));
		        	user.setRoleType(rs.getString("role_type"));
		        	user.setCreateDt( rs.getDate("create_dt") ) ;
		        	user.setLastLoginDt( rs.getDate("last_login_dt") ) ;
		        	user.setStatus( rs.getInt("status") ) ;
		        	
		        	user.setSessionId( user.getUname() + "-"+user.getSid() +"-"+ Calendar.getInstance().getTimeInMillis()  ) ;
		           	
		        	user.setProfileImage(rs.getString("profile_image"));
		           	
		           	
//		        	user.setGamesTot( "" + getTotalMyGamesCount( user.getSid() ) ); 
		        	
//		        	user.setTeamsTot( "" + getTotalMyTeamsCount( user.getSid() ) ); 
		        	
		            return user;
		        }
		    };

		   
		
		    try {
		    	
		    	 ret =  simpleJdbcTemplate.queryForObject(sql, mapper, sid );

		    	
		    } catch (EmptyResultDataAccessException e) {
				
		    	logger.info("No user   sid: " + sid );
		    	
			}
		    
		logger.debug("getUserWithPassword( int sid ) EXIT ");
		return ret; 
		
	}
	
	
	
	
	
	public User getUserByUName( String uname ){
		User ret= null; 
		logger.debug("getUserByUName  ENTER ");
		
		String sql ="SELECT  *  " +
				" FROM user WHERE uname = ?   ";
	
		ParameterizedRowMapper<User> mapper = new ParameterizedRowMapper<User>() {
			    
		        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		        	User user = new User();
		        	user.setSid(rs.getInt("sid"));
		        	user.setUname(rs.getString("uname"));
		        	user.setPword("*****");// always set to dummy just for fun 
		        	user.setEmail(rs.getString("email"));
		        	user.setTel(rs.getString("tel"));
		        	user.setRoleType(rs.getString("role_type"));
		        	user.setCreateDt( rs.getDate("create_dt") ) ;
		        	user.setLastLoginDt( rs.getDate("last_login_dt") ) ;
		        	user.setStatus( rs.getInt("status") ) ;
		        	
		           	user.setProfileImage(rs.getString("profile_image"));
		        	
		        	user.setSessionId( user.getUname() + "-"+user.getSid() +"-"+ Calendar.getInstance().getTimeInMillis()  ) ;
		        	
		        	
		            return user;
		        }
		    };


		    
		    try {
		    	
		    	 ret =  simpleJdbcTemplate.queryForObject(sql, mapper, uname );

		    } catch (EmptyResultDataAccessException e) {
				
		    	logger.info("No user   uname: " + uname );
		    	
			}
		    
		    
		
		logger.debug("getUserByUNameEXIT ");
		return ret; 
		
	}
	
	
	
	
	
	public User getUserByEmail( String email ){
		User ret= null; 
		logger.debug("getUserByEmail  ENTER ");
		
		String sql ="SELECT *  FROM user WHERE email = ?   ";
	
		ParameterizedRowMapper<User> mapper = new ParameterizedRowMapper<User>() {
			    
		        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		        	User user = new User();
		        	user.setSid(rs.getInt("sid"));
		        	user.setUname(rs.getString("uname"));
		        	user.setPword("*****");// always set to dummy just for fun 
		        	user.setEmail(rs.getString("email"));
		        	user.setTel(rs.getString("tel"));
		        	user.setRoleType(rs.getString("role_type"));
		        	user.setCreateDt( rs.getDate("create_dt") ) ;
		        	user.setLastLoginDt( rs.getDate("last_login_dt") ) ;
		        	user.setStatus( rs.getInt("status") ) ;
		        	
		           	user.setProfileImage(rs.getString("profile_image"));
		           	
		        	user.setSessionId( user.getUname() + "-"+user.getSid() +"-"+ Calendar.getInstance().getTimeInMillis()  ) ;
		        	
		        	
		            return user;
		        }
		    };


		    
		    try {
		    	
		    	 ret =  simpleJdbcTemplate.queryForObject(sql, mapper, email );

		    } catch (EmptyResultDataAccessException e) {
				
		    	logger.info("No user with   email: " + email );
		    	
			}
		    
		    
		
		logger.debug("getUserByEmail Exit ");
		return ret; 
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public int insert( User user  ){
		int ret= -1;
		logger.debug("insert ENTER ");
		
		String sql = "INSERT INTO user (sid, uname, pword, email, tel, role_type, create_dt, last_login_dt, profile_image, status ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );";
		logger.debug("sql = "+sql);
		ret = simpleJdbcTemplate.update(
				sql, 
				new Object[] {user.getSid(), 
							  user.getUname(),
							  user.getPword(),
							  user.getEmail(),
							  user.getTel(),
							  user.getRoleType(),
							  user.getCreateDt(),
							  user.getLastLoginDt(),
							  user.getProfileImage(),
							  user.getStatus()
				});
		
		logger.debug("insert EXIT ");
		return ret; 
		
	}
	
	
	public int updatePassword( User user  ){
		int ret= -1;
		logger.debug("updatePassword ENTER ");
	
		String sql ="UPDATE user SET pword = ?  WHERE sid = ? ;"; 
		logger.debug("sql = "+sql);
		ret = simpleJdbcTemplate.update(
				sql, 
				new Object[] { 
							  user.getPword(),
							  user.getSid()						  
				});
		
		logger.debug("updatePassword EXIT ");
		return ret; 
		
	}
	
	public int updateLastLogin( User user  ){
		int ret= -1;
		logger.debug("updateLastLogin ENTER ");
	
		String sql ="UPDATE user SET last_login_dt = now()  WHERE sid = ? ;"; 
		
		logger.debug("sql = "+sql);
		ret = simpleJdbcTemplate.update(
				sql, 
				new Object[] { 
							  user.getSid()						  
				});
		
		logger.debug("updateLastLogin EXIT ");
		return ret; 
		
	}
	
	
	public int updateEmail( User user  ){
		int ret= -1;
		logger.debug("updateEmail ENTER ");
	
		String sql ="UPDATE user SET email = ?  WHERE sid = ? ;"; 
		logger.debug("sql = "+sql);
		
		ret = simpleJdbcTemplate.update(
				sql, 
				new Object[] { 
							  user.getEmail(),
							  user.getSid()						  
				});
		
		logger.debug("updateEmail EXIT ");
		return ret; 
		
	}
	
	
	public int updateTel( User user  ){
		int ret= -1;
		logger.debug("updateTel ENTER ");
	
		String sql ="UPDATE user SET tel = ?  WHERE sid = ? ;"; 
		logger.debug("sql = "+sql);
		
		ret = simpleJdbcTemplate.update(
				sql, 
				new Object[] { 
							  user.getTel(),
							  user.getSid()						  
				});
		
		logger.debug("updateTel EXIT ");
		return ret; 
		
	}
	
	
	public int updateImage( User user  ){
		int ret= -1;
		logger.debug("updateImage ENTER ");
	
		String sql ="UPDATE user SET profile_image = ?  WHERE sid = ? ;"; 
		logger.debug("sql = "+sql);
		
		ret = simpleJdbcTemplate.update(
				sql, 
				new Object[] { 
							  user.getProfileImage(),
							  user.getSid()						  
				});
		
		logger.debug("updateImage EXIT ");
		return ret; 
		
	}
	
	
	public int update( User user  ){
		int ret= -1;
		logger.debug("update ENTER ");
		
		// we don't update the sid  and the createdt 	
		String sql ="UPDATE user SET uname = ?, pword = ? , email = ? , role_type = ? , last_login_dt = ? , profile_image = ?, status = ? WHERE sid = ? ;"; 
		logger.debug("sql = "+sql);	
		ret = simpleJdbcTemplate.update(
				sql, 
				new Object[] { 
							  user.getUname(),
							  user.getPword(),
							  user.getEmail(),
							  user.getRoleType(),
							  user.getLastLoginDt(),
							  user.getProfileImage(),
							  user.getStatus(),
							  user.getSid()						  
				});
		
		logger.debug("update EXIT ");
		return ret; 
		
	}
	

	
}
