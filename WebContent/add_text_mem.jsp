<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="PathSnippet" %>
<%@ include file="LoginSnippet" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户上传页</title>
</head>
<body>
	<%  Boolean status = (Boolean) session.getAttribute("login_status");
		if (status == null || status.equals(LoginNo)) { %> 
			<h2>只有登录用户可以上传条目，请返回首页登录</h2>
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
			String user_id = (String) session.getAttribute("user_id"); %>
			<h1>Welcome <%= username %></h1>
			<h1>You have successfully logined in!</h1>
			<br>
			<h1>Your user ID is <%= user_id %></h1>
			<form action="login2.do" method="get">
				<input type="submit" name="submit" value="退出登陆" />
			</form>

	<form action="AddTextMem.do" method="post">
		
	<div id="jp_input">
		<label for="jp_text">日文文本:</label>
		<br>
		<textarea id="jp_text" name="jp_text" rows="6" cols="50" style="resize:none"></textarea>
		<br>
		
		<label for="jp_author">作者:</label>
		<input name="jp_author" id="jp_author" />
		
		<label for="jp_trans">译者:</label>
		<input name="jp_trans" id="jp_trans" />
		
		<label for="jp_publisher">出版社:</label>
		<input name="jp_publisher" id="jp_publisher" />
		
		<label for="jp_pub_date">出版时间:</label>
		<input name="jp_pub_date" id="jp_pub_date" />
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
		
		<label for="ch_pub_date">出版时间:</label>
		<input name="ch_pub_date" id="ch_publish" />
	</div>
	
	<br><br>
	<select name="language" id="selectLan" onchange="toggle(this)">
		<option value="jp">原文为日文，译文为中文</option>
		<option value="ch">原文为中文，译文为日文</option>
	</select>
	<input type="submit" name="submit" id="submit" value="justify" />
	</form>
	
	<%} %> <!-- else if (status.equals(LoingYes)) -->
	
</body>

<script type="text/javascript" >  
	var jp_trans = document.getElementById("jp_trans");
	var jp_author = document.getElementById("jp_author");
	var ch_trans = document.getElementById("ch_trans");
	var ch_author = document.getElementById("ch_author");
	
	jp_trans.disabled = true;
	ch_author.disabled = true;

	function toggle(el) {
		var lang = el.options[el.selectedIndex].value;
		if (lang === "ch") {
			jp_trans.disabled = false;
			ch_author.disabled = false;
			
			jp_author.disabled = true;
			ch_trans.disabled = true;
			jp_author.value = '';
			ch_trans.value = '';
		} else {
			jp_trans.disabled = true;
			ch_author.disabled = true;
			
			jp_author.disabled = false;
			ch_trans.disabled = false;
			jp_author.value = '';
			ch_trans.value = '';
		}
	}
	
	function closeIt() {
		ch_trans.disabled = false;
		jp_author.disabled = false;
		jp_trans.disabled = true;
		ch_author.disabled = true;
		
		jp_trans.value = '';
		ch_author.value = '';
		
		var opt= document.getElementById('selectLan');
	    opt.value = "jp";
	}
	window.onbeforeunload = closeIt;

</script> 



</html>