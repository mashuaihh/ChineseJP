<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>关于</title>
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
			            <li class="active"><a href="#">关于</a></li>
			            <li><a href="contact.jsp">联系我们</a></li>
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
									<li><a href="<%=adminMemPath %>">管理用户</a></li>
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
      <div class="jumbotron" style="text-align: center; padding-top: 50px; padding-bottom: 67px;">
      	<p><b>教育部哲学社会科学研究重大课题攻关项目“东亚国家语言中<br/>汉字词汇使用现状研究”中间成果</b></p>
        <h1><b>中日对译语料库</b></h1>
        <br/>
        <br/>
        <br/>
        <div class="row" style="text-align: left;">
        	<div class="col-md-7">
        		<p>    从研究中华文化海外传播规律及其海外影响力、服务对外汉语教学、外语教学、外来词使用规范、语言信息智能处理等目的出发，利用大规模语料库和计算语言学、语料库语言学理论以及计算机自然语言处理技术，在外语框架下研究日本、韩国、越南等东亚国家当代语言生活中汉字词汇的使用状况，调查这些国家语言中汉字词汇的条目、汉字词汇的使用频率。从形态、意义、用法等角度，开展日、韩、越等东亚国家语言汉字词汇和我国现代汉语词汇的比较研究，包括研究我国和日、韩、越等国通用的汉字词汇问题。还将从自然语言处理、词汇学、认知语言学等角度对东亚国家汉字词汇进行理论探索。建设东亚国家语言汉字词汇数据库和汉字词汇认同网站。由于日、韩、越等国家政府有关本国语言中汉字使用的政策深刻影响着汉字词汇在这些国家语言中的使用，因此本课题还将研究日、韩、越等国政府有关汉字和汉字词汇使用的政策及其变化。
  </p> 
   <p>该攻关课题所研究的汉字词汇包括两类：一类是从汉语借入、音韵上与汉语有对应关系、根据其音韵规律可以转写成汉字的日韩越等语言中的词汇；另一类是日韩越等国人民利用汉字自创的汉字词汇以及被汉语吸收且可以用汉字书写的这些国家语言的固有词汇。该攻关课题由日语汉字词汇使用现状研究子课题、韩国语汉字词汇使用现状研究子课题、越南语汉字词汇使用现状研究子课题、东亚国家语言汉字词汇研究相关计算机软件工具开发子课题、日本韩国越南关于汉字及汉字词汇使用政策研究子课题等五个子课题组成。
   </p>
   <p>通过本课题研究我们计划实现以下几个目标： 一、调查清楚日本、韩国、越南等国家当代语言生活中所使用的汉字词汇的条目、汉字词汇的使用频率及分布情况；二、调查清楚中、日、韩、越等国家通用汉字词汇的数量、条目；三、调查清楚日、韩、越等国家独有汉字词汇的条目；四、研究面向我国日语教学、韩语教学、越语教学的这三种语言的核心汉字词汇；五、研究面向对日、韩、朝、越等国家汉语教学的汉语核心词汇；六、研究中、日、韩、越核心汉字词汇形态、意义、用法的异同；七、实现东亚国家语言中汉字词汇认同网站，把研究成果建设成数据库，通过互联网发布供学术研究和语言学习者使用；八、开发汉日、汉韩、汉越语料库分析工具；九、研究中日韩越通用汉字词汇的自动识别工具；十、研究开发通用词汇按意义（同形同义、同形类义、同形异义）计算机辅助归类工具；十一、研究日、韩、朝、越等国关于汉字和汉字词汇使用的政策。
   </p>
   <p>该课题攻关团队由北京外国语大学、解放军外国语学院等单位的30余名专家学者组成。该重大项目首席专家由北京外国语大学北京日本学研究中心施建军教授担任。	
   				</p>
        	</div>
        	<div class="col-md-5">
        		<a target="_blank" href="http://www.cjkvword.org" class="thumbnail">
        			<img src="images/new.jpg" />
        		</a>
        	</div>
        </div>
      </div>
    </div>

</body>
</html>