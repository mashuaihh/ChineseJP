<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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

	<%} %>

	<form action="upload.do" method="post">
		
	<div id="jp_input">
	<label for="jp_text">日语文本:</label>
	<br>
	<textarea id="jp_text" name="jp_text" rows="6" cols="50" style="resize:none"></textarea>
	<br>
	<label for="jp_author">作者:</label>
	<input name="jp_author" id="jp_author" />
	<label for="jp_trans">译者:</label>
	<input name="jp_trans" id="jp_trans" />
	<label for="jp_publisher">出版社:</label>
	<input name="jp_publisher" id="jp_publisher" />
	<label for="jp_pubdate">出版时间:</label>
	<input name="jp_pubdate" id="jp_pubdate" />
	</div>
	
	<br><br><br>
	
	<div id="ch_input">
	<label for="ch_text">中文文本:</label>
	<br>
	<textarea id="ch_text" name="ch_text" rows="6" cols="50" style="resize:none"></textarea>
	<br>
	<label for="ch_author">作者:</label>
	<input name="ch_author" id="ch_author" />
	<label for="ch_trans">译者:</label>
	<input name="ch_trans" id="ch_trans" />
	<label for="ch_publisher">出版社:</label>
	<input name="ch_publisher" id="ch_publisher" />
	<label for="ch_pubdate">出版时间:</label>
	<input name="ch_pubdate" id="ch_publish" />
	</div>
	<br><br>
	<select name="language">
		<option value="chjp">上传文本及文本译本</option>
		<option value="ch">使用日文检索</option>
		<option value="jp">使用日文检索</option>
	</select>
	<input type="submit" name="submit" id="submit" value="justify" />
	</form>
</body>

<!--  To disable the input area

<script type="text/javascript" >  

	document.getElementById("jp_text").disabled = true;
	var allChild = document.getElementById("jp_input").getElementsByTagName('*');
	for (var i = 0; i < allChild.length; i++) {
		allChild[i].disabled = true;
	}
</script> 

-->

</html>