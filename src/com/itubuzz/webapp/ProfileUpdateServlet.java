package com.itubuzz.webapp;
/**
 * International Technological University, San Jose
 * To Update a user profile successfully 
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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.itubuzz.dao.FetchProfileDetailsDAO;
import com.itubuzz.dao.MyGroupIdRetrieveDAO;
import com.itubuzz.dao.RetrieveSearchDAO;
import com.itubuzz.dao.UpdateProfileDAO;
import com.itubuzz.valueobjects.GroupVO;
import com.itubuzz.valueobjects.PostVO;
import com.itubuzz.valueobjects.QuestionVO;
import com.itubuzz.valueobjects.ReplyVO;
import com.itubuzz.valueobjects.SearchPostVO;

/**
 * Servlet implementation class ProfileUpdateServlet
 */
@MultipartConfig
public class ProfileUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SLASH = "/";
	private SearchPostVO search_post_results;
	private ArrayList<QuestionVO> search_question_results;
	private ArrayList<PostVO> all_post_data;
	private ArrayList<ReplyVO> all_reply_data;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

    public ProfileUpdateServlet() {
        super();
       
    	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Edited on : 03/18/2016
	 * @author Poorvisha Muthusamy
	 */
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String name = (String) session.getAttribute("user_name_login");		
		request.setAttribute("name", name);
		
		if(session != null && null != session.getAttribute("emailId")) {
			String eMailId = (String) session.getAttribute("emailId");
	        String userType = (String) session.getAttribute("role");
			String role = null;		
			String firstName = request.getParameter("first_name");
	        String middleName = request.getParameter("middle_name");
	        String lastName = request.getParameter("last_name");
	        eMailId = eMailId.replace(SLASH, "");
	        String month = request.getParameter("month");
	        String day = request.getParameter("day_of_month");
	        String year = request.getParameter("birthday_year");
	        String date = year+"-"+month+"-"+day;
	        String dept = request.getParameter("department");
	        String sem = request.getParameter("semester");
	        String month_of_passing = request.getParameter("month_of_passing");
	        String year_of_passing = request.getParameter("year_of_passing");
	        String passYear = year_of_passing+"-"+month_of_passing+"-"+"01";
	        System.out.println("birth date is :"+date);
	        System.out.println("radio button selected is : "+userType);
	        System.out.println("year of passing is :"+passYear);
	        Part filePart = request.getPart("photo");
	        
	      //  HttpSession session = request.getSession(false); 
			
	        /*if(userType.equalsIgnoreCase("student")){
	        	role = "student";
	        }
	        else if (userType.equalsIgnoreCase("alumni")){
	        	role = "Alumni";
	        }
	        else if (userType.equalsIgnoreCase("faculty")){
	        	role = "Faculty";
	        }*/
	        role = userType;
	        Map < String, String > user = null;
	        
	        if(role.equalsIgnoreCase("student")){
	        	if(UpdateProfileDAO.updateStudentProfileDetails(firstName,middleName,lastName,eMailId,date,dept,sem,filePart))
	    		{
	        		if(FetchProfileDetailsDAO.fetchProfile(eMailId)){
	        		   user = FetchProfileDetailsDAO.UserDetails();
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
	        		}
	    		}
	        }else if(role.equalsIgnoreCase("Alumni")){
	        	if(UpdateProfileDAO.updateAlumniProfileDetails(firstName,middleName,lastName,eMailId,date,dept,passYear,filePart))
	    		{
	        		if(FetchProfileDetailsDAO.fetchProfile(eMailId)){
		        		   user = FetchProfileDetailsDAO.UserDetails();
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
	        		 
	        		}
	    		}
	        }else if(role.equalsIgnoreCase("Faculty")){
	        	if(UpdateProfileDAO.updateFacultyProfileDetails(firstName,middleName,lastName,eMailId,date,dept,filePart))
	    		{
	        		if(FetchProfileDetailsDAO.fetchProfile(eMailId)){
		        		   user = FetchProfileDetailsDAO.UserDetails();
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
	        		 
	        		}
	    		}
	        }
		
	       Integer userid = (Integer) session.getAttribute("user_id");
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
			List<GroupVO> group_list = new ArrayList<GroupVO>();
		     
			  group_list = MyGroupIdRetrieveDAO.retrievegroupIdforGroup(userid);
			  
			  session.setAttribute("all_groups", group_list);

		   RequestDispatcher rd=request.getRequestDispatcher("ViewProfile.jsp");    
		   rd.forward(request,response);
		   
		} else {
			RequestDispatcher rd=request.getRequestDispatcher("HomePage.jsp");    
            rd.forward(request,response);
            
		}
		out.close();
      }
        
	}	