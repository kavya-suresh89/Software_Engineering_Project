<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ArrayList,java.util.Iterator,com.itubuzz.valueobjects.SearchVO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="style.css" type="text/css" />
<title> ITUBuzz - Your ITU Social Network </title>
	<script src="./js/jquery-1.12.2.min.js"></script>
	<script src="./js/jquery.tools.min.js"></script>
	<script src="./js/TextboxList.js"></script>
	<link rel="stylesheet" type="text/css" href="./css/overlay-apple.css">
	<link rel="stylesheet" type="text/css" href="./css/TextboxList.css">
	<!-- required stylesheet for TextboxList -->
	<link rel="stylesheet" href="./css/TextboxList.css" type="text/css" media="screen" charset=ISO-8859-1>
	<!-- required for TextboxList -->
	<script src="./js/GrowingInput.js" type="text/javascript" charset="utf-8"></script>
	<script src="./js/TextboxList.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" href="dist/css/paper.min.css">
	<link rel="stylesheet" href="css/demo.css">
	<link rel="stylesheet" href="css/timeline.css">
	<script type="text/javascript" src="dist/js/vendor/jquery.min.js"></script>
	<script type="text/javascript" src="dist/js/paper.min.js"></script>
	<script type="text/javascript" src="js/handlebars-v2.0.0.js"></script>
	<script type="text/javascript" src="js/jquery.autosize.min.js"></script>
	<script type="text/javascript" src="js/timeline.js"></script>

<%
	if(null!=request.getAttribute("errorMessage"))
    {
        out.println(request.getAttribute("errorMessage"));
    }
%>

<script type="text/javascript">
function displayReply() {
	var selectedobj=document.getElementById('reply_text');
	if(selectedobj.className=='hide'){  //check if classname is hide 
	    selectedobj.style.display = "block";
	    selectedobj.className ='show';
	  }else{
	    selectedobj.style.display = "none";
	    selectedobj.className ='hide';
	 }

	var selectedobj=document.getElementById('reply_button');
	if(selectedobj.className=='hide'){  //check if classname is hide 
	    selectedobj.style.display = "block";
	    selectedobj.className ='show';
	  }else{
	    selectedobj.style.display = "none";
	    selectedobj.className ='hide';
	 }
}

document.getElementById("date").innerHTML = Date();
</script>

  
</head>

<body >
<input type="hidden" id="refreshed" value="no">

  <nav class="navbar navbar-default" role="navigation">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a id="logo" class="navbar-brand">ITUBuzz</a>
      </div>
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav navbar-right">
		  <li>
			<a href="PostLoopServlet?log_user_id=<%=session.getAttribute("user_id")%>" id="postloop" class="btn btn-info btn-lg">
			<span class="glyphicon glyphicon-home"></span> Home
			<input type="hidden" name="home_name" id="home_name"  value="<%=request.getAttribute("home_name")%>">
			</a>
		  </li>
          <li>
            <a href="FetchProfileServlet" id="profile"><img class="navbar-avatar" src="https://en.gravatar.com/userimage/23763355/e7bbf514106dc2fd9ddb4e8160a72e8c.png" alt="Profile"> 
			<br /> <%=session.getAttribute("log_user_name")%> </a>
          </li>
		  <li><a href="LoginAndRegister.jsp" id="logout"><span class="glyphicon glyphicon-log-out"></span></a></li>    
        </ul>

        <form class="navbar-form navbar-right" role="search" id="search_form" action="SearchServlet" method="post">
			<div class="form-group">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Search">
					<div class="input-group-addon"><i class="fa fa-search"></i></div>
				</div>
			</div>
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
              <li><img src="../assets/img/img-1.jpg"></li>
              <li><img src="../assets/img/img-2.jpg"></li>
              <li><img src="../assets/img/img-3.jpg"></li>
              <li><img src="../assets/img/img-4.jpg"></li>
              <li><img src="../assets/img/img-5.jpg"></li>
              <li><img src="../assets/img/img-6.jpg"></li>
              <li><img src="../assets/img/img-7.jpg"></li>
              <li><img src="../assets/img/img-8.jpg"></li>
            </ul>
            <!-- This is where the profile picture will come. We need to merge code here and replace this avatar. --> 
           <img src="${pageContext.request.contextPath}/images/${eMailId}" alt="user picture" class="avatar">
          </div>
          <div class="about">
            <h3 class="name">User Name</h3>
            <label id="location"><i class="fa fa-map-marker"></i> City, State</label>
          </div>
        </div><br/>
        <div class="panel panel-default">
          <div class="panel-heading">
          </div>
          <div class="panel-body">
            <ul id="trends" class="panel-list">
              <a class="list-group-item" href="createGroup" rel="#createGroupContainer"><i class="fa fa-group fa-fw"></i>&nbsp; Create Group</a>
			  	<div class="apple_overlay" id="createGroupContainer">
					Create Group<br><br>
					<div>
						<table >
						<tr>
							<td align="right">Group Name: </td>
							<td><input type="text" name="group_name" maxlength="50" style="width:445px;height: 35px;"/></td>
						</tr>
						<tr>
							<td colspan=2>&nbsp;</td>
						</tr>
						<tr>
							<td align="right">Members :    </td>
							<td><div class="form_tags"><input type="text" name="members" value="" id="form_tags_input_2" autocomplete="off" style="display: none;width:455px;height: 35px;" placeholder="sample@students.itu.edu,sample@example.com,sample@itu.edu">
							</div></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><input type="submit" name="btnCreateGroup" value="Create Group"/></td>
						</tr>
						</table>
					</div>
				</div>

              <a class="list-group-item" href="#"><i class="fa fa-link fa-fw"></i>&nbsp; Related Links</a>
              <a class="list-group-item" href="GetforumServlet" id="knowledge"><i class="fa fa-file-text-o fa-fw"></i>&nbsp; Ask Questions</a>
			  <input type="hidden" id="user_name_login" name="user_name_login" value="<%=session.getAttribute("name")%>">
			  <a class="list-group-item" href="#"><i class="fa fa-group fa-fw"></i>&nbsp; Edit Profile</a>
           </ul>
          </div>
        </div>		
      </div>
	  
      <div class="col-lg-6 col-md-5">
        <div class="panel panel-default">
          <div id="new-micropost" class="panel-body">	  
<%
			@SuppressWarnings("unchecked")
			ArrayList<SearchVO> dbooks=(ArrayList<SearchVO>)session.getAttribute("search_values");
			Iterator<SearchVO> list=dbooks.iterator();
			
			if (!list.hasNext()){
%>
			<div class="post">
				<textarea  id="postdisplaytext" name="postdisplaytext" tabindex="101" readonly="readonly">Sorry, No matching results found..</textarea>
			</div>
			<br />
<% 
			}
			else {
				while(list.hasNext()) {
					SearchVO b=(SearchVO)list.next();
%>
			<div class="post">
				<div class="postby">
					Posted by: <%= session.getAttribute("name") %><br/>
				</div>
				<textarea  id="postdisplaytext" name="postdisplaytext" tabindex="101" readonly="readonly"><%=b.search_value%></textarea>
				<input type="hidden" id="post_id" name="post_id" value="<%= b.search_value %>">
				<button id="like" onclick="displayCounter()">Like</button>
				<button id="reply" onclick="displayReply()">Reply</button>
				<textarea id="reply_text" name="reply_text" style="display:none" class="hide" placeholder="Comment..."></textarea>
				<input type="submit" id="reply_button" name="reply_button" value="Submit" style="display:none" class="hide">
			</div>
		  </div>			
    <br />

<%
    		}
    	}
%>    

		</div>
	</div>
  </div>
</div>

</body>
</html>