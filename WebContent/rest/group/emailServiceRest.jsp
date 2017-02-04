<%@page import="java.io.BufferedWriter"%>
<%@page import="org.w3c.dom.Document"%>
<%@ page 
language="java"
contentType="application/json;"
%>
<%@ page import="com.itubuzz.services.MemberService"%>
<%@ page import="com.itubuzz.valueobjects.GroupSearchVO"%>
<%@ page import="com.itubuzz.valueobjects.UserVO"%>
<%@ page import="com.google.gson.*"%>
<%@ page import="java.util.*"%>
<%
	MemberService service = new MemberService();
	String groupId = request.getParameter("gid");
	JsonObject obj = new JsonObject();
	List<String> strList = new ArrayList<String>();
	GroupSearchVO criteria = new GroupSearchVO();
	
	if("fetch".equals(request.getParameter("action"))){
		criteria.setGroupId(Long.parseLong(groupId));
		System.out.println("group id is  " + groupId);
	}else if("add".equals(request.getParameter("action"))){
		String memberEmails = request.getParameter("members");
		service.addMembers(Integer.parseInt(groupId),memberEmails);
		criteria.setGroupId(Long.parseLong(groupId));
	}

	List<UserVO> voList = service.fetchGroupMembers(criteria);
	System.out.println("list size is  "+voList.size());
	for(UserVO vo : voList){
		strList.add(vo.getFirst_name()+" "+vo.getLast_name());
	}
	
	String jsonArray = new Gson().toJson(strList);
	obj.addProperty("data", jsonArray);
	obj.addProperty("count", voList.size());

	out.print(obj);
%>