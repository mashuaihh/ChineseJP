<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, tool.*, db.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="PathSnippet" %>
<%@ include file="LoginSnippet" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中日对译语料库首页</title>
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
			<a href="<%=uploadPath%>" style="text-decoration: none">上传文本</a> 
			<br>
			<a href="member_center.jsp" style="text-decoration: none">用户中心</a>

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
	
	<%  
		SelectRegex sr = (SelectRegex) request.getAttribute("sr");
		if (sr == null) {
			
		} else {
		ArrayList<SelectJpOriContent>  jpOriList = sr.getJpOriList();
		ArrayList<SelectChOriContent>  chOriList = sr.getChOriList();
			if (jpOriList == null && chOriList == null) { %>
				<h1>sha mei you</h1>
		<% 	} else { %>
				<h3>jpOriList <%=jpOriList.size() %></h3>
		<%  	for (int i = 0; i < jpOriList.size(); i++) { 
				SelectJpOriContent jp_each = jpOriList.get(i);
				System.out.println("jp ori :" + jpOriList.size());
			%>
				
				<h3><%= jp_each.getJp_text() %></h3>
				<h3><%= jp_each.getCt_text() %></h3>
				<br>
	<% 		} %>
		
				<h1>From here on is Ch Ori Content</h1> <br>
				<h3>chOriList <%=chOriList.size() %></h3>
		<%	
			for (int i = 0; i < chOriList.size(); i++) {
				SelectChOriContent ch_each = chOriList.get(i); %>
				<h2><%= chOriList == null %></h2>
				<h2><%= ch_each.getJt_text()%></h2>
				<h2><%= ch_each.getCh_text() %></h2>
	<% 	}	 
		}
	}
		%>
	

</body>
</html>