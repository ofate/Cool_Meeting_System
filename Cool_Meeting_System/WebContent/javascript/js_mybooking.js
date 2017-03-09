/**
 *
 */
document.onload = meetingLoad();
function meetingLoad(){
	$.getJSON('getAllMeetingsByBookerID'
			,function(result){
				for(var i=0;i<result.length;i++){
					var timeTemp;

					var timeTemp;

					timeTemp = new Date(Number(result[i].starttime.time)
							- Number(result[i].starttime.timezoneOffset*60*1000));
					var year = timeTemp.getFullYear();
					var month = timeTemp.getMonth() + 1;
					var day = timeTemp.getDate();
					var hours = timeTemp.getHours().toString().length<2?'0' + timeTemp.getHours().toString():timeTemp.getHours();
					var minutes = timeTemp.getMinutes().toString().length<2?'0'+timeTemp.getMinutes().toString():timeTemp.getMinutes();
					var startTime = year + "-" + month + "-" + day + " " + hours + "：" + minutes;

					timeTemp = new Date(Number(result[i].endtime.time)
							- Number(result[i].endtime.timezoneOffset*60*1000));
					var year = timeTemp.getFullYear();
					var month = timeTemp.getMonth() + 1;
					var day = timeTemp.getDate();
					var hours = timeTemp.getHours().toString().length<2?'0' + timeTemp.getHours().toString():timeTemp.getHours();
					var minutes = timeTemp.getMinutes().toString().length<2?'0'+timeTemp.getMinutes().toString():timeTemp.getMinutes();
					var endTime = year + "-" + month + "-" + day + " " + hours + "：" + minutes;

					timeTemp = new Date(Number(result[i].reservationtime.time)
							- Number(result[i].reservationtime.timezoneOffset*60*1000));
					var year = timeTemp.getFullYear();
					var month = timeTemp.getMonth() + 1;
					var day = timeTemp.getDate();
					var hours = timeTemp.getHours().toString().length<2?'0' + timeTemp.getHours().toString():timeTemp.getHours();
					var minutes = timeTemp.getMinutes().toString().length<2?'0'+timeTemp.getMinutes().toString():timeTemp.getMinutes();
					var reservationtime = year + "-" + month + "-" + day + " " + hours + "：" + minutes;

					var target = $('#bookedMeetings');
					var inHTML = "<tr id=" + result[i].meetingid + "><td>" + result[i].meetingname + "</td>"
						+ "<td>" + result[i].meetingroom.roomName + "</td>"
						+ "<td>" + startTime + "</td>"
						+ "<td>" + endTime + "</td>"
						+ "<td>" + reservationtime + "</td>"
						+ '<td hidden="hidden">' + result[i].description + '</td>'
						+ '<td hidden="hidden"></td>'
						+ '<td><a class="clickbutton" name="'+result[i].meetingid+'" onclick="GotoDetail('+result[i].meetingid+')">查看/撤销</a></td></tr>';
					target.append(inHTML);
				}
			});
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
	sessionStorage.desc = children[5].innerHTML;
	window.location.href = "mymeetingdetails.jsp";
}
