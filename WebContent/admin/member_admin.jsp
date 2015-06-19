<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, tool.*, db.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员用户管理页面</title>
<script src="../css/jquery-1.11.2.min.js"></script>
<script src="../css/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="../css/bootstrap-theme.min.css" />
<style type="text/css">
	.jumbotron {
	background-color: white;
	}
	.table-striped>tbody>tr:nth-child(odd)>td, 
	.table-striped>tbody>tr:nth-child(odd)>th {
   	background-color: #D8D7D7;
   	
   	.non-active {
   	pointer-events: none;
   	cursor: default;
   	}
   	
 	}
</style>
</head>
<body>
<%
	SelectUser su = new SelectUser();
	ArrayList<UserDataBean> userList = su.getUserlist();
	request.setAttribute("userList", userList);
%>
		<nav class="navbar navbar-default navbar-fixed-top">
      		<div class="container">
        		<div class="navbar-header">
          			<div class="navbar-brand"></div>
       			 </div>
       			 <div id="navbar" class="navbar-collapse collapse">
        			<ul class="nav navbar-nav">
			            <li><a href="../index.jsp">主页</a></li>
			            <li><a href="../about.jsp">关于</a></li>
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
							
							<c:when test="${login_status == true && role == 'admin' }">
								<ul class="nav navbar-nav">
									<li><a>${login_name} ${role}</a></li>
									<li class="active"><a href="#">管理用户</a></li>
									<li><a href="../member/textMember.jsp">个人中心</a></li>
									<li><a href="../member/add_text_mem.jsp">上传语料</a></li>
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

<div class="container" style="padding-top: 70px">

	<div class="jumbotron">
		   <table class="table table-hover ">
       		 <thead>
	          		<tr>
	            			<th>用户名</th>
	            			<th>Email</th>
	            			<th>权限</th>
	            			<th>institute</th>
	            			<th>memo</th>
	            			<th></th>
	          		</tr>
        	</thead>
        	
        	 <tbody>
          		<c:forEach var="each" items="${userList}">
          			<tr>
						<td class="col-md-2">${each.username}</td>
						<td class="col-md-2">${each.email}</td>
						<td class="col-md-2">${each.role}</td>
						<td class="col-md-2">${each.institute}</td>
						<td class="col-md-2">${each.memo}</td>
		<c:if test="${each.activated == 0}">
			<td class="col-md-2">
			<form action="../activate.do" method="post">
				<input type="hidden" name="user_id" value="${fn:escapeXml(each.user_id) }" />
					<input type="submit" value="激活" />
			</form>
			</td>
		</c:if>
		
		<c:if test="${each.activated == 1}">
			<td class="col-md-3">
			已激活
			</td>
		</c:if>
		
					</tr>
				</c:forEach>
        	 </tbody>
      	    </table>
	</div>
</div>

</body>
</html>