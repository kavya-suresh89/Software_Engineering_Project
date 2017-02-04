package com.itubuzz.validators;
/**
 * International Technological University, San Jose
 * created on : 03/01/2016
 * @author Kavya
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidator {
	/**
	 * method : validateDob(String dob)
	 * @param dateToValidate
	 * @param dateFromat
	 * @return
	 * @author Kavya
	 * @throws ParseException 
	 * edited on : 03/31/2016
	 */
	
	public static boolean validateDob(String dob) throws ParseException{
		boolean value=false;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	Date currentDate = dateFormat.parse(dateFormat.format(new Date()));
    Date enteredDob = dateFormat.parse(dob);
    System.out.println("current date is : "+currentDate);
    System.out.println("entered date is : "+enteredDob);
		if(enteredDob.compareTo(currentDate) >= 0){
			value=false;
		}
		else{
			value = true;
		}
		return value;
	}
	
	
	public static boolean validateYop(String yop) throws ParseException{
		  boolean value = false;
		  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		  
		  Date currentDate = dateFormat.parse(dateFormat.format(new Date())); 
		  Date yearOfPass = dateFormat.parse(yop);
		  System.out.println("current date is : "+currentDate);
		  System.out.println("entered date is : "+yearOfPass);
		  if(yearOfPass.compareTo(currentDate) <= 0){
			  value = true;
		  }
		  else{
			  value = false;
		  }
		  return value;
		  
		}
	
}