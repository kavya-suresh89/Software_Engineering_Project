package com.itubuzz.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itubuzz.dao.*;
import com.itubuzz.valueobjects.*;

/**
 * Servlet implementation class GetforumServlet
 */

public class GetforumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<QuestionVO> all_question_data = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetforumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        String name = null;
        String loggedInUserId = request.getParameter("loggedInUser");
        if(session.getAttribute("log_user_name")!=null){
        name =  session.getAttribute("log_user_name").toString();
        }
        System.out.println("setting name in get forum servlet forum for navigation : "+name);
        System.out.println("setting user id  in get forum servlet forum for navigation : "+loggedInUserId);
		 session.setAttribute("log_user_name", name);
		 session.setAttribute("logged_user_id", loggedInUserId);
        all_question_data = new ArrayList<QuestionVO>();
        all_question_data = RetrieveQaforumDAO.retrieveQueData();
		
        List<GroupVO> group_list = new ArrayList<GroupVO>();
	     if(loggedInUserId == null){
		  group_list = MyGroupIdRetrieveDAO.retrievegroupIdforGroup(Integer.parseInt(request.getParameter("log_user_id")));
	     }
	     else{
	    	 group_list = MyGroupIdRetrieveDAO.retrievegroupIdforGroup(Integer.parseInt(loggedInUserId));
	     }
		  session.setAttribute("all_groups", group_list);
        
		if(all_question_data != null){         
			   session.setAttribute("all_questions", all_question_data);
			   
			  
			   session.setAttribute("name", name);
			   System.out.println("the name for answer forum to be set in qa forum : "+session.getAttribute("name"));
			   RequestDispatcher rd=request.getRequestDispatcher("QAforum.jsp");    
			   rd.forward(request,response);

		   }
		out.close();
	}
   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}