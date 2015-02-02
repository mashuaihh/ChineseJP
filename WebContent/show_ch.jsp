<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>结果</title>
</head>
<body>
	<%  String ch = (String) request.getAttribute("ch"); 
		String jp = (String) request.getAttribute("jp");
		ArrayList<String> chs = (ArrayList<String>) request.getAttribute("chs");
		ArrayList<String> jps = (ArrayList<String>) request.getAttribute("jps");
	%>
	<h1> The Chinese is :<%= ch %> </h1>
	<br>
	<h2> The Japanese is :<%= jp %></h2>
	<br><br><br>
	The Array list is : <br>
	<% for (int i = 0; i < chs.size(); i++) { %>
		 <h3><%= chs.get(i) %></h3>
		 <h3><%= jps.get(i) %></h3> <br>
	<% } %>
	

	
</body>
</html>