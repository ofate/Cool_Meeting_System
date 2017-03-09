<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="page-header">
	    <div class="header-banner">
	        <img src="../images/header.png" alt="CoolMeeting"/>
	    </div>
	    <div class="header-title">
	        欢迎访问Cool-Meeting会议管理系统
	    </div>
	    <div class="header-quicklink">
	        欢迎您，<strong id="username">${sessionScope.employee.employeename}</strong>
	        <a href="changepassword.html">[修改密码]</a>
	    </div>
	</div>
	<script src="../jQuery/jquery-1.6.4.min.js" type="text/javascript"></script>
	<script>
		$(document).ready(function(){
			if($("#username").text().length == 0){
				alert('请登录');
				window.location.href = "../login.jsp";
			}			
		})
	</script>
</body>
</html>