<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, tool.*, db.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="PathSnippet" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中日对译语料库首页</title>
</head>
<body>
	
	<c:choose>
		<c:when test="${login_status==null || login_status == false }">
			<h2>用户登陆</h2>
			<form action="login2.do" method="post">
				<label for="username">用户名:</label>
				<input type="text" name="username" id="username"/>
				<br><br>
				<label for="password">密码:</label>
				<input type="password" name="password" id="password"/>
				<br>
				<input type="submit" name="submit" value="登陆" />
			</form>
		</c:when>
		
		<c:when test="${login_status == true && role == 'member' }">
			<h1>Your name is : ${login_name }</h1>
			<h1>Your id is : ${user_id }</h1>
			<h2>Your role is : ${role }</h2>
			
			<form action="login2.do" method="get">
				<input type="submit" name="submit" value="退出登陆" />
			</form>
			<a href="<%=uploadPath%>" style="text-decoration: none">上传文本</a> 
			<br>
			<a href="<%=centerPath %>" style="text-decoration: none">用户中心</a>
		</c:when>
		
		<c:when test="${login_status == true && role == 'admin' }">
			<h1>Your name is : ${login_name }</h1>
			<h1>Your id is : ${user_id }</h1>
			<h2>Your role is : ${role }</h2>
			
			<form action="login2.do" method="get">
				<input type="submit" name="submit" value="退出登陆" />
			</form>
			<a href="<%=uploadPath%>" style="text-decoration: none">上传文本</a> 
			<br>
			<a href="<%=adminMemPath %>" style="text-decoration: none">用户管理中心</a>
		</c:when>
	</c:choose>
	
		<br>
		<br>
		<form action="selectRegexWord.do" method="post">
			<input type="text" name="keyword" value="" />
			<select name="language">
				<option value="ch">使用中文检索</option>
				<option value="jp">使用日文检索</option>
			</select>
			<input type="submit" name="submit" value="搜索" />
		</form>
		
	<a href="signup.jsp" style="text-decoration: none">注册</a>
	
	<c:choose>
		<c:when test="${jp_ori == null && ch_ori == null}">
			<h1>暂无任何记录</h1>
		</c:when>
		<c:when test="${jp_ori != null && ch_ori == null}">
			<h1>暂无中文原文记录</h1>
		</c:when>
		<c:when test="${jp_ori == null && ch_ori != null}">
			<h1>暂无日文原文记录</h1>
		</c:when>
	</c:choose>

	<h1>日文原文对应中文译文</h1>
	<c:forEach var="jp_each" items="${jp_ori}">
		<h2>${jp_each.jp_text }</h2>
		<h2>${jp_each.ct_text }</h2>
		<br>
	</c:forEach>

	<h1>中文原文对应日文译文</h1>
	<c:forEach var="ch_each" items="${ch_ori}">
		<h2>${ch_each.ch_text }</h2>
		<h2>${ch_each.jt_text }</h2>
		<br>
	</c:forEach>

</body>
</html>