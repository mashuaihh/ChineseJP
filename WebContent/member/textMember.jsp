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
	request.setAttribute("ch_ori", textMem.getChOriList());
	request.setAttribute("jp_ori", textMem.getJpOriList());
%>
	
	<h1>日译中</h1>
	<c:forEach var="jp_each" items="${jp_ori}">
		<form action="../UpdateWordInfo.do" method="post">
			<input type="text" name="ori" value="jp_ori" />
			<input type="text" name="jp_id" value="${jp_each.jp_id }" />
				<h2>${jp_each.jp_text }</h2>
			<input type="submit" value="更新" />
		</form>
		
		<form action="../UpdateWordInfo.do" method="post">
			<input type="text" name="ori" value="jp_ori" />
			<input type="text" name="ch_id" value="${jp_each.ct_id }" />
				<h2>${jp_each.ct_text }</h2>
			<input type="submit" value="更新" />
		</form>
		<br>
	</c:forEach>

	<h1>中译日</h1>

	<c:forEach var="ch_each" items="${ch_ori}">
		<form action="../UpdateWordInfo.do" method="post">
			<input type="text" name="ori" value="ch_ori" />
			<input type="text" name="jp_id" value="${ch_each.jt_id }" />
				<h2>${ch_each.jt_text }</h2>
			<input type="submit" value="更新" />
		</form>
		
		<form action="../UpdateWordInfo.do" method="post">
			<input type="text" name="ori" value="ch_ori" />
			<input type="text" name="ch_id" value="${ch_each.ch_id }" />
				<h2>${ch_each.ch_text }</h2>
			<input type="submit" value="更新" />
		</form>
		
		<br>
	</c:forEach>

</body>
</html>