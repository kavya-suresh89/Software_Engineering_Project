package com.itubuzz.webapp;
/**
 * International Technological University, San Jose
 * Register a user successfully 
 * Created date : 03/02/2016
 * @ Author  Kavya Suresh Babu
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itubuzz.dao.RegisterUserDAO;
import com.itubuzz.validators.DateValidator;
import com.itubuzz.validators.EMailIdValidator;
import com.itubuzz.validators.NameValidator;
import com.itubuzz.validators.PasswordValidator;
import com.itubuzz.valueobjects.UserVO;

/**
 * Servlet implementation class RegisterUserServlet
 */
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

    public RegisterUserServlet() {
        super();
       
    	}
        // TODO Auto-generated constructor stub
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @author Kavya
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}





	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * This method allows us to receive all the user input data and call all the required validations for the entered user values
	 * Edited on : 03/07/2016
	 * @author Kavya
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String dob = "";
		String passYear = "";
		String student_sem = "";
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter(); 
		HttpSession session = request.getSession(false); 
		UserVO user = new UserVO();
		String month =null;
		String day= null;
		String year = null;
		
		
		/**
		 * re-setting all fields on page re-load
		 * edited on : 04/02/2016
		 * @author Kavya
		 */
		 
		  request.getSession().removeAttribute("errorMessage");
		  request.getSession().removeAttribute("errorMessageNames");
		  request.getSession().removeAttribute("errorMessageMiddleName");
		  request.getSession().removeAttribute("errorMessageDate");
		  request.getSession().removeAttribute("errorMessageYOP");
		  request.getSession().removeAttribute("errorMessageSem");
		  request.getSession().removeAttribute("errorMessageMail");
		  request.getSession().removeAttribute("errorMessageStudentDept");
		  request.getSession().removeAttribute("errorMessageAlumniDept");
		  request.getSession().removeAttribute("errorMessageAlumniMail");
		  request.getSession().removeAttribute("errorMessageFacultyDept");
		  request.getSession().removeAttribute("errorMessageFacultyMail");
		  request.getSession().removeAttribute("errorMessageFacultyPassword");
		  request.getSession().removeAttribute("errorMessageAlumniPassword");
		  request.getSession().removeAttribute("errorMessageStudentPassword");
		  request.getSession().removeAttribute("success_message");
		
		/**
		 * @author Kavya
		 * edited on : 03/30/2016
		 * Fixing code for proper validations and error messages
		 */
		if(!(request.getParameter("first_name").isEmpty() && request.getParameter("last_name").isEmpty())){
			if(NameValidator.validateUserNames(request.getParameter("first_name"),request.getParameter("last_name"))){
				user.setFirst_name(request.getParameter("first_name"));
				user.setLast_name(request.getParameter("last_name"));
			}
			else{
				
				request.setAttribute("errorMessageNames", "Name should contain only Alphabets!");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginAndRegister.jsp");
			    requestDispatcher.forward(request, response);
			    return;
				
			}
		}
		else{
			
            request.setAttribute("errorMessageNames", "Name cannot be empty!");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginAndRegister.jsp");
		    requestDispatcher.forward(request, response);
		    return;
            
		}
			
        if(!(request.getParameter("middle_name").isEmpty())){
			if(NameValidator.validateMiddleName(request.getParameter("middle_name"))){
			 user.setMiddle_name(request.getParameter("middle_name"));
		   }
			else{
				
				request.setAttribute("errorMessageMiddleName", "Name should contain only Alphabets!");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginAndRegister.jsp");
			    requestDispatcher.forward(request, response);
			    return;
				
			}
	    }
        else{
        	user.setMiddle_name("");
        }
        
        /**
         * setting the parameters for date of birth and validating the same
         * edited by kavya on 04/24/2016 to fix issues 19 and 20
         */

        month = request.getParameter("month");

         day = request.getParameter("day_of_month");

         year = request.getParameter("birthday_year");
         
          if( month.equals("00") && day.equals("00") && (!year.equals("0000"))) {
         	request.setAttribute("errorMessageDate", "Please select a valid Day (or) Month (or) Year");
 			RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginAndRegister.jsp");
 		    requestDispatcher.forward(request, response);
 		    return;
         }
         
         else if( (!month.equals("00")) && day.equals("00") && year.equals("0000")) {
         	request.setAttribute("errorMessageDate", "Please select a valid Day (or) Month (or) Year");
 			RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginAndRegister.jsp");
 		    requestDispatcher.forward(request, response);
 		    return;
         }
         else if( month.equals("00") && (!day.equals("00")) && year.equals("0000")) {
         	request.setAttribute("errorMessageDate", "Please select a valid Day (or) Month (or) Year");
 			RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginAndRegister.jsp");
 		    requestDispatcher.forward(request, response);
 		    return;
         }
 
         else  if(!(month.equals("00") && day.equals("00") && year.equals("0000"))){
        	dob = year+"-"+month+"-"+day;
           try {
			if(DateValidator.validateDob(dob)){
			      user.setDob(dob);
			      }
			   else{
				    
					request.setAttribute("errorMessageDate", "Date cannot be same as or greater than current date  !\n Please enter a valid date !");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginAndRegister.jsp");
				    requestDispatcher.forward(request, response);
				    return;
				    
			   }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }  
       
        else{
        	user.setDob(dob);
        }
        
        /**
         * setting parameters for year of passing and validating the same
         */
        String month_of_passing = request.getParameter("month_of_passing");
        String year_of_passing = request.getParameter("year_of_passing");
        if(!(month_of_passing.equals("00") && year_of_passing.equals("0000"))){
        passYear = year_of_passing+"-"+month_of_passing+"-"+"01";
           try {
			if(DateValidator.validateYop(passYear)){
			user.setYearOfPassing(passYear);
			}
			else{
			    
				request.setAttribute("errorMessageYOP", "Year of passing should be less than the current date !");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginAndRegister.jsp");
			    requestDispatcher.forward(request, response);
			    return;
		   }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
        else{
        	user.setYearOfPassing(passYear);
        }
        
        
       
        
        	
        
        
        String name = user.getFirst_name();
        if(session!=null) { 
        request.setAttribute("name", name);
        }
        
        user.setUser_type(request.getParameter("register"));
        /**
         * edited on : 03/31/2016
         * updating code to include proper validations
         */
        if(user.getUser_type().equalsIgnoreCase("currentStudent")){
        		if(EMailIdValidator.validateUserEmailIdStudent(request.getParameter("e_mail_id_student"), user.getUser_type())){
        			if(PasswordValidator.validateUserPasswords(request.getParameter("new_password_student"), request.getParameter("re_enter_password_student"), request.getParameter("e_mail_id_student"))){	
        				if(!(request.getParameter("department_student").isEmpty())){
        					if(!(request.getParameter("semester_student").isEmpty())){
        					user.setE_mailId(request.getParameter("e_mail_id_student"));
        					user.setPasswordNew(request.getParameter("new_password_student"));
        					user.setPasswordReenter(request.getParameter("re_enter_password_student"));
        					user.setDept(request.getParameter("department_student"));
        					System.out.println(user.getDept());
        					student_sem = request.getParameter("semester_student");
        					user.setTrimester(student_sem);
        					user.setRole("student");
        				}
        					else{
        			        	
        						request.setAttribute("errorMessageSem", "Please select a trimester!");
        						RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginAndRegister.jsp");
        					    requestDispatcher.forward(request, response);
        					    return;
        					    
        			        }
        				}
        				else{
        					
        					request.setAttribute("errorMessageStudentDept", "Please select a valid Department!");
        					RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginAndRegister.jsp");
        				    requestDispatcher.forward(request, response);
        				    return;
        				    
        				}
        			}
        			else{
        				
                        request.setAttribute("errorMessageStudentPassword", "Password must be at least 8 characters with at least one special character, one uppercase letter,one lowercase letter and one number! Password and Confirm password should be the same ! Passwords cannot be the same as your email-id ");
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginAndRegister.jsp");
        			    requestDispatcher.forward(request, response);
        			    return;
        			    
            		 }
        		   }
        			
        		else {
        			
                    request.setAttribute("errorMessageMail", "Please enter a valid EMail Id");
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginAndRegister.jsp");
    			    requestDispatcher.forward(request, response);
    			    return;
    			    
        		}
        		
        }
        		
        	
        	
        
        else if (user.getUser_type().equalsIgnoreCase("alumni")){
        	if(EMailIdValidator.validateUserEmailIdAlumni(request.getParameter("e_mail_id_alumni"), user.getUser_type())){
        		if(PasswordValidator.validateUserPasswords(request.getParameter("new_password_alumni"), request.getParameter("re_enter_password_alumni"), request.getParameter("e_mail_id_alumni"))){	
        			if(!(request.getParameter("department_alumni").isEmpty())){
        				user.setE_mailId(request.getParameter("e_mail_id_alumni"));
        				user.setPasswordNew(request.getParameter("new_password_alumni"));
        				user.setPasswordReenter(request.getParameter("re_enter_password_alumni"));
        				user.setDept(request.getParameter("department_alumni"));
        				/**
        				 * edited on : 04/02/2016
        				 * @author Kavya
        				 * added empty field for Trimester
        				 */
        				user.setTrimester("");
        				user.setRole("alumni");
        			}
        		else{
        			
                    request.setAttribute("errorMessageAlumniDept", "Please select a valid Department !");
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginAndRegister.jsp");
    			    requestDispatcher.forward(request, response);
    			    return;
    			      
            	}
        	}
        		else{
        	    
                request.setAttribute("errorMessageAlumniPassword", "Password must be at least 8 characters with at least one special character, one uppercase letter,one lowercase letter and one number! Password and Confirm password should be the same ! Passwords cannot be the same as your email-id ");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginAndRegister.jsp");
			    requestDispatcher.forward(request, response);
			    return;
			    
    		 }
        	}
        		
        	else{
        		
                request.setAttribute("errorMessageAlumniMail", "Please enter a valid email-id!");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginAndRegister.jsp");
			    requestDispatcher.forward(request, response);
			    return;  
        	}
        }
         
         
         
         
        else if (user.getUser_type().equalsIgnoreCase("faculty")){
        	 if(EMailIdValidator.validateUserEmailIdFaculty(request.getParameter("e_mail_id_faculty"), user.getUser_type())){
        		if(PasswordValidator.validateUserPasswords(request.getParameter("new_password_faculty"), request.getParameter("re_enter_password_faculty"), request.getParameter("e_mail_id_faculty"))){
        			if(!(request.getParameter("department_faculty").isEmpty())){
        				user.setE_mailId(request.getParameter("e_mail_id_faculty"));
        				user.setPasswordNew(request.getParameter("new_password_faculty"));
        				user.setPasswordReenter(request.getParameter("re_enter_password_faculty"));
        				user.setDept(request.getParameter("department_faculty"));
        				System.out.println(user.getDept());
        				/**
        				 * edited on : 04/02/2016
        				 * @author Kavya
        				 * added empty field for trimester
        				 */
        				user.setTrimester("");
        				user.setRole("faculty");
        			}
        			else{
        				
        				request.setAttribute("errorMessageFacultyDept", "Please select a valid Department!");
        				RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginAndRegister.jsp");
        			    requestDispatcher.forward(request, response);
        			    return;
     			    }
        		}
        		else{
        			
                    request.setAttribute("errorMessageFacultyPassword", "Password must be at least 8 characters with at least one special character, one uppercase letter,one lowercase letter and one number! Password and Confirm password should be the same ! Passwords cannot be the same as your email-id ");
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginAndRegister.jsp");
    			    requestDispatcher.forward(request, response);
    			    return;
        		}
     			
     		}
     		else {
     			 
                 request.setAttribute("errorMessageFacultyMail", "Please enter a valid EMail Id");
                 RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginAndRegister.jsp");
 			     requestDispatcher.forward(request, response);
 			     return;
     		}
     		
     }
     		else {
     			 
                 request.setAttribute("errorMessage", "Unable to register at the moment! Please try again later !");
                 RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginAndRegister.jsp");
 			     requestDispatcher.forward(request, response);
 			     return;
     		}
        
        	  
        	      try {
					if(RegisterUserDAO.enterUserCredentials(user.getFirst_name(),user.getMiddle_name(),user.getLast_name(),user.getPasswordNew(),user.getE_mailId(),user.getDob(),user.getDept(),user.getTrimester(),user.getYearOfPassing(),user.getRole()))
						{
						  request.getSession().removeAttribute("errorMessage");
						  request.getSession().removeAttribute("errorMessageNames");
						  request.getSession().removeAttribute("errorMessageMiddleName");
						  request.getSession().removeAttribute("errorMessageDate");
						  request.getSession().removeAttribute("errorMessageYOP");
						  request.getSession().removeAttribute("errorMessageSem");
						  request.getSession().removeAttribute("errorMessageMail");
						  request.getSession().removeAttribute("errorMessageStudentDept");
						  request.getSession().removeAttribute("errorMessageAlumniDept");
						  request.getSession().removeAttribute("errorMessageAlumniMail");
						  request.getSession().removeAttribute("errorMessageFacultyDept");
						  request.getSession().removeAttribute("errorMessageFacultyMail");
						  request.getSession().removeAttribute("errorMessageFacultyPassword");
						  request.getSession().removeAttribute("errorMessageAlumniPassword");
						  request.getSession().removeAttribute("errorMessageStudentPassword");
						  request.setAttribute("success_message", "Successfully registered ! Please login with registered e-mail and password to continue");
					      RequestDispatcher rd=request.getRequestDispatcher("LoginAndRegister.jsp");    
					      rd.forward(request,response); 
						    
						}
					  else{
						   
					       request.setAttribute("errorMessage", "This e-mail id is already exists.Please register with a unique id!");
					       RequestDispatcher requestDispatcher = request.getRequestDispatcher("LoginAndRegister.jsp");
						   requestDispatcher.forward(request, response);
						   return;
					      }
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                  
        

        
        	
        out.close(); 
     }	            

}
