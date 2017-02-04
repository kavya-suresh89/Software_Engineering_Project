package com.itubuzz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.itubuzz.valueobjects.PostVO;
import com.itubuzz.valueobjects.ReplyVO;

public class MostPopularPostsAndRepliesDAO {

	
	
	public static ArrayList<PostVO> retrieveMostPopular(){
		 Connection conn = null;  
	          
	        ResultSet rs = null; 
	        final String DB_URL="jdbc:mysql://127.0.0.1:3306/itubuzz"; 
		    String driver = "com.mysql.jdbc.Driver";  
		    String userName = "root";  
		    String password = "root"; 
		    ArrayList<PostVO> retrievedList = new ArrayList<PostVO>();
			ArrayList<PostVO> mostPopularPosts = new ArrayList<PostVO>();
	 		retrievedList = RetrievePostDAO.retrievePostedData();
		    try { 
		    Class.forName(driver).newInstance();  
	        conn = DriverManager  
	                .getConnection(DB_URL, userName, password);
	        PreparedStatement ps=null;
	        
		
		
			for(int i=0; i<retrievedList.size();i++){
							ps=conn.prepareStatement(  
				        		"select count(reply_id) from replies where post_id=?");
				        ps.setLong(1, retrievedList.get(i).getPost_id());
				        		
				        rs = ps.executeQuery();
				        
				        while(rs!=null && rs.next() ){
				        	if(rs.getInt(1)>5){
				        		mostPopularPosts.add(retrievedList.get(i));
				        	}
				        }
			}
			}
			catch (Exception e) {  
	            System.out.println(e);  
	        } finally {  
	            if (conn != null) {  
	                try {  
	                    conn.close();  
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
		return mostPopularPosts;
		
		
	}
	
	
	
	
	public static ArrayList<ReplyVO> retrieveMostPopularReplies(ArrayList<PostVO> mostPopularPost){
		
		 Connection conn = null;  
	          
	        ResultSet rs = null; 
	        final String DB_URL="jdbc:mysql://127.0.0.1:3306/itubuzz"; 
		    String driver = "com.mysql.jdbc.Driver";  
		    String userName = "root";  
		    String password = "root"; 
			ArrayList<ReplyVO> mostPopularReplies = new ArrayList<ReplyVO>();
	 		
		    try { 
		    Class.forName(driver).newInstance();  
	        conn = DriverManager  
	                .getConnection(DB_URL, userName, password);
	        PreparedStatement ps=null;
	       
	        		for(PostVO posts : mostPopularPost){
				        ps=conn.prepareStatement(  
				        		"select * from replies where post_id=?");
				        ps.setLong(1, posts.getPost_id());
				        		
				        rs = ps.executeQuery();
				        
				        while(rs!=null && rs.next() ){
				        	  ReplyVO p = new ReplyVO();
				        	  p.setReply_number(rs.getLong(1));
					            p.setReply_id(rs.getLong(2));
					            p.setReply_text(rs.getString(3));
					            p.setImmparent_id(rs.getLong(4));
					            p.setPost_id(rs.getInt(5));
					            p.setLog_user_id(rs.getInt(6));
					            p.setLog_reply_name(rs.getString(7));
					        
				        		mostPopularReplies.add(p);
				        	}
				        }
			}
			catch (Exception e) {  
	            System.out.println(e);  
	        } finally {  
	            if (conn != null) {  
	                try {  
	                    conn.close();  
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
		return mostPopularReplies;
		
		
	}
	
}

	
