package com.itubuzz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.itubuzz.valueobjects.GroupVO;



public class MyGroupIdRetrieveDAO {

	
	
	public static List<GroupVO> retrievegroupIdforGroup(long userId) {          
	       
        Connection conn = null;  
        PreparedStatement pst = null;  
        ResultSet rs = null;  
        
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
                    .prepareStatement("select * from user_group where user_id=?  ");  
            pst.setLong(1, userId);  
            
  
            rs = pst.executeQuery();
            
            System.out.println("value of rs "+rs.getFetchSize());
            
            while(rs.next())
            {            	
                createdGroup= (retrieveGroupName(rs.getLong(1))); 
                
                System.out.println( "the group ids returned are : "+rs.getLong(1));
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
    
	
	public static GroupVO retrieveGroupName(long groupId) {          
	       
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
                    .prepareStatement("select * from groups where group_id=?  ");  
            pst.setLong(1,groupId);  
            
  
            rs = pst.executeQuery();
            
            System.out.println("value of rs "+rs.getFetchSize());
            
            if(rs != null && rs.next())
            {
            
                createdGroup.setGroupId(groupId); 
            	createdGroup.setGroupName(rs.getString(2));
            	
            	
                
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
