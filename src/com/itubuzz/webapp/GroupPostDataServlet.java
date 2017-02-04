package com.itubuzz.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
public class GroupPostDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<GroupPostVO> all_post_data;
	private ArrayList<GroupReplyVO> all_reply_data;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupPostDataServlet() {
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
		String group_id=request.getParameter("log_group_id");
		String group_name=request.getParameter("log_group_name");
		
		String name = (String) session.getAttribute("name");
		  request.setAttribute("name", name);
		
		if(post_text.length()>0){
			if(GroupPostDAO.postdataCred(group_id,post_text,user_id,post_name)){ 
				 all_post_data = new ArrayList<GroupPostVO>();
				 all_post_data = RetrieveGroupPostDAO.retrieveGroupPostedData(Integer.parseInt(group_id));
				 request.setAttribute("all_group_posts", all_post_data);
				 all_reply_data = new ArrayList<GroupReplyVO>();
				 all_reply_data = RetrieveGroupReplyDAO.retrieveRepliedData(Integer.parseInt(group_id));
				 request.setAttribute("all_group_replies", all_reply_data);
				 
				 System.out.println("In Qaforum data" + all_post_data.toString());
				 RequestDispatcher rd=request.getRequestDispatcher("GroupPage.jsp?id="+group_id+"&name="+group_name);      
		         rd.forward(request,response);
	             }    
			}
			else {
  	    	  session.invalidate();
                 request.setAttribute("errorMessage", "Please type a post");
                 RequestDispatcher rd = request.getRequestDispatcher("GroupPage.jsp?id="+group_id+"&name="+group_name);
                 rd.forward(request, response);
  	          }
		
		out.close(); 
	}
}