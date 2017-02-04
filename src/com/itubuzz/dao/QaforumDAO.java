package com.itubuzz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QaforumDAO {

	public static boolean forumdataCred(String question_text,String user_id,String user_name  ) {
		// TODO Auto-generated method stub
		 boolean status = false;  
	        Connection conn = null;  
	        PreparedStatement ps = null;  
	        int rs = 0;
	        
	        Integer userId = Integer.parseInt((user_id));
	        
	        final String DB_URL="jdbc:mysql://127.0.0.1:3306/itubuzz"; 
	        String driver = "com.mysql.jdbc.Driver";  
	        String userName = "root";  
	        String password = "root";  
	        try {  
	            Class.forName(driver).newInstance();  
	            conn = DriverManager  
	                    .getConnection(DB_URL, userName, password);  
	  
	            ps=conn.prepareStatement(  
	            		"insert into questions(question_text, log_user_id,log_user_name) values(?,?,?)");  
       			  
	            		ps.setString(1, question_text);
	            		ps.setInt(2, userId);
	            		ps.setString(3, user_name);
	            		rs=ps.executeUpdate();
	            
	            		if(rs>0){
	            			status=true;
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
	            if (ps != null) {  
	                try {  
	                    ps.close();  
	                } catch (SQLException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	             
	        }  
	        return status;  
	}

	public static boolean forumdataCred(String answer_id, String answer_text, String immparent_id, String question_id, String user_id, String user_name) {
		// TODO Auto-generated method stub
		 	boolean status = false;  
	        Connection conn = null;  
	        PreparedStatement ps = null;  
	        ResultSet rs = null;
	        
	        Long answerId = Long.parseLong((answer_id));
	        Long immparentId = Long.parseLong((immparent_id));
	        Integer questionId = Integer.parseInt((question_id));
	        Integer userId = Integer.parseInt((user_id));
	        
	        final String DB_URL="jdbc:mysql://127.0.0.1:3306/itubuzz"; 
	        String driver = "com.mysql.jdbc.Driver";  
	        String userName = "root";  
	        String password = "root";  
	        
	        try {  
	            Class.forName(driver).newInstance();  
	            conn = DriverManager  
	                    .getConnection(DB_URL, userName, password);  
	   /**
	    * Removed Generated key functionality as there is no return value
	    * @author Kavya
	    * edited on : 04/04/2016
	    */
	            ps=conn.prepareStatement(  
	            		"insert into answers(answer_id, answer_text, immparent_id, question_id, log_user_id, log_user_name) values(?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);  
       			  
	            		ps.setLong(1, answerId);
	            		ps.setString(2, answer_text);
	            		ps.setLong(3, immparentId);
	            		ps.setInt(4, questionId);
	            		ps.setInt(5, userId);	
	            		ps.setString(6, user_name);
	            		 ps.executeUpdate();
	                      
	            		 rs= ps.getGeneratedKeys();
	            		 if(rs != null && rs.next()){
	                         System.out.println(" answer Generated user Id: "+rs.getInt(1));
	                         status = true;
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
	            if (ps != null) {  
	                try {  
	                    ps.close();  
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
	        return status;  
	}
}