<%@page import="java.io.BufferedWriter"%>
<%@page import="org.w3c.dom.Document"%>
<%@ page 
language="java"
contentType="application/json;"
%>
<%@ page import="com.itubuzz.validators.EmailGroupValidator"%>
<%@ page import="com.google.gson.*"%>
<%@ page import="java.util.*"%>
<%
	String emailIds = request.getParameter("emailIds");
	List<String> nonExistingEmails = EmailGroupValidator.validateEmailAddress(emailIds);
	JsonObject obj = new JsonObject();
	String jsonArray = new Gson().toJson(nonExistingEmails);
	obj.addProperty("data", jsonArray);
	out.print(obj);
%>