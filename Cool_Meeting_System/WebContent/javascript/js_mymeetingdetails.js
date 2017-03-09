/**
 *
 */
 document.onload = DetailLoad();
 function DetailLoad(){
 	var peoples;
 	var meetingPeoples = $('#meetingPeoples');
 	$.getJSON('MeetingDetail'
 			,{meetingID:sessionStorage.meetingID}
 			,function(result){
 				peoples = result.length;
 				for(var i=0;i<result.length;i++){
 					var ele = '<tr><td>' + result[i].employeename + '</td>'
 							+ '<td>' + result[i].phone + '</td>'
 							+ '<td>' + result[i].email + '</td>'
 							+ '</tr>';
 					meetingPeoples.append(ele);
 				}
 				$('#peoples').html(peoples);
 			});
 	$('#meetingName').html(sessionStorage.meetingName);
 //	$('#peoples').html(peoples);
 	$('#startTime').html(sessionStorage.startTime);
 	$('#endTime').html(sessionStorage.endTime);
 	$('#description').html(sessionStorage.desc);
 }
