<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, tool.*, db.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../css/jquery-1.11.2.min.js"></script>
<script src="../css/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="../css/bootstrap-theme.min.css" />
<style type="text/css">
	.col-md-3 {
	padding: 14px 9px 0px 10px;
	}
</style>

<title>用户上传页</title>
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top">
      		<div class="container">
        		<div class="navbar-header">
          			<div class="navbar-brand"></div>
       			 </div>
       			 <div id="navbar" class="navbar-collapse collapse">
        			<ul class="nav navbar-nav">
			            <li><a href="../index.jsp">主页</a></li>
			            <li><a href="#about">关于</a></li>
			            <li><a href="#contact">联系我们</a></li>
			        </ul>
        			
        			<div class="navbar-right">
		          		<c:choose>

							<c:when test="${login_status == true && role == 'member' }">
								<ul class="nav navbar-nav">
									<li><a>${login_name} ${role}</a></li>
									<li><a href="">个人中心</a></li>
									<li class="active"><a href="">上传语料</a></li>
									<li><a href="fileUpload.jsp">upload file</a></li>
									<div class="navbar-form navbar-right">
										<div class="form-group">
											<form action="../login2.do" method="get">
												<input class="btn btn-success" type="submit" name="submit" value="退出登陆" />
											</form>
										</div>
									</div>
								</ul>
							</c:when>
							
							<c:when test="${login_status == true && role == 'admin' }">
								<ul class="nav navbar-nav">
									<li><a>${login_name} ${role}</a></li>
									<li><a href="">Admin Page</a></li>
									<li><a href="">Upload</a></li>
									<div class="navbar-form navbar-right">
										<div class="form-group">
											<form action="../login2.do" method="get">
												<input class="btn btn-success" type="submit" name="submit" value="退出登陆" />
											</form>
										</div>
									</div>
								</ul>
							</c:when>
		          		</c:choose>
         			</div>
        		 </div><!--/.navbar-collapse -->
            </div>
        </nav>


	<div class=container style="padding-top: 100px">
		<form action="../AddTextMem.do" method="post" >
		<div class="form-group">
			<div class="col-md-6">
		        <textarea id="jp_text" class="form-control" name="jp_text" rows="6" cols="50" style="resize:none" placeholder="日文文本"></textarea>
			</div>

			<div class="col-md-6">
				<textarea id="ch_text" class="form-control" name="ch_text" rows="6" cols="50" style="resize:none" placeholder="中文文本"></textarea>
			</div>
		</div>

		<div class="form-inline">
			<div class="col-md-3">
				<input name="jp_author" id="jp_author" class="form-control" placeholder="日文原作者"/>
			</div>
			<div class="col-md-3">
				<input name="jp_trans" id="jp_trans" class="form-control" placeholder="翻译者"/>
            </div>

			<div class="col-md-3">
				<input name="ch_author" id="ch_author" class="form-control" placeholder="中文原作者"/>
			</div>
			<div class="col-md-3">
				<input name="ch_trans" id="ch_trans" class="form-control" placeholder="翻译者"/>
        	</div>
        </div>
        
		<div class="form-inline">
			<div class="col-md-3">
				<input name="jp_publisher" id="jp_publisher" class="form-control" placeholder="出版社"/>
			</div>
			<div class="col-md-3">
				<input name="jp_pub_date" id="jp_pub_date" class="form-control" placeholder="出版日期"/>
            </div>

			<div class="col-md-3">
				<input name="ch_publisher" id="ch_publisher" class="form-control" placeholder="出版社"/>
			</div>
			<div class="col-md-3">
				<input name="ch_pub_date" id="ch_publish" class="form-control" placeholder="出版日期"/>
        	</div>
        </div>
		
		<div class="form-inline">
			<div style="padding-top:30px">
			<select class="col-md-3 form-control" name="language" id="selectLan" onchange="toggle(this)" >
				<option value="jp">原文为日文，译文为中文</option>
				<option value="ch">原文为中文，译文为日文</option>
			</select>
			<input class="col-md-2 btn btn-success" type="submit" name="submit" id="submit" value="上传"/>
			</div><!-- end of div padding -->
		</div>
		
		</form>
	</div>
	
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
			ch_author.value = '';
			jp_trans.value = '';
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