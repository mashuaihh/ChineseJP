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
<!--  
<style type="text/css">
	.col-md-3 {
	padding: 14px 9px 0px 10px;
	}
</style>
-->

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
			            <li><a href="../about.jsp">关于</a></li>
			            <li><a href="../contact.jsp">联系我们</a></li>
			        </ul>
        			
        			<div class="navbar-right">
		          		<c:choose>

							<c:when test="${login_status == true && role == 'member' }">
								<ul class="nav navbar-nav">
									<li><a>${login_name} ${role}</a></li>
									<li><a href="textMember.jsp">个人中心</a></li>
									<li class="active"><a href="">上传语料</a></li>
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
									<li><a href="../admin/member_admin.jsp">管理用户</a></li>
									<li class="active"><a href="#">上传语料</a></li>
									<div class="navbar-form navbar-right">
										<div class="form-group">
											<form action="../login2.do" method="get"> <input class="btn btn-success" type="submit" name="submit" value="退出登陆" />
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

	<div class="container" style="padding-top: 100px">
		请选择上传方式
		<select id="updateByHand">
			<option value="file">上传Excel文件</option>
			<option value="hand">手工输入上传内容</option>
		</select>
		<br/><br/>

		<div id="fileArea">
		<form method="post" action="../uploadExcel.do" enctype="multipart/form-data">
			请选择要上传的文件，格式仅限Excel
			<select name="language">
				<option value="ch">原文中文，译文日文</option>
				<option value="jp">原文日文，译文中文</option>
			</select>
			<br/>
			<input type="file" name="dataFile" id="fileChooser"/><br/>
			<input type="submit" class="btn btn-success" value="上传" />
		</form>
		
		<br/>
		<div class="well">
		<strong>注意：</strong>上传文件只能为<strong>Excel</strong>，且必须使用固定格式。格式如下：<br/>
		</div>
		<strong>原文为中文，译文为日文</strong> 时请使用下图中的格式：<br/>
		<img src="../images/ch_ori.bmp" alt="some text">	
		<br/><br/>
		<strong>原文为日文，译文为中文</strong> 时请使用下图中的格式：<br/>
		<img src="../images/jp_ori.bmp" alt="some text">	
		</div>
		
		<form id="handArea" action="../AddTextMem.do" method="post" >
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
		
		<div class="form-inline pull-right">
			<select class="col-md-3 form-control" style="padding-top: 0px" name="language" id="selectLan" onchange="toggle(this)" >
				<option value="jp">原文为日文，译文为中文</option>
				<option value="ch">原文为中文，译文为日文</option>
			</select>
			<input class="col-md-2 btn btn-success form-control" type="submit" name="submit" id="submit" value="上传"/>
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
	
	$('#updateByHand').change(function() {
		if ($('#updateByHand').val() === "file") {
			$('#handArea').hide();
			$('#fileArea').show();
		} else {
			$('#handArea').show();
			$('#fileArea').hide();
		}
	});

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
	
	$(document).ready(function() {
		$('#handArea').hide();
		$('#fileArea').show();
	});

	window.onbeforeunload = closeIt;

</script> 



</html>
