<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新条目</title>
</head>
<body>
	
		<form action="UpdateWord.do" method="post">

			<input type="text" id="ori" name="ori" value="${fn:escapeXml(textBean.ori) }" />
			<input type="text" id="updateLan" name="updateLan" value="${fn:escapeXml(textBean.updateLan) }" />
			<input type="text" name="updateTable" value="${fn:escapeXml(textBean.updateTable) }" />
			<input type="text" name="text_id" value="${fn:escapeXml(textBean.text_id) }" />
			<label for="text">文本:</label>
			<br>
			<textarea id="text" name="text_content" rows="6" cols="50" style="resize:none"><c:out value="${textBean.text_content }" />
			</textarea>
			<br>
			<label for="author">作者:</label>
			<input name="author" id="author" value="${fn:escapeXml(textBean.author) }" />

			<label for="trans">译者:</label>
			<input name="translator" id="trans" value="${fn:escapeXml(textBean.translator) }" />

			<label for="publisher">出版社:</label>
			<input name="publisher" id="publisher" value="${fn:escapeXml(textBean.publisher) }" />

			<label for="pub_date">出版时间:</label>
			<input name="pub_date" id="pub_date" value="${fn:escapeXml(textBean.pub_date) }" />
			
			<input type="submit" value="确认修改" />
		</form>
			
</body>

<script type="text/javascript" >  

	function closeIt() {
		var ori = document.getElementById("ori");
		var updateLan = document.getElementById("updateLan");
		var ori_value = ori.value;
		var updateLan_value = updateLan.value;
		var ori_lan = ori_value.slice(0, 2);
		
		var author = document.getElementById("author");
		var trans = document.getElementById("trans");
		
		if (ori_lan === updateLan_value) {
			author.disabled = false;
			trans.value = '';
			trans.disabled = true;
		} else {
			trans.disabled = false;
			author.value = '';
			author.disabled = true;

		}
	}
	
	window.onload = closeIt;
	window.onbeforeunload = closeIt;

</script> 
</html>