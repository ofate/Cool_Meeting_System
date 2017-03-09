<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>CoolMeeting会议管理系统</title>
        <link rel="stylesheet" href="../styles/common.css"/>
        <style type="text/css">
            #divfrom{
                float:left;
                width:200px;
            }
            #divto{
                float:left;
                width:200px;
            }
            #divoperator{
                float:left;
                width:50px;
                padding:60px 5px;
            }
            #divoperator input[type="button"]{
                margin:10px 0;
            }
            #selDepartments{
                display:block;
                width:100%;
            }
            #selEmployees{
                display:block;
                width:100%;
                height:200px;
            }
            #selSelectedEmployees{
                display:block;
                width:100%;
                height:225px;
            }
        </style>
    </head>
    <body>
    	<%@include file="../pagepart/pagehead.jsp" %>
        <div class="page-body">
        	<%@include file="../pagepart/pagesidebar.jsp" %>
            <div class="page-content">
                <div class="content-nav">
                    会议预定 > 修改会议预定
                </div>
                <form>
                    <fieldset>
                        <legend>会议信息</legend>
                        <table id="meetingDetails" class="formtable">
                            <tr>
                                <td>会议名称：</td>
                                <td id='meetingName'></td>
                            </tr>
                            <tr>
                                <td>预计参加人数：</td>
                                <td id='peoples'></td>
                            </tr>
                            <tr>
                                <td>预计开始时间：</td>
                                <td id='startTime'></td>
                            </tr>
                            <tr>
                                <td>预计结束时间：</td>
                                <td id='endTime'>
                                </td>
                            </tr>
                            <tr>
                                <td>会议说明：</td>
                                <td>
                                    <textarea id="description" rows="5" readonly></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td>参会人员：</td>
                                <td>
                                    <table id='meetingPeoples' class="listtable">
                                        <tr class="listheader">
                                            <th>姓名</th>
                                            <th>联系电话</th>
                                            <td>电子邮件</td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td class="command" colspan="2">
                                    <input type="button" class="clickbutton" value="返回" onclick="window.history.back();"/>
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
            <img src="../images/footer.png" alt="CoolMeeting"/>
        </div>
        <script src="../jQuery/jquery-1.6.4.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="../javascript/js_meetingdetails.js"></script>
    </body>
</html>