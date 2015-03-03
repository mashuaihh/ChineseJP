<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="PathSnippet" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中日对译语料库首页</title>
<%! final int LOGIN_YES = 1;
	final int LOGIN_NO = 0;
	Boolean LoginYes = true;
	Boolean LoginNo = false;
	%>
</head>
<body>
	<%  Boolean status = (Boolean) session.getAttribute("login_status");
		if (status == null || status.equals(LoginNo)) { %>
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
	<%	} 
		else if (status.equals(LoginYes)){ 
			String username = (String) session.getAttribute("login_name"); 
			String user_id = (String) session.getAttribute("user_id"); %>
			<h1>Welcome <%= username %></h1>
			<h1>You have successfully logined in!</h1>
			<br>
			<h1>Your user ID is <%= user_id %></h1>
			<form action="login2.do" method="get">
				<input type="submit" name="submit" value="退出登陆" />
			</form>
<!--		<form action="UploadPage.do" method="get">
				<input type="submit" name="submit" value="上传文本" />
			</form> -->
			<a href="<%=uploadPath%>" style="text-decoration: none">上传文本</a>

	<%} %>
		<br>
		<br>
		<form action="searchWord.do" method="post">
			<input type="text" name="keyword" value="" />
			<select name="language">
				<option value="ch">使用中文检索</option>
				<option value="jp">使用日文检索</option>
			</select>
			<input type="submit" name="submit" value="搜索" />
		</form>
	
	<%  ArrayList<String> chs = (ArrayList<String>) request.getAttribute("chs");
		ArrayList<String> jps = (ArrayList<String>) request.getAttribute("jps");
		if (chs == null && jps == null) { %>
			<h2>未登录</h2>
	<%	}
		else { 
			for (int i = 0; i < chs.size(); i++) { %>
				
				<h3><%= i+1 %><%= chs.get(i) %></h3>
				<h3><%= jps.get(i) %></h3>
				<br>
	<% 			}
		} %>
	

</body>
</html>