<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, db.*, tool.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>alsjkd;vl</title>
</head>
<body>
<%
	String user_id = (String) session.getAttribute("user_id");
	SelectTextMem textMem = new SelectTextMem(user_id);
	request.setAttribute("chh_ori", textMem.getChOriList());
	request.setAttribute("jpp_ori", textMem.getJpOriList());
%>
	
	<c:forEach var="jp_each" items="${jpp_ori}">
		<h2>${jp_each.jp_text }</h2>
		<h2>${jp_each.ct_text }</h2>
		<br>
	</c:forEach>

	<c:forEach var="ch_each" items="${chh_ori}">
		<h2>${ch_each.ch_text }</h2>
		<h2>${ch_each.jt_text }</h2>
		<br>
	</c:forEach>

</body>
</html>