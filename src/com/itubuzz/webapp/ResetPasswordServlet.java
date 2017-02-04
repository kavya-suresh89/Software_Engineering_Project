package com.itubuzz.webapp;
/**
 * International Technological University, San Jose
 * @author Kavya
 * created on : 03/20/2016
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.itubuzz.dao.EmailandPasswordRetrieveDAO;
import com.itubuzz.dao.ResetPasswordDAO;
import com.itubuzz.validators.PasswordValidator;

/**
 * Servlet implementation class ResetPasswordServlet
 */
public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPasswordServlet() {
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
	 * @author Kavya
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String passwordMessage = null;
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter(); 
		String eMailId = request.getParameter("user_name");
		String new_reset_password = request.getParameter("new_reset_password");
		String reenter_new_reset_password = request.getParameter("reenter_new_reset_password");
		
		HttpSession session = request.getSession(false);  
		
		if(eMailId != null){
			if(EmailandPasswordRetrieveDAO.retrieveEMailForResetPassword(eMailId)){
				if(new_reset_password.equals(reenter_new_reset_password)){
					if(PasswordValidator.validateUserPasswords(new_reset_password, reenter_new_reset_password, eMailId)){
						if(EmailandPasswordRetrieveDAO.retrievePasswordForResetPassword(new_reset_password, eMailId)){
					 passwordMessage = ResetPasswordDAO.resetPassword(eMailId, new_reset_password, reenter_new_reset_password);
					 request.setAttribute("passwordSuccessMessage", passwordMessage);
					 System.out.println(passwordMessage);
					 RequestDispatcher rd=request.getRequestDispatcher("LoginAndRegister.jsp");    
			            rd.forward(request,response);
					}
					else{
					 session.invalidate();
	                 request.setAttribute("errorMessagePassword", "Password already used ! Please enter a new password ");
	                 RequestDispatcher rd = request.getRequestDispatcher("ResetPassword.jsp");
	                 rd.forward(request, response);
					}
				   }
					else{
						 session.invalidate();
		                 request.setAttribute("errorMessagePassword", "Passwords do not match ! Please enter valid password with the following specifications :   a) Password must not be the same as your Email-id.    b) Password be of minimum 8 characters and a maximum of 20 characters.   c) Password should contain atleast one upper case alphabet.   d) Password should contain atleast one lower case alphabet.   e) Password should contain atleast one number.   f) Password should contain atleast one special character");
		                 RequestDispatcher rd = request.getRequestDispatcher("ResetPassword.jsp");
		                 rd.forward(request, response);
						}
				}
				else {
					session.invalidate();
	                 request.setAttribute("errorMessagePassword", "New password and re-enter password don't match!");
	                 RequestDispatcher rd = request.getRequestDispatcher("ResetPassword.jsp");
	                 rd.forward(request, response);
				}
			}
			else {
				session.invalidate();
                 request.setAttribute("errorMessagePassword", "Entered e-mail id is not valid! Please enter your registered E-mail id !");
                 RequestDispatcher rd = request.getRequestDispatcher("ResetPassword.jsp");
                 rd.forward(request, response);
			}
		}
		else {
			session.invalidate();
             request.setAttribute("errorMessagePassword", " E-Mail id field cannot be empty !");
             RequestDispatcher rd = request.getRequestDispatcher("ResetPassword.jsp");
             rd.forward(request, response);
		}
		 out.close(); 
	}

}