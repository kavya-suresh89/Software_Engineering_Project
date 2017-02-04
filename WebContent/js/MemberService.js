function fetchExistingMembers(groupId){
	  jQuery.ajax({
	        url: '/ITUBuzz/rest/group/emailServiceRest.jsp?action=fetch&gid='+groupId,
	        type: 'GET',
	        contentType: 'application/json',
	        success: function parseResponse(data)
	        {
	        	var objArray = JSON.parse(data.data);
	        	var html = '';
	        	for(var i=0;i<objArray.length;i++){
	        		html += '<b style="color:#0000ff;font-size:14px;">'+objArray[i]+'</b>';
	        		html+='<br>';
	        	}
	        	
	        	jQuery('#membersDiv').html(html);
	        	jQuery('#count').html(data.count);
	        },
	        error: handleError	
	  });
}

function addNewMembers(){
	var memberEmailArray = [];
	var i=0;
	jQuery.each(jQuery('.textboxlist-bits li'),function(index,value){
		if(jQuery(this).text() !=''){
			memberEmailArray[i] = jQuery(this).text(); 
			i++;
		}
	});
	
	var params = "members="+memberEmailArray.toString();
	  jQuery.ajax({
	        url: '/ITUBuzz/rest/group/emailServiceRest.jsp?action=add&gid='+id+"&"+params,
	        type: 'POST',
	        data: params,
	        contentType: 'application/json',
	        success: function parseResponse(data)
	        {
	        	var objArray = JSON.parse(data.data);
	        	var html = '';
	        	for(var i=0;i<objArray.length;i++){
	        		html += '<a href="">'+objArray[i]+'</a>';
	        		html +='<br>';
	        	}
	        	
	        	jQuery('#membersDiv').html(html);
	        	jQuery('#count').html(data.count);
	        	clearMemberEmailFields();
	        },
	        error: handleError	
	  });
	
}

function clearMemberEmailFields(){
	jQuery.each(jQuery('.textboxlist-bits li'),function(index,value){
		if(jQuery(this).text() !=''){
			jQuery(this).text(''); 
			i++;
		}
	});
	
}