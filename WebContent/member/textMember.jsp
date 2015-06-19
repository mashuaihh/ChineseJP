<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, db.*, tool.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心</title>
<script src="../css/jquery-1.11.2.min.js"></script>
<script src="../css/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="../css/bootstrap-theme.min.css" />
</head>
<body>
<%
	String user_id = (String) session.getAttribute("user_id");
	SelectTextMem textMem = new SelectTextMem(user_id);
	request.setAttribute("ch_ori", textMem.getChOriList());
	request.setAttribute("jp_ori", textMem.getJpOriList());
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
			            <li><a href="../contact.jsp">联系我们</a></li>
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
									<li class="active"><a href="#">个人中心</a></li>
									<li><a href="add_text_mem.jsp">上传语料</a></li>
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
									<li><a href="../admin/member_admin.jsp">用户管理</a></li>
									<li class="active"><a href="#">个人中心</a></li>
									<li><a href="add_text_mem.jsp">上传语料</a></li>
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

	<div class="container"  style="margin-top: 70px">
      	<div class="checkbox" style="display: inline;">
      		<label>
      			<input type="checkbox" id="show_jp" checked="checked" value="show_jp">显示原文为日文部分
      		</label>
      	</div>
      	<div class="checkbox" style="display: inline;padding-left: 32px;" >
      		<label>
      			<input type="checkbox" id="show_ch" checked="checked" value="show_ch">显示原文为中文部分
      		</label>
      	</div>

      	<div id="jp_ori">
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
		<td class="col-md-6">
		<form action="../UpdateWordInfo.do" method="post">
			<input type="hidden" name="ori" value="jp_ori" />
			<input type="hidden" name="jp_id" value="${jp_each.jp_id }" />
			<div class="col-md-10">
				${jp_each.jp_text }
			</div>
			<div class="col-md-2">			
			<input class="btn btn-sm  btn-primary" type="submit" value="更新" />
			</div>
		</form>
		</td>
		
		<td class="col-md-6">
		<form action="../UpdateWordInfo.do" method="post">
			<input type="hidden" name="ori" value="jp_ori" />
			<input type="hidden" name="ch_id" value="${jp_each.ct_id }" />
			<div class="col-md-10">
				${jp_each.ct_text}
			</div>
			<div class="col-md-2">
			<input class="btn btn-sm  btn-primary"  type="submit" value="更新" />
			</div>
		</form>
		</td>
		</tr>
	</c:forEach>
        	 </tbody>
      	    </table>
      	    <p>共${jpOriPageNum }页</p>
      	    
      	    <nav>
      	    </nav>	
		</div> <!--  end of div id="jp_ori" -->

		<div id="ch_ori">
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
	<td class="col-md-6">		
		<form action="../UpdateWordInfo.do" method="post">
			<input type="hidden" name="ori" value="ch_ori" />
			<input type="hidden" name="ch_id" value="${ch_each.ch_id }" />
			<div class="col-md-10">
				${ch_each.ch_text }
			</div>
			<div class="col-md-2">
			<input class="btn btn-sm btn-primary"  type="submit" value="更新" />
			</div>
		</form>
	</td>
		
	<td class="col-md-6">
		<form action="../UpdateWordInfo.do" method="post">
			<input type="hidden" name="ori" value="ch_ori" />
			<input type="hidden" name="jp_id" value="${ch_each.jt_id }" />
			<div class="col-md-10">
				${ch_each.jt_text }
			</div>
			<div class="col-md-2">
			<input class="btn btn-sm btn-primary"  type="submit" value="更新" />
			</div>
		</form>
	</td>
	</tr>
	</c:forEach>
        	</tbody>
      	</table>
      	    <p>共${chOriPageNum }页</p>
		</div>
      </div>

</body>
</html>