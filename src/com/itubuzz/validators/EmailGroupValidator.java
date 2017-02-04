package com.itubuzz.validators;

import java.util.ArrayList;
import java.util.List;
import com.itubuzz.dao.CreateGroupDAO;

public class EmailGroupValidator {

	public static List<String> validateEmailAddress(String emailIds){
		List<String> emailIdsList  = new ArrayList<String>();	
		
		String[] incomingEmailIds = emailIds.split(",");
		
		for(int i=0;i<incomingEmailIds.length;i++){
			boolean isEmailPresent = CreateGroupDAO.verifyIfEmailExists(incomingEmailIds[i]);
			// if email is not present add to the list
			if(!isEmailPresent){
				emailIdsList.add(incomingEmailIds[i]);
			}
		}
		
		return emailIdsList;
	}
	
}
