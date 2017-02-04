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
 * Servlet implementation class ReplyDataServlet
 */
public class ReplyDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<ReplyVO> all_reply_data;
	private ArrayList<PostVO> all_post_data;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reply_text = null;
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter(); 
		HttpSession session = request.getSession(false);
		
		String reply_id = request.getParameter("reply_id");
		reply_text = request.getParameter("reply_text");
		String immparent_id = request.getParameter("immparent_id");
		String post_id = request.getParameter("log_post_id");
		String user_id = request.getParameter("log_user_id");
		String reply_name = request.getParameter("reply_user_name");
				
		List<GroupVO> group_list = new ArrayList<GroupVO>();
	     
		  group_list = MyGroupIdRetrieveDAO.retrievegroupIdforGroup(Integer.parseInt(user_id));
		  
		  session.setAttribute("all_groups", group_list);
		
		
		if(reply_text.length()>0){
			if(ReplyDAO.replydataCred(reply_id, reply_text, immparent_id, post_id, user_id, reply_name)){ 
				all_reply_data = new ArrayList<ReplyVO>();
				all_reply_data = RetrieveReplyDAO.retrieveRepliedData();
				all_post_data = new ArrayList<PostVO>();
				all_post_data = RetrievePostDAO.retrievePostedData();
				session.setAttribute("all_replies", all_reply_data);
				session.setAttribute("all_posts", all_post_data);
				RequestDispatcher rd=request.getRequestDispatcher("HomePage.jsp");      
		        rd.forward(request,response);
	            }    
			}
			else {
  	    	  session.invalidate();
                 request.setAttribute("errorMessage", "Please type a reply");
                 RequestDispatcher rd = request.getRequestDispatcher("HomePage.jsp");
                 rd.forward(request, response);
  	          }
		
		out.close(); 
	}		
}