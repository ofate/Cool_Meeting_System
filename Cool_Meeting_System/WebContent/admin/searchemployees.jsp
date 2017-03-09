<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CoolMeeting会议管理系统</title>
        <link rel="stylesheet" href="../styles/common.css"/>
        <style type="text/css">
            
        </style>
    </head>
    <body onload="SearchEmployees()">
    	<%@include file="../pagepart/pagehead.jsp" %>
        <div class="page-body">
        	<%@include file="../pagepart/pagesidebar.jsp" %>
            <div class="page-content">
                <div class="content-nav">
                    会议预定 > 搜索员工
                </div>
                <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
                <form>
                    <fieldset>
                        <legend>搜索会议</legend>
                        <table class="formtable">
                            <tr>
                                <td>姓名：</td>
                                <td>
                                    <input type="text" id="employeename" maxlength="20"/>
                                </td>
                                <td>账号名：</td>
                                <td>
                                    <input type="text" id="accountname" maxlength="20"/>
                                </td>
                                <td>状态：</td>
                                <td>
                                    <input type="radio" id="status" name="status" value="1"/><label>已批准</label>
                                    <input type="radio" id="status" name="status" value="0"/><label>待审批</label>
                                    <input type="radio" id="status" name="status" value="2"/><label>已关闭</label>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="6" class="command">
                                    <input type="button" id="search" class="clickbutton" value="查询" onclick="javascript:SearchEmployees()"/>
                                    <input type="reset" class="clickbutton" value="重置"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
                <div>
                    <h3 style="text-align:center;color:black">查询结果</h3>
                    <div class="pager-header">
                        <div class="header-info">
                            共<span id="spanrows" class="info-number"></span>条结果，
                            分成<span id="spanpages" class="info-number"></span>页显示，
                            当前第<span id="spancurrentpage" class="info-number"></span>页
                        </div>
                        <div class="header-nav">
                            <input id="toTop" type="button" class="clickbutton" value="首页" onclick="toTop()"/>
                            <input id="prePage" type="button" class="clickbutton" value="上页" onclick="prePage()"/>
                            <input id="nextPage" type="button" class="clickbutton" value="下页" onclick="netxPage()"/>
                            <input id="toEnd" type="button" class="clickbutton" value="末页" onclick="toEnd()"/>
                            跳到第<input type="text" id="pagenum" class="nav-number"/>页
                            <input id="jump" type="button" class="clickbutton" value="跳转" onclick="selPage()"/>
                        </div>
                    </div>
                </div>
                <table id="listtable" class="listtable" style="display: none">
                	<tr class="listheader">
                        <th>姓名</th>
                        <th>账号名</th>
                        <th>联系电话</th>
                        <th>电子邮件</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${requestScope.employees }" var="e">
                    	<tr>
	                    	<td>${e.employeename }</td>
	                    	<td>${e.username }</td>
	                    	<td>${e.phone }</td>
	                    	<td>${e.email }</td>
	                    	<td><a id="closeaccountbutton" class="clickbutton" style="disabled:true" href="#">关闭账号</a></td>
                    	</tr>
                    </c:forEach>
                </table>
                <table id="searchtable" class="listtable"></table>
            </div>
        </div>
        <div class="page-footer">
            <hr/>
            更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a>
            <img src="../images/footer.png" alt="CoolMeeting"/>
        </div>
        
        <script src="../jQuery/jquery-1.6.4.min.js" type="text/javascript"></script>
       	<script language="javascript">
       	//当前页变量
       	var currentpage=1;
       	//总页数变量
       	var pages = 0;
       	//总数据量
       	var rows = 0;
       	//搜索条件
       	var condition="";
       	
       	//跳到首页
       	function toTop() {
       		/* window.location.href="ShowSearchemployees?currentpage=1"; */
       		currentpage = 1;
       		SearchEmployees();
       	}
       	//跳到尾页
       	function toEnd() {
       		currentpage = pages;
       		SearchEmployees();
       	}
       	//跳到上一页
       	function prePage() {
       		if(currentpage <= 1) {
       			alert("已到首页");
       		} else {
       			/* window.location.href="ShowSearchemployees?currentpage=${requestScope.currentpage-1}"; */
       			currentpage = currentpage-1;
       			SearchEmployees();
       		}
       	}
       	//跳到下一页
       	function netxPage() {
       		if(currentpage == pages) {
       			alert("已到尾页");
       		} else {
       			/* window.location.href="ShowSearchemployees?currentpage=${requestScope.currentpage+1}"; */
       			currentpage =currentpage+1;
       			SearchEmployees();
       		}
       	}
       	//跳转到指定页
       	function selPage() {
       		/* window.location.href="ShowSearchemployees?currentpage="+$('#pagenum').val(); */
       		var selpage = document.getElementById("pagenum").value;
       		if(selpage>0 && selpage<=pages){
       			currentpage = selpage;
           		SearchEmployees();
       		} else {
       			alert("输入页码无效！");
       		}
       	}
       	//获取状态值
       	function getRadioValue() {
       		var obj = document.getElementsByName("status");
       		for (var i=0; i<obj.length; i++) {
       			if(obj[i].checked) {
       				return obj[i].value;
       			}
       		}
       		return null;
       	}
       	//获取搜索条件——员工姓名的值
       	function getEmployeenameCondition() {
       		if($("#employeename").val() != "") {
       			return $("#employeename").val()
       		} else {
       			return null;
       		}
       	}
       	//获取搜索条件——登录名的值
       	function getAccountnameCondition() {
       		if($("#accountname").val() != "") {
       			return $("#accountname").val()
       		} else {
       			return null;
       		}
       	}
       	
       	/* function SearchByAjax(){
       		var xmlhttp;
       		if(window.XMLHttprequest) {
       			xmlhttp=new XMLHttpRequest();
       		} else {
       			xmlhttp	=new ActiveXObject("Microsoft.XMLHTTP");
       		}
       	} */
       	
       	//创建搜索条件
       	function CreateCondition() {
       		condition = "";
       		if(getRadioValue() != null){
       			condition = condition+"AND sta="+getRadioValue();
       		}
       		if(getEmployeenameCondition() != null) {
       			condition = condition+" AND employeename like '%"+getEmployeenameCondition()+"%'";
       		}
       		if(getAccountnameCondition() != null) {
       			condition = condition+" AND username like '%"+getAccountnameCondition()+"%'";
       		}
       		return condition;
       	}
       	
       	//查询员工
       	function SearchEmployees() {
       		var text = '<tr class="listheader"><th>姓名</th><th>账号名</th><th>联系电话</th><th>电子邮件</th><th>操作</th></tr>';
       		/* $("#listtable").hide();
       		$("#searchtable").show(); */
       			$.getJSON("searchemployee_SerchEmployees",{
       				condition:CreateCondition(),
	       			currentpage:currentpage
	       		},function(data){
	       			currentpage = data[data.length-3];
	       			pages = data[data.length-2];
	       			rows = data[data.length-1];
	       			$("#listtable").hide();
	       			for(var i=0; i<data.length-3; i++){
	       				text += "<tr>";
	       				text += "<td>" + data[i]["employeename"]+"</td>";
	       				text += "<td>" + data[i]["username"]+"</td>";
	       				text += "<td>" + data[i]["phone"]+"</td>";
	       				text += "<td>" + data[i]["email"]+"</td>";
	       				var empid = data[i]["employeeid"];
	       				
	       				var status = data[i]["status"];
	       				if(status == 2){
	       					text += '<td><a id='+empid+' class="clickbutton" style="disabled:true" href="javascript:ModAccount('+empid+')">激活账号</a></td></tr>'
	       				}else{
	       					text += '<td><a id='+empid+' class="clickbutton" href="javascript:ModAccount('+empid+')">关闭账号</a></td></tr>';
	       				}
	       				
	       			}
		       		$("#searchtable").html(text);
		       		$("#spanrows").html(rows);
		       		$("#spanpages").html(pages);
		       		$("#spancurrentpage").html(currentpage);
	       		})
       	}
       	
       	function ModAccount(id) {
       		/* alert("modaccount"); */
       		var modtype = document.getElementById(id).innerHTML;
       		var type="";
       		
       		if(modtype == "关闭账号"){
       			type="close";
       		}else{
       			type="active";
       		}
       		
       		$.post("ModAccount",{
       			employeeid:id,
       			type:type
       		},function(data){
       			/* if(data == "success") {
       				$("#"+id).html("激活账号");
       			}
       			if(data == "fail"){
       				$("#"+id).html("关闭账号");
       			} */
       			SearchEmployees();
       		})
       	}
       	
       	</script>
    </body>
</html>