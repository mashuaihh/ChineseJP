<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, tool.*, db.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="PathSnippet" %>
<style type="text/css">
	.jumbotron h1 {
	font-size:62px;
	}
	div#table {
	background-color: white;
	}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中日对译语料库首页</title>
</head>
<body>

		<nav class="navbar navbar-default navbar-fixed-top">
      		<div class="container">
        		<div class="navbar-header">
          			<div class="navbar-brand"></div>
       			 </div>
       			 <div id="navbar" class="navbar-collapse collapse">
        			<ul class="nav navbar-nav">
			            <li class="active"><a href="index.jsp">主页</a></li>
			            <li><a href="#about">关于</a></li>
			            <li><a href="#contact">联系我们</a></li>
			        </ul>
        			
        			<div class="navbar-right">
		          		<c:choose>
							<c:when test="${login_status==null || login_status == false }">
								<form class="navbar-form" action="login2.do" method="post">
									<div class="form-group">
										<input type="text" name="username" id="username" class="form-control input-sm" placeholder="用户名"/>
									</div>
									<div class="form-group">
										<input type="password" name="password" id="password" class="form-control input-sm" placeholder="密码"/>
									</div>
									<input type="submit" name="submit" value="登陆" class="btn btn-primary"/>
									<a class="btn btn-success" href="signup.jsp">注册</a>
								</form>
							</c:when>


							<c:when test="${login_status == true && role == 'member' }">
								<ul class="nav navbar-nav">
									<li><a>${login_name} ${role}</a></li>
									<li><a href="<%=centerPath %>">个人中心</a></li>
									<li><a href="<%=uploadPath%>">上传语料</a></li>
									<div class="navbar-form navbar-right">
										<div class="form-group">
											<form action="login2.do" method="get">
												<input class="btn btn-success" type="submit" name="submit" value="退出登陆" />
											</form>
										</div>
									</div>
								</ul>
							</c:when>
							
							<c:when test="${login_status == true && role == 'admin' }">
								<ul class="nav navbar-nav">
									<li><a>${login_name} ${role}</a></li>
									<li><a href="<%=adminMemPath %>">Admin Page</a></li>
									<li><a href="<%=uploadPath%>">Upload</a></li>
									<div class="navbar-form navbar-right">
										<div class="form-group">
											<form action="login2.do" method="get">
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
    

		<div class="container" style="padding-top: 70px">

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron" style="text-align: center; padding-top: 50px; padding-bottom: 67px;">
        <h1><b>中日对译语料库</b></h1>
        <form action="selectRegexWord.do" method="post" style="padding-right: 101px; padding-left: 101px; padding-top: 18px;">
			
			<div class="radio">
      			<label style="padding-right: 33px;"><input type="radio" name="language" value="ch" checked="checked">使用中文检索</label>
      			<label><input type="radio" name="language" value="jp">使用日文检索</label>
    		</div>
    		
			<div class="input-group">
      			<input type="text" name="keyword" class="form-control" placeholder="输入一个或多个关键词" />
      			<span class="input-group-btn">
        			<button type="submit" class="btn btn-primary" style="width: 80px">检索</button>
      			</span>
    		</div><!-- /input-group -->
		</form>
      </div>

    </div> <!-- /container -->
    
    <div class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading">Panel heading</div>

  <!-- Table -->
  <table class="table">
    ...
  </table>
</div>

	<div class="container">
      <div class="jumbotron"  id="table"  style="padding-top: 1px;">
		   <table class="table">
       		 <thead>
          		<tr>
            		<th>日文原文</th>
            		<th>中文译文</th>
          		</tr>
        	</thead>
        	
        	<tbody>
          		<c:forEach var="jp_each" items="${jp_ori}">
          			<tr>
						<td>${jp_each.jp_text }</td>
						<td>${jp_each.ct_text }</td>
					</tr>
				</c:forEach>
        	</tbody>
      	</table>
      
		   <table class="table">
       		 <thead>
          		<tr>
            		<th>中文原文</th>
            		<th>日文译文</th>
          		</tr>
        	</thead>
        	
        	<tbody>
          		<c:forEach var="ch_each" items="${ch_ori}">
          			<tr>
						<td>${ch_each.ch_text }</td>
						<td>${ch_each.jt_text }</td>
					</tr>
				</c:forEach>
        	</tbody>
      	</table>
      </div>

    </div> <!-- /container -->
	
	<c:choose>
		<c:when test="${jp_ori == null && ch_ori == null}">
		</c:when>
		<c:when test="${jp_ori != null && ch_ori == null}">
			<h1>暂无中文原文记录</h1>
		</c:when>
		<c:when test="${jp_ori == null && ch_ori != null}">
			<h1>暂无日文原文记录</h1>
		</c:when>
	</c:choose>

</body>
</html>