package com.itubuzz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GroupPostDAO {

	public static boolean postdataCred(String group_id, String post_text,String user_id,String post_user_name  ) {
		// TODO Auto-generated method stub
		 boolean status = false;  
	        Connection conn = null;  
	        PreparedStatement pst = null;  
	        ResultSet rs = null;  	        

	        Integer userId = Integer.parseInt((user_id));
	        
	        final String DB_URL="jdbc:mysql://127.0.0.1:3306/itubuzz"; 
	        String driver = "com.mysql.jdbc.Driver";  
	        String userName = "root";  
	        String password = "root";  
	        try {  
	            Class.forName(driver).newInstance();  
	            conn = DriverManager  
	                    .getConnection(DB_URL, userName, password);  
	  
	            PreparedStatement ps=conn.prepareStatement(  
	            		"insert into group_posts (g_post_text, log_user_id, log_user_name,group_id) values(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);  
	            		
	            ps.setString(1,post_text);
	            ps.setInt(2,userId);
	            ps.setString(3, post_user_name);
	            ps.setInt(4, Integer.parseInt(group_id));
	            ps.executeUpdate();

	            rs = ps.getGeneratedKeys();
	            
                if(rs != null && rs.next()){
                    System.out.println("Generated post Id: "+rs.getInt(1));
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
	        return status;  
	    } 
}