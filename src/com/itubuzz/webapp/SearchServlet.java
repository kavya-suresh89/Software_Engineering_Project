package com.itubuzz.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itubuzz.dao.*;
import com.itubuzz.valueobjects.*;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SearchPostVO search_post_results;
	private ArrayList<QuestionVO> search_question_results;
	private ArrayList<PostVO> all_post_data;
	private ArrayList<ReplyVO> all_reply_data;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter(); 
		HttpSession session = request.getSession(false); 
		String searchtext = request.getParameter("search");
		String searchstring = "%"+searchtext.replace(" ", "%")+"%";
		
		if(searchtext.length()>0){
			String pagename = request.getParameter("page_name");
				if (pagename == "post") {
				     search_post_results = RetrieveSearchDAO.retrieveSearchedPostData(searchstring);
				     all_post_data = search_post_results.search_plist;
				     all_reply_data = search_post_results.search_rlist;
				     
					 session.setAttribute("all_posts", all_post_data);
					 session.setAttribute("all_replies", all_reply_data);
					 RequestDispatcher rd=request.getRequestDispatcher("HomePage.jsp");
					 rd.forward(request,response);
				}
				else if (pagename == "que") {
					 search_question_results = RetrieveSearchDAO.retrieveSearchedQueData(searchstring);
					 session.setAttribute("all_questions", search_question_results);
					 RequestDispatcher rd=request.getRequestDispatcher("QAforum.jsp");
					 rd.forward(request,response);
				}			      
			}
			else {
  	    	  session.invalidate();
                 request.setAttribute("errorMessage", "Not found");
                 RequestDispatcher rd = request.getRequestDispatcher("HomePage.jsp");
                 rd.forward(request, response);
  	          }
		
		out.close(); 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter(); 
		HttpSession session = request.getSession(false); 
		String searchtext = request.getParameter("search");
		String searchstring = "%"+searchtext.replace(" ", "%")+"%";
		
		if(searchtext.length()>0){
			String pagename = request.getParameter("page_name");
				if (pagename.equals("post")) {
				     search_post_results = RetrieveSearchDAO.retrieveSearchedPostData(searchstring);
				     all_post_data = search_post_results.search_plist;
				     all_reply_data = search_post_results.search_rlist;
				     
					 session.setAttribute("all_posts", all_post_data);
					 session.setAttribute("all_replies", all_reply_data);
					 RequestDispatcher rd=request.getRequestDispatcher("HomePage.jsp");
					 rd.forward(request,response);
				}
				else if (pagename.equals("que")) {
					 search_question_results = RetrieveSearchDAO.retrieveSearchedQueData(searchstring);
					 session.setAttribute("all_questions", search_question_results);
					 RequestDispatcher rd=request.getRequestDispatcher("QAforum.jsp");
					 rd.forward(request,response);
				}			      
			}
			else {
  	    	  session.invalidate();
                 request.setAttribute("errorMessage", "Not found");
                 RequestDispatcher rd = request.getRequestDispatcher("HomePage.jsp");
                 rd.forward(request, response);
  	          }
		
		out.close(); 
	}
}