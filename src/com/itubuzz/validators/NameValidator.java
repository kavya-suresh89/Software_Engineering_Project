package com.itubuzz.validators;
/**
 * International Technological University, San Jose
 * Validating a user entered name 
 * created on : 03/03/2016
 * @author Kavya
 *
 */
public class NameValidator {
	
	/**
	 * method : validateUserNames(String firstName,String middleName, String lastName)
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @return
	 * @author Kavya
	 */
      
	public static boolean validateUserNames(String firstName, String lastName){
		boolean nameVal = false;
		
		if(firstName != null  && lastName != null  ){
			String upperCaseChars = "(.*[A-Z].*)";
			String lowerCaseChars = "(.*[a-z].*)";
	        if ((firstName.matches(upperCaseChars) && lastName.matches(upperCaseChars)) || (firstName.matches(lowerCaseChars )  && lastName.matches(lowerCaseChars ))){
      
			        nameVal = true;
	        	
		    }
	    }
		else{
			System.err.println("First Name and Last Name cannot be null and Names should not contain Alphanumeric characters ! ");
		}
		
		return nameVal;
		
	}
		/**
		 * method : validateMiddleName(String middleName)
		 * @param middleName
		 * @return
		 * @author Kavya
		 * edited on : 03/17/2016
		 */
		public static boolean validateMiddleName(String middleName){
			boolean value = false;
			String upperCaseChars = "(.*[A-Z].*)";
			String lowerCaseChars = "(.*[a-z].*)";
			if(middleName.matches(lowerCaseChars) || middleName.matches(upperCaseChars)){
				value = true;
			}
			else {
				System.out.println("middle name is not valid or is empty!");
			}
			return value;
		}
}
