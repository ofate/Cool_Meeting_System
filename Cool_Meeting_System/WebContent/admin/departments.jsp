<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CoolMeeting会议管理系统</title>
        <link rel="stylesheet" href="../styles/common.css"/>
    </head>
    <body>
    	
    	<%@include file="../pagepart/pagehead.jsp" %>
        <div class="page-body">
        	<%@include file="../pagepart/pagesidebar.jsp" %>
            <div class="page-content">
                <div class="content-nav">
                    人员管理 > 部门管理
                </div>
                <form action="AddDepartment" method="post">
                    <fieldset>
                        <legend>添加部门</legend>
	                        <div id="addsuccess" style="display:none;color: red;"></div>
	                        部门名称:
	                        <input type="text" id="departmentname" name="departmentname" maxlength="20" />
	                        <input type="submit" class="clickbutton" value="添加" id="btnAdd"/>
                    </fieldset>
                </form>
                <table class="listtable">
                    <caption>所有部门:</caption>
                    <tr class="listheader">
                        <th>部门编号</th>
                        <th>部门名称</th>
                        <th>操作</th>
                    </tr>
                    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
                    <c:forEach items="${requestScope.departments}" var="d">
                    	<tr>
                    		<td>${d.departmentid}</td>
                    		<td>
                    			<div id="textdis${d.departmentid }">${d.departmentname }</div>
                    			<div id="textedit${d.departmentid }" style="display: none">
                    				<input id="hidden${d.departmentid }" value="${d.departmentname }" style="display: none" />
                    				<input id="input${d.departmentid }" type="text" value="${d.departmentname }" />
                    			</div>
                    		</td>
                    		<td>
                    			<div id="divdis${d.departmentid }" style="display: block">
                    				<a class="clickbutton" href="javascript:editable('${d.departmentid }')">编辑</a>
                    				<a class="clickbutton" href="javascript:deldept('${d.departmentid }')">删除</a>
                    			</div>
                    			<div id="divedit${d.departmentid }" style="display: none">
                    				<a id="submit" class="clickbutton" href="javascript:moddept('${d.departmentid }')">提交</a>
                            		<a class="clickbutton" href="javascript:editcancel('${d.departmentid }')">取消</a>
                            		<a class="clickbutton" href="javascript:deldept('${d.departmentid }')">删除</a>
                    			</div>
                    		</td>
                    	</tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <div class="page-footer">
            <hr/>
            更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a>
            <img src="../images/footer.png" alt="CoolMeeting"/>
        </div>
        
	<script src="../jQuery/jquery-1.6.4.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	
	var deptid;
	var input;
	
	//编辑按钮动作
	function editable(divid){
		deptid = divid;
		var divdis = document.getElementById("divdis"+divid);
		var divedit = document.getElementById("divedit"+divid);
		
		var textdis = document.getElementById("textdis"+divid);
		var textedit = document.getElementById("textedit"+divid);
		
		divdis.style.display = "none";
		divedit.style.display = "block";
		textdis.style.display = "none";
		textedit.style.display = "block";
	}
	//取消按钮动作
	function editcancel(divid){
		var divdis = document.getElementById("divdis"+divid);
		var divedit = document.getElementById("divedit"+divid);
		
		var textdis = document.getElementById("textdis"+divid);
		var textedit = document.getElementById("textedit"+divid);
		
		var input = document.getElementById("input"+divid);
		var hiddentinput = document.getElementById("hidden"+divid);
		
		input.value = hiddentinput.value;
		
		divdis.style.display="block";
		divedit.style.display = "none";
		textdis.style.display = "block";
		textedit.style.display = "none";
	}
	
	//提交按钮动作
	function moddept(deptid){
		var divdis = document.getElementById("divdis"+deptid);
		var divedit = document.getElementById("divedit"+deptid);
		
		var textdis = document.getElementById("textdis"+deptid);
		var textedit = document.getElementById("textedit"+deptid);
		
		var input = document.getElementById("input"+deptid);
		var hiddentinput = document.getElementById("hidden"+deptid);
		
		$.post("ModDepartment",{
			departmentid:deptid,
			departmentname:input.value
		},function(data){
			if(data == "success"){
				textdis.innerHTML=input.value;
				divdis.style.display="block";
				divedit.style.display = "none";
				textdis.style.display = "block";
				textedit.style.display = "none";
			} else {
				alert("fail");
			}
		})
	}
	
	//删除按钮动作
	function deldept(deptid){
		$.post("DelDepartment",{
			departmentid:deptid
		},function(data){
			if (data=="success"){
				window.location.href="ShowDepartments";
			} else {
				alert("fail");
			}
		})
	}

	
	
	/* $(function(){
		$("#btnAdd").click(function(){
			var departmentname = $("#departmentname").val();
			alert(departmentname);
			$.post("AddDepartment",{
				departmentname:departmentname
			},function(data){
				if(date == 'success'){
					$("#addsuccess").css("display","block").html("添加成功");
					$("#departmentname").html("");
				} else {
					$("#addsuccess").css("display","block").html("添加失败");
				}
			})
			
		})
	}) */
	</script>
    </body>
</html>