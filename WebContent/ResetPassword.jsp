<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <head>
		<meta http-equiv="Content-Type" name="viewport" width="device-width", initial-scale="1"; charset="utf-8">
        <link rel="icon" href="marca.png" type="image/png">
        <link rel="icon" href="marca.png" type="image/png">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <link href='https://fonts.googleapis.com/css?family=Fredericka+the+Great' rel='stylesheet' type='text/css'>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="style.css" type="text/css" />

        <style>
        </style>
        <title>ITUBUZZ - Your ITU Social Network </title>

<%
    if(null!=request.getAttribute("errorMessagePassword"))
    {
        out.println(request.getAttribute("errorMessagePassword"));
    }
%>

</head>

<body class="loginpage">
<br />
        <div class="container sign-in-up well">
            <h1>ITUBuzz</h1>
            <h6> Your ITU Social Network</h6>
			    <h5 align="center">Reset Password</h5>            
            <div class="row">
                <div class="col-md-12">
                    <!-- Nav tabs -->
                    <div class="text-center">
                        <div class="btn-group">
                        <h3 style="color:green;font-size: 14px">
							<% if(null != request.getAttribute("success_message")) 
							{
								out.println(request.getAttribute("success_message"));
							}
								if(null!=request.getAttribute( "passwordSuccessMessage")){ 
								out.println(request.getAttribute( "passwordSuccessMessage"));
							} %>
        				</h3>
                        </div>
                    </div>
					<div class="tab-content">
						<div class="tab-pane fade in active" id="user">
						<br>	
						<fieldset>
							<form role="form" id="reset_form" action="ResetPasswordServlet" method="post">
								<table border="0" align="center">
									<tr>
								        <div class="form-group">
			                            	<div class="right-inner-addon">
				    							<input type="text" id="email"  name="user_name" class="form-control input" placeholder="Registered e-mail address*" maxlength="60"/>	
				  							</div>
										</div>
									</tr>	
									<tr>
								        <div class="form-group">
			                            	<div class="right-inner-addon">
				    							<input type="password"  id="password"  name="new_reset_password"  class="form-control input" placeholder="Enter new password*" maxlength="60"/>	
				  							</div>
										</div>
									</tr>		
									<tr>
								        <div class="form-group">
			                            	<div class="right-inner-addon">
				    							<input type="password"  id="password"  name="reenter_new_reset_password"  class="form-control input" placeholder="Re-enter new password*" maxlength="60"/>	
				  							</div>
										</div>
									</tr>			
									<center>
										<div class="form-group">
											<div class="right-inner-addon">										
												<button class="btn btn-success btn-md">Submit</button>									
											</div>
										</div>							
									</center>
								</table>
							</form>
						</fieldset>
						</div>
					</div>
				</div>
			</div>
		</div>

</body>
</html>