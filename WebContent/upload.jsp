<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
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