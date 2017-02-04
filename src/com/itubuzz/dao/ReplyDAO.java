package com.itubuzz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ReplyDAO {

	public static boolean replydataCred(String reply_id, String reply_text, String immparent_id, String post_id, String user_id, String reply_name ) {
		// TODO Auto-generated method stub
		 boolean status = false;  
	        Connection conn = null;  
	        ResultSet rs = null;
	        PreparedStatement ps = null;
	        
	        Long replyId = Long.parseLong((reply_id));
	        Long immparentId = Long.parseLong((immparent_id));
	        Integer postId = Integer.parseInt((post_id));
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
	            		"insert into replies (reply_id, reply_text, immparent_id, post_id, log_user_id, log_reply_name) values(?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);  
	            		
        		ps.setLong(1, replyId);
        		ps.setString(2, reply_text);
        		ps.setLong(3, immparentId);
        		ps.setInt(4, postId);
        		ps.setInt(5, userId);
        		ps.setString(6, reply_name);
               ps.executeUpdate() ; 
               rs = ps.getGeneratedKeys();
               if(rs != null && rs.next()){
                   System.out.println(" reply Generated user Id: "+rs.getInt(1));
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