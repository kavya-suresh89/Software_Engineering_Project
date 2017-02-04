package com.itubuzz.webapp;
/**
 * International Technological University, San Jose
 * Login successfully 
 * Created date : 03/03/2016
 * @ Author  Kavya Suresh Babu
 */
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
 * Servlet implementation class Login
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<PostVO> all_post_data;
	private ArrayList<ReplyVO> all_reply_data;
	
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * this method allows us to get the email id and password entered by the user and compare it with valid database entries
	 * edited on : 03/07/2016
	 * @author Kavya
	 */
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        String eMail=null;
        String password = null;
        
        List<UserVO> user_list= new ArrayList<UserVO>();
        List<GroupVO> group_list = new ArrayList<GroupVO>();
        
        if(request.getParameter("user_name").isEmpty()){
        	request.setAttribute("errorMessageLogin", "E-mail id cannot be null!");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginAndRegister.jsp");
		    requestDispatcher.forward(request, response);
		    return;
        }
        else{
        eMail=request.getParameter("user_name");     
        }   

        if(request.getParameter("login_password").isEmpty()){
        	request.setAttribute("errorMessageLogin", "Password cannot be null!");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginAndRegister.jsp");
		    requestDispatcher.forward(request, response);
		    return;
        }
        else{
        	password=request.getParameter("login_password");
        }
        
        if(eMail !=null && password !=null){
        user_list = LoginDAO.validate(eMail, password);
        }
        all_post_data = new ArrayList<PostVO>();
        all_post_data = RetrievePostDAO.retrievePostedData();

        all_reply_data = new ArrayList<ReplyVO>();
        all_reply_data = RetrieveReplyDAO.retrieveRepliedData();

        
		if(session!=null)  {
        	   if(all_post_data != null){
        		   if(!(user_list.isEmpty())){
        		   for(UserVO user : user_list){
        			   session.setAttribute("name", user.getFirst_name());
        			   session.setAttribute("user_id", user.getUser_id());
        			   long loggedIn_userId = user.getUser_id();
        			   session.setAttribute("log_user_name", user.getFirst_name());
        			   /**
	            	    * @author Poorvisha
	            	    * change added for my profile page
	            	    * edited on : 03/26/2016
	            	    */
	            	   session.setAttribute("emailId", user.getE_mailId());
	            	   
	            	   /**
	            	    * @author kavya
	            	    * edited on : 04/19/2016
	            	    * change added for my registered groups
	            	    */
	            	   
	            	   group_list = MyGroupIdRetrieveDAO.retrievegroupIdforGroup(loggedIn_userId);
	            	   
		           }

        		   session.setAttribute("all_posts", all_post_data);
        	   
        		   if(all_reply_data != null){
        			   session.setAttribute("all_replies", all_reply_data);
        		   }
        		   session.setAttribute("all_groups", group_list);
        		   
    			   RequestDispatcher rd=request.getRequestDispatcher("HomePage.jsp");    
    			   rd.forward(request,response);

        	   }
        		   else{
            		   request.setAttribute("errorMessageLogin", "Enter valid login credentials!");
           			RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginAndRegister.jsp");
           		    requestDispatcher.forward(request, response);
           		    return;
            	   }
        	   }
        	   
		}
       else	{    
            RequestDispatcher rd=request.getRequestDispatcher("LoginAndRegister.jsp");    
            rd.include(request,response);    
        }
		out.close();
	}

	
	
}