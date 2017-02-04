package com.itubuzz.webapp;
/**
 * International Technological University, San Jose 
 * @author poorvisha
 * created on : 03/18/2016
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/images/*")
public class FetchImageServlet extends HttpServlet {
 private static final long serialVersionUID = 1L;




 /**
  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
  * edited on : 03/18/2016
  * @author Poorvisha Muthusamy
  */

 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  // TODO Auto-generated method stub
	  response.setContentType("image/jpeg");
	  Connection conn = null;
	  PreparedStatement pst = null;
	  ResultSet resultSet = null;
	  String eMail = request.getParameter("user_name");
	  final String DB_URL = "jdbc:mysql://127.0.0.1:3306/itubuzz";
	  String driver = "com.mysql.jdbc.Driver";
	  String userName = "root";
	  String password = "root";
	  try {
		   eMail = request.getPathInfo().substring(1);
		   Class.forName(driver).newInstance();
		   conn = DriverManager
		    .getConnection(DB_URL, userName, password);
		   pst = conn
		    .prepareStatement("select * from profile_images where e_mail_id=?");
		   pst.setString(1, eMail);
		
		   resultSet = pst.executeQuery();
		   if (resultSet.next()) {
			    byte[] content = resultSet.getBytes("image");
			    response.setContentType("image/jpeg");
			    response.setContentLength(content.length);
			    response.getOutputStream().write(content);
		   } else {
			   response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
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
		   if (resultSet != null) {
			    try {
			    	resultSet.close();
			    } catch (SQLException e) {
			    	e.printStackTrace();
			    }
		   }
	  }	
	}
}