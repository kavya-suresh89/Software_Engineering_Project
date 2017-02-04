package com.itubuzz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itubuzz.valueobjects.UserVO;

public class RetrieveUserDetailsbyIdDAO {

	
	
	 public static List<UserVO> retrieveUserDetails(String loguserId) {          
	       
	        Connection conn = null;  
	        PreparedStatement pst = null;  
	        ResultSet rs = null;  
	        List<UserVO> user_list= new ArrayList<UserVO>();
	        final String DB_URL="jdbc:mysql://127.0.0.1:3306/itubuzz"; 
	        String driver = "com.mysql.jdbc.Driver";  
	        String userName = "root";  
	        String password = "root";  
	        try {  
	            Class.forName(driver).newInstance();  
	            conn = DriverManager  
	                    .getConnection(DB_URL, userName, password);  
	  
	            pst = conn  
	                    .prepareStatement("select * from userLogin where user_id=?  ");  
	            pst.setString(1, loguserId);  
	            
	  
	            rs = pst.executeQuery();
	            
	            System.out.println("value of rs "+rs.getFetchSize());
	            
	            if(rs != null && rs.next()){
	            	
	                
	            	UserVO registeredUser = new UserVO();
	            	registeredUser.setUser_id(Integer.parseInt(rs.getString("user_id")));
	            	registeredUser.setFirst_name(rs.getString("first_name"));
	            	registeredUser.setMiddle_name(rs.getString("middle_name"));
	            	registeredUser.setLast_name(rs.getString("last_name"));
	            	registeredUser.setE_mailId(rs.getString("e_mail_id"));
	            	registeredUser.setDob(rs.getString("date_of_birth"));
	            	registeredUser.setDept(rs.getString("department"));
	            	registeredUser.setYearOfPassing(rs.getString("year_of_passing"));
	            	registeredUser.setRole(rs.getString("user_type"));
	            	user_list.add(registeredUser);
	            	
	                
	            }
	            
	 
	  
	        } catch (Exception e) {  
	            System.out.println(e);  
	        } finally {  
	            if (conn != null) {  
	                try {  
	                    conn.close();  
	                } catch (SQLException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	            if (pst != null) {  
	                try {  
	                    pst.close();  
	                } catch (SQLException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	            if (rs != null) {  
	                try {  
	                    rs.close();  
	                } catch (SQLException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	        }  
	        return user_list;  
	    } 
	    
	    
	    
}