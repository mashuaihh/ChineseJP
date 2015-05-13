<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>联系我们</title>
<%@ include file="PathSnippet" %>
</head>
<body>
		<nav class="navbar navbar-default navbar-fixed-top">
      		<div class="container">
        		<div class="navbar-header">
          			<div class="navbar-brand"></div>
       			 </div>
       			 <div id="navbar" class="navbar-collapse collapse">
        			<ul class="nav navbar-nav">
			            <li><a href="index.jsp">主页</a></li>
			            <li><a href="about.jsp">关于</a></li>
			            <li class="active"><a href="#">联系我们</a></li>
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
      <div class="jumbotron" style="text-align: center; padding-top: 50px; padding-bottom: 67px;">
      	<p><b>教育部哲学社会科学研究重大课题攻关项目“东亚国家语言中<br/>汉字词汇使用现状研究”中间成果</b></p>
        <h1><b>中日对译语料库</b></h1>
        <br/>
        <br/>
        <h2><b>邮编：100089 <br/> 地址：北京西三环北路2号北京外国语大学216信箱 <br/> 电话：(010)88816584  传真：(010)88816585</b></h2>
      </div>
    </div>

</body>

</html>