<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="js/angular.min.js"></script>
	<script src="js/msController.js"></script>
<title>Sign Up</title>
</head>

<body>
<div ng-app="">

  <div ng-controller="formController" >
	<fieldset>
	<h1>用户注册</h1>
<!-- 		<form name="myForm" action="signUp.do" method="post" ng-submit="register()"> -->
		<form name="myForm" action="signUp.do" method="post" >
			
			<label>用户姓名：</label>
			<input name="username" type="text" ng-model="person.name" ng-minlength=3 required/>
			<small ng-show="myForm.username.$error.required">*该项必须填写</small>
			<small ng-show="myForm.username.$error.minlength">*至少输入三个以上字符</small>
			<small ng-show="myForm.username.$valid">√</small>
		</br>

			<label>邮箱地址：</label>
  			<input name="email" type="email" ng-model="person.email" required/>
  				<small ng-show="myForm.email.$error.required">*该项必须填写</small>
  				<small ng-show="myForm.email.$error.email">*email 地址不正确</small>
  				<small ng-show="myForm.email.$valid">√</small>
 <!--  				<div ng-show="myForm.$dirty && myForm.personEmail.$invalid">
 	<small ng-show="myForm.personEmail.$error.email">email 地址不对</small>
 </div> -->
  			</br>
			
			<label>设置密码：</label>
  			<input name="psw" type="password" ng-model="person.pswd1" required/>
  				<small ng-show="myForm.psw.$error.required">*该项必须填写</small>
  				<small ng-show="myForm.psw.$valid">√</small></br>
  			

  			<label>重输密码：</label>
  			<input name="personPswd2" type="password" ng-model="person.pswd2" required/>
  				<small ng-show="myForm.personPswd2.$error.required">*该项必须填写</small>
  				<small ng-show="samePswd">√</small>
  				<small ng-show="!samePswd">*两次输入密码不一致</small>
  			</br>
  			
  			<label>所在单位：</label>
  			<input name="institute" type="text" ng-model="person.institute" required/>
  				<small ng-show="myForm.institute.$error.required">*该项必须填写</small>
  			</br>

  			<label>备注：</label>
  			<input name="memo" type="text" />
  			</br>

  			<input name="personCheck" type="checkbox" ng-model="person.check" required/><small>是否同意用户守则</small></br>
<!-- 			除了ng自带的表单验证外，加上密码验证，才可以注册 -->
  			<button type="submit" ng-disabled="myForm.$invalid || !samePswd" >注册</button>
 
		</form>
 	</fieldset> 

   </div>
  
</div>	
	
</body>
</html>