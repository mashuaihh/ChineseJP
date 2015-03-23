<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, tool.*, db.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员用户管理页面</title>
</head>
<body>
<%
	SelectUser su = new SelectUser();
	ArrayList<UserDataBean> userList = su.getUserlist();
	request.setAttribute("userList", userList);
%>

	<a href="../index.jsp" style="text-decoration: none">返回首页</a>

	<c:forEach var="each" items="${userList }">
		<h3>${each.user_id}</h3>
		<h3>${each.username}</h3>
		<h3>${each.email}</h3>
		<h3>${each.role}</h3>
		<c:if test="${each.activated == 0}">
			<form action="../activate.do" method="post">
				<input type="hidden" name="user_id" value="${fn:escapeXml(each.user_id) }" />
				<input type="submit" value="activate" />
			</form>
		</c:if>
		<br>
	</c:forEach>
</body>
</html>