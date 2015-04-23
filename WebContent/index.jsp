<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, tool.*, db.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="PathSnippet" %>
<style type="text/css">
	.jumbotron h1 {
	font-size:62px;
	}
	.jumbotron {
	background-color: #dddddd;	
	}
	div#table {
	background-color: white;
	}
	.table-striped>tbody>tr:nth-child(odd)>td, 
	.table-striped>tbody>tr:nth-child(odd)>th {
   	background-color: #D8D7D7;
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
	 <c:if test="${isSearch == null || isSearch == false }">
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
      </c:if>
      <c:if test="${isSearch == true }">
       <div class="jumbotron" style="text-align: center; padding-top: 25px; padding-bottom: 25px;">
          <form class="form-inline" style="float: left; padding-left: 30px" action="selectRegexWord.do" method="post">
          	 <div class="radio">
          	 	<c:if test="${param.language == 'ch' }">
          	 	<label><input type="radio" name="language" value="ch" checked="checked">使用中文检索</label>
				<label><input type="radio" name="language" value="jp">使用日文检索</label>
				</c:if>
                <c:if test="${param.language == 'jp' }">
          	 	<label><input type="radio" name="language" value="ch" >使用中文检索</label>
				<label><input type="radio" name="language" value="jp" checked="checked">使用日文检索</label>
				</c:if>   
          	 </div>
          	 <div class="input-group">
          	 	<input type="text" name="keyword" class="form-control" style="padding-right: 107px" 
          	 	value="${fn:escapeXml(param.keyword) }"/>
				<span class="input-group-btn">
        			<button type="submit" class="btn btn-primary" 
        			style="width: 80px; ">检索</button>
      			</span>
          	 </div>
          </form>
          <form class="form">
          	  <input type="hidden" name="keyword" class="form-control" 
          	 	value="${fn:escapeXml(param.keyword) }"/>
		      <button type="submit" style="margin-left: 106px;"
		      class="btn btn-primary">Download</button>
          </form>
       </div>
      </c:if>
    </div> <!-- /container -->
    

	<c:if test="${isSearch == true }">
	<div class="container">
      <div class="jumbotron"  id="table"  style="padding-top: 1px;">

		   <table class="table table-hover table-striped">
       		 <thead>
	          		<tr>
	            			<th>日文原文</th>
	            			<th>中文译文</th>
	          		</tr>
        	</thead>
        	
        	 <tbody>
          		<c:forEach var="jp_each" items="${jp_ori}">
          			<tr>
          				<td>${jp_each.jp_id }</td>
						<td class="col-md-6">${jp_each.jp_text }</td>
						<td>${jp_each.ct_id }</td>
						<td class="col-md-6">${jp_each.ct_text }</td>
					</tr>
				</c:forEach>
        	 </tbody>
      	    </table>
      	    <h1>The jpOriPage num is ${jpOriPageNum }</h1>


		   <table class="table table-hover table-striped">
       		 <thead>
          		<tr>
            		<th>中文原文</th>
            		<th>日文译文</th>
          		</tr>
        	</thead>
        	
        	<tbody>
          		<c:forEach var="ch_each" items="${ch_ori}">
          			<tr>
          				<td>${ch_each.ch_id }</td>
						<td class="col-md-6">${ch_each.ch_text }</td>
						<td>${ch_each.jt_id }</td>
						<td class="col-md-6">${ch_each.jt_text }</td>
					</tr>
				</c:forEach>
        	</tbody>
      	</table>
      	    <h1>The chOriPage num is ${chOriPageNum }</h1>

      </div>
    </div> <!-- /container -->
    </c:if>
</body>
</html>