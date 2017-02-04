package com.itubuzz.webapp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
  
import java.io.PrintWriter;    
import javax.servlet.http.HttpSession;  
public class LogoutServlet extends HttpServlet {  
        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException {  
            response.setContentType("text/html");  
            PrintWriter out=response.getWriter();  
             
              
            HttpSession session=request.getSession(false);
            if(session!=null)
            	session.invalidate(); 
            request.getSession().removeAttribute("success_message"); 
           System.out.print("You are successfully logged out!");  
           request.getRequestDispatcher("LogOut.jsp").include(request, response);
            out.close();  
    }  
}  