package com.itubuzz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itubuzz.valueobjects.GroupVO;

public class RetrieveGroupUsersDAO {

	public static List<GroupVO> retrievegroupUsersforGroupId(String groupId) {          
	       
		Connection conn = null;  
        PreparedStatement pst = null;  
        ResultSet rs = null;  
        long group_ID = Long.parseLong(groupId);
        List<GroupVO> mygroups_list= new ArrayList<GroupVO>();
        
        final String DB_URL="jdbc:mysql://127.0.0.1:3306/itubuzz"; 
        String driver = "com.mysql.jdbc.Driver";  
        String userName = "root";  
        String password = "root";  
        try {  
        	GroupVO createdGroup = new GroupVO();
            Class.forName(driver).newInstance();  
            conn = DriverManager  
                    .getConnection(DB_URL, userName, password);  
  
            pst = conn  
                    .prepareStatement("select * from user_group where group_id=?  ");  
            pst.setLong(1, group_ID);  
            
  
            rs = pst.executeQuery();
            
            System.out.println("value of rs "+rs.getFetchSize());
            
            while(rs.next())
            {            	
                createdGroup= (retrieveUserNames(rs.getLong(2))); 
                
                System.out.println( "the user ids returned are : "+rs.getLong(2));
                mygroups_list.add(createdGroup);
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
        return mygroups_list;  
    } 
    
	
	public static GroupVO retrieveUserNames(long userId) {          
	       
        Connection conn = null;  
        PreparedStatement pst = null;  
        ResultSet rs = null;  
        GroupVO createdGroup = new GroupVO();
        
        final String DB_URL="jdbc:mysql://127.0.0.1:3306/itubuzz"; 
        String driver = "com.mysql.jdbc.Driver";  
        String userName = "root";  
        String password = "root";  
        try {  
            Class.forName(driver).newInstance();  
            conn = DriverManager  
                    .getConnection(DB_URL, userName, password);  
  
           
            

            pst = conn  
                    .prepareStatement("select first_name,last_name from userLogin where user_id=?  ");  
            pst.setLong(1,userId);  
            
  
            rs = pst.executeQuery();
            
            System.out.println("value of rs "+rs.getFetchSize());
            
            if(rs != null && rs.next())
            {
            
                createdGroup.setUserId(userId); 
            	createdGroup.setFirstName(rs.getString("first_name"));
            	createdGroup.setLastName(rs.getString("last_name"));
            	
            	
                
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
        return createdGroup;  
    } 
    
	
}
