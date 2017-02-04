package com.itubuzz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import java.util.Set;

import com.itubuzz.valueobjects.GroupSearchVO;
import com.itubuzz.valueobjects.UserVO;

public class CreateGroupDAO {
	
	private static ResultSet rs;

	public static int createGroup( String groupName) {
		    Connection conn = null;  
	        PreparedStatement pst = null;  
	        rs = null;  
			int groupId = -1;
	  
	        final String DB_URL="jdbc:mysql://127.0.0.1:3306/itubuzz"; 
	        String driver = "com.mysql.jdbc.Driver";  
	        String userName = "root";  
	        String password = "root";  
	        try {  
	            Class.forName(driver).newInstance();  
	            conn = DriverManager  
	                    .getConnection(DB_URL, userName, password);  
	            
				if(groupName != null){
					PreparedStatement ps=conn.prepareStatement(  
	            		"insert into groups(group_name) values (?)",Statement.RETURN_GENERATED_KEYS);  
	            		
	            		ps.setString(1,groupName);
	            		ps.executeUpdate(); 
	            		
	            		rs = ps.getGeneratedKeys();
	                     if(rs != null && rs.next()){
	                         groupId = rs.getInt(1);
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
	        return groupId;  
	} 

	public static int assignUsersToGroup( int groupId, String members ) {
		// TODO Auto-generated method stub
		    Connection conn = null;  
	        PreparedStatement pst = null;  
	        rs = null;  
	        int[] numOfInserts = null;
	        final String DB_URL="jdbc:mysql://127.0.0.1:3306/itubuzz"; 
	        String driver = "com.mysql.jdbc.Driver";  
	        String userName = "root";  
	        String password = "root";  
	        List<Integer> existingUserIds = new ArrayList<Integer>();
	        try {  
	            Class.forName(driver).newInstance();  
	            conn = DriverManager  
	                    .getConnection(DB_URL, userName, password); 
				//Edited by kavya for type mismatch
	            
	           // List<Integer> userIds = UserDAO.retrieveUserIdsByEmailIds(members);
	            
	            
				Set<Integer> userIds = UserDAO.retrieveUserIdsByEmailIds(members);
				
				// added by Priyanka
				GroupSearchVO criteria = new GroupSearchVO();
				criteria.setGroupId(groupId);
				List<UserVO> existingUserIdsInGroup = GroupDAO.fetchGroupMembers(criteria);
				for(UserVO vo:existingUserIdsInGroup){
					existingUserIds.add(vo.getUser_id());
				}
				
				userIds.removeAll(existingUserIds); // remove existig ones and add only new ones.
	            if(!userIds.isEmpty()){
					PreparedStatement ps=conn.prepareStatement(  
	            		"insert into user_group(group_id,user_id) values(?,?)");  
				
					// we will do a batch insert here rather than inserting each record
					for(Integer userId : userIds){
						ps.setInt(1,groupId);
						ps.setInt(2, userId);
						ps.addBatch();
					}
	            	numOfInserts = ps.executeBatch(); 
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
	        return numOfInserts.length;  
	} 

	/**
	 * Probably this can be moved to Email related DAOs
	 * @param e_mail
	 * @return
	 */
	public static boolean verifyIfEmailExists(String e_mail){
		
	    boolean status = false;  
        Connection conn = null;  
        PreparedStatement pst = null;  
        ResultSet rs = null; 
  
        final String DB_URL="jdbc:mysql://127.0.0.1:3306/itubuzz"; 
        String driver = "com.mysql.jdbc.Driver";  
        String userName = "root";  
        String password = "root";  
        try {  
            Class.forName(driver).newInstance();  
            conn = DriverManager  
                    .getConnection(DB_URL, userName, password);  
  
            pst = conn  
                    .prepareStatement("select e_mail_id from userLogin where e_mail_id=?");  
            pst.setString(1, e_mail);  
            rs = pst.executeQuery();
            while(rs.next()){
            	 status = true;
            }
        }catch (Exception e) {  
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
