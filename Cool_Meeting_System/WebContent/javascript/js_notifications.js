/**
 * 
 */
document.onload = getMeetings();
function getMeetings(){
	$.getJSON("notifications"
			,function(result){
				for(var i=0; i<result[0].length; i++){
					if(result[0][i].canceledtime != null && result[0][i].canceledtime.toString() != ""){
						continue;
					}
					var timeTemp;
					
					timeTemp = new Date(Number(result[0][i].starttime.time) 
							- Number(result[0][i].starttime.timezoneOffset*60*1000));
					var year = timeTemp.getFullYear();
					var month = timeTemp.getMonth() + 1;
					var day = timeTemp.getDate();
					var hours = timeTemp.getHours().toString().length<2?'0' + timeTemp.getHours().toString():timeTemp.getHours();
					var minutes = timeTemp.getMinutes().toString().length<2?'0'+timeTemp.getMinutes().toString():timeTemp.getMinutes();
					var startTime = year + "-" + month + "-" + day + " " + hours + "：" + minutes;
					
					timeTemp = new Date(Number(result[0][i].endtime.time) 
							- Number(result[0][i].endtime.timezoneOffset*60*1000));
					var year = timeTemp.getFullYear();
					var month = timeTemp.getMonth() + 1;
					var day = timeTemp.getDate();
					var hours = timeTemp.getHours().toString().length<2?'0' + timeTemp.getHours().toString():timeTemp.getHours();
					var minutes = timeTemp.getMinutes().toString().length<2?'0'+timeTemp.getMinutes().toString():timeTemp.getMinutes();
					var endTime = year + "-" + month + "-" + day + " " + hours + "：" + minutes;
					
					var target = $('#meetings');
					var inHTML = "<tr id=" + result[0][i].meetingid + "><td>" + result[0][i].meetingname + "</td>"
						+ "<td>" + result[0][i].meetingroom.roomName + "</td>"
						+ "<td>" + startTime + "</td>"
						+ "<td>" + endTime + "</td>"
						+ '<td hidden="hidden">' + result[0][i].description + '</td>'
						+ '<td hidden="hidden"></td>'
						+ '<td><a class="clickbutton" name="'+result[0][i].meetingid+'" onclick="GotoDetail('+result[0][i].meetingid+')" href="../admin/meetingdetails.jsp">查看详情</a></td></tr>';
					
					target.append(inHTML);
				}
				
				for(var i=0; i<result[1].length; i++){
					var timeTemp;
					
					timeTemp = new Date(Number(result[1][i].starttime.time) 
							- Number(result[1][i].starttime.timezoneOffset*60*1000));
					var year = timeTemp.getFullYear();
					var month = timeTemp.getMonth() + 1;
					var day = timeTemp.getDate();
					var hours = timeTemp.getHours().toString().length<2?'0' + timeTemp.getHours().toString():timeTemp.getHours();
					var minutes = timeTemp.getMinutes().toString().length<2?'0'+timeTemp.getMinutes().toString():timeTemp.getMinutes();
					var startTime = year + "-" + month + "-" + day + " " + hours + "：" + minutes;
					
					timeTemp = new Date(Number(result[1][i].endtime.time) 
							- Number(result[1][i].endtime.timezoneOffset*60*1000));
					var year = timeTemp.getFullYear();
					var month = timeTemp.getMonth() + 1;
					var day = timeTemp.getDate();
					var hours = timeTemp.getHours().toString().length<2?'0' + timeTemp.getHours().toString():timeTemp.getHours();
					var minutes = timeTemp.getMinutes().toString().length<2?'0'+timeTemp.getMinutes().toString():timeTemp.getMinutes();
					var endTime = year + "-" + month + "-" + day + " " + hours + "：" + minutes;
					
					var target = $('#cancelmeetings');
					var cancelledMeeting = 'cancelledMeeting';
					var inHTML = "<tr id=" + result[1][i].meetingid + "><td>" + result[1][i].meetingname + "</td>"
						+ "<td>" + result[1][i].meetingroom.roomName + "</td>"
						+ "<td>" + startTime + "</td>"
						+ "<td>" + endTime + "</td>"
						+ "<td></td>"
						+ '<td hidden="hidden">' + result[0][i].description + '</td>'
						+ '<td hidden="hidden">' + cancelledMeeting + '</td>'
						+ '<td><a class="clickbutton" name="'+result[0][i].meetingid+'" onclick="GotoDetail('+result[1][i].meetingid+')" href="../admin/meetingdetails.jsp">查看详情</a></td></tr>';
					
					target.append(inHTML);
				}
	})
}

function GotoDetail(meetingID){
	sessionStorage.clear();
	sessionStorage.meetingID = meetingID;
	var children = $('#' + meetingID).children();
	var meetingName;
	if(children[6].innerHTML == ""){
		meetingName = children[0].innerHTML;
	} else {
		meetingName = children[0].innerHTML + '（已取消）';
	}
	sessionStorage.meetingName = meetingName;
	sessionStorage.meetingRoom = children[1].innerHTML;
	sessionStorage.startTime = children[2].innerHTML;
	sessionStorage.endTime = children[3].innerHTML;
	sessionStorage.desc = children[4].innerHTML;
}