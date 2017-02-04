package com.itubuzz.dao;
/**
 * International Technological University, San Jose
 * @author Poorvisha
 * created on : 03/20/2016
 */
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.Part;

public class UpdateProfileDAO {
	
	 private static ResultSet rs1;
	 private static ResultSet rs2;
	 private static ResultSet rs3;
	
	 public static boolean updateStudentProfileDetails(String firstName, String middleName,
		String lastName, String eMailId, String dob,
		String dept, String sem, Part filePart) {
		java.sql.Date sqlDateDob;
		 
 		  
 		if((dob.equals("0000-00-00")) || (dob.equals("null-null-null")) ) {
 			sqlDateDob = null;
 		} else {
 			sqlDateDob = java.sql.Date.valueOf(dob);
 		}
 		 
		  boolean status = false;
		  Connection conn = null;
		  PreparedStatement ps = null;
		  rs1 = null;
		  rs2 = null;
		  rs3 = null;
		
		  final String DB_URL = "jdbc:mysql://127.0.0.1:3306/itubuzz";
		  String driver = "com.mysql.jdbc.Driver";
		  String userName = "root";
		  String password = "root";
		
		  InputStream inputStream = null;
		  if (filePart != null) {
			   // prints out some information for debugging
			   System.out.println(filePart.getName());
			   System.out.println(filePart.getSize());
			   System.out.println(filePart.getContentType());
			
			   // obtains input stream of the upload file
			   try {
				   inputStream = filePart.getInputStream();
			   } catch (IOException e) {
				   e.printStackTrace();
			   }
		  }
		  try {
			   Class.forName(driver).newInstance();
			   conn = DriverManager
			    .getConnection(DB_URL, userName, password);
			
			   if (firstName != null && lastName != null && eMailId != null && dept != null && sem != null) {
			    /* PreparedStatement ps=conn.prepareStatement(  
			     		"insert into usertest(first_name, middle_name, last_name, password, email_id, department, semester, role) values(?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);*/
			    ps = conn.prepareStatement(
			     "update userLogin set first_name=?, middle_name=?, last_name=?, date_of_birth=?,department=?, trimester=? where e_mail_id=?", Statement.RETURN_GENERATED_KEYS);
			
			
			    ps.setString(1, firstName);
			    ps.setString(2, middleName);
			    ps.setString(3, lastName);
			    //ps.setString(4, newPassword);
			    ps.setDate(4, sqlDateDob);
			    ps.setString(5, dept);
			    ps.setString(6, sem);
			    ps.setString(7, eMailId);
			
			    ps.executeUpdate();
			
			    rs1 = ps.getGeneratedKeys();
			    ps.close();
			    if (filePart.getSize() > 0) {
				     ps = conn.prepareStatement("select * from profile_images where e_mail_id=?", Statement.RETURN_GENERATED_KEYS);
				     ps.setString(1, eMailId);
				     rs2 = ps.executeQuery();
				     if (!rs2.next()) {
				    	 ps.close();
					      ps = conn.prepareStatement(
					       "insert into profile_images(e_mail_id,image) values (?,?)", Statement.RETURN_GENERATED_KEYS);
					      ps.setString(1, eMailId);
					      if (inputStream != null) {
					       ps.setBlob(2, inputStream);
					      }
					      ps.executeUpdate();
				     } else {
				    	 ps.close();
					      ps = conn.prepareStatement(
					       "update profile_images set image=? where e_mail_id=?", Statement.RETURN_GENERATED_KEYS);
					      if (inputStream != null) {
					       ps.setBlob(1, inputStream);
					      }
					      ps.setString(2, eMailId);
					      ps.executeUpdate();
				     }
			    }
			
			    if (rs1 != null) {
			     status = true;
			    } else {
			     status = false;
			    }
			   } else {
			    status = false;
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
				   if (rs1 != null) {
					    try {
					     rs1.close();
					    } catch (SQLException e) {
					     e.printStackTrace();
					    }
				   }	
			  }
		  return status;
	 }
	
	 public static boolean updateFacultyProfileDetails(String firstName, String middleName,
	  String lastName, String eMailId, String dob,
		  String dept, Part filePart) {
		 java.sql.Date sqlDateDob = null;
 		
	 		if((dob.equals("0000-00-00")) || (dob.equals("null-null-null")) ) {
	 			sqlDateDob = null;
	 		} else {
	 			sqlDateDob = java.sql.Date.valueOf(dob);
	 		}
		 
		  boolean status = false;
		  Connection conn = null;
		  PreparedStatement ps = null;
		  rs1 = null;
		  rs2 = null;
		  rs3 = null;
		
		  final String DB_URL = "jdbc:mysql://127.0.0.1:3306/itubuzz";
		  String driver = "com.mysql.jdbc.Driver";
		  String userName = "root";
		  String password = "root";
		  InputStream inputStream = null;
		  if (filePart != null) {
			   // prints out some information for debugging
			   System.out.println(filePart.getName());
			   System.out.println(filePart.getSize());
			   System.out.println(filePart.getContentType());
			
			   // obtains input stream of the upload file
			   try {
			    inputStream = filePart.getInputStream();
			   } catch (IOException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			   }
		  }
		  try {
			   Class.forName(driver).newInstance();
			   conn = DriverManager
			    .getConnection(DB_URL, userName, password);
			
			   if (firstName != null && lastName != null && eMailId != null && dept != null) {
				    ps = conn.prepareStatement(
				    		"update userLogin set first_name=?, middle_name=?, last_name=?, date_of_birth=?,department=? where e_mail_id=?", Statement.RETURN_GENERATED_KEYS);			
				    ps.setString(1, firstName);
				    ps.setString(2, middleName);
				    ps.setString(3, lastName);
				    ps.setDate(4, sqlDateDob);
				    ps.setString(5, dept);
				    ps.setString(6, eMailId);
				
				    ps.executeUpdate();
				
				    rs1 = ps.getGeneratedKeys();
				   
				    ps.close();
				    if (filePart.getSize() > 0) {
					     ps = conn.prepareStatement("select * from profile_images where e_mail_id=?", Statement.RETURN_GENERATED_KEYS);
					     ps.setString(1, eMailId);
					     rs2 = ps.executeQuery();
					     if (!rs2.next()) {
					    	 ps.close();
						      ps = conn.prepareStatement(
						       "insert into profile_images(e_mail_id,image) values (?,?)", Statement.RETURN_GENERATED_KEYS);
						      ps.setString(1, eMailId);
						      if (inputStream != null) {
						       ps.setBlob(2, inputStream);
						      }
						      ps.executeUpdate();
					     } else {
					    	 ps.close();
						      ps = conn.prepareStatement(
						       "update profile_images set image=? where e_mail_id=?", Statement.RETURN_GENERATED_KEYS);
						      if (inputStream != null) {
						       ps.setBlob(1, inputStream);
						      }
						      ps.setString(2, eMailId);
						      ps.executeUpdate();
					     }
				    }
				    if (rs1 != null) {
					     status = true;
					    } else {
					     status = false;
					    }
			   } else {
				   status = false;
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
			   if (rs1 != null) {
				    try {
				     rs1.close();
				    } catch (SQLException e) {
				     e.printStackTrace();
				    }
			   }	
		  }
		  return status;
	}
	 public static boolean updateAlumniProfileDetails(String firstName, String middleName,
	  String lastName, String eMailId, String dob,
	  String dept, String yop, Part filePart) {
		 java.sql.Date sqlDateDob = null;
 		java.sql.Date sqlDateYop = null;
 		
 		if((dob.equals("0000-00-00")) || (dob.equals("null-null-null")) ) {
 			sqlDateDob = null;
 		} else {
 			sqlDateDob = java.sql.Date.valueOf(dob);
 		}

 		if((yop.equals("0000-00-01")) || (yop.equals("null-null-01")) ) {
 			sqlDateYop = null;
 		} else {
 			sqlDateYop = java.sql.Date.valueOf(yop);
 		}
		 
		  boolean status = false;
		  Connection conn = null;
		  PreparedStatement ps = null;
		  rs1 = null;
		  rs2 = null;
		  rs3 = null;
		
		  final String DB_URL = "jdbc:mysql://127.0.0.1:3306/itubuzz";
		  String driver = "com.mysql.jdbc.Driver";
		  String userName = "root";
		  String password = "root";
		  InputStream inputStream = null;
		  if (filePart != null) {
			   // prints out some information for debugging
			   System.out.println(filePart.getName());
			   System.out.println(filePart.getSize());
			   System.out.println(filePart.getContentType());
			
			   // obtains input stream of the upload file
			   try {
				   inputStream = filePart.getInputStream();
			   } catch (IOException e) {
				   e.printStackTrace();
			   }
		  }
		  try {
			   Class.forName(driver).newInstance();
			   conn = DriverManager
			    .getConnection(DB_URL, userName, password);
			
			   if (firstName != null && lastName != null && eMailId != null && dept != null && yop != null) {
				    ps = conn.prepareStatement(
				     "update userLogin set first_name=?, middle_name=?, last_name=?, date_of_birth=?,department=?, year_of_passing=? where e_mail_id=?", Statement.RETURN_GENERATED_KEYS);				
				    ps.setString(1, firstName);
				    ps.setString(2, middleName);
				    ps.setString(3, lastName);
				    ps.setDate(4, sqlDateDob);
				    ps.setString(5, dept);
				    ps.setDate(6, sqlDateYop);
				    ps.setString(7, eMailId);
				
				    ps.executeUpdate();
				
				    rs1 = ps.getGeneratedKeys();
				   
				    ps.close();
				    if (filePart.getSize() > 0) {
					     ps = conn.prepareStatement("select * from profile_images where e_mail_id=?", Statement.RETURN_GENERATED_KEYS);
					     ps.setString(1, eMailId);
					     rs2 = ps.executeQuery();
					     if (!rs2.next()) {
					    	 ps.close();
						      ps = conn.prepareStatement(
						       "insert into profile_images(e_mail_id,image) values (?,?)", Statement.RETURN_GENERATED_KEYS);
						      ps.setString(1, eMailId);
						      if (inputStream != null) {
						       ps.setBlob(2, inputStream);
						      }
						      ps.executeUpdate();
					     } else {
					    	 ps.close();
						      ps = conn.prepareStatement(
						       "update profile_images set image=? where e_mail_id=?", Statement.RETURN_GENERATED_KEYS);
						      if (inputStream != null) {
						       ps.setBlob(1, inputStream);
						      }
						      ps.setString(2, eMailId);
						      ps.executeUpdate();
					     }
				    }
				    if (rs1 != null) {
					     status = true;
					    } else {
					     status = false;
					    }
			   } else {
				   status = false;
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
				   if (rs1 != null) {
					    try {
					     rs1.close();
					    } catch (SQLException e) {
					     e.printStackTrace();
					    }
				   }	
			  }
		  return status;
	 }
}