<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%! final int LOGIN_YES = 1;
	final int LOGIN_NO = 0;%>
</head>
<body>
	<%  Integer status = (Integer) session.getAttribute("login_status");
		if (status == null) { %>
			<h1>The session is null.<h1>
	<%	} 
		else if (status.equals(LOGIN_YES)){ %>
			<h1>someont just logined in</h1>
	<%} 
		else { %>
			<h2>No body is in.</h2>
	<%} %>

</body>
</html>