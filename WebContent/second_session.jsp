<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%! final int SUCCESS =1; %>
</head>
<body>
	<%  Integer status = (Integer) session.getAttribute("login_status");
		if (status == null) { %>
			<h1>I am an empty guy</h1>
	<% } 
		else if (status.equals(SUCCESS)) {%>
			<h1>Yes here I am.</h1>
	<% } else {%>
			<h1>No I am not here !!!!</h1>
	<% } %>
	
	<form action="logout.do" method="post">
		<input type="submit" name="submit" value="logout" />
	</form>
	
	<h3><a href="main_session.jsp">here to main_session.jsp</a></h3>
</body>
</html>