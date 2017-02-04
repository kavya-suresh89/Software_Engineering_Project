<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList,java.util.Iterator,com.itubuzz.valueobjects.*" %>
    
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
window.getAtQ = function (question_id, logged_user_name) {
	var question_id = document.getElementById('question_id').value;
	var logged_user_name = document.getElementById('logged_user_name').value;
	var url1 = "/ITUBuzz/GetAnstoQueServlet?question_id=";
	var url2 = "&logged_user_name=";
	window.location.href = url1.concat(question_id.concat(url2.concat(logged_user_name)));
}

</script>
</head>

<body class="qaforum">
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
    			<input type="hidden" name="page_name" value="que">
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
            <img src="${pageContext.request.contextPath}/images/${eMailId}" alt="user picture" class="avatar">
          </div>
          <div class="about">
            <h3 class="name"><%=session.getAttribute("log_user_name")%></h3>
          </div>
        </div><br/>
        <div class="panel panel-default">
          <div class="panel-body">
            <ul id="trends" class="panel-list">
			  	<a class="list-group-item" href="FetchProfileServlet?user_id=<%=session.getAttribute("user_id")%>&emailId=<%=session.getAttribute("emailId")%>&name=<%=session.getAttribute("log_user_name")%>" id="profile">&nbsp;<i class="fa fa-user"></i>&nbsp;  My Profile</a>
			  	<a class="list-group-item" href="MostPopularServlet?log_user_id=<%=session.getAttribute("user_id")%>"><i class="fa fa-asterisk"></i>&nbsp; Most Popular</a>
				<input type="hidden" id="user_name_login" name="user_name_login" value="<%=session.getAttribute("name")%>">
				<form action="createGroup" id="createGroupForm" method="post" role="form">
				<a class="list-group-item" href="#createGroupContainer" rel="#createGroupContainer"><i class="fa fa-group fa-fw"></i>&nbsp; Create Group</a>
			  	<div class="apple_overlay" id="createGroupContainer">
					<h3 align="center" style="color:green;font-size: 24px">Create Group</h3>            
					<br><br>
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
      </div>

      <div class="col-md-7 col-md-7">
        <div class="panel panel-default">
          <div id="new-micropost" class="panel-body">
            <form name="qaforum_form" id="qaforum_form" action="QaforumQueServlet" method="post" autocomplete="off" role="form">
              <div class="form-group">
                <textarea name="question_text" id="question_text" rows="1" cols="10" class="form-control" placeholder="Ask Question"></textarea>
				<%
				if(null!=request.getAttribute("errorMessageQuestion"))
				{
					out.println(request.getAttribute("errorMessageQuestion"));
				}
				%>

              </div>
              <button id="post" class="btn btn-success">
                <i class="fa fa-send-o"></i> <b>Ask</b>
              </button>
			  	<input type="hidden" name="log_user_id" id="log_user_id" value="<%=session.getAttribute("user_id")%>">
				<input type="hidden" name="log_user_name" id="log_user_name" value="<%=session.getAttribute("log_user_name")%>">
            </form>
          </div>
        </div>

		<h3 id="TopQuestions">Questions</h3>
		<div id="microposts" class="feed">


<%
   	@SuppressWarnings("unchecked")
	ArrayList<QuestionVO> questions=(ArrayList<QuestionVO>)session.getAttribute("all_questions");
    Iterator<QuestionVO> q_list= questions.iterator();
    
    while(q_list.hasNext()) {
		QuestionVO q=(QuestionVO)q_list.next();
%>
			<div id="<%= q.getquestion_id() %>" class="question_list">
				<span class="name">
					<p>Question by : <%= q.getLog_user_name() %> </p>
				</span>
				<a id="displayquestion" name="displayquestion" style="font-style:18px" href="GetAnstoQueServlet?question_id=<%= q.getquestion_id() %>&logged_user_id=<%=session.getAttribute("user_id")%>&answer_user_name=<%=session.getAttribute("name")%>"><%=q.getquestion_text() %>  
				<input type="hidden" name="question_id"  value="<%= q.getquestion_id() %>">
				<input type="hidden" name="answer_user_name"  value="<%=session.getAttribute("name")%>">
				</a>				
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