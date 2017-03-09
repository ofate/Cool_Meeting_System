<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>CoolMeeting会议管理系统</title>
	<link rel="stylesheet" href="styles/common.css"/>
    </head>
    <body>
        <div class="page-header">
            <div class="header-banner">
                <img src="images/header.png" alt="CoolMeeting"/>
            </div>
            <div class="header-title">
                欢迎访问Cool-Meeting会议管理系统
            </div>
            <div class="header-quicklink">
                <!-- 欢迎您，<strong>admin</strong>
                <a href="changepassword.html">[修改密码]</a> -->
                <a href="login.jsp">欢迎，请登录</a>
            </div>
        </div>
        <div class="page-body">
        	<%@include file="pagepart/pagesidebar.jsp" %>
            <div class="page-content">
                <div class="content-nav">
                    登录
                </div>
                <!-- <form action="login" method="post" > -->
                <form>
                    <fieldset>
                        <legend>登录信息</legend>
                        <table class="formtable" style="width:50%">
                        	<tr>
                        		<td></td>
                        		<td><div id="divMassage"></div></td>
                        	</tr>
                            <tr>
                                <td>账号名:</td>
                                <td>
                                    <input id="accountname" name="accountname" type="text" />
                                </td>
                            </tr>
                            <tr>
                                <td>密码:</td>
                                <td>
                                    <input id="password" name="password" type="password" />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="command">
                                    <!-- <input type="button" value="登录" class="clickbutton" id="btnOk" /> -->
                                    <input type="button" value="登录" class="clickbutton" onclick="login()"/>
                                    <!-- <input type="button" value="登录" class="clickbutton" id="btnOk"/> -->
                                    <!-- <input type="submit" value="登录" class="clickbutton" /> -->
                                    <input type="button" value="返回" class="clickbutton" onclick="window.history.back();"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
        <div class="page-footer">
            <hr/>
            更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a>
            <img src="images/footer.png" alt="CoolMeeting"/>
        </div>
<script src="jQuery/jquery-1.6.4.min.js" type="text/javascript"></script>
<script type="text/javascript">

window.onload(
		function(){
			$.post('Init')	
		}
		)

$(function(){
	$("#btnOk").click(function(){
		var accountname = $("#accountname").val();
		var password=$("#password").val();
		$.post("login",{
			accountname:accountname,
			password:password
		},function(data){
			if(data == "fail"){
				$("#divMassage").css("color","red").html("*用户名或密码不正确");
			} else {
				window.location.href="admin/notifications.jsp";
			}
		})
		/* $.getJSON("login",{
			accountname:$("#accountname").val(),
			password:$("#password").val()
		},function(data){
			if(data[0] == "fail"){
				$("#divError").css("color","red").html("*用户名或密码不正确");
			} else {
				window.location.href="admin/notifications.jsp";
			}
		}) */
	})
})

function login() {
	var employeename;
	$.getJSON("login",{
		accountname:$("#accountname").val(),
		password:$("#password").val()
		},function(data){
			$("#divMassage").html("");
			if(data[1].loginRes == "success") {
				window.location.href="admin/notifications.jsp";
			} else {
				$("#divMassage").css("color","red").html("*用户名或密码不正确");
			}
		}
	)
}
</script>
    </body>
</html>