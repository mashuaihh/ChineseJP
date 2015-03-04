<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, db.*, tool.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String user_id = (String) session.getAttribute("user_id");
	SelectTextMem textMem = new SelectTextMem(user_id);
	ArrayList<SelectJpOriContent>  jpOriList = null;
	ArrayList<SelectChOriContent>  chOriList = null;
	jpOriList = textMem.getJpOriList();
	chOriList = textMem.getChOriList();
%>

	<% for (int i = 0; i < jpOriList.size(); i++)  { 
		SelectJpOriContent each = jpOriList.get(i);
	%>
		<h2>日文原文：<%=each.getJp_text() %></h2>
		<h2>对应中文：<%=each.getCt_text() %></h2>
	<%} %>
</body>
</html>