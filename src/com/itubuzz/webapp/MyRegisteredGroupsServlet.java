package com.itubuzz.webapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itubuzz.dao.RetrieveGroupPostDAO;
import com.itubuzz.dao.RetrieveGroupReplyDAO;
import com.itubuzz.dao.RetrieveGroupUsersDAO;
import com.itubuzz.valueobjects.GroupPostVO;
import com.itubuzz.valueobjects.GroupReplyVO;
import com.itubuzz.valueobjects.GroupVO;

/**
 * Servlet implementation class MyRegisteredGroupsServlet
 */
public class MyRegisteredGroupsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyRegisteredGroupsServlet() {
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
		response.setContentType("text/html");
		  HttpSession session = request.getSession(false); 
		  RequestDispatcher rd = null;
		  List<GroupVO> group_list = new ArrayList<GroupVO>();
		  List<GroupPostVO> post_list=new ArrayList<GroupPostVO>();
		  List<GroupReplyVO> reply_list=new ArrayList<GroupReplyVO>();
		  String groupName = request.getParameter("group_name");
		  System.out.println("inside navigation to group page jsp name is : "+groupName);
		  String groupId = request.getParameter("group_id");
		  System.out.println("inside navigation to group page jsp id is : "+groupId);
		  String log_name = request.getParameter("user_name_login");
		  System.out.println("inside navigation to group page jsp user name is : "+log_name);
		  session.setAttribute("my_group_name",groupName);
		  session.setAttribute("User_name", log_name);
		  String user_id = session.getAttribute("user_id").toString();
		  System.out.println("inside navigation to group page jsp user id is : "+user_id);
		  session.setAttribute("User_id", user_id);
		  
		  group_list= RetrieveGroupUsersDAO.retrievegroupUsersforGroupId(groupId);
		  
		  post_list = RetrieveGroupPostDAO.retrieveGroupPostedData(Integer.parseInt(groupId));
		  
		  reply_list = RetrieveGroupReplyDAO.retrieveRepliedData(Integer.parseInt(groupId));
		  
		  if( group_list != null){
			  session.setAttribute("my_group_users", group_list);
		  }
		  else{
			  request.setAttribute("error_messageUser", "Unable to retrieve any users at the moment" );
		  }
		  
		  if( post_list != null){
			  session.setAttribute("all_group_posts",post_list);
		  }
		  
		  
		  if(reply_list != null){
			  session.setAttribute("all_group_replies", reply_list);
		  }
		  
		  rd=request.getRequestDispatcher("GeneralGroupHomePage.jsp?id="+groupId+"&name="+groupName);    
		   rd.forward(request,response);
		
	}
}
