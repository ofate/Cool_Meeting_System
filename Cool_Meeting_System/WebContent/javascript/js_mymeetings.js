/**
 * 
 */
document.onload = myMeetingLoad();

function myMeetingLoad(){
	$.getJSON('getMeetingsByEmpID',
			function(result){
				for(var i=0;i<result.length;i++){
					if(result[i].canceledtime != null && result[i].canceledtime.toString() != ""){
						continue;
					}

					var startTime = dateUtil(result[i].starttime);
					var endTme = dateUtil(result[i].endtime);
					var reservationTime = dateUtil(result[i].reservationtime);
					
					timeTemp = new Date(Number(result[i].endtime.time) 
							- Number(result[i].endtime.timezoneOffset*60*1000));
					var year = timeTemp.getFullYear();
					var month = timeTemp.getMonth() + 1;
					var day = timeTemp.getDate();
					var hours = timeTemp.getHours().toString().length<2?'0' + timeTemp.getHours().toString():timeTemp.getHours();
					var minutes = timeTemp.getMinutes().toString().length<2?'0'+timeTemp.getMinutes().toString():timeTemp.getMinutes();
					var endTime = year + "-" + month + "-" + day + " " + hours + "：" + minutes;
					
					var target = $('#meetings');
					var inHTML = "<tr id=" + result[i].meetingid + "><td>" + result[i].meetingname + "</td>"
						+ "<td>" + result[i].meetingroom.roomName + "</td>"
						+ "<td>" + startTime + "</td>"
						+ "<td>" + endTime + "</td>"
						+ "<td>" + reservationTime + "</td>"
						+ "<td>" + result[i].booker.employeename + "</td>"
						+ '<td hidden="hidden">' + result[i].description + '</td>'
						+ '<td hidden="hidden"></td>'
						+ '<td><a class="clickbutton" name="'+result[i].meetingid+'" onclick="GotoDetail('+result[i].meetingid+')">查看详情</a></td></tr>';
					
					target.append(inHTML);
				}
	});
}

function GotoDetail(meetingID){
	sessionStorage.clear();
	sessionStorage.meetingID = meetingID;
	var children = $('#' + meetingID).children();
	var meetingName = children[0].innerHTML;
	sessionStorage.meetingName = meetingName;
	sessionStorage.meetingRoom = children[1].innerHTML;
	sessionStorage.startTime = children[2].innerHTML;
	sessionStorage.endTime = children[3].innerHTML;
	sessionStorage.desc = children[6].innerHTML;
	
	window.location.href = 'meetingdetails.jsp';
}

function dateUtil(date){
	var timeTemp = new Date(Number(date.time) 
			- Number(date.timezoneOffset*60*1000));
	var year = timeTemp.getFullYear();
	var month = timeTemp.getMonth() + 1;
	var day = timeTemp.getDate();
	var hours = timeTemp.getHours().toString().length<2?'0' + timeTemp.getHours().toString():timeTemp.getHours();
	var minutes = timeTemp.getMinutes().toString().length<2?'0'+timeTemp.getMinutes().toString():timeTemp.getMinutes();
	var date = year + "-" + month + "-" + day + " " + hours + "：" + minutes;
	
	return date;
}