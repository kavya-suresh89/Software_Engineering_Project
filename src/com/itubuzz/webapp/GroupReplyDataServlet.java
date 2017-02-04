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
 * Servlet implementation class ReplyDataServlet
 */
public class GroupReplyDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<GroupReplyVO> all_group_reply_data;
	private ArrayList<GroupPostVO> all_post_data;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupReplyDataServlet() {
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
		String group_id=request.getParameter("log_group_id");
		String group_name=request.getParameter("log_group_name");

				
		if(reply_text.length()>0){
			if(GroupReplyDAO.replydataCred(reply_id, reply_text, immparent_id, post_id, user_id, reply_name,group_id)){ 
				all_group_reply_data = new ArrayList<GroupReplyVO>();
				all_group_reply_data = RetrieveGroupReplyDAO.retrieveRepliedData(Integer.parseInt(group_id));
				request.setAttribute("all_group_replies", all_group_reply_data);
				all_post_data = RetrieveGroupPostDAO.retrieveGroupPostedData(Integer.parseInt(group_id));
				request.setAttribute("all_group_posts", all_post_data);
				RequestDispatcher rd=request.getRequestDispatcher("GroupPage.jsp?id="+group_id+"&name="+group_name);      
		        rd.forward(request,response);
	            }    
			}
			else {
  	    	  session.invalidate();
                 request.setAttribute("errorMessage", "Please type a reply");
                 RequestDispatcher rd = request.getRequestDispatcher("GroupPage.jsp?id="+group_id+"&name="+group_name);
                 rd.forward(request, response);
  	          }
		
		out.close(); 
	}		
}