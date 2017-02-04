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
 * Servlet implementation class QaforumQueServlet
 */
public class QaforumQueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionVO question_data = new QuestionVO();
	private ArrayList<QuestionVO> all_question_data = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QaforumQueServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean question = false;
		String question_text = null;
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter(); 
		HttpSession session = request.getSession(false);
		
		question_text = request.getParameter("question_text");
		String user_id = request.getParameter("log_user_id");
		String user_name = request.getParameter("log_user_name");
		System.out.println("in QA forum question servlet user name is :"+user_name);
		session.setAttribute("name", user_name);
		
		List<GroupVO> group_list = new ArrayList<GroupVO>();
	     
		  group_list = MyGroupIdRetrieveDAO.retrievegroupIdforGroup(Integer.parseInt(user_id));
		  
		  session.setAttribute("all_groups", group_list);
		
		
		
		if ( question_text.endsWith("?") ){ 
			String qkeywords = "(.*[WHAT, WHERE, WHY, WHICH, WHO, WHOSE, HOW, WHEN].*$)";
			
				if(question_text.toUpperCase().matches(qkeywords)) {					
					question = true;
				}
				else{
					request.setAttribute("errorMessageQuestion", "Questions should contain any of the following keywords :  'WHAT, WHERE, WHY, WHICH, WHO, WHOSE, HOW, WHEN'!");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("QAforum.jsp");
				    requestDispatcher.forward(request, response);
				    return;
				}
		}
		else{
			request.setAttribute("errorMessageQuestion", "Questions should End with a question mark (?)");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("QAforum.jsp");
		    requestDispatcher.forward(request, response);
		    return;
		}
		if (question) {
			if(question_text.length()>0){
						if(QaforumDAO.forumdataCred(question_text,user_id,user_name)){ 
							all_question_data = RetrieveQaforumDAO.retrieveQueData();
							// question_data = RetrieveQaforumDAO.retrieveQueData(question_text);
							// session.setAttribute("question_data", question_data);
							// RequestDispatcher rd=request.getRequestDispatcher("QuestionPage.jsp");  
							if(all_question_data != null){         
								   session.setAttribute("all_questions", all_question_data);
								   RequestDispatcher rd=request.getRequestDispatcher("QAforum.jsp");    
								   rd.forward(request,response);
							}
							
					         
				             }    
						}
		} else {
  	    	  session.invalidate();
                 request.setAttribute("errorMessage", "Please type a question");
                 RequestDispatcher rd = request.getRequestDispatcher("QAforum.jsp");
                 rd.forward(request, response);
  	          }
		
		out.close(); 
	}
}