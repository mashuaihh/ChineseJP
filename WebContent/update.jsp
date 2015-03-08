<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="PathSnippet" %>
<%@ include file="LoginSnippet" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新条目</title>
</head>
<body>
	<%  Boolean status = (Boolean) session.getAttribute("login_status");
		if (status == null || status.equals(LoginNo)) { %> 
			<h2>只有登录用户可以进入此页面，请返回首页登录</h2>
			<a href="<%=IndexPath%>" style="text-decoration: none">返回首页</a>
	<%	}
		else if (status.equals(LoginYes)){
			String username = (String) session.getAttribute("login_name"); 
			String user_id = (String) session.getAttribute("user_id"); 
			// ch_id, ch_text, author, publisher, pub_date, trans
		%>
	 		<h1>Welcome <%= username %></h1>
			<br>
			<h1>Your user ID is <%= user_id %></h1>
			<br><br>
			
		
		<form action="UpdateWord.do" method="post">

			<input type="text" name="ori" value="${fn:escapeXml(textBean.ori) }" />
			<input type="text" name="updateLan" value="${fn:escapeXml(textBean.updateLan) }" />
			<input type="text" name="updateTable" value="${fn:escapeXml(textBean.updateTable) }" />
			<input type="text" name="text_id" value="${fn:escapeXml(textBean.text_id) }" />
			<label for="text">文本:</label>
			<br>
			<textarea id="text" name="text" rows="6" cols="50" style="resize:none"><c:out value="${textBean.text_content }" />
			</textarea>
			<br>
			<label for="author">作者:</label>
			<input name="author" id="author" value="${fn:escapeXml(textBean.author) }" />

			<label for="trans">译者:</label>
			<input name="trans" id="trans" value="${fn:escapeXml(textBean.translator) }" />

			<label for="publisher">出版社:</label>
			<input name="publisher" id="jp_publisher" value="${fn:escapeXml(textBean.publisher) }" />

			<label for="pub_date">出版时间:</label>
			<input name="pub_date" id="pub_date" value="${fn:escapeXml(textBean.pub_date) }" />
			</div>
			
			<input type="submit" value="确认修改" />
		</form>
			
	<%} %> <!-- }else if (status.equals(LoginYes)){  -->
</body>
</html>