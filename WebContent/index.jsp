<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, tool.*, db.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="PathSnippet" %>
<style type="text/css">
	div#table {
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
			            <li><a href="about.jsp">关于</a></li>
			            <li><a href="contact.jsp">联系我们</a></li>
			        </ul>
        			
        			<div class="navbar-right">
		          		<c:choose>
							<c:when test="${login_status==null || login_status == false }">
								<form name="loginForm" class="navbar-form" onsubmit="return(validateLogin());" action="login2.do" method="post">
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
									<li><a href="<%=adminMemPath %>">管理用户</a></li>
									<li><a href="<%=centerPath%>">个人中心</a></li>
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
		          		</c:choose>
         			</div>
        		 </div><!--/.navbar-collapse -->
            </div>
        </nav>
    

	<div class="container" style="padding-top: 70px">
	 <c:if test="${isSearch == null || isSearch == false }">
      <div class="jumbotron" style="text-align: center; padding-top: 50px; padding-bottom: 67px;">
      	<p><b>教育部哲学社会科学研究重大课题攻关项目<br/>“东亚国家语言中汉字词汇使用现状研究”成果</b></p>
        <h1><b>中日平行语料库</b></h1>
        <form name="myForm" action="selectRegexWord.do" method="get" onsubmit="return(validate());" style="padding-right: 101px; padding-left: 101px; padding-top: 18px;">
			<div class="radio">
      			<label style="padding-right: 33px;"><input type="radio" name="language" value="ch" checked="checked">使用中文检索</label>
      			<label><input type="radio" name="language" value="jp">使用日文检索</label>
    		</div>
    		
    
    <!--  regex 
   		    <div class="checkbox">
    			<label><input type="checkbox" name="isRegex" value="regex" />使用正则表达式检索</label>
    		</div>
     -->
     
     
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

          <form name="myForm" onsubmit="return(validate());" class="form-inline" style="float: left; padding-left: 30px" action="selectRegexWord.do" method="get">

          	 <div class="radio">
          	 	<c:if test="${param.language == 'ch' }">
          	 	<label><input type="radio" name="language" value="ch" checked="checked">使用中文检索</label>
				<label><input type="radio" name="language" value="jp">使用日文检索</label>
				</c:if>

                <c:if test="${param.language == 'jp' }">
          	 	<label><input type="radio" name="language" value="ch" >使用中文检索</label>
				<label><input type="radio" name="language" value="jp" checked="checked">使用日文检索</label>
				</c:if>   

			<div class="checkbox">
				<c:if test="${param.isRegex == 'regex' }">
    				<label style="padding-left: 12px"><input type="checkbox" name="isRegex" value="regex" checked="checked"/>使用正则表达式检索</label>
    			</c:if>

				<c:if test="${param.isRegex == null }">
    				<label style="padding-left: 12px"><input type="checkbox" name="isRegex" value="regex" />使用正则表达式检索</label>
    			</c:if>
    		</div>

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
          <c:if test="${login_status == null || login_status == false }">
		      <button id="download" style="margin-left: 106px;" disabled="disabled"
		      class="btn btn-primary">下载为Excel</button>
          </c:if>
          <c:if test="${login_status == true }">
          <form class="form" action="downloadExcel.do" method="get">
          	  <input type="hidden" name="keyword" class="form-control" 
          	 	value="${fn:escapeXml(param.keyword) }"/>
          	  <input type="hidden" name="language" class="form-control" 
          	 	value="${fn:escapeXml(param.language) }"/>
		      <button type="submit" style="margin-left: 106px;"
		      class="btn btn-primary">下载为Excel</button>
          </form>
          </c:if>
       </div>
      </c:if>
    </div> <!-- /container -->
    

<!-- if sth has been searched
 -->
	<c:if test="${isSearch == true }">
	<div class="container">
      <div class="jumbotron"  id="table"  style="padding-top: 1px;">
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
        	 
        	 
        	 <c:choose>
        		<c:when test="${login_status==null || login_status == false || role == 'member'}">
        			<c:forEach var="jp_each" items="${jp_ori}">
        				<tr onmousedown='return false;' onselectstart='return false;'>
        					<td class="col-md-6">
        						${jp_each.jp_text }
        					</td>
        					
        					<td class="col-md-6">
        						${jp_each.ct_text }
        					</td>
        				</tr>
        			</c:forEach>
        		</c:when>
        		
        		<c:when test="${login_status == true && role == 'admin' }">
        			<c:forEach var="jp_each" items="${jp_ori}">
          			<tr onmousedown='return false;' onselectstart='return false;'>
						<td class="col-md-6">
							<div class="col-md-10">			
								${jp_each.jp_text }
							</div>

						<form action="UpdateWordInfo.do" method="post">
							<input type="hidden" name="ori" value="jp_ori" />
							<input type="hidden" name="jp_id" value="${jp_each.jp_id }" />
							<div class="col-md-2">			
								<input class="btn btn-sm  btn-primary" type="submit" value="更新" />
							</div>
						</form>

						</td>
						
						
						<td class="col-md-6">
							<div class="col-md-10">			
								${jp_each.ct_text }
							</div>

						<form action="UpdateWordInfo.do" method="post">
							<input type="hidden" name="ori" value="jp_ori" />
							<input type="hidden" name="ch_id" value="${jp_each.ct_id }" />
							<div class="col-md-2">			
								<input class="btn btn-sm  btn-primary" type="submit" value="更新" />
							</div>
						</form>

						</td>
					</tr>
				</c:forEach>
        		</c:when>
        	</c:choose>
        	 
				
        	 </tbody>
      	    </table>
      	    <p>共${jpOriPageNum }页</p>
      	    
      	    <nav>
      	    	<ul class="pagination">
      	    		<c:url var="url1" value="/selectRegexWord.do">
				    	<c:param name="language" value="${fn:escapeXml(param.language) }" />
    					<c:param name="keyword" value="${fn:escapeXml(param.keyword) }" />
    					<c:param name="jpOriIndex" value="${fn:escapeXml(jpPreviousIndex) }" />
    					<c:param name="isRegex" value="${fn:escapeXml(param.isRegex) }" />
    					<c:param name="chOriIndex" value="${fn:escapeXml(chCurrentIndex) }" />
    				</c:url>

    			<!-- first '<' button -->
    				<!--  jpCurrentIndex = 1 -->
    				<c:if test="${jpCurrentIndex == jpFirstIndex }">
    					<li class="disabled non-active">
    						<a href="#"><span aria-hidden="true">&laquo;</span></a>
    					</li>
    				</c:if>
					
    				<c:if test="${jpCurrentIndex != jpFirstIndex }">
    					<li>
    						<a href="${url1 }"><span aria-hidden="true">&laquo;</span></a>
    					</li>
    				</c:if>
      	    	
      	    	<!--  2, 3, ... last but not least -->
    			<c:forEach var="jpPage" items="${jpOriPagesList }">
    				<c:url var="url" value="/selectRegexWord.do">
				    	<c:param name="language" value="${fn:escapeXml(param.language) }" />
    					<c:param name="keyword" value="${fn:escapeXml(param.keyword) }" />
    					<c:param name="jpOriIndex" value="${fn:escapeXml(jpPage) }" />
    					<c:param name="isRegex" value="${fn:escapeXml(param.isRegex) }" />
    					<c:param name="chOriIndex" value="${fn:escapeXml(chCurrentIndex) }" />
    				</c:url>
					
    				<c:if test="${jpPage == jpCurrentIndex }">
    					<li class="active">
    						<a href="${url }">${jpPage }</a>
    					</li>
    				</c:if>
    				<c:if test="${jpPage  != jpCurrentIndex }">
    					<li>
    						<a href="${url }">${jpPage }</a>
    					</li>
    				</c:if>
    			</c:forEach>
    			
    			
    			<!--  last '>' index -->	
    				<c:url var="url2" value="/selectRegexWord.do">
				    	<c:param name="language" value="${fn:escapeXml(param.language) }" />
    					<c:param name="keyword" value="${fn:escapeXml(param.keyword) }" />
    					<c:param name="isRegex" value="${fn:escapeXml(param.isRegex) }" />
    					<c:param name="jpOriIndex" value="${fn:escapeXml(jpNextIndex) }" />
    					<c:param name="chOriIndex" value="${fn:escapeXml(chCurrentIndex) }" />
    				</c:url>
    				<c:if test="${jpCurrentIndex == jpOriPageNum }">
    					<li class="disabled non-active" >
    						<a href="#"><span aria-hidden="true">&raquo;</span></a>
    					</li>
    				</c:if>
    				<c:if test="${jpCurrentIndex != jpOriPageNum }">
    					<li>
    						<a href="${url2 }"><span aria-hidden="true">&raquo;</span></a>
    					</li>
    				</c:if>
      	    	</ul>
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
        	<c:choose>
        		<c:when test="${login_status==null || login_status == false || role == 'member'}">
        			<c:forEach var="ch_each" items="${ch_ori}">
        			<tr onmousedown='return false;' onselectstart='return false;'>
        				<td class="col-md-6">
        					${ch_each.ch_text }
        				</td>
        				
        				<td class="col-md-6">
        					${ch_each.jt_text }
        				</td>
        			</tr>
        			</c:forEach>
        		</c:when>
        			
        		<c:when test="${login_status == true && role == 'admin' }">
          			<c:forEach var="ch_each" items="${ch_ori}">
          			
          			<tr onmousedown='return false;' onselectstart='return false;'>
						<td class="col-md-6">
							<div class="col-md-10">
								${ch_each.ch_text }
							</div>
							
							<form action="UpdateWordInfo.do" method="post">
								<input type="hidden" name="ori" value="ch_ori" />
								<input type="hidden" name="ch_id" value="${ch_each.ch_id }" />
							
								<div class="col-md-2">
									<input class="btn btn-sm btn-primary"  type="submit" value="更新" />
								</div>
							</form>
											
						</td>
						
						
						
						<td class="col-md-6">
							<div class="col-md-10">
								${ch_each.jt_text }
							</div>
							
							<form action="UpdateWordInfo.do" method="post">
								<input type="hidden" name="ori" value="ch_ori" />
								<input type="hidden" name="jp_id" value="${ch_each.jt_id }" />
								
								<div class="col-md-2">
									<input class="btn btn-sm btn-primary"  type="submit" value="更新" />
								</div>
							</form>
						
						</td>
					</tr>
				</c:forEach>
				</c:when>
			</c:choose>
				
				
				
        	</tbody>
      	</table>
      	    <p>共${chOriPageNum }页</p>
      	    
      	    <nav>
      	    	<ul class="pagination">
      	    		<c:url var="url1" value="/selectRegexWord.do">
				    	<c:param name="language" value="${fn:escapeXml(param.language) }" />
    					<c:param name="isRegex" value="${fn:escapeXml(param.isRegex) }" />
    					<c:param name="keyword" value="${fn:escapeXml(param.keyword) }" />
    					<c:param name="chOriIndex" value="${fn:escapeXml(chPreviousIndex) }" />
    					<c:param name="jpOriIndex" value="${fn:escapeXml(jpCurrentIndex) }" />
    				</c:url>
    				<c:if test="${chCurrentIndex == chFirstIndex }">
    					<li class="disabled non-active">
    						<a href="#"><span aria-hidden="true">&laquo;</span></a>
    					</li>
    				</c:if>
    				<c:if test="${chCurrentIndex != chFirstIndex }">
    					<li>
    						<a href="${url1 }"><span aria-hidden="true">&laquo;</span></a>
    					</li>
    				</c:if>
      	    	
    			<c:forEach var="chPage" items="${chOriPagesList }">
    				<c:url var="url" value="/selectRegexWord.do">
				    	<c:param name="language" value="${fn:escapeXml(param.language) }" />
    					<c:param name="keyword" value="${fn:escapeXml(param.keyword) }" />
    					<c:param name="isRegex" value="${fn:escapeXml(param.isRegex) }" />
    					<c:param name="jpOriIndex" value="${fn:escapeXml(jpCurrentIndex) }" />
    					<c:param name="chOriIndex" value="${fn:escapeXml(chPage) }" />
    				</c:url>
					
    				<c:if test="${chPage == chCurrentIndex }">
    					<li class="active">
    						<a href="${url }">${chPage }</a>
    					</li>
    				</c:if>
    				<c:if test="${chPage  != chCurrentIndex }">
    					<li>
    						<a href="${url }">${chPage }</a>
    					</li>
    				</c:if>
    			</c:forEach>
    			
    				<c:url var="url2" value="/selectRegexWord.do">
    				  <c:param name="language" value="${fn:escapeXml(param.language) }" />
    					<c:param name="keyword" value="${fn:escapeXml(param.keyword) }" />
    					<c:param name="isRegex" value="${fn:escapeXml(param.isRegex) }" />
    					<c:param name="chOriIndex" value="${fn:escapeXml(chNextIndex) }" />
    					<c:param name="jpOriIndex" value="${fn:escapeXml(jpCurrentIndex) }" />
    				</c:url>
    				<c:if test="${chCurrentIndex == chOriPageNum }">
    					<li class="disabled non-active" >
    						<a href="#"><span aria-hidden="true">&raquo;</span></a>
    					</li>
    				</c:if>
    				<c:if test="${chCurrentIndex != chOriPageNum }">
    					<li>
    						<a href="${url2 }"><span aria-hidden="true">&raquo;</span></a>
    					</li>
    				</c:if>
      	    	</ul>
      	    </nav>	
		</div>


      </div>
    </div> <!-- /container -->
    </c:if>
    

    <footer class="footer">
    	<div class="container">
    		<p class="text-muted" style="text-align: center">北京外国语大学   日本学研究中心 </p>
    	</div>
    </footer>
</body>
<script type="text/javascript">
	$('#show_jp').change(function() {
		if ($(this).is(":checked")) {
			$('#jp_ori').show();
		} else {
			$('#jp_ori').hide();
		}
	});
	$('#show_ch').change(function() {
		if ($(this).is(":checked")) {
			$('#ch_ori').show();
		} else {
			$('#ch_ori').hide();
		}
	});
	$('#download').click(function() {
		alert("只有用户可以下载搜索结果");
	});
	
	function validate()
	{
	 
	   if( document.myForm.keyword.value == "" || document.myForm.keyword.value == " ")
	   {
	     alert( "Please enter the keywords." );
	     document.myForm.keyword.focus() ;
	     return false;
	   }
	   return( true );
	}

	function validateLogin()
	{
	 
	   if( document.loginForm.username.value == "" )
	   {
	     alert( "Please enter the username." );
	     document.loginForm.username.focus() ;
	     return false;
	   }
	   if( document.loginForm.password.value == "" )
	   {
	     alert( "Please enter the password." );
	     document.loginForm.password.focus() ;
	     return false;
	   }

	   return( true );
	}
</script>
</html>