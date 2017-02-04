package com.itubuzz.dao;
/**
 * International Technological Universoty, San Jose
 * @author Poorvisha
 * created on : 03/18/2016
 */
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.HashMap;
	import java.util.Map;

	public class FetchProfileDetailsDAO {
		
		 static String firstName = null;
		 static String middleName = null;
		 static String lastName = null;
		 static String eMailId = null;
		 static String dept = null;
		 static String sem = null;
		 static String role = null;
		 static String dob = null;
		 static String yop = null;
		 static String user_id = null;
		
		/**
		 * @param eMail
		 * @return
		 * This method fetches user profile details from DB
		 */
		public static boolean fetchProfile(String eMail) {
		  boolean status = false;
		  Connection conn = null;
		  PreparedStatement pst = null;
		  ResultSet rs = null;
		
		  final String DB_URL = "jdbc:mysql://127.0.0.1:3306/itubuzz";
		  String driver = "com.mysql.jdbc.Driver";
		  String userName = "root";
		  String password = "root";
		  try {
			   Class.forName(driver).newInstance();
			   conn = DriverManager
			    .getConnection(DB_URL, userName, password);
			
			   pst = conn
			    .prepareStatement("select * from userLogin where e_mail_id=?");
			   pst.setString(1, eMail);
			
			   rs = pst.executeQuery();
			
			
			   if (rs != null && rs.next()) {
				   user_id = rs.getString("user_id");
			    firstName = rs.getString("first_name");
			    lastName = rs.getString("last_name");
			    middleName = rs.getString("middle_name");
			    eMailId = rs.getString("e_mail_id");
			    dept = rs.getString("department");
			    sem = rs.getString("trimester");
			    role = rs.getString("user_type");
			    dob = rs.getString("date_of_birth");
			    yop = rs.getString("year_of_passing");
			    if(null == middleName || "null" == middleName){
			    	middleName = "";
			    }
			    status = true;
			   }
			   pst.close();
			   /*pst = conn
			    .prepareStatement("select * from profile_images where e_mail_id=?");
			   pst.setString(1, eMail);
			
			   rs = pst.executeQuery();*/
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
		  return status;
		}
		
		
		 /**
		 * @return
		 * This method returns user details as a map
		 */
		public static Map < String, String > UserDetails() {
			  Map < String, String > user = new HashMap < String, String > ();
			  String[] dobList  = null;
			  if (dob != null)
			  {
				  dobList = dob.split("-");
			  }
			  if (yop != null && yop.length() > 2) {
				   String[] yopList = yop.split("-");
				   user.put("yop_month", yopList[1]);
				   user.put("yop_year", yopList[0]);
			  }
			  user.put("user_id", user_id);
			  user.put("firstName", firstName);
			  user.put("lastName", lastName);
			  user.put("middleName", middleName);
			  user.put("eMailId", eMailId);
			  user.put("dept", dept);
			  user.put("sem", sem);
			  user.put("role", role);
			  if(dobList !=null)
			  {
			  user.put("dob_day", dobList[2]);
			  user.put("dob_month", dobList[1]);
			  user.put("dob_year", dobList[0]);
			  }
			  return user;
		 }
		
	}

