package com.itubuzz.validators;
/**
 * International Technological University, San Jose
 * created on : 03/05/2016
 * @author Kavya
 */
public class EMailIdValidator {
    
	/**
	 * method : validateUserEmailId(String eMailId, String userType)
	 * @param eMailId
	 * @param userType
	 * @return boolean
	 * @author Kavya
	 */
	
	public static boolean validateUserEmailIdStudent(String eMailId, String userType){
		boolean eMailVal = false;
		if(!(eMailId.isEmpty() && eMailId == null)){
		if((!(eMailId.equalsIgnoreCase("sample@students.itu.edu")) && userType.equalsIgnoreCase("currentStudent")  )){
		 if(eMailId.contains("@") && eMailId.endsWith("students.itu.edu")){
	
		String lowerCaseChars = "(.*[a-z].*)";
		String numbers = "(.*[0-9].*)";
		boolean matchesSpecialChar = validateEmailSpecialCharacters(eMailId);
		boolean matchesNumbers = eMailId.matches(numbers );
                 if(eMailId.toLowerCase().matches(lowerCaseChars ) && matchesSpecialChar || matchesNumbers){
					
					eMailVal = true;
					}
				}
                 
	    }
		}
		
		return eMailVal;
	}
	
	public static boolean validateUserEmailIdFaculty(String eMailId, String userType){
		boolean eMailVal = false;
		if(!(eMailId.isEmpty() || eMailId == null)){
		if((!(eMailId.equalsIgnoreCase("sample@itu.edu")) && userType.equalsIgnoreCase("faculty")  )){
		if(eMailId.contains("@") && eMailId.endsWith("itu.edu")){
		
		String lowerCaseChars = "(.*[a-z].*)";
		String numbers = "(.*[0-9].*)";
		boolean matchesSpecialChar = validateEmailSpecialCharacters(eMailId);
		boolean matchesNumbers = eMailId.matches(numbers );
                 if(eMailId.toLowerCase().matches(lowerCaseChars ) && matchesSpecialChar || matchesNumbers){
					
					eMailVal = true;
					}
				}
                 
	         }
		}	
		return eMailVal;
	}
	
	public static boolean validateUserEmailIdAlumni(String emailId, String userType){
		
	boolean value = false;
	if(!(emailId.isEmpty() || emailId == null)){
	if((!(emailId.equalsIgnoreCase("sample@example.com"))) && userType.equalsIgnoreCase("alumni")){
		String  values = "(.*[.com,.org,.net,.int,.edu,.gov,.mil,.co,.in])";
		
		String lowerCaseChars = "(.*[a-z].*)";
		String numbers = "(.*[0-9].*) ";
		if(emailId.matches(values) &&  emailId.toLowerCase().matches(lowerCaseChars) && validateEmailSpecialCharacters(emailId) || emailId.matches(numbers)){
				value = true;
			}
      }
	}
	return value;
	}
	
	private static boolean validateEmailSpecialCharacters(String emailId){
		boolean message = false;
		String specialChars = "(.*[,~,!,#,$,%,^,&,*,(,),-,=,+,[,{,],},|,;,:,<,>,/,?].*$)";
		  if(emailId.matches(specialChars)){
			  message = false;
		  }
		  else{
			  message=true;
		  }
		return message;
	}
}
