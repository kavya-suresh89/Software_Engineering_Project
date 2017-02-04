package com.itubuzz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itubuzz.valueobjects.GroupSearchVO;
import com.itubuzz.valueobjects.UserVO;

public class GroupDAO {

	public static List<UserVO> fetchGroupMembers(GroupSearchVO criteria) {
	  Connection conn = null;
	  PreparedStatement pst = null;
	  ResultSet rs = null;
	  List<UserVO> listVo = new ArrayList<UserVO>();
	  UserVO vo = null;
	  
	  /**
	   * this can be refactored by creating a DB Connection class and return connection on demand
	   */
	  final String DB_URL = "jdbc:mysql://127.0.0.1:3306/itubuzz";
	  String driver = "com.mysql.jdbc.Driver";
	  String userName = "root";
	  String password = "root";
	  try {
		   Class.forName(driver).newInstance();
		   conn = DriverManager
		    .getConnection(DB_URL, userName, password);
		
		   pst = conn
		    .prepareStatement("select u.first_name,u.last_name,u.e_mail_id,u.user_id from userLogin u, user_group ug where u.user_id = ug.user_id and ug.group_id=?");
		   pst.setLong(1, criteria.getGroupId());
		
		   rs = pst.executeQuery();
		
		
		   while (rs != null && rs.next()) {
			   vo = new UserVO();
			   vo.setFirst_name(rs.getString("first_name"));
			   vo.setLast_name(rs.getString("last_name"));
			   vo.setE_mailId(rs.getString("e_mail_id"));
			   vo.setUser_id(rs.getInt("user_id"));
			   listVo.add(vo);
		   }
	  }catch (Exception e) {
		  System.out.println(e);
	  }finally {
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
	  return listVo;
	}

	public static void addMembersToGroup(Integer gid, String emailIds) {
		  Connection conn = null;
		  PreparedStatement pst = null;
		  ResultSet rs = null;
		  		  /**
		   * this can be refactored by creating a DB Connection class and return connection on demand
		   */
		  final String DB_URL = "jdbc:mysql://127.0.0.1:3306/itubuzz";
		  String driver = "com.mysql.jdbc.Driver";
		  String userName = "root";
		  String password = "root";
		  try {
			   Class.forName(driver).newInstance();
			   conn = DriverManager
			    .getConnection(DB_URL, userName, password);
			
			   int numberOfRecordsCreated = CreateGroupDAO.assignUsersToGroup(gid,emailIds);
			   System.out.println("inside groupDAO number of records created are : "+numberOfRecordsCreated);
		  }catch (Exception e) {
			  System.out.println(e);
		  }finally {
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
		}
}
