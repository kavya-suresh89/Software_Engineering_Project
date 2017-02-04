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
 * Servlet implementation class PostDataServlet
 */
public class PostDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<PostVO> all_post_data;
	private ArrayList<ReplyVO> all_reply_data;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostDataServlet() {
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
		
		String post_text = null;
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter(); 
		HttpSession session = request.getSession(false); 
		post_text = request.getParameter("post_text");	
		String user_id = request.getParameter("log_user_id");
		String post_name=request.getParameter("log_user_name");
		String name = (String) session.getAttribute("name");
		  request.setAttribute("name", name);
		  
		  List<GroupVO> group_list = new ArrayList<GroupVO>();
		     
		  group_list = MyGroupIdRetrieveDAO.retrievegroupIdforGroup(Integer.parseInt(user_id));
		  
		  session.setAttribute("all_groups", group_list);
		
		if(post_text.length()>0){
			if(PostDAO.postdataCred(post_text,user_id,post_name)){ 
				 all_post_data = new ArrayList<PostVO>();
				 all_post_data = RetrievePostDAO.retrievePostedData();
				 all_reply_data = new ArrayList<ReplyVO>();
				 all_reply_data = RetrieveReplyDAO.retrieveRepliedData();
				 session.setAttribute("all_posts", all_post_data);
				 session.setAttribute("all_replies", all_reply_data);
				 System.out.println("In Qaforum data" + all_post_data.toString());
				 RequestDispatcher rd=request.getRequestDispatcher("HomePage.jsp");      
		         rd.forward(request,response);
	             }    
			}
			else {
  	    	  session.invalidate();
                 request.setAttribute("errorMessage", "Please type a post");
                 RequestDispatcher rd = request.getRequestDispatcher("HomePage.jsp");
                 rd.forward(request, response);
  	          }
		
		out.close(); 
	}
}