<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.ArrayList,java.util.Iterator,com.itubuzz.valueobjects.*" %>
        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
<meta charset="utf-8" />
  	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
  	<meta name="viewport" content="width=device-width, initial-scale=1" />

	<meta http-equiv="cache-control" content="max-age=0" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
	<meta http-equiv="pragma" content="no-cache" />

    <title> ITUBuzz - Your ITU Social Network </title>
    
	<script src="js/jquery-1.12.2.min.js"></script>
	<script src="js/jquery.tools.min.js"></script>
	<script src="js/TextboxList.js"></script>
	<script src="js/GrowingInput.js" type="text/javascript"></script>
	<script src="dist/js/vendor/jquery.min.js" type="text/javascript" ></script>
	<script src="dist/js/paper.min.js" type="text/javascript" ></script>
	<script src="js/EmailValidator.js" type="text/javascript" charset="utf-8"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
	<link rel="stylesheet" href="css/overlay-apple.css" type="text/css" />
	<link rel="stylesheet" href="css/TextboxList.css" type="text/css" />
	<link rel="stylesheet" href="dist/css/paper.min.css" />
	<link rel="stylesheet" href="css/demo.css" />
	<link rel="stylesheet" href="css/timeline.css" />
	<link rel="stylesheet" href="style.css" type="text/css" />
	<link href='https://fonts.googleapis.com/css?family=Fredericka+the+Great' rel='stylesheet' type='text/css'>

	<style type="text/css" media="screen">
		.textboxlist { width: 400px; bgcolor: #a8a69f; }
	</style>

<%
	if(null!=request.getAttribute("errorMessage"))
    {
        out.println(request.getAttribute("errorMessage"));
    }
%>
	<script src="./js/jquery-1.12.2.min.js"></script>
    <script src="./js/jquery.tools.min.js"></script>
	<link rel="stylesheet" type="text/css" href="./css/overlay-apple1.css">
	<!--  <link rel="stylesheet" type="text/css" href="./css/TextboxList.css">-->
	<!-- required stylesheet for TextboxList -->
	<link rel="stylesheet" href="./css/TextboxList.css" type="text/css" media="screen" charset=ISO-8859-1>
	<!-- required for TextboxList -->
	<script src="./js/GrowingInput.js" type="text/javascript" charset="utf-8"></script>
	<script src="./js/TextboxList.js" type="text/javascript" charset="utf-8"></script>	
	<script src="./js/EmailValidator.js" type="text/javascript" charset="utf-8"></script>
	
<style type="text/css" media="screen">
		.textboxlist { width: 400px; bgcolor: #a8a69f; }
	</style>
	<script type="text/javascript">
$(function(){
	// With custom adding keys 
	var t2 = $('#form_tags_input_2').textboxlist({bitsOptions:{editable:{addKeys: [188]}}});
});

$(document).ready(function() {
	  $("a[rel]").overlay({effect: 'apple'});
      });

onload=function(){
	var e = document.getElementById("refreshed");
	if (e.value == "no")
		e.value="yes";
	else {
		e.value="no";
		location.reload();
	}
}

function displayMyGroup() {
    if (document.getElementById('myGroups').onclick) {
        document.getElementById('myGroup_1').style.display = 'block';
        }
}
                function nextChildid(parentid) {

                    var childs = document.getElementById(parentid).childNodes;
                    var childid = 0;
                    var lastChildid = 0;
                    var num = 0;

                    for (i = 0; i < childs.length; i++) {
                        if (childs[i].nodeName == "DIV") {
                            lastChildid = childs[i].id;
                            num = num + 1;
                        }
                    }

                      if (num == 0) {
                        var num = 1;
                        childid = parentid.toString().concat(num.toString());
                    } else {
                        childid = Number(lastChildid) + 1;
                    }

                    return childid;
                }

                function displayReply(parentid) {
                    var userid = document.getElementById("log_user_id").value;
                    var replyname = document.getElementById("log_user_name").value;
                    var parent = document.getElementById(parentid);
                    var num = 0;

                    var rootparent = document.getElementById(parentid);

                    while (rootparent.parentNode.getAttribute('id') != 'microposts') {
                        rootparent = rootparent.parentNode;
                        num = num + 1;
                    }

                    var leftspace = null;

                    if (num == 0)
                        leftspace = "margin-left: 0px;";
                    else
                        leftspace = "margin-left: 50px;";


                    var rootparentid = rootparent.getAttribute('id');

                    var childid = nextChildid(parentid);

                    var form = document.createElement("form");
                    form.setAttribute('action', 'ReplyDataServlet');
                    form.setAttribute('method', 'post');
                    form.setAttribute('role', 'form');
                    form.setAttribute('autocomplete', 'off');

                    var childiv = document.createElement("div");
                    childiv.setAttribute('id', childid);
                    childiv.setAttribute('class', 'form-group');
                    childiv.setAttribute('style', leftspace);

                    var node1 = document.createElement("textarea");
                    node1.setAttribute('name', 'reply_text');
                    node1.setAttribute('class', 'form-control');
                    node1.setAttribute('id', 'reply_text');
                    node1.setAttribute('autocomplete', 'off');
                    node1.setAttribute('placeholder', 'Comment...');

                    var node2 = document.createElement("br");

                    var node3 = document.createElement("button");
                    node3.setAttribute('class', 'btn btn-success btn-xs');
                    node3.setAttribute('id', 'nextreplybutton');
                    var node31 = document.createElement("i");
                    node31.setAttribute('class', 'fa fa-send-o');
                    node31.innerHTML = "Submit";
                    node3.appendChild(node31);

                    var node4 = document.createElement("input");
                    node4.setAttribute('type', 'hidden');
                    node4.setAttribute('name', 'immparent_id');
                    node4.setAttribute('id', 'immparent_id');
                    node4.setAttribute('value', parentid);

                    var node5 = document.createElement("input");
                    node5.setAttribute('type', 'hidden');
                    node5.setAttribute('name', 'log_post_id');
                    node5.setAttribute('id', 'log_post_id');
                    node5.setAttribute('value', rootparentid);

                    var node6 = document.createElement("input");
                    node6.setAttribute('type', 'hidden');
                    node6.setAttribute('name', 'reply_id');
                    node6.setAttribute('id', 'reply_id');
                    node6.setAttribute('value', childid);

                    var node7 = document.createElement("input");
                    node7.setAttribute('type', 'hidden');
                    node7.setAttribute('name', 'log_user_id');
                    node7.setAttribute('id', 'log_user_id');
                    node7.setAttribute('value', userid);

                    var node8 = document.createElement("input");
                    node8.setAttribute('type', 'hidden');
                    node8.setAttribute('name', 'reply_user_name');
                    node8.setAttribute('id', 'reply_user_name');
                    node8.setAttribute('value', replyname);

                    childiv.appendChild(node1);
                    childiv.appendChild(node2);
                    childiv.appendChild(node3);
                    childiv.appendChild(node4);
                    childiv.appendChild(node5);
                    childiv.appendChild(node6);
                    childiv.appendChild(node7);
                    childiv.appendChild(node8);

                    form.appendChild(childiv);
                    parent.appendChild(form);

                    node3.onclick = function() {
                        var parentid = node3.parentNode.getAttribute('id');
                        displayReply(parentid);
                    };
                }


                function displayReplyTree(rootparentid, childid, reply_text, immparentid, userid, replyname) {

                    var leftspace = null;
                    if (rootparentid == immparentid)
                        leftspace = "margin-left: 0px;";
                    else
                        leftspace = "margin-left: 50px;";


                    var parent = document.getElementById(immparentid);
                    var replyname = replyname;
                    var childiv = document.createElement("div");
                    childiv.setAttribute('id', childid);
                    childiv.setAttribute('style', leftspace);

                    var node1 = document.createElement("p");
                    node1.setAttribute('id', 'displayName');
                    node1.innerHTML = replyname + ":	";

                    var node2 = document.createElement("span");
                    node2.setAttribute('id', 'displayReply');
                    node2.innerHTML = reply_text;
                    node1.appendChild(node2);
                    node1.setAttribute('font-style', '6px');

                    var node3 = document.createElement("hr");

                    var node4 = document.createElement("a");
                    node4.setAttribute('id', 'nextreplybutton');
                    node4.setAttribute('href', '#');
                    var node41 = document.createElement("i");
                    node41.setAttribute('class', 'fa fa-retweet');
                    node41.innerHTML = "Comment";
                    node4.appendChild(node41);

                    childiv.appendChild(node1);
                    childiv.appendChild(node4);
                    childiv.appendChild(node3);
                    parent.appendChild(childiv);

                    node4.onclick = function() {
                        var parentid = node4.parentNode.getAttribute('id');
                        displayReply(parentid);
                    };

                }
         function currentUserTypeCheck() {
             if (document.getElementById('role_hidden').value == "student") {
                 document.getElementById('student1').style.display = 'block';
                 document.getElementById('alumni1').style.display = 'none';
                 document.getElementById('faculty1').style.display = 'none';
             }
             else if (document.getElementById('role_hidden').value == "alumni"){
             	document.getElementById('student1').style.display = 'none';
                 document.getElementById('alumni1').style.display = 'block';
                 document.getElementById('faculty1').style.display = 'none';
             }
             else if (document.getElementById('role_hidden').value == "faculty"){
             	document.getElementById('student1').style.display = 'none';
                 document.getElementById('alumni1').style.display = 'none';
                 document.getElementById('faculty1').style.display = 'block';
             }
             else{
             	document.getElementById('student1').style.display = 'none';
                 document.getElementById('alumni1').style.display = 'none';
                 document.getElementById('faculty1').style.display = 'none';
             }
         }
         
         
         function DisableUserfieldsOnViewProfile()
         {
        	var fields = ['first_name','middle_name','last_name','e_mail_id','day_of_month','month','birthday_year','department','semester','month_of_passing','year_of_passing'];
        	for(j=0;j<fields.length;j++){
	        	var n = document.getElementsByName(fields[j]);
	          	for(i = 0;i < n.length; ++i) {
	                 n[i].disabled = true;      
	             }
        	}
        	
        	var buttonfields = ['cancel','save'];
        	for(j=0;j<buttonfields.length;j++){
	        	var n = document.getElementsByName(buttonfields[j]);
	          	for(i = 0;i < n.length; i++) {
	                 n[i].style.visibility = "hidden";     
	             }
        	}
         	
         	
         }
         function EnableUserfieldsOnEditProfile()
         {
        	var fields = ['first_name','middle_name','last_name','day_of_month','month','birthday_year','department','semester','month_of_passing','year_of_passing','e_mail_id'];
         	for(j=0;j<fields.length;j++){
 	        	var n = document.getElementsByName(fields[j]);
 	          	for(i = 0;i < n.length; ++i) {
 	                 n[i].disabled = false;      
 	             }
         	}
         
         	var buttonfields = ['cancel','save','upload'];
        	for(j=0;j<buttonfields.length;j++){
	        	var n = document.getElementsByName(buttonfields[j]);
	          	for(i = 0;i < n.length; ++i) {
	                 n[i].style.visibility = "visible";     
	             }
        	}
         	var editbuttons = document.getElementsByName('edit');
         	for(i = 0;i < editbuttons.length; ++i) {
         		editbuttons[i].style.visibility = "hidden";       
             }
         	var email = document.getElementsByName('e_mail_id');
         	for(i = 0;i < email.length; ++i) {
         		email[i].disabled = true;      
             }
         	
         }
         function validateForm()
         {		
        	 var dateVar = "";
        	 var passingDateVar = "";
        	 if (document.getElementById('role_hidden').value == "student") {
        		var firstName = $('#student1 #first_name').val();
              	var lastName = $('#student1 #last_name').val();
              	var dept = $('#student1 #dept').val();
              	var sem = $('#student1 #sem').val();
              	var bday = $('#student1 #day_of_month').val();
              	var bmonth = $('#student1 #month').val();
         		var byear = $('#student1 #birthday_year').val();
         		dateVar = byear+"-"+bmonth+"-"+bday;
             }
             else if (document.getElementById('role_hidden').value == "alumni"){
            	var firstName = $('#alumni1 #first_name').val();
              	var lastName = $('#alumni1 #last_name').val();
              	var dept = $('#alumni1 #dept').val();
              	var yop_month = $('#alumni1 #month_of_passing').val();
          		var yop_year = $('#alumni1 #year_of_passing').val();
          		var bday = $('#alumni1 #day_of_month').val();
         		var bmonth = $('#alumni1 #month').val();
         		var byear = $('#alumni1 #birthday_year').val();
         		dateVar = byear+"-"+bmonth+"-"+bday;
         		passingDateVar = yop_year+"-"+ yop_month+"-"+ "28";
             }
             else if (document.getElementById('role_hidden').value == "faculty"){
            	var firstName = $('#faculty1 #first_name').val();
              	var lastName = $('#faculty1 #last_name').val()
              	var dept = $('#faculty1 #dept').val();
              	var bday = $('#faculty1 #day_of_month').val();
         		var bmonth = $('#faculty1 #month').val();
         		var byear = $('#faculty1 #birthday_year').val();
         		dateVar = byear+"-"+bmonth+"-"+bday;
             }
        	 var bdate=new Date(dateVar);
        	 var passdate = new Date(passingDateVar);
        	 console.log(bdate);
      		var currentDate = new Date();
         	var status = true;
         	document.getElementById('errors').innerHTML = "";
         	if (firstName == null || firstName == "") {
         		document.getElementById('errors').innerHTML +="*Please enter your First Name*"+"<br>";
         		status = false;
             }
         	if (lastName == null || lastName == "") {
         		document.getElementById('errors').innerHTML +="*Please enter your Last Name*"+"<br>";
         		status = false;
             }
         	if(dateVar != '0000-00-00' && isNaN(bdate)){
     			document.getElementById('errors').innerHTML +="*Please select a valid birth date*"+"<br>";
         		status = false;
     		}else if(dateVar != '0000-00-00' && bdate >= currentDate){
     			document.getElementById('errors').innerHTML +="*Please select Date of Birth less than Current Date*"+"<br>";
         		status = false;
             }
         	if (dept == null || dept == "" || dept=="Select") {
         		document.getElementById('errors').innerHTML +="*Please enter your Department*"+"<br>";
         		status = false;
             }
         	if(document.getElementById('role_hidden').value == "student")
         		{
         	if (sem == null || sem == "" || sem == "Select") {
         		document.getElementById('errors').innerHTML +="*Please enter Current Trimester*"+"<br>";
         		status = false;
             }
         		}
         	if(document.getElementById('role_hidden').value == "alumni")
         		{
         		if((yop_month == null || yop_month == "" ||yop_month == "00") ||(yop_year == null || yop_year == "" ||yop_year == "0000") )
         		{
             		document.getElementById('errors').innerHTML +="*Please enter Year Of Passing*"+"<br>";
             		status = false;
                 }
         		if(isNaN(passdate)){
         			document.getElementById('errors').innerHTML +="*Please select a valid year of passing *"+"<br>";
             		status = false;
         		}else if(passdate >= currentDate){
         			document.getElementById('errors').innerHTML +="*Please select year of passing less than Current Date*"+"<br>";
             		status = false;
         		}
         		}
         	return status;
         }
         
         function readURL(input) {
             if (input.files && input.files[0]) {
                 var reader = new FileReader();
         
                 reader.onload = function (e) {
                 	console.log(e.target.result);
                 	var files = document.getElementsByName('profilepic');
                	if (document.getElementById('role_hidden').value == "student") {
                        files[0].src=e.target.result;
                    }
                    else if (document.getElementById('role_hidden').value == "alumni"){
                    	files[1].src=e.target.result;
                    }
                    else if (document.getElementById('role_hidden').value == "faculty"){
                    	files[2].src=e.target.result;
                    }
                     //document.getElementById('profilepic').src=e.target.result;
                 };
         
                 reader.readAsDataURL(input.files[0]);
             }
         }
         
         function fileopen()
         {
        	var files = document.getElementsByName('photo');
        	if (document.getElementById('role_hidden').value == "student") {
                files[0].click();
            }
            else if (document.getElementById('role_hidden').value == "alumni"){
            	files[1].click();
            }
            else if (document.getElementById('role_hidden').value == "faculty"){
            	files[2].click();
            }
         }
         
         
         function selectedValues() {
         	
         	var dob_month = "<%=session.getAttribute("dob_month")%>";
         	var dob_day = "<%=session.getAttribute("dob_day")%>";
         	var dob_year = "<%=session.getAttribute("dob_year")%>";
         	var yop_month = "<%=session.getAttribute("yop_month")%>";
         	var yop_year = "<%=session.getAttribute("yop_year")%>";
         	var dept = "<%=session.getAttribute("dept")%>";
         	var sem = "<%=session.getAttribute("sem")%>";
         	var eMailId = "<%=session.getAttribute("eMailId")%>";
         	//var img = document.createElement('img');
         	//imgPath="${pageContext.request.contextPath}/images/${eMailId}";
         	
         	
         	if(document.getElementById('role_hidden').value == "student"){
         		$('#student1 #day_of_month').val(dob_day);
         		$('#student1 #month').val(dob_month);
         		$('#student1 #birthday_year').val(dob_year);
         		$('#student1 #dept').val(dept);
         		$('#student1 #sem').val(sem);
         		DisableUserfieldsOnViewProfile();
         		currentUserTypeCheck();
         		$('#student1 #eMailId').prop('disabled', true);
         		//document.getElementById('eMailId').disabled = true;
         		
         	}else if(document.getElementById('role_hidden').value == "alumni"){	
         		$('#alumni1 #day_of_month').val(dob_day);
         		$('#alumni1 #month').val(dob_month);
         		$('#alumni1 #birthday_year').val(dob_year);
         		$('#alumni1 #dept').val(dept);
         		$('#alumni1 #month_of_passing').val(yop_month);
         		$('#alumni1 #year_of_passing').val(yop_year);
         		currentUserTypeCheck();
         		$('#alumni1 #eMailId').prop('disabled', true);
         		DisableUserfieldsOnViewProfile();
         	}else if(document.getElementById('role_hidden').value == "faculty"){
         		$('#faculty1 #day_of_month').val(dob_day);
         		$('#faculty1 #month').val(dob_month);
         		$('#faculty1 #birthday_year').val(dob_year);
         		$('#faculty1 #dept').val(dept);
         		DisableUserfieldsOnViewProfile();
         		currentUserTypeCheck();
         		$('#faculty1 #eMailId').prop('disabled', true);
         		
         	}
         	
         	//document.body.appendChild(img);
         	
         }
         
         
         function ClearFieldsOnCancelClick()
         {
        	document.location = "${pageContext.request.contextPath}/FetchProfileServlet";
         }
         
      </script>
   </head>
   <body class="viewprofile">
<input type="hidden" id="refreshed" value="no">

  <nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
      <div class="navbar-header">
        <h1 style="color:white; ">ITUBuzz
        <img class="logoimage" src="marca.png"  height=30px">
        </h1>
      </div>
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">      
	       <ul class="nav navbar-nav navbar-right">
	        <li>
				<a href="PostLoopServlet?log_user_id=<%=session.getAttribute("user_id")%>" id="postloop" class="btn btn-xs">
					<i class="fa fa-home" style="font-size:14px;color:white"> Home</i>
					<input type="hidden" name="home_name" id="home_name"  value="<%=request.getAttribute("home_name")%>">
				</a>
			  </li>
			  <li>
			  	<a href="LoginAndRegister.jsp" class="btn btn-xs" id="logout">
			  		<i class="fa fa-sign-out" style="font-size:14px;color:white"> SignOut</i>
			  	</a>			  	 
			  </li>    
		    </ul>
	        <form class="navbar-form navbar-right" role="search" id="search_form" action="SearchServlet" method="post">
	           <div class="input-group add-on">
 				    <input type="text" class="form-control" placeholder="Search" name="search" id="search">
      				<div class="input-group-btn">
        				<button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
      				</div>
    			</div>
    			<input type="hidden" name="page_name" value="post">
 	        </form>
        </div>
      </div>
  </nav>

  <div class="container">
    <div class="row">
      <div class="col-md-3 col-md-3">
        <div class="card">
          <div class="cover" data-fade="1500" data-duration="2500">
            <ul class="cover-pics">
              <li><img src="assets/img/img-1.jpg"></li>
              <li><img src="assets/img/img-2.jpg"></li>
              <li><img src="assets/img/img-3.jpg"></li>
              <li><img src="assets/img/img-4.jpg"></li>
              <li><img src="assets/img/img-5.jpg"></li>
              <li><img src="assets/img/img-6.jpg"></li>
              <li><img src="assets/img/img-7.jpg"></li>
              <li><img src="assets/img/img-8.jpg"></li>
            </ul>
            <!-- This is where the profile picture will come. We need to merge code here and replace this avatar. --> 			
            <!-- <img src="https://www.digiserved.com/Images/svg/avatar_placeholder.svg" alt="user picture" class="avatar"> -->
			<img src="${pageContext.request.contextPath}/images/${eMailId}" alt="user picture" class="avatar">
          </div>
          <div class="about">
            <h3 class="name"><%=session.getAttribute("log_user_name")%></h3>
          </div>
        </div><br/>
        <div class="panel panel-default">
          <div class="panel-body">
            <ul id="trends" class="panel-list">
			  	<a class="list-group-item" href="#microposts_posts" id="profile_post"><i class="fa fa-file-text"></i>&nbsp; My Posts</a>
                                    <a class="list-group-item" href="#microposts_questions" id="profile_question"><i class="fa fa-file-text"></i>&nbsp; My Questions</a>
				
			  <a class="list-group-item" href="GetforumServlet?loggedInUser=<%=session.getAttribute("user_id")%>" id="knowledge"><i class="fa fa-file-text-o fa-fw"></i>&nbsp; Knowledge Forum</a>
              <a class="list-group-item" href="MostPopularServlet?log_user_id=<%=session.getAttribute("user_id")%>"><i class="fa fa-asterisk"></i>&nbsp; Most Popular</a>
			  <input type="hidden" id="user_name_login" name="user_name_login" value="<%=session.getAttribute("name")%>">
			  <form action="createGroup" id="createGroupForm" method="post" role="form">
				<a class="list-group-item" href="#createGroupContainer" rel="#createGroupContainer"><i class="fa fa-group fa-fw"></i>&nbsp; Create Group</a>
			  	<div class="apple_overlay" id="createGroupContainer">
					<h3 align="center" style="color:green;font-size: 24px">Create Group</h3>            
					<br>
					<div class="form-group">
						<table>
						<tr>
							<td align="right">Group Name: </td>
							<td><input type="text" class="form-control" name="group_name" value=""/></td>
						</tr>
						<tr>
							<td colspan=2>&nbsp;</td>
						</tr>
						<tr>
							<td align="right">Members :    </td>
							<td><div class="form_tags"><input type="text" class="form-control" name="members" value="" id="form_tags_input_2" autocomplete="off" style="display: none;" placeholder="sample@students.itu.edu,sample@example.com,sample@itu.edu">
							</div></td>
						</tr>
						<tr>
							<td colspan=2>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><input type="button" class="btn btn-success btn-md" name="btnCreateGroup" value="Create Group" onclick="javascript:validateEmailAddress(false)"/></td>
						</tr>
						</table>
					</div>
                    <div id="emailerrmsg">
					</div>
                </div>
				</form>
			  <a class="list-group-item" href="#" onclick="displayMyGroup();" id="myGroups"><i class="fa fa-link fa-fw"></i>&nbsp; My Groups</a>
			  
			 <div class="" id="myGroup_1" style="display:none;">
			 
			  <%
			  @SuppressWarnings("unchecked")
			  ArrayList<GroupVO> group = (ArrayList<GroupVO>)session.getAttribute("all_groups");
			    Iterator<GroupVO> g_list= group.iterator();
			    
			    while(g_list.hasNext()){
			    	GroupVO g = (GroupVO)g_list.next();
			    	
			    	%>
			    	<form id="my_group_names" action="MyRegisteredGroupsServlet" method="post" role="form">
			    					<button id="my_registered_group" class="my-group-button" style="background-color: #fff;color: #999999;height:35px;width:100%;">
                						 <b style="color: #555;font-family: Courier New;font-size: 12px"><%=g.getGroupName() %></b>
             						 </button>	    				   
			   						<input type="hidden"  name="group_name" value="<%=g.getGroupName()%>">
			   						<input type="hidden"  name="group_id" value="<%=g.getGroupId() %>">
			   						<input type="hidden"  name="user_name_login" value="<%=session.getAttribute("name")%>">
			   						<br>
			   		</form>
			  <%  	
			    }
			  %>
			  </div>
		  </ul>
          </div>
        </div>		
      </div>      <br>
      <br>
      <br>
      <div class="col-md-7 col-md-7 my_post_que">
                        <div class="card">
                            <form role="form" id="viewprofile" name="myForm" action="ProfileUpdateServlet" onsubmit="return validateForm()" method="post" enctype="multipart/form-data">
         <table border="0" align="center">
            <tr>
               <td>
                  <input type="hidden" id="role_hidden" name="user_type" size="20" value=<%=session.getAttribute("role")%>>
               </td>
            </tr>
         </table>
         <br>
         <br>
         <div id ="errors">
         </div>
         <div id= "student1" style="display:none">
	 <input type="hidden" id="role_hidden" name="user_type" size="20" value=<%=session.getAttribute( "role")%>>
	 <input type="hidden" id="user_id" name="user_id" value="<%= session.getAttribute(" user_id ") %>">
                                    <input type="hidden" id="user_name_login" name="user_name_login" value="<%=session.getAttribute(" firstName ")%>">
            <table class="table table-striped" align="center">
               <tr>
                  <td>
                  	 <img name="profilepic" id="profilepic" alt="ProfileImage" align="center" src="${pageContext.request.contextPath}/images/${eMailId}" width="160" height="160">
                  	 <td>
                     <input type="button" align="center" class="btn btn-success" value="Upload" id="upload" name ="upload" style='visibility: hidden;' onclick ="fileopen()">
                     <input type="file" align="center" name="photo" id="photo" size="50" style='visibility: hidden;' onchange="readURL(this);" >
                  	</td>
                
               </tr>
               <tr>
               <div class="form-group">
                  <td >First Name* :</td>
                  <td><input type="text" class="form-control" name="first_name" maxlength = 20 id="first_name" size="20" value=<%=session.getAttribute("firstName")%>></td>
               	</div>
               </tr>
               <tr>
               <div class="form-group">
                  <td>Middle Name : </td>
                  <td><input type="text" class="form-control" name="middle_name" maxlength = 20 id="middle_name" size="20" value=<%=session.getAttribute("middleName")%>></td>
               </div>
               </tr>
               <tr>
               <div class="form-group">
                  <td>Last Name* :</td>
                  <td> <input type="text" class="form-control"  name="last_name" maxlength = 20 id="last_name" value=<%=session.getAttribute("lastName")%>></td>
               </div>
               </tr>
               <tr>
               <div class="form-group">
                  <td>E-mail id* :</td>
                  <td> <input type="text" class="form-control" id="eMailId" name="e_mail_id" value=<%=session.getAttribute("eMailId")%>></td>
               </div>
               </tr>
               <tr align="left">
                  <td>Date of Birth :</td>
                  <td >
                     <select name="day_of_month" id="day_of_month">
                        <option value="00" selected>Day</option>
                        <option value ="01">01</option>
                        <option value ="02">02</option>
                        <option value ="03">03</option>
                        <option value ="04">04</option>
                        <option value ="05">05</option>
                        <option value ="06">06</option>
                        <option value ="07">07</option>
                        <option value ="08">08</option>
                        <option value ="09">09</option>
                        <option value ="10">10</option>
                        <option value ="11">11</option>
                        <option value ="12">12</option>
                        <option value ="13">13</option>
                        <option value ="14">14</option>
                        <option value ="15">15</option>
                        <option value ="16">16</option>
                        <option value ="17">17</option>
                        <option value ="18">18</option>
                        <option value ="19">19</option>
                        <option value ="20">20</option>
                        <option value ="21">21</option>
                        <option value ="22">22</option>
                        <option value ="23">23</option>
                        <option value ="24">24</option>
                        <option value ="25">25</option>
                        <option value ="26">26</option>
                        <option value ="27">27</option>
                        <option value ="28">28</option>
                        <option value ="29">29</option>
                        <option value ="30">30</option>
                        <option value ="31">31</option>
                     </select>
                     <select name="month" id="month">
                        <option value="00" selected>Month</option>
                        <option value ="01">01</option>
                        <option value ="02">02</option>
                        <option value ="03">03</option>
                        <option value ="04">04</option>
                        <option value ="05">05</option>
                        <option value ="06">06</option>
                        <option value ="07">07</option>
                        <option value ="08">08</option>
                        <option value ="09">09</option>
                        <option value ="10">10</option>
                        <option value ="11">11</option>
                        <option value ="12">12</option>
                     </select>
                     <select  name="birthday_year" id="birthday_year">
                        <option value="0000" selected>Year</option>
                        <option value="2016">2016</option>
                        <option value="2015">2015</option>
                        <option value="2014">2014</option>
                        <option value="2013">2013</option>
                        <option value="2012">2012</option>
                        <option value="2011">2011</option>
                        <option value="2010">2010</option>
                        <option value="2009">2009</option>
                        <option value="2008">2008</option>
                        <option value="2007">2007</option>
                        <option value="2006">2006</option>
                        <option value="2005">2005</option>
                        <option value="2004">2004</option>
                        <option value="2003">2003</option>
                        <option value="2002">2002</option>
                        <option value="2001">2001</option>
                        <option value="2000">2000</option>
                        <option value="1999">1999</option>
                        <option value="1998">1998</option>
                        <option value="1997">1997</option>
                        <option value="1996">1996</option>
                        <option value="1995">1995</option>
                        <option value="1994">1994</option>
                        <option value="1993">1993</option>
                        <option value="1992">1992</option>
                        <option value="1991">1991</option>
                        <option value="1990">1990</option>
                        <option value="1989">1989</option>
                        <option value="1988">1988</option>
                        <option value="1987">1987</option>
                        <option value="1986">1986</option>
                        <option value="1985">1985</option>
                        <option value="1984">1984</option>
                        <option value="1983">1983</option>
                        <option value="1982">1982</option>
                        <option value="1981">1981</option>
                        <option value="1980">1980</option>
                        <option value="1979">1979</option>
                        <option value="1978">1978</option>
                        <option value="1977">1977</option>
                        <option value="1976">1976</option>
                        <option value="1975">1975</option>
                        <option value="1974">1974</option>
                        <option value="1973">1973</option>
                        <option value="1972">1972</option>
                        <option value="1971">1971</option>
                        <option value="1970">1970</option>
                        <option value="1969">1969</option>
                        <option value="1968">1968</option>
                        <option value="1967">1967</option>
                        <option value="1966">1966</option>
                        <option value="1965">1965</option>
                        <option value="1964">1964</option>
                        <option value="1963">1963</option>
                        <option value="1962">1962</option>
                        <option value="1961">1961</option>
                        <option value="1960">1960</option>
                        <option value="1959">1959</option>
                        <option value="1958">1958</option>
                        <option value="1957">1957</option>
                        <option value="1956">1956</option>
                        <option value="1955">1955</option>
                        <option value="1954">1954</option>
                        <option value="1953">1953</option>
                        <option value="1952">1952</option>
                        <option value="1951">1951</option>
                        <option value="1950">1950</option>
                        <option value="1949">1949</option>
                        <option value="1948">1948</option>
                        <option value="1947">1947</option>
                        <option value="1946">1946</option>
                        <option value="1945">1945</option>
                        <option value="1944">1944</option>
                        <option value="1943">1943</option>
                        <option value="1942">1942</option>
                        <option value="1941">1941</option>
                        <option value="1940">1940</option>
                        <option value="1939">1939</option>
                        <option value="1938">1938</option>
                        <option value="1937">1937</option>
                        <option value="1936">1936</option>
                        <option value="1935">1935</option>
                        <option value="1934">1934</option>
                        <option value="1933">1933</option>
                        <option value="1932">1932</option>
                        <option value="1931">1931</option>
                        <option value="1930">1930</option>
                        <option value="1929">1929</option>
                        <option value="1928">1928</option>
                        <option value="1927">1927</option>
                        <option value="1926">1926</option>
                        <option value="1925">1925</option>
                        <option value="1924">1924</option>
                        <option value="1923">1923</option>
                        <option value="1922">1922</option>
                        <option value="1921">1921</option>
                        <option value="1920">1920</option>
                        <option value="1919">1919</option>
                        <option value="1918">1918</option>
                        <option value="1917">1917</option>
                        <option value="1916">1916</option>
                        <option value="1915">1915</option>
                        <option value="1914">1914</option>
                        <option value="1913">1913</option>
                        <option value="1912">1912</option>
                        <option value="1911">1911</option>
                        <option value="1910">1910</option>
                        <option value="1909">1909</option>
                        <option value="1908">1908</option>
                        <option value="1907">1907</option>
                        <option value="1906">1906</option>
                        <option value="1905">1905</option>
                     </select>
                  </td>
               </tr>
               <tr>
                  <td>Department* : </td>
                  <td>
                     <select  name="department" id="dept" >
                        <option  value="Select">--select--</option>
                        <option  value="Software">MS in Software Engineering</option>
                        <option  value="Electrical">MS in Computer/Electrical Engineering</option>
                        <option  value="Digital">MS in Digital Arts</option>
                        <option  value="EngMgt">MS in Engineering Management</option>
                        <option  value="IntSc">MS in Interdisciplinary Science</option>
                        <option  value="MBA">Masters of Business Administration</option>
                     </select>
                  </td>
               </tr>
               <tr>
                  <td>Current Trimester* : </td>
                  <td>
                     <select name="semester" id="sem">
                        <option  value="Select">--select--</option>
                        <option  value="sem1">  Trimester I  </option>
                        <option  value="sem2">  Trimester II  </option>
                        <option  value="sem3">  Trimester III  </option>
                        <option  value="sem4">  Trimester IV  </option>
                     </select>
                  </td>
               </tr>
            </table>
            <br>
            <center>
               <input type="button" class="btn btn-success" name="edit" id="edit" value="Edit" onclick="EnableUserfieldsOnEditProfile()"/>
               <input type="submit" class="btn btn-success" name="save" id="save" value="Save"/>
               <input type="button" class="btn btn-success" name="cancel" id="cancel" value="Cancel" onclick="ClearFieldsOnCancelClick()"/>
            </center>
         </div>
         </form>
         <form name ="myForm"  action="ProfileUpdateServlet" onsubmit="return validateForm()" method="post" enctype="multipart/form-data">
         <div id= "alumni1" style="display:none">
	 <input type="hidden" id="role_hidden" name="user_type" size="20" value=<%=session.getAttribute( "role")%>>
	 <input type="hidden" id="user_id" name="user_id" value="<%= session.getAttribute(" user_id ") %>">
         <input type="hidden" id="user_name_login" name="user_name_login" value="<%=session.getAttribute(" firstName ")%>">
            <table align="center" >
               <tr>
               <div>
                  <td>Profile Photo:</td>
                  	<td>
                  	<img name="profilepic" alt="ProfileImage" align="center" src="${pageContext.request.contextPath}/images/${eMailId}" width="160" height="160">
                  	</td>
                  	<td>
                    <input type="button" align="center" class="btn btn-success" value="Upload" id="upload" name="upload" style='visibility: hidden;'  onclick ="fileopen()">
                    <input type="file" align="center" name="photo" id="photo" size="50" style='visibility: hidden;' onchange="readURL(this);">
                  </td>
               </tr>
               <tr>
                  <td >First Name* :</td>
                  <td><input type="text" class="form-control" name="first_name" maxlength = 20 id ="first_name" size="20" value=<%=session.getAttribute("firstName")%>></td>
               </tr>
               <tr>
                  <td>Middle Name : </td>
                  <td><input type="text" class="form-control" name="middle_name" maxlength = 20 id="middle_name" size="20" value=<%=session.getAttribute("middleName")%>></td>
               </tr>
               <tr>
                  <td>Last Name* :</td>
                  <td> <input type="text" class="form-control" name="last_name" maxlength = 20 id="last_name" value=<%=session.getAttribute("lastName")%>></td>
               </tr>
               <tr>
                  <td>E-mail id* :</td>
                  <td> <input type="text" class="form-control" id = "eMailId" name="e_mail_id" value=<%=session.getAttribute("eMailId")%>></td>
               </tr>
               <tr align="left">
                  <td>Date of Birth :</td>
                  <td >
                     <select name="day_of_month" id="day_of_month">
                        <option value="00" selected>Day</option>
                        <option value ="01">01</option>
                        <option value ="02">02</option>
                        <option value ="03">03</option>
                        <option value ="04">04</option>
                        <option value ="05">05</option>
                        <option value ="06">06</option>
                        <option value ="07">07</option>
                        <option value ="08">08</option>
                        <option value ="09">09</option>
                        <option value ="10">10</option>
                        <option value ="11">11</option>
                        <option value ="12">12</option>
                        <option value ="13">13</option>
                        <option value ="14">14</option>
                        <option value ="15">15</option>
                        <option value ="16">16</option>
                        <option value ="17">17</option>
                        <option value ="18">18</option>
                        <option value ="19">19</option>
                        <option value ="20">20</option>
                        <option value ="21">21</option>
                        <option value ="22">22</option>
                        <option value ="23">23</option>
                        <option value ="24">24</option>
                        <option value ="25">25</option>
                        <option value ="26">26</option>
                        <option value ="27">27</option>
                        <option value ="28">28</option>
                        <option value ="29">29</option>
                        <option value ="30">30</option>
                        <option value ="31">31</option>
                     </select>
                     <select name="month" id="month">
                        <option value="00" selected>Month</option>
                        <option value ="01">01</option>
                        <option value ="02">02</option>
                        <option value ="03">03</option>
                        <option value ="04">04</option>
                        <option value ="05">05</option>
                        <option value ="06">06</option>
                        <option value ="07">07</option>
                        <option value ="08">08</option>
                        <option value ="09">09</option>
                        <option value ="10">10</option>
                        <option value ="11">11</option>
                        <option value ="12">12</option>
                     </select>
                     <select  name="birthday_year" id="birthday_year">
                        <option value="0000" selected>Year</option>
                        <option value="2016">2016</option>
                        <option value="2015">2015</option>
                        <option value="2014">2014</option>
                        <option value="2013">2013</option>
                        <option value="2012">2012</option>
                        <option value="2011">2011</option>
                        <option value="2010">2010</option>
                        <option value="2009">2009</option>
                        <option value="2008">2008</option>
                        <option value="2007">2007</option>
                        <option value="2006">2006</option>
                        <option value="2005">2005</option>
                        <option value="2004">2004</option>
                        <option value="2003">2003</option>
                        <option value="2002">2002</option>
                        <option value="2001">2001</option>
                        <option value="2000">2000</option>
                        <option value="1999">1999</option>
                        <option value="1998">1998</option>
                        <option value="1997">1997</option>
                        <option value="1996">1996</option>
                        <option value="1995">1995</option>
                        <option value="1994">1994</option>
                        <option value="1993">1993</option>
                        <option value="1992">1992</option>
                        <option value="1991">1991</option>
                        <option value="1990">1990</option>
                        <option value="1989">1989</option>
                        <option value="1988">1988</option>
                        <option value="1987">1987</option>
                        <option value="1986">1986</option>
                        <option value="1985">1985</option>
                        <option value="1984">1984</option>
                        <option value="1983">1983</option>
                        <option value="1982">1982</option>
                        <option value="1981">1981</option>
                        <option value="1980">1980</option>
                        <option value="1979">1979</option>
                        <option value="1978">1978</option>
                        <option value="1977">1977</option>
                        <option value="1976">1976</option>
                        <option value="1975">1975</option>
                        <option value="1974">1974</option>
                        <option value="1973">1973</option>
                        <option value="1972">1972</option>
                        <option value="1971">1971</option>
                        <option value="1970">1970</option>
                        <option value="1969">1969</option>
                        <option value="1968">1968</option>
                        <option value="1967">1967</option>
                        <option value="1966">1966</option>
                        <option value="1965">1965</option>
                        <option value="1964">1964</option>
                        <option value="1963">1963</option>
                        <option value="1962">1962</option>
                        <option value="1961">1961</option>
                        <option value="1960">1960</option>
                        <option value="1959">1959</option>
                        <option value="1958">1958</option>
                        <option value="1957">1957</option>
                        <option value="1956">1956</option>
                        <option value="1955">1955</option>
                        <option value="1954">1954</option>
                        <option value="1953">1953</option>
                        <option value="1952">1952</option>
                        <option value="1951">1951</option>
                        <option value="1950">1950</option>
                        <option value="1949">1949</option>
                        <option value="1948">1948</option>
                        <option value="1947">1947</option>
                        <option value="1946">1946</option>
                        <option value="1945">1945</option>
                        <option value="1944">1944</option>
                        <option value="1943">1943</option>
                        <option value="1942">1942</option>
                        <option value="1941">1941</option>
                        <option value="1940">1940</option>
                        <option value="1939">1939</option>
                        <option value="1938">1938</option>
                        <option value="1937">1937</option>
                        <option value="1936">1936</option>
                        <option value="1935">1935</option>
                        <option value="1934">1934</option>
                        <option value="1933">1933</option>
                        <option value="1932">1932</option>
                        <option value="1931">1931</option>
                        <option value="1930">1930</option>
                        <option value="1929">1929</option>
                        <option value="1928">1928</option>
                        <option value="1927">1927</option>
                        <option value="1926">1926</option>
                        <option value="1925">1925</option>
                        <option value="1924">1924</option>
                        <option value="1923">1923</option>
                        <option value="1922">1922</option>
                        <option value="1921">1921</option>
                        <option value="1920">1920</option>
                        <option value="1919">1919</option>
                        <option value="1918">1918</option>
                        <option value="1917">1917</option>
                        <option value="1916">1916</option>
                        <option value="1915">1915</option>
                        <option value="1914">1914</option>
                        <option value="1913">1913</option>
                        <option value="1912">1912</option>
                        <option value="1911">1911</option>
                        <option value="1910">1910</option>
                        <option value="1909">1909</option>
                        <option value="1908">1908</option>
                        <option value="1907">1907</option>
                        <option value="1906">1906</option>
                        <option value="1905">1905</option>
                     </select>
                  </td>
               </tr>
               <tr>
                  <td>Department* : </td>
                  <td>
                     <select name="department" id="dept">
                        <option  value="Select">--select--</option>
                        <option  value="Software">MS in Software Engineering</option>
                        <option  value="Electrical">MS in Computer/Electrical Engineering</option>
                        <option  value="Digital">MS in Digital Arts</option>
                        <option  value="EngMgt">MS in Engineering Management</option>
                        <option  value="IntSc">MS in Interdisciplinary Science</option>
                        <option  value="MBA">Masters of Business Administration</option>
                     </select>
                  </td>
               </tr>
               <tr>
                  <td>Year of Passing* : </td>
                  <td>
                     <select name="month_of_passing" id="month_of_passing">
                        <option value="00" selected>Month</option>
                        <option value ="01">01</option>
                        <option value ="02">02</option>
                        <option value ="03">03</option>
                        <option value ="04">04</option>
                        <option value ="05">05</option>
                        <option value ="06">06</option>
                        <option value ="07">07</option>
                        <option value ="08">08</option>
                        <option value ="09">09</option>
                        <option value ="10">10</option>
                        <option value ="11">11</option>
                        <option value ="12">12</option>
                     </select>
                     <select  name="year_of_passing" id="year_of_passing">
                        <option value="0000" selected>Year</option>
                        <option value="2016">2016</option>
                        <option value="2015">2015</option>
                        <option value="2014">2014</option>
                        <option value="2013">2013</option>
                        <option value="2012">2012</option>
                        <option value="2011">2011</option>
                        <option value="2010">2010</option>
                        <option value="2009">2009</option>
                        <option value="2008">2008</option>
                        <option value="2007">2007</option>
                        <option value="2006">2006</option>
                        <option value="2005">2005</option>
                        <option value="2004">2004</option>
                        <option value="2003">2003</option>
                        <option value="2002">2002</option>
                        <option value="2001">2001</option>
                        <option value="2000">2000</option>
                        <option value="1999">1999</option>
                        <option value="1998">1998</option>
                        <option value="1997">1997</option>
                        <option value="1996">1996</option>
                        <option value="1995">1995</option>
                        <option value="1994">1994</option>
                        <option value="1993">1993</option>
                        <option value="1992">1992</option>
                        <option value="1991">1991</option>
                        <option value="1990">1990</option>
                        <option value="1989">1989</option>
                        <option value="1988">1988</option>
                        <option value="1987">1987</option>
                        <option value="1986">1986</option>
                        <option value="1985">1985</option>
                        <option value="1984">1984</option>
                        <option value="1983">1983</option>
                        <option value="1982">1982</option>
                        <option value="1981">1981</option>
                        <option value="1980">1980</option>
                        <option value="1979">1979</option>
                        <option value="1978">1978</option>
                        <option value="1977">1977</option>
                        <option value="1976">1976</option>
                        <option value="1975">1975</option>
                        <option value="1974">1974</option>
                        <option value="1973">1973</option>
                        <option value="1972">1972</option>
                        <option value="1971">1971</option>
                        <option value="1970">1970</option>
                        <option value="1969">1969</option>
                        <option value="1968">1968</option>
                        <option value="1967">1967</option>
                        <option value="1966">1966</option>
                        <option value="1965">1965</option>
                        <option value="1964">1964</option>
                        <option value="1963">1963</option>
                        <option value="1962">1962</option>
                        <option value="1961">1961</option>
                        <option value="1960">1960</option>
                        <option value="1959">1959</option>
                        <option value="1958">1958</option>
                        <option value="1957">1957</option>
                        <option value="1956">1956</option>
                        <option value="1955">1955</option>
                        <option value="1954">1954</option>
                        <option value="1953">1953</option>
                        <option value="1952">1952</option>
                        <option value="1951">1951</option>
                        <option value="1950">1950</option>
                        <option value="1949">1949</option>
                        <option value="1948">1948</option>
                        <option value="1947">1947</option>
                        <option value="1946">1946</option>
                        <option value="1945">1945</option>
                        <option value="1944">1944</option>
                        <option value="1943">1943</option>
                        <option value="1942">1942</option>
                        <option value="1941">1941</option>
                        <option value="1940">1940</option>
                        <option value="1939">1939</option>
                        <option value="1938">1938</option>
                        <option value="1937">1937</option>
                        <option value="1936">1936</option>
                        <option value="1935">1935</option>
                        <option value="1934">1934</option>
                        <option value="1933">1933</option>
                        <option value="1932">1932</option>
                        <option value="1931">1931</option>
                        <option value="1930">1930</option>
                        <option value="1929">1929</option>
                        <option value="1928">1928</option>
                        <option value="1927">1927</option>
                        <option value="1926">1926</option>
                        <option value="1925">1925</option>
                        <option value="1924">1924</option>
                        <option value="1923">1923</option>
                        <option value="1922">1922</option>
                        <option value="1921">1921</option>
                        <option value="1920">1920</option>
                        <option value="1919">1919</option>
                        <option value="1918">1918</option>
                        <option value="1917">1917</option>
                        <option value="1916">1916</option>
                        <option value="1915">1915</option>
                        <option value="1914">1914</option>
                        <option value="1913">1913</option>
                        <option value="1912">1912</option>
                        <option value="1911">1911</option>
                        <option value="1910">1910</option>
                        <option value="1909">1909</option>
                        <option value="1908">1908</option>
                        <option value="1907">1907</option>
                        <option value="1906">1906</option>
                        <option value="1905">1905</option>
                     </select>
                  </td>
               </tr>
            </table>
            <br>
            <center>
               <input type="button" class="btn btn-success" name="edit" id="edit" value="Edit" onclick="EnableUserfieldsOnEditProfile()"/>
               <input type="submit" class="btn btn-success" name="save" id="save" value="Save"/>
               <input type="submit" class="btn btn-success" name="cancel" id="cancel" value="Cancel" onclick="ClearFieldsOnCancelClick()"/> 
            </center>
         </div>
         </form>
         <form name ="myForm"  action="ProfileUpdateServlet" onsubmit="return validateForm()" method="post" enctype="multipart/form-data">
         <div id= "faculty1" style="display:none">
			<input type="hidden" id="role_hidden" name="user_type" size="20" value=<%=session.getAttribute( "role")%>>
			<input type="hidden" id="user_id" name="user_id" value="<%= session.getAttribute(" user_id ") %>">
            <input type="hidden" id="user_name_login" name="user_name_login" value="<%=session.getAttribute(" firstName ")%>">
            <table align="center">
               <tr>
                  <td>Profile Photo:</td>
                  <td>
                  	 <img name="profilepic" alt="ProfileImage" align="center" src="${pageContext.request.contextPath}/images/${eMailId}" width="160" height="160">
                  	 </td>
                  	 <td>
                     <input type="button" align="center" class="btn btn-success" value="Upload" id="upload" name="upload" style='visibility: hidden;'  onclick ="fileopen()">
                     <input type="file"  align="center" name="photo" id="photo" size="50" style='visibility: hidden;'onchange="readURL(this);">
					</td>                  	
               </tr>
               	<tr>
               		<div class="form-group">
	                  <td >First Name* :</td>
	                  <td><input type="text" class="form-control" name="first_name" class="form-control input" maxlength = 20 id= "first_name" size="20" value=<%=session.getAttribute("firstName")%>></td>
               		</div>
               	</tr>
               	<tr>
               		<div class="form-group">
	                  <td>Middle Name : </td>
	                  <td><input type="text" name="middle_name" class="form-control" maxlength = 20 id="middle_name" size="20" value=<%=session.getAttribute("middleName")%>></td>
               		</div>
               </tr>
               	<tr>
               		<div class="form-group">
	                  <td>Last Name* :</td>
	                  <td> <input type="text" name="last_name"  class="form-control" maxlength = 20 id="last_name" value=<%=session.getAttribute("lastName")%>></td>
               		</div>
               	</tr>
               	<tr>
               		<div class="form-group">
	                  <td>E-mail id* :</td>
	                  <td> <input type="text" id="eMailId"  class="form-control" name="e_mail_id" size="50" value=<%=session.getAttribute("eMailId")%>></td>
               		</div>
               	</tr>
               <tr align="left">
                  <td>Date of Birth :</td>
                  <td >
                     <select name="day_of_month" id="day_of_month">
                        <option value="00" selected>Day</option>
                        <option value ="01">01</option>
                        <option value ="02">02</option>
                        <option value ="03">03</option>
                        <option value ="04">04</option>
                        <option value ="05">05</option>
                        <option value ="06">06</option>
                        <option value ="07">07</option>
                        <option value ="08">08</option>
                        <option value ="09">09</option>
                        <option value ="10">10</option>
                        <option value ="11">11</option>
                        <option value ="12">12</option>
                        <option value ="13">13</option>
                        <option value ="14">14</option>
                        <option value ="15">15</option>
                        <option value ="16">16</option>
                        <option value ="17">17</option>
                        <option value ="18">18</option>
                        <option value ="19">19</option>
                        <option value ="20">20</option>
                        <option value ="21">21</option>
                        <option value ="22">22</option>
                        <option value ="23">23</option>
                        <option value ="24">24</option>
                        <option value ="25">25</option>
                        <option value ="26">26</option>
                        <option value ="27">27</option>
                        <option value ="28">28</option>
                        <option value ="29">29</option>
                        <option value ="30">30</option>
                        <option value ="31">31</option>
                     </select>
                     <select name="month" id="month">
                        <option value="00" selected>Month</option>
                        <option value ="01">01</option>
                        <option value ="02">02</option>
                        <option value ="03">03</option>
                        <option value ="04">04</option>
                        <option value ="05">05</option>
                        <option value ="06">06</option>
                        <option value ="07">07</option>
                        <option value ="08">08</option>
                        <option value ="09">09</option>
                        <option value ="10">10</option>
                        <option value ="11">11</option>
                        <option value ="12">12</option>
                     </select>
                     <select  name="birthday_year" id="birthday_year">
                        <option value="0000" selected>Year</option>
                        <option value="2016">2016</option>
                        <option value="2015">2015</option>
                        <option value="2014">2014</option>
                        <option value="2013">2013</option>
                        <option value="2012">2012</option>
                        <option value="2011">2011</option>
                        <option value="2010">2010</option>
                        <option value="2009">2009</option>
                        <option value="2008">2008</option>
                        <option value="2007">2007</option>
                        <option value="2006">2006</option>
                        <option value="2005">2005</option>
                        <option value="2004">2004</option>
                        <option value="2003">2003</option>
                        <option value="2002">2002</option>
                        <option value="2001">2001</option>
                        <option value="2000">2000</option>
                        <option value="1999">1999</option>
                        <option value="1998">1998</option>
                        <option value="1997">1997</option>
                        <option value="1996">1996</option>
                        <option value="1995">1995</option>
                        <option value="1994">1994</option>
                        <option value="1993">1993</option>
                        <option value="1992">1992</option>
                        <option value="1991">1991</option>
                        <option value="1990">1990</option>
                        <option value="1989">1989</option>
                        <option value="1988">1988</option>
                        <option value="1987">1987</option>
                        <option value="1986">1986</option>
                        <option value="1985">1985</option>
                        <option value="1984">1984</option>
                        <option value="1983">1983</option>
                        <option value="1982">1982</option>
                        <option value="1981">1981</option>
                        <option value="1980">1980</option>
                        <option value="1979">1979</option>
                        <option value="1978">1978</option>
                        <option value="1977">1977</option>
                        <option value="1976">1976</option>
                        <option value="1975">1975</option>
                        <option value="1974">1974</option>
                        <option value="1973">1973</option>
                        <option value="1972">1972</option>
                        <option value="1971">1971</option>
                        <option value="1970">1970</option>
                        <option value="1969">1969</option>
                        <option value="1968">1968</option>
                        <option value="1967">1967</option>
                        <option value="1966">1966</option>
                        <option value="1965">1965</option>
                        <option value="1964">1964</option>
                        <option value="1963">1963</option>
                        <option value="1962">1962</option>
                        <option value="1961">1961</option>
                        <option value="1960">1960</option>
                        <option value="1959">1959</option>
                        <option value="1958">1958</option>
                        <option value="1957">1957</option>
                        <option value="1956">1956</option>
                        <option value="1955">1955</option>
                        <option value="1954">1954</option>
                        <option value="1953">1953</option>
                        <option value="1952">1952</option>
                        <option value="1951">1951</option>
                        <option value="1950">1950</option>
                        <option value="1949">1949</option>
                        <option value="1948">1948</option>
                        <option value="1947">1947</option>
                        <option value="1946">1946</option>
                        <option value="1945">1945</option>
                        <option value="1944">1944</option>
                        <option value="1943">1943</option>
                        <option value="1942">1942</option>
                        <option value="1941">1941</option>
                        <option value="1940">1940</option>
                        <option value="1939">1939</option>
                        <option value="1938">1938</option>
                        <option value="1937">1937</option>
                        <option value="1936">1936</option>
                        <option value="1935">1935</option>
                        <option value="1934">1934</option>
                        <option value="1933">1933</option>
                        <option value="1932">1932</option>
                        <option value="1931">1931</option>
                        <option value="1930">1930</option>
                        <option value="1929">1929</option>
                        <option value="1928">1928</option>
                        <option value="1927">1927</option>
                        <option value="1926">1926</option>
                        <option value="1925">1925</option>
                        <option value="1924">1924</option>
                        <option value="1923">1923</option>
                        <option value="1922">1922</option>
                        <option value="1921">1921</option>
                        <option value="1920">1920</option>
                        <option value="1919">1919</option>
                        <option value="1918">1918</option>
                        <option value="1917">1917</option>
                        <option value="1916">1916</option>
                        <option value="1915">1915</option>
                        <option value="1914">1914</option>
                        <option value="1913">1913</option>
                        <option value="1912">1912</option>
                        <option value="1911">1911</option>
                        <option value="1910">1910</option>
                        <option value="1909">1909</option>
                        <option value="1908">1908</option>
                        <option value="1907">1907</option>
                        <option value="1906">1906</option>
                        <option value="1905">1905</option>
                     </select>
                  </td>
               </tr>
               <tr>
                  <td>Department* : </td>
                  <td>
                     <select  name="department" id="dept" >
                        <option  value="Select">--select--</option>
                        <option  value="Software">MS in Software Engineering</option>
                        <option  value="Electrical">MS in Computer/Electrical Engineering</option>
                        <option  value="Digital">MS in Digital Arts</option>
                        <option  value="EngMgt">MS in Engineering Management</option>
                        <option  value="IntSc">MS in Interdisciplinary Science</option>
                        <option  value="MBA">Masters of Business Administration</option>
                     </select>
                  </td>
               </tr>
            </table>
            <br>
            <center>
               <input type="button" class="btn btn-success" name="edit" id="edit" value="Edit" onclick="EnableUserfieldsOnEditProfile()"/>
               <input type="submit" class="btn btn-success" name="save" id="save" value="Save"/>
               <input type="button" class="btn btn-success" name="cancel" id="cancel" value="Cancel" onclick="ClearFieldsOnCancelClick()"/>
            </center>
            <script type="text/javascript">
               selectedValues();
            </script>
         </div>
      </form>
</div>
						<br />
						<br />
						<div id="microposts_posts" class="feed posts">
                            <input type="hidden" id="user_id" name="user_id" value="<%= session.getAttribute(" user_id ") %>">
                            <h3>My Posts</h3>                            
							<hr />
<% 
								ArrayList<PostVO> posts = (ArrayList<PostVO>)session.getAttribute("all_posts"); 
								Iterator<PostVO> p_list= posts.iterator(); 
								
								@SuppressWarnings("unchecked") ArrayList
								<ReplyVO> replies = (ArrayList<ReplyVO>)session.getAttribute("all_replies"); 
								Iterator<ReplyVO> r_list= replies.iterator(); 
								while(p_list.hasNext()) { 
								PostVO p=(PostVO)p_list.next(); 
%>
									<div id="<%= p.getPost_id() %>" class="form-group">
										<p id="displaypost" align="center" style="text-align:justify;width:575px;font-size:18px">
											<%=p.getPost_text() %>
										</p>
										<a href="#" id="replybutton" onclick="displayReply(<%= p.getPost_id() %>)">
											<i class="fa fa-retweet">Comment</i>
										</a>
										<input type="hidden" name="post_id" value="<%=p.getPost_id() %>">
										<input type="hidden" id="log_user_id" name="log_user_id" value="<%=p.getLog_user_id() %>">
										<input type="hidden" id="log_user_name" name="log_user_name" value="<%=session.getAttribute("log_user_name")%>">
									</div>
									<hr />
<% 
									while (r_list.hasNext()) {
										ReplyVO r=(ReplyVO)r_list.next(); 
										if ( p.getPost_id() == r.getPost_id() ) { 
%>
									<script>
										displayReplyTree( <%= r.getPost_id() %> , <%= r.getReply_id() %> , "<%= r.getReply_text() %>", <%= r.getImmparent_id() %> , <%= r.getLog_user_id() %> , "<%=r.getLog_reply_name() %>");
									</script>
									<br />
<% 
										} 
									} 
								} 
								posts.clear(); 
								replies.clear(); 
%>
						</div>
						<div id="microposts_questions" class="feed questions">
								<h3>Questions Asked by Me</h3>							
								<hr />
<% 
								@SuppressWarnings( "unchecked") 
								ArrayList<QuestionVO> questions=(ArrayList<QuestionVO>)session.getAttribute("all_questions"); 
								Iterator<QuestionVO> q_list= questions.iterator(); 
								while(q_list.hasNext()) { 
									QuestionVO q=(QuestionVO)q_list.next(); 
%>
									<div id="<%= q.getquestion_id() %>" class="question_list">
										<a id="displayquestion" name="displayquestion" style="font-style:18px" href="GetAnstoQueServlet?question_id=<%= q.getquestion_id() %>&logged_user_id=<%=session.getAttribute("user_id")%>&answer_user_name=<%=session.getAttribute("name")%>"><%=q.getquestion_text() %>
										</a>
										<input type="hidden" name="question_id" id="question_id" value="<%= q.getquestion_id() %>">
										<input type="hidden" name="answer_user_name" id="answer_user_name" value="<%=session.getAttribute(" name ")%>">
									</div>
									<hr />							
<% 
								} 
%>
                        </div>
					</div>
                </div>
            </div>
   </body>
</html>