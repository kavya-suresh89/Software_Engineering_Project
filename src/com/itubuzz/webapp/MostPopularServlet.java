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

import com.itubuzz.dao.MostPopularPostsAndRepliesDAO;
import com.itubuzz.dao.MyGroupIdRetrieveDAO;
import com.itubuzz.dao.RetrieveUserDetailsbyIdDAO;
import com.itubuzz.valueobjects.GroupVO;
import com.itubuzz.valueobjects.PostVO;
import com.itubuzz.valueobjects.ReplyVO;
import com.itubuzz.valueobjects.UserVO;

/**
 * Servlet implementation class MostPopularServlet
 */
public class MostPopularServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<PostVO> popular_post_data;
	private ArrayList<ReplyVO> popular_reply_data;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostPopularServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
System.out.println("inside most loop servlet");
		
		
		response.setContentType("text/html");
		  PrintWriter out = response.getWriter();
		  HttpSession session = request.getSession(true); 
		  RequestDispatcher rd = null;
		  String userid = request.getParameter("log_user_id");
		  
		  System.out.println("the id value passed from home page jsp is : "+userid);
		  
		  List<UserVO> user_list= new ArrayList<UserVO>();
		  
		  user_list = RetrieveUserDetailsbyIdDAO.retrieveUserDetails(userid);
		
		  popular_post_data = new ArrayList<PostVO>();
		  popular_post_data = MostPopularPostsAndRepliesDAO.retrieveMostPopular();

        popular_reply_data = new ArrayList<ReplyVO>();
        popular_reply_data = MostPopularPostsAndRepliesDAO.retrieveMostPopularReplies(popular_post_data);

        List<GroupVO> group_list = new ArrayList<GroupVO>();
	     
		  group_list = MyGroupIdRetrieveDAO.retrievegroupIdforGroup(Integer.parseInt(userid));
		  
		  session.setAttribute("all_groups", group_list);
		
        	   if(popular_post_data != null){
        		   
        		   for(UserVO user : user_list){
        			   session.setAttribute("name", user.getFirst_name());
        			   session.setAttribute("user_id", user.getUser_id());
        			   session.setAttribute("log_user_name", user.getFirst_name());
        			   System.out.println("testing loop for home page name : "+user.getFirst_name());
        			   /**
	            	    * @author Poorvisha
	            	    * change added for my profile page
	            	    * edited on : 03/26/2016
	            	    */
	            	   session.setAttribute("emailId", user.getE_mailId());
		           }

        		   session.setAttribute("popular_posts", popular_post_data);
        	   
        		   if(popular_reply_data != null){
        			   session.setAttribute("popular_replies", popular_reply_data);
        		   }
        		   
    			    rd=request.getRequestDispatcher("MostPopular.jsp");    
    			   rd.forward(request,response);

        	   }
		
       else	{    
             rd=request.getRequestDispatcher("HomePage.jsp");    
            rd.include(request,response);    
        }
		out.close();
	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
