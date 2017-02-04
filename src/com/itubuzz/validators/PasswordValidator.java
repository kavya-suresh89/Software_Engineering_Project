package com.itubuzz.validators;
/**
 * International Technological University, San Jose
 * Validating a password field class
 * created on : 03/04/2016
 * @author Kavya
 *
 */
public class PasswordValidator {

	/**
	 * This method is used to validate the password fields and returns true if all validations are passed
	 * @param newPassword
	 * @param reEnterPassword
	 * @param eMailId
	 * @return
	 * @author Kavya
	 * edited on : 03/07/2016
	 * edited on : 03/31/2016
	 */
	public static boolean validateUserPasswords(String newPassword, String reEnterPassword, String eMailId){
		boolean passVal = false;
		if(!(newPassword.isEmpty() && reEnterPassword.isEmpty() || newPassword == null || reEnterPassword == null )){
		// entered password should not be the same as the email id
		if(newPassword.equals(reEnterPassword)){
		 if(!(newPassword.equals(eMailId) && reEnterPassword.equals(eMailId)))	{
			 	// restricting password length between 8 to 20
			 	System.out.println("length of password : "+newPassword.length());
		    	if(newPassword.length()>=8  && newPassword.length()<=20){
		    			String upperCaseChars = "(.*[A-Z].*)";
		    			// validating that password contains at least one Upper case letter
		    			if (newPassword.matches(upperCaseChars) ){
		    				String lowerCaseChars = "(.*[a-z].*)";
		    				// validating that password contains at least one Lower case letter
		    				if (newPassword.matches(lowerCaseChars )){
		    					String numbers = "(.*[0-9].*)";
		    					// validating that password contains at least one number
		    					if (newPassword.matches(numbers )) {
		    						String specialChars = "(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)";
		    						//validating that password contains at least one of the above mentioned special characters
		    						if(newPassword.matches(specialChars )){
		    							passVal = true;
		    						}
		    					}
		    				}
		    			}
		    	}
		 }
		} 
		}
		return passVal;
	}
}
