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
	<link href='https://fonts.googleapis.com/css?family=Fredericka+the+Great' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="dist/css/paper.min.css" />
	<link rel="stylesheet" href="css/demo.css" />
	<link rel="stylesheet" href="css/timeline.css" />
	<link rel="stylesheet" href="style.css" type="text/css" />

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
         if ( childs[i].nodeName == "DIV" ){
        	 lastChildid = childs[i].id;
        	 num = num + 1;
         }
    }
	
        
    if ( num == 0 ) {
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

	while ( rootparent.parentNode.getAttribute('id') != null) {
		rootparent = rootparent.parentNode;
		num = num + 1;
	}
   	
   	var leftspace = null;
   	
	if ( num == 0)
		leftspace = "margin-left: 0px;";
	 else 
		leftspace = "margin-left: 50px;";


   	var rootparentid = rootparent.getAttribute('id');

    var childid = nextChildid(parentid);
    
    var form = document.createElement("form");
	form.setAttribute('action', 'ReplyDataServlet');
    form.setAttribute('method', 'post');
    form.setAttribute('role', 'form');
    form.setAttribute('autocomplete','off');
    
	var childiv = document.createElement("div");
	childiv.setAttribute('id', childid);
	childiv.setAttribute('class', 'form-group');
	childiv.setAttribute('style', leftspace);
	
	var node1 = document.createElement("textarea");
	node1.setAttribute('name', 'reply_text');
	node1.setAttribute('class', 'form-control');
	node1.setAttribute('id', 'reply_text');
	node1.setAttribute('autocomplete','off');
	node1.setAttribute('autofocus', 'true');
	node1.setAttribute('placeholder','Comment...');
		
	var node2 = document.createElement("br");
	
	var node3 = document.createElement("button");
	node3.setAttribute('class', 'btn btn-success btn-xs');
	node3.setAttribute('id', 'nextreplybutton');
	var node31 = document.createElement("i");
	node31.setAttribute('class', 'fa fa-send-o');
	node31.innerHTML = "Reply";
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
		
	node3.onclick = function () {
		var parentid = node3.parentNode.getAttribute('id');
		displayReply(parentid);
	};
}


function displayReplyTree(rootparentid, childid, reply_text, immparentid, userid, replyname) {
		
	var leftspace = null;
	if ( rootparentid == immparentid)
		leftspace = "margin-left: 0px;";
	 else 
		leftspace = "margin-left: 50px;";
	
	var parent = document.getElementById(immparentid);
	var replyname = replyname;
	var childiv = document.createElement("div");
	childiv.setAttribute('id', childid);
	childiv.setAttribute('style', leftspace);
		
	var node1 = document.createElement("p");
	node1.setAttribute('id','displayName');
	
	node1.innerHTML = replyname;
	
	var node2 = document.createElement("span");
	node2.setAttribute('id', 'displayReply');
	node2.innerHTML = reply_text;
	node1.appendChild(node2);
	node1.setAttribute('font-style', '6px');
	
	var node3 = document.createElement("br");
	
	var node4 = document.createElement("a");
	node4.setAttribute('id', 'nextreplybutton');
	node4.setAttribute('href','#');
	var node41 = document.createElement("i");
	node41.setAttribute('class', 'fa fa-retweet');
	node41.innerHTML = "Comment";
	node4.appendChild(node41);
	
	childiv.appendChild(node1);
	childiv.appendChild(node4);
	childiv.appendChild(node3);
	parent.appendChild(childiv);
			
	node4.onclick = function () {
		var parentid = node4.parentNode.getAttribute('id');
		displayReply(parentid);
	};
	
}
</script>  
</head>

<body class="homepage">
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
				
			  <a class="list-group-item" href="GetforumServlet?loggedInUser=<%=session.getAttribute("user_id")%>" id="knowledge"><i class="fa fa-file-text-o fa-fw"></i>&nbsp; Knowledge Forum</a>
			  <input type="hidden" id="user_name_login" name="user_name_login" value="<%=session.getAttribute("name")%>">
			  <form action="createGroup" id="createGroupForm" method="post" role="form">
				<a class="list-group-item" href="#createGroupContainer" rel="#createGroupContainer"><i class="fa fa-group fa-fw"></i>&nbsp; Create Group</a>
			  	<div class="apple_overlay" id="createGroupContainer">
					<h3 align="center">Create Group</h3>            
					<br>
					<div class="form-group">
						<table>
						<tr>
					       <div class="form-group">
	                           	<div class="right-inner-addon">
	                           	   	<td>Group Name</td>
	                           	   	<td>
		    						<input type="text" class="form-control input" name="group_name" value=""/>	
		    						</td>
		  						</div>
							</div>
						</tr>
						<tr>
							<td colspan=2>&nbsp;</td>
						</tr>
						<tr>
					       <div class="form-group">
	                           	<div class="right-inner-addon">
	                           	  <td>Add Members</td>
	                           	  <td>
		    					<div class="form_tags"><input type="text" id="form_tags_input_2" autocomplete="off"  name="members" class="form-control input" placeholder="sample@students.itu.edu,sample@example.com,sample@itu.edu" name="members" value=""/>	
		  						</td>
		  						</div>
							</div>
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
	
            <form name="post_form" id="post_form" action="PostDataServlet" method="post" autocomplete="off" role="form">
              <div class="form-group">
                <textarea name="post_text" id="post_text" rows="1" cols="10" class="form-control" placeholder="Share your thoughts"></textarea>
              </div>
              <button id="post" class="btn btn-success">
                <i class="fa fa-send-o"></i> <b>Post</b>
              </button>
			  	<input type="hidden" name="log_user_id" id="log_user_id" value="<%=session.getAttribute("user_id")%>">
				<input type="hidden" name="log_user_name" id="log_user_name" value="<%=session.getAttribute("log_user_name")%>">
            </form>
          </div>
        </div>
		<h3>Recent Activity</h3>
		<div id="microposts" class="feed">
        <table align="center" width="100%" cellpadding="5">
         <tr>
    
<%
   	@SuppressWarnings("unchecked")
	ArrayList<PostVO> posts=(ArrayList<PostVO>)session.getAttribute("popular_posts");
    Iterator<PostVO> p_list= posts.iterator();
    
    while(p_list.hasNext()) {
		PostVO p=(PostVO)p_list.next();
%>
</tr>
<tr >
 <td ><p style="color:blue;">Posted By : <%=p.getPost_user_name() %></p></td>
 </tr>
 <tr >
 <td width="60%">
 <div id="<%= p.getPost_id() %>" class="form-group" style="text-align:justify;width:575px;">
  			<p id="displaypost" align="center" style="text-align:justify;width:575px;"><%=p.getPost_text()%></p>
  			<hr>
     		<a href="#" id="replybutton" onclick="displayReply(<%=p.getPost_id() %>)" ><i class="fa fa-retweet">Comment</i></a>
            
    		<input type="hidden" name="post_id" value="<%=p.getPost_id()%>">
         <input type="hidden" name="log_user_id" value="<%=p.getLog_user_id() %>">
         <input type ="hidden" name="reply_user_name" value="<%=p.getPost_user_name()%>">
 </div>   	
    	<hr />
<%
    }

   	@SuppressWarnings("unchecked")
	ArrayList<ReplyVO> replies=(ArrayList<ReplyVO>)session.getAttribute("popular_replies");
    Iterator<ReplyVO> r_list= replies.iterator();
    
	
    if (r_list.hasNext()) {
    	while (r_list.hasNext()) {
    		ReplyVO r=(ReplyVO)r_list.next();
    		Iterator<PostVO> pr_list= posts.iterator();
    		
			while(pr_list.hasNext()) {
				PostVO p=(PostVO)pr_list.next();	

	    		if ( p.getPost_id() == r.getPost_id() ) {
    			
%>						
   	 				<script> displayReplyTree(<%= r.getPost_id() %>, <%= r.getReply_id()%>, "<%= r.getReply_text()%>", <%= r.getimmparent_id() %>,<%= r.getLog_user_id()%>, "<%= r.getLog_reply_name()%>"); </script>
   	 				
        			<br>
        			
        			
<%
       			} 
			}
    	}
    }
%>

</td>
<td>

</td>
</tr>
</table>
</div>
</body>
</html>