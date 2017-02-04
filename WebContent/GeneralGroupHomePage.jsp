<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Iterator,com.itubuzz.valueobjects.*" %>

<html>




<!-- this is the code for working group Page this is not to be changed -->
	<script type="text/javascript" charset="utf-8">
		var id = <%=request.getParameter("id")%>;
	</script>

       <meta charset="utf-8" />
  	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
  	<meta name="viewport" content="width=device-width, initial-scale=1" />

	<meta http-equiv="cache-control" content="max-age=0" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
	<meta http-equiv="pragma" content="no-cache" />

    <title> ITUBuzz - Your ITU Social Network </title>

	<link rel="stylesheet" href="dist/css/paper.min.css" />
	<link rel="stylesheet" href="css/demo.css" />
	<link rel="stylesheet" href="css/timeline.css" />
	<link rel="stylesheet" href="style.css" type="text/css" />
       
    <script src="dist/js/vendor/jquery.min.js" type="text/javascript" ></script>
	<script src="dist/js/paper.min.js" type="text/javascript" ></script>
	<link rel="stylesheet" type="text/css" href="./css/overlay-apple.css">
	<link rel="stylesheet" href="./css/TextboxList.css" type="text/css" media="screen" charset=ISO-8859-1>
	<link href='https://fonts.googleapis.com/css?family=Fredericka+the+Great' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Inconsolata' rel='stylesheet' type='text/css'>
	<script src="./js/jquery-1.12.2.min.js"></script>
    <script src="./js/jquery.tools.min.js"></script>
		
	<!-- sample initialization -->
	<%
	if(null!=request.getAttribute("errorMessage"))
    {
        out.println(request.getAttribute("errorMessage"));
    }

	if(null!=request.getAttribute("success_createGroup"))
    {
        out.println(request.getAttribute("success_createGroup"));
    }
	%>
	
	
	<script type="text/javascript" charset="utf-8">
	
	$(function(){
		// With custom adding keys 
		var t2 = jQuery('#form_tags_input_2').textboxlist({bitsOptions:{editable:{addKeys: [188]}}});
		var t2 = jQuery('#form_tags_input_3').textboxlist({bitsOptions:{editable:{addKeys: [188]}}});
		
	});

	$(document).ready(function() {
	  jQuery("a[rel]").overlay({effect: 'apple'});
		  fetchExistingMembers(id);
    });
		
////////////////////////////////
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
		    	childid = parseInt(lastChildid) + 1;
    }	
    
    return childid;
}

function displayReply(parentid) {
	var userid = document.getElementById("log_user_id").value;
	var replyname = document.getElementById("log_user_name").value;
			var groupname = document.getElementById("log_group_name").value;
			var groupid = document.getElementById("log_group_id").value;
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
			form.setAttribute('action', 'GroupReplyDataServlet');
		    form.setAttribute('method', 'post');
		    form.setAttribute('autocomplete','off');
		    
			var childiv = document.createElement("div");
			childiv.setAttribute('id', childid);
			childiv.setAttribute('style', leftspace);
			
			var node1 = document.createElement("textarea");
			node1.setAttribute('name', 'reply_text');
			node1.setAttribute('id', 'reply_text');
			node1.setAttribute('autocomplete','off');
			node1.setAttribute('placeholder','Comment...');
			node1.setAttribute('style','width:60%;height:50px');
			
			var node2 = document.createElement("br");
			
			var node3 = document.createElement("button");
			node3.setAttribute('id', 'nextreplybutton');
			node3.innerHTML = "Submit";
		
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

			var node9 = document.createElement("input");
			node9.setAttribute('type', 'hidden');
			node9.setAttribute('name', 'log_group_name');
			node9.setAttribute('id', 'log_group_name');
			node9.setAttribute('value', groupname);

	var node10 = document.createElement("input");
	node10.setAttribute('type', 'hidden');
	node10.setAttribute('name', 'log_group_id');
	node10.setAttribute('id', 'log_group_id');
	node10.setAttribute('value', groupid);
	
	childiv.appendChild(node1);
	childiv.appendChild(node2);
	childiv.appendChild(node3);
	childiv.appendChild(node4);
	childiv.appendChild(node5);
	childiv.appendChild(node6);
	childiv.appendChild(node7);
	childiv.appendChild(node8);
        childiv.appendChild(node9);
	childiv.appendChild(node10);
	
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
	node1.setAttribute('style','color:#007f00;');
	node1.innerHTML = replyname;
	
	var node2 = document.createElement("span");
	node2.setAttribute('id', 'displayReply');
	node2.innerHTML = reply_text;
	node1.appendChild(node2);
	node1.setAttribute('font-style', '6px');
	
	var node3 = document.createElement("br");
	
	
	
	childiv.appendChild(node1);
	childiv.appendChild(node2);
	childiv.appendChild(node3);
	parent.appendChild(childiv);
			
	node4.onclick = function () {
		var parentid = node4.parentNode.getAttribute('id');
		displayReply(parentid);
	};
	
}
</script>  
	<script src="./js/GrowingInput.js" type="text/javascript" charset="utf-8"></script>
	<script src="./js/TextboxList.js" type="text/javascript" charset="utf-8"></script>	
	<script src="./js/EmailValidator.js" type="text/javascript" charset="utf-8"></script>
	<script src="./js/MemberService1.js" type="text/javascript" charset="utf-8"></script>	
<style type="text/css" media="screen">
		.textboxlist { width: 400px; }
	</style>

	
	<body background="../TableBg.png">
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
                    <div id="emailerrmsg2">
					</div>
                </div>
				</form>
			
		  </ul>
          </div>
        </div>		
      </div>


	   
  
	
      
      <br>
	 
      <h3 align="left">Group Activities</h3>
      <table align="left">
      <tr>
      <td>
      <div class="form-group">
      	<%= (String)session.getAttribute("name") %> has created the group&nbsp;<%=request.getParameter("name") %>
      </div>
      </td>
      </tr>
      <tr>
      <td>
     
		<div >
			<table >
			<tr>
			<div class="form-group">
               <div class="right-inner-addon">
				<td align="right">Members:</td>
				<td><div class="form_tags"><input type="text" name="members" value="" id="form_tags_input_3" autocomplete="off" >
					</div></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="button" name="btnAddMembers" class="btn btn-success" value="Add Members" onclick="javascript:validateEmailAddress(true,'addMembers');"/></td>
			</tr>
			</table>
		</div>
		<div id="emailerrmsg2">
		</div>
		</td>
		</tr>
		<tr>
			<td colspan=2>
				&nbsp;
			</td>
		</tr>
		<tr>
			<td colspan=2>
			
		<form name="post_form" id="post_form" action="GroupPostDataServlet" method="post" role="form">
    	<div class="form-group">
			<textarea name="post_text" class="form-control" rows="5" id="post_text" style="width:575px;align:center;height:150px;"></textarea>
			<br>
			<input type="submit" class="btn btn-success" id="postbutton" value="Post">
			<input type="hidden" name="log_user_id" id="log_user_id" value="<%=session.getAttribute("user_id")%>">
			<input type="hidden" name="log_user_name" id="log_user_name" value="<%=session.getAttribute("name")%>">
			<input type="hidden" name="log_group_id" id="log_group_id" value="<%=request.getParameter("id")%>">
			<input type="hidden" name="log_group_name" id="log_group_name" value="<%=request.getParameter("name")%>">
		</div>
		</form>			
				
			</td>
		</tr>
		<tr>
			<td colspan=2>
			
			<h3>Recent Activity</h3>
<!-- ------------------ -->

<table align="center" width="100%" cellpadding="5">
    
<%
   	@SuppressWarnings("unchecked")
	List<GroupPostVO> posts=(ArrayList<GroupPostVO>)session.getAttribute("all_group_posts");
	
	if(posts!=null && !posts.isEmpty()){
	    for(GroupPostVO p : posts) {
%>
   	<tr >
   	 <td ><p style="color:blue;">Posted By : <%=p.getPost_user_name()%></p></td>
   	 </tr>
   	 <tr >
   	 <td width="60%">
   	 <div id="<%= p.getPost_id() %>" class="post" style="text-align:justify;width:575px;">
   	  			<p id="displaypost" align="center" style="text-align:justify;width:575px;"><%=p.getPost_text()%></p>
   	  			<hr>
   	     		<a href="#" id="replybutton" onclick="displayReply(<%=p.getPost_id() %>)" ><i class="fa fa-retweet">Comment</i></a>
   	            <hr>
   	    		<input type="hidden" name="post_id" value="<%=p.getPost_id()%>">
   	         <input type="hidden" name="log_user_id" value="<%=p.getLog_user_id() %>">
   	         <input type ="hidden" name="reply_user_name" value="<%=p.getPost_user_name()%>">
			<input type="hidden" name="log_group_id" id="log_group_id" value="<%=request.getParameter("id")%>">
			<input type="hidden" name="log_group_name" id="log_group_name" value="<%=request.getParameter("name")%>">
   	 </div>   	
   	    	<br/>
<%
   	    }
	}
	
   	@SuppressWarnings("unchecked")
	ArrayList<GroupReplyVO> replies=(ArrayList<GroupReplyVO>)session.getAttribute("all_group_replies");
    
	
    if (replies!=null && !replies.isEmpty()) {
    	for(GroupReplyVO r: replies) {
    		
    		if(posts!=null && !posts.isEmpty())
			for(GroupPostVO p : posts) {
	    		if ( p.getPost_id() == r.getPost_id() ) {
    			
%>
   	 				<script> displayReplyTree(<%= r.getPost_id() %>, <%= r.getReply_id()%>, "<%= r.getReply_text()%>", <%= r.getimmparent_id() %>,<%= r.getLog_user_id()%>, "<%= r.getLog_reply_name()%>"); </script>
   	 				
        			<br />
<%
       			} 
			}
    	}
    }
%>
</td>
</tr>
</table>


<!-- ----------------------- -->			
			</td>
		</table>
		
		
		<table align="center">
		<tr>
		<td>
			<span id="count"></span>
		</td> 
		<td>
			&nbsp; Members added to the group
		</td>
		</tr>
		<tr >
		<td colspan=2>
			<div id="membersDiv"></div>
		</td>
		</tr>
		</table>

		
	</body>
</html>