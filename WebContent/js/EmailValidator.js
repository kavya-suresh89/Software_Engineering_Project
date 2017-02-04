var memberEmailArray;
function validateEmailAddress(async,action){
	var retVal = checkEmailAddressFormat(async);
	if(retVal == true){
		verifyIfEmailRegistered(async,action);
	}
}

function checkEmailAddressFormat(async) {
	var i=0;
	memberEmailArray = [];
	jQuery.each(jQuery('.textboxlist-bits li'),function(index,value){
		if(jQuery(this).text() !=''){
			memberEmailArray[i] = jQuery(this).text(); 
			i++;
		}
	});

	var errMsg = "";
    var retVal = true;
  
    if(memberEmailArray.length == 0 || memberEmailArray[0] == '')
    {
      errMsg = "Invalid Member Email Address.";
      retVal = false;
    }
    else
    {
      for(var i=0;i<memberEmailArray.length;i++)
      {
        if(!isValidEmailAddress(memberEmailArray[i]))
        {
          errMsg = "One of the Members Email Address format is invalid. Please correct.";
          retVal = false;
        }
      }  
    }
    
    if(!async){
    	jQuery("#emailerrmsg").html(errMsg);
    }else{
    	jQuery("#emailerrmsg2").html(errMsg);
    }
  
    return retVal;
}

function isValidEmailAddress(emailAddress) {
  var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
  
  if(emailAddress != 0)
  {
    return pattern.test(emailAddress);
  }
  else
  {
    return false;
  }
}

function verifyIfEmailRegistered(async,action)
{
  var params = "emailIds=";
  for(var i=0; i<memberEmailArray.length;i++){
	  if(i== (memberEmailArray.length-1)){
		  params = params + memberEmailArray[i];
		  break;
	  }
	  
	  params = params + memberEmailArray[i] + ",";  
  }
  
  jQuery.ajax({
        url: 'rest/group/emailValidator.jsp?'+params,
        type: 'POST',
        data: params,
        contentType: 'application/json',
        success: function parseResponse(data)
        {
        	var objArray = JSON.parse(data.data);
        	var html = '';
        	if(objArray.length !=0){
        		html = "The following emails don't exist. Please register"
	        	for(var i=0;i<objArray.length;i++){
	        		html += objArray[i];
	        	}
        		
        	    if(!async){
        	    	jQuery("#emailerrmsg").html(errMsg);
        	    }else{
        	    	jQuery("#emailerrmsg2").html(errMsg);
        	    }
        	    return false;
        	}

        	if(!async){
        		jQuery("#form_tags_input_2").val(memberEmailArray.toString());
        	}else{
        		jQuery("#form_tags_input_3").val(memberEmailArray.toString());
        	}
        	
        	if(!async){
        		jQuery("#createGroupForm").submit();
        	}else if(async && action=='addMembers'){
        		addNewMembers();
        	}
        	
        	try{
        		jQuery("#createGroupContainer").overlay().close();
        	}catch(error){
        		// do nothing...
        	}
        },
        error: handleError	
  });
}


function handleError(xhr)
{
   alert(xhr.responseText);
}
