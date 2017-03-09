<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 		<div class="page-sidebar">
           <div class="sidebar-menugroup">
               <div class="sidebar-grouptitle">个人中心</div>
               <ul class="sidebar-menu">
                   <li class="sidebar-menuitem"><a href="../admin/notifications.jsp">最新通知</a></li>
                   <li class="sidebar-menuitem active"><a href="../admin/mybookings.jsp">我的预定</a></li>
                   <li class="sidebar-menuitem"><a href="../admin/mymeetings.jsp">我的会议</a></li>
               </ul>
           </div>
           <div class="sidebar-menugroup">
               <div class="sidebar-grouptitle">人员管理</div>
               <ul class="sidebar-menu">
                   <li class="sidebar-menuitem"><a href="departments.jsp">部门管理</a></li>
                   <li class="sidebar-menuitem"><a href="register.jsp">员工注册</a></li>
                   <li class="sidebar-menuitem"><a href="approveaccount.jsp">注册审批</a></li>
                   <li class="sidebar-menuitem"><a href="searchemployees.jsp">搜索员工</a></li>
               </ul>
           </div>
                <div class="sidebar-menugroup">
                    <div class="sidebar-grouptitle">会议预定</div>
                    <ul class="sidebar-menu">
                        <li class="sidebar-menuitem"><a href="Addmeetingroom">添加会议室</a></li>
                        <li class="sidebar-menuitem"><a href="Meetingrooms">查看会议室</a></li>
                        <li class="sidebar-menuitem"><a href="Bookmeeting">预定会议</a></li>
                        <li class="sidebar-menuitem"><a href="Searchmeetings">搜索会议</a></li>
                    </ul>
                </div>
            </div>
</body>
</html>