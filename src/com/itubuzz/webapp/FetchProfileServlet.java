package com.itubuzz.webapp;
/**
 * International Technological University, San Jose
 * Fetch Profile 
 * Created date : 03/18/2016
 * @ Author  Poorvisha Muthusamy
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itubuzz.dao.FetchProfileDetailsDAO;
import com.itubuzz.dao.MyGroupIdRetrieveDAO;
import com.itubuzz.dao.RetrieveSearchDAO;
import com.itubuzz.valueobjects.GroupVO;
import com.itubuzz.valueobjects.PostVO;
import com.itubuzz.valueobjects.QuestionVO;
import com.itubuzz.valueobjects.ReplyVO;
import com.itubuzz.valueobjects.SearchPostVO;

/**
 * Servlet implementation for fetching user profile details
 */
public class FetchProfileServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
		private SearchPostVO search_post_results;
		private ArrayList<QuestionVO> search_question_results;
		private ArrayList<PostVO> all_post_data;
		private ArrayList<ReplyVO> all_reply_data;

	 /**
	  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	  * edited on : 03/18/2016
	  * @author Poorvisha Muthusamy
	  */
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setContentType("text/html");
		  PrintWriter out = response.getWriter();
		  HttpSession session = request.getSession(false); 
		  RequestDispatcher rd = null;
		  List<GroupVO> group_list = new ArrayList<GroupVO>();
	        
		  Integer userid = (Integer) session.getAttribute("user_id");
		  group_list = MyGroupIdRetrieveDAO.retrievegroupIdforGroup(userid);
		  
		  session.setAttribute("all_groups", group_list);
		  
		  if(session != null && null != session.getAttribute("emailId")){
			  String eMail = (String) session.getAttribute("emailId");
			  String name = (String) session.getAttribute("name");
			  request.setAttribute("name", name);
			  Map < String, String > user = null;
						  
			  if (FetchProfileDetailsDAO.fetchProfile(eMail)) {
				   user = FetchProfileDetailsDAO.UserDetails();
				   Long user_id = Long.parseLong(user.get("user_id"));
				   session.setAttribute("logged_user_id", user_id);
				   session.setAttribute("firstName", user.get("firstName"));
				   session.setAttribute("middleName", user.get("middleName"));
				   session.setAttribute("lastName", user.get("lastName"));
				   session.setAttribute("eMailId", user.get("eMailId"));
				   session.setAttribute("sem", user.get("sem"));
				   session.setAttribute("dept", user.get("dept"));
				   session.setAttribute("role", user.get("role"));
				   session.setAttribute("dob_day", user.get("dob_day"));
				   session.setAttribute("dob_month", user.get("dob_month"));
				   session.setAttribute("dob_year", user.get("dob_year"));
				   session.setAttribute("yop_day", user.get("yop_day"));
				   session.setAttribute("yop_month", user.get("yop_month"));
				   session.setAttribute("yop_year", user.get("yop_year"));

				   // Added to get posts and question data posted by user 
				   search_post_results = RetrieveSearchDAO.retrieveYourPostQueData(userid);
				   search_question_results = RetrieveSearchDAO.retrieveYourQueData(userid);
					
				    if ( search_post_results != null || search_question_results != null ) {
					    all_post_data = search_post_results.search_plist;
					    all_reply_data = search_post_results.search_rlist;
						    if ( search_post_results != null ) { 
								session.setAttribute("all_posts", all_post_data);
								session.setAttribute("all_replies", all_reply_data);
						    }
						    if ( search_question_results != null ) {
	
								session.setAttribute("all_questions", search_question_results);
						    }
						}

				   rd = request.getRequestDispatcher("ViewProfile.jsp");
				   rd.forward(request, response);
			  } else {
				   rd = request.getRequestDispatcher("HomePage.jsp");
				   rd.include(request, response);
			  }
		  }else{
			  rd = request.getRequestDispatcher("LoginAndRegister.jsp");
			  rd.forward(request, response);
		  }
		  out.close();
	 }
	 
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setContentType("text/html");
		  PrintWriter out = response.getWriter();
		  HttpSession session = request.getSession(false); 
		  RequestDispatcher rd = null;
		  Integer userid = (Integer) session.getAttribute("user_id");

		  if(session != null && null != session.getAttribute("emailId")){
			  String eMail = (String) session.getAttribute("emailId");
			  String name = (String) session.getAttribute("name");
			  request.setAttribute("name", name);
			  Map < String, String > user = null;
						  
			  if (FetchProfileDetailsDAO.fetchProfile(eMail)) {
				   user = FetchProfileDetailsDAO.UserDetails();
				   Long user_id = Long.parseLong(user.get("user_id"));
				   session.setAttribute("logged_user_id", user_id);
				   session.setAttribute("firstName", user.get("firstName"));
				   session.setAttribute("middleName", user.get("middleName"));
				   session.setAttribute("lastName", user.get("lastName"));
				   session.setAttribute("eMailId", user.get("eMailId"));
				   session.setAttribute("sem", user.get("sem"));
				   session.setAttribute("dept", user.get("dept"));
				   session.setAttribute("role", user.get("role"));
				   session.setAttribute("dob_day", user.get("dob_day"));
				   session.setAttribute("dob_month", user.get("dob_month"));
				   session.setAttribute("dob_year", user.get("dob_year"));
				   session.setAttribute("yop_day", user.get("yop_day"));
				   session.setAttribute("yop_month", user.get("yop_month"));
				   session.setAttribute("yop_year", user.get("yop_year"));

				   // Added to get posts and question data posted by user 
				   search_post_results = RetrieveSearchDAO.retrieveYourPostQueData(userid);
				   search_question_results = RetrieveSearchDAO.retrieveYourQueData(userid);
					
				    if ( search_post_results != null || search_question_results != null ) {
					    all_post_data = search_post_results.search_plist;
					    all_reply_data = search_post_results.search_rlist;
						    if ( search_post_results != null ) { 
								session.setAttribute("all_posts", all_post_data);
								session.setAttribute("all_replies", all_reply_data);
						    }
						    if ( search_question_results != null ) {
	
								session.setAttribute("all_questions", search_question_results);
						    }
						}

				   rd = request.getRequestDispatcher("ViewProfile.jsp");
				   rd.forward(request, response);
			  } else {
				   rd = request.getRequestDispatcher("HomePage.jsp");
				   rd.include(request, response);
			  }
		  }else{
			  rd = request.getRequestDispatcher("LoginAndRegister.jsp");
			  rd.forward(request, response);
		  }
		  out.close();		 
	 }
}