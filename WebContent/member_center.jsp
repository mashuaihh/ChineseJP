<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, db.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<%@ include file="PathSnippet" %>
<%@ include file="LoginSnippet" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户资料页</title>
</head>

<body>
<%  Boolean status = (Boolean) session.getAttribute("login_status");
		if (status == null || status.equals(LoginNo)) { %> 
			<h2>只有登录用户可以进入此页面，请返回首页登录</h2>
			<a href="<%=IndexPath%>" style="text-decoration: none">返回首页</a>
		<!-- 	<h2>用户登陆</h2>
			<form action="login2.do" method="post">
				<label for="username">用户名:</label>
				<input type="text" name="username" id="username"/>
				<br><br>
				<label for="password">密码:</label>
				<input type="password" name="password" id="password"/>
				<br>
				<input type="submit" name="submit" value="登陆" />
			</form>
			-->
	<%	} 
		else if (status.equals(LoginYes)){ 
			String username = (String) session.getAttribute("login_name"); 
			String user_id = (String) session.getAttribute("user_id"); 
			MemberWord mw = new MemberWord(user_id);
			ArrayList<Integer> chIdList = mw.getChList();
			ArrayList<Integer> jpIdList = mw.getJpList();
			ArrayList<String> chTextList = mw.getChTextList();
			ArrayList<String> jpTextList = mw.getJpTextList();
			%>
			
			<h1>Welcome <%= username %></h1>
			<br>
			<h1>Your user ID is <%= user_id %></h1>
			<form action="login2.do" method="get">
				<input type="submit" name="submit" value="退出登陆" />
			</form>
			
			<% if (chTextList != null && jpTextList != null) { 
				 for (int i = 0; i < chTextList.size(); i++) { %>

				 	<h2>Chinese id:<%= chIdList.get(i) %></h2>
					<h2><%= chTextList.get(i) %></h2>
					<form action="UpdateInfo.do" method="post">
				 		<input type="hidden" name="ch_id" value="<%=chIdList.get(i)%>" />
				 		<input type="submit" value="修改" />
				 	</form>
				 	<form action="Delete.do" method="get">
				 		<input type="hidden" name="ch_id" value="<%=chIdList.get(i)%>" />
				 		<input type="submit" value="删除" />
				 	</form>
					<h2>Japanese id:<%= jpIdList.get(i) %></h2>
					<h2><%= jpTextList.get(i) %></h2>
					<form action="UpdateInfo.do" method="post">
				 		<input type="hidden" name="jp_id" value="<%=jpIdList.get(i)%>" />
				 		<input type="submit" value="修改" />
				 	</form>
				 	<form action="Delete.do" method="get">
				 		<input type="hidden" name="jp_id" value="<%=jpIdList.get(i)%>" />
				 		<input type="submit" value="删除" />
				 	</form>
					<br><br>
			<% } 
			} else { %>
				<h1>No available items now</h1>
			<%}
			
     } %>
</body>
</html>