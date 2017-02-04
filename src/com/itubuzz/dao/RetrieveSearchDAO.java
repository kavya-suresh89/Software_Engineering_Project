package com.itubuzz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.itubuzz.valueobjects.*;

public class RetrieveSearchDAO {

	public static SearchPostVO retrieveSearchedPostData(String searchstring) {
		Connection conn = null;  
	    PreparedStatement ps = null;  
	    ResultSet rs = null;
	    ResultSet rrs = null;
	    
	    final String DB_URL="jdbc:mysql://127.0.0.1:3306/itubuzz"; 
	    String driver = "com.mysql.jdbc.Driver";  
	    String userName = "root";  
	    String password = "root"; 
	    
    	ArrayList<PostVO> search_plist=new ArrayList<PostVO>();
        ArrayList<ReplyVO> search_rlist=new ArrayList<ReplyVO>();
        SearchPostVO sp = new SearchPostVO();
	    
	    try {  
	        Class.forName(driver).newInstance();  
	        conn = DriverManager  
	                .getConnection(DB_URL, userName, password);  
	        String query = "select * from posts  where post_text  like \'" + searchstring + "\';";
	        ps=conn.prepareStatement(query);
	        rs = ps.executeQuery();
	       
	        while(rs.next())
	        {
	            PostVO p = new PostVO();

	            p.setPost_id(rs.getInt(1));
	            p.setPost_text(rs.getString(2));
	            p.setLog_user_id(rs.getInt(3));
	            p.setPost_user_name(rs.getString(4));
	            search_plist.add(p);
	            
		        query = "select * from replies where post_id = ? ;";
		        ps=conn.prepareStatement(query);
		        ps.setInt(1, p.getPost_id());
		        rrs = ps.executeQuery();
		        
		        while(rrs.next())
		        {
		            ReplyVO r = new ReplyVO();
		            
		            r.setReply_number(rrs.getLong(1));
		            r.setReply_id(rrs.getLong(2));
		            r.setReply_text(rrs.getString(3));
		            r.setImmparent_id(rrs.getLong(4));
		            r.setPost_id(rrs.getInt(5));
		            r.setLog_user_id(rrs.getInt(6));
		            r.setLog_reply_name(rrs.getString(7));
		        
		            search_rlist.add(r);
		        }

	        }
	        
	        sp.search_plist = search_plist;
	        sp.search_rlist = search_rlist;

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
	    return sp;  
	}

	public static ArrayList<QuestionVO> retrieveSearchedQueData(String searchstring) {
		Connection conn = null;  
	    PreparedStatement ps = null;  
	    ResultSet rs = null;  
	    	    
	    final String DB_URL="jdbc:mysql://127.0.0.1:3306/itubuzz"; 
	    String driver = "com.mysql.jdbc.Driver";  
	    String userName = "root";  
	    String password = "root"; 
	    
    	ArrayList<QuestionVO> search_qlist=new ArrayList<QuestionVO>();
	    
	    try {  
	        Class.forName(driver).newInstance();  
	        conn = DriverManager  
	                .getConnection(DB_URL, userName, password);  
	        String query = "select * from questions  where question_text  like \'" + searchstring + "\';";	        
	        ps=conn.prepareStatement(query);  	        		
	        rs = ps.executeQuery();
	       
	        while(rs.next())
	        {
	        	QuestionVO q = new QuestionVO();
	        	
	            q.setquestion_id(rs.getInt(1));
	            q.setquestion_text(rs.getString(2));
	            q.setLog_user_id(rs.getInt(3));
	            q.setLog_user_name(rs.getString(4));
	                              
	            search_qlist.add(q);	        
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
	    return search_qlist;  
	}

	public static SearchPostVO retrieveYourPostQueData(Integer userid) {
		Connection conn = null;  
	    PreparedStatement ps = null;  
	    ResultSet rs = null;
	    ResultSet rrs = null;
	    
	    final String DB_URL="jdbc:mysql://127.0.0.1:3306/itubuzz"; 
	    String driver = "com.mysql.jdbc.Driver";  
	    String userName = "root";  
	    String password = "root"; 
	    
    	ArrayList<PostVO> search_plist=new ArrayList<PostVO>();
        ArrayList<ReplyVO> search_rlist=new ArrayList<ReplyVO>();
        SearchPostVO sp = new SearchPostVO();
	    
	    try {  
	        Class.forName(driver).newInstance();  
	        conn = DriverManager  
	                .getConnection(DB_URL, userName, password);  
	        String query = "select * from posts where log_user_id = ?";
	        ps=conn.prepareStatement(query);
	        ps.setInt(1, userid);
	        rs = ps.executeQuery();
	       
	        while(rs.next())
	        {
	            PostVO p = new PostVO();

	            p.setPost_id(rs.getInt(1));
	            p.setPost_text(rs.getString(2));
	            p.setLog_user_id(rs.getInt(3));
	            p.setPost_user_name(rs.getString(4));
	            search_plist.add(p);
	            
		        query = "select * from replies where post_id = ? ;";
		        ps=conn.prepareStatement(query);
		        ps.setInt(1, p.getPost_id());
		        rrs = ps.executeQuery();
		        
		        while(rrs.next())
		        {
		            ReplyVO r = new ReplyVO();
		            
		            r.setReply_number(rrs.getLong(1));
		            r.setReply_id(rrs.getLong(2));
		            r.setReply_text(rrs.getString(3));
		            r.setImmparent_id(rrs.getLong(4));
		            r.setPost_id(rrs.getInt(5));
		            r.setLog_user_id(rrs.getInt(6));
		            r.setLog_reply_name(rrs.getString(7));
		        
		            search_rlist.add(r);
		        }

	        }
	        
	        sp.search_plist = search_plist;
	        sp.search_rlist = search_rlist;

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
	    return sp;  

	}

	public static ArrayList<QuestionVO> retrieveYourQueData(Integer userid) {
		Connection conn = null;  
	    PreparedStatement ps = null;  
	    ResultSet rs = null;  
	    	    
	    final String DB_URL="jdbc:mysql://127.0.0.1:3306/itubuzz"; 
	    String driver = "com.mysql.jdbc.Driver";  
	    String userName = "root";  
	    String password = "root"; 
	    
    	ArrayList<QuestionVO> search_qlist=new ArrayList<QuestionVO>();
	    
	    try {  
	        Class.forName(driver).newInstance();  
	        conn = DriverManager  
	                .getConnection(DB_URL, userName, password);  
	        String query = "select * from questions  where log_user_id = ?;";	        
	        ps=conn.prepareStatement(query);  	        		
	        ps.setInt(1, userid);
	        rs = ps.executeQuery();
	       
	        while(rs.next())
	        {
	        	QuestionVO q = new QuestionVO();
	        	q.setquestion_id(rs.getInt(1));
	            q.setquestion_text(rs.getString(2));
	            q.setLog_user_id(rs.getInt(3));
	            q.setLog_user_name(rs.getString(4));
	            
	                              
	            search_qlist.add(q);	        
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
	    return search_qlist;  
	}
}
