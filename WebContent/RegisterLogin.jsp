<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ITUBUZZ</title>
</head>
<body>
<form action="loginServlet" method="post"> 
<div>
<table align="left">
<caption><b>ITU Buzz</b></caption>
<tr>
<td colspan="2">The ITU Social Network</td>
</tr>
</table>


<table border="0" align="center" cellspacing="15">
<caption>Hi <%=session.getAttribute("name")%> ! Continue to login</caption>
<tr>
<td>E-Mail : <input type="text" name="user_name" /></td>
<td>Password : <input type="password" name="login_password" /></td>
<td><input type="submit"  value="SignIn" /></td>
</tr>
<tr>
<td></td>
<td></td>
<td><a href="ResetPassword.jsp" >Forgot password?</a></td>
</tr>
</table>

</div>
</form>
</body>
</html>