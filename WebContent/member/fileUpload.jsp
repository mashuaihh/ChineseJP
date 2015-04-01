<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="../uploadExcel.do" enctype="multipart/form-data">
		Select file to upload:
		<select name="language">
			<option value="ch">ch</option>
			<option value="jp">jp</option>
		</select>
		<input type="file" name="dataFile" id="fileChooser"/><br/><br/>
		<input type="submit" value="Upload" />
	</form>
</body>
</html>