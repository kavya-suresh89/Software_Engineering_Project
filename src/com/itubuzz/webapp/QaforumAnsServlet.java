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
 * Servlet implementation class QaforumAnsServlet
 */
public class QaforumAnsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<AnswerVO> all_answer_data = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QaforumAnsServlet() {
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
		// TODO Auto-generated method stub
		String answer_text = null;
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter(); 
		HttpSession session = request.getSession(false);
		
		String answer_id = request.getParameter("answer_id");
		answer_text = request.getParameter("answer_text");
		String immparent_id = request.getParameter("immparent_id");
		String question_id = request.getParameter("log_question_id");
		String user_id = request.getParameter("log_user_id");
		String user_name = request.getParameter("log_user_name");
		System.out.println("inside QA forum answer servlet user name is : "+user_name);
		session.setAttribute("name", user_name);
		if(answer_text.length()>0){
			if(QaforumDAO.forumdataCred(answer_id, answer_text, immparent_id, question_id, user_id, user_name)){ 
				all_answer_data = new ArrayList<AnswerVO>();
				all_answer_data = RetrieveQaforumDAO.retrieveAnsData();
				session.setAttribute("all_answers", all_answer_data);
				List<GroupVO> group_list = new ArrayList<GroupVO>();
			     
				  group_list = MyGroupIdRetrieveDAO.retrievegroupIdforGroup(Integer.parseInt(user_id));
				  
				  session.setAttribute("all_groups", group_list);
				
				RequestDispatcher rd=request.getRequestDispatcher("QuestionPage.jsp");      
		        rd.forward(request,response);
	            }    
			}
			else {
  	    	  session.invalidate();
                 request.setAttribute("errorMessage", "Please type a Answer");
                 RequestDispatcher rd = request.getRequestDispatcher("QAforum.jsp");
                 rd.forward(request, response);
  	          }
		
		out.close(); 
	}
}