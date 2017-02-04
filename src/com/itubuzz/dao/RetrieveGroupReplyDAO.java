package com.itubuzz.dao;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.itubuzz.valueobjects.GroupReplyVO;

public class RetrieveGroupReplyDAO {

	public static ArrayList<GroupReplyVO> retrieveRepliedData(int group_id){
		    Connection conn = null;  
		    PreparedStatement pst = null;  
		    ResultSet rs = null;  
		    
		    final String DB_URL="jdbc:mysql://127.0.0.1:3306/itubuzz"; 
		    String driver = "com.mysql.jdbc.Driver";  
		    String userName = "root";  
		    String password = "root"; 
		    
		    ArrayList<GroupReplyVO> reply_list=new ArrayList<GroupReplyVO>();
		    try {  
		        Class.forName(driver).newInstance();  
		        conn = DriverManager  
		                .getConnection(DB_URL, userName, password);  

		        PreparedStatement ps=conn.prepareStatement(  
		        		"select * from group_replies where group_id = ? order by g_post_id , g_reply_id desc");  
		        
		        ps.setInt(1, group_id);
		        rs = ps.executeQuery();
		       
		        while(rs.next())
		        {
		            GroupReplyVO p = new GroupReplyVO();
		            
		            p.setReply_number(rs.getLong(1));
		            p.setReply_id(rs.getLong(2));
		            p.setReply_text(rs.getString(3));
		            p.setImmparent_id(rs.getLong(4));
		            p.setpost_id(rs.getInt(5));
		            p.setLog_user_id(rs.getInt(6));
		            p.setLog_reply_name(rs.getString(7));
		            p.setGroup_id(group_id);
		        
		            reply_list.add(p);
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
		    return reply_list;  
		} 
	}
