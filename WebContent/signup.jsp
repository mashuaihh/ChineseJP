<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="signUp.do" method="post" >
		<label for="username">用户名:</label>
		<input type="text" name="username" /> <br>
		
		<label for="psw">密码：</label>
		<input type="password" name="psw" /><br>
		
		<label for="email">mail:</label>
		<input type="text" name="email" />
		
		<input type="submit" value="Sign UP" />
	</form>
</body>
</html>