package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.MeetingRoom;
import jdbc.DBUtil;

public class MeetingRoomDao {

	private DBUtil dbutil;

	public MeetingRoomDao() {
		dbutil = DBUtil.getInstance();
	}

	/**
	 * 获取会议室信息集合
	 * 
	 * @return 会议室信息集合
	 * @throws SQLException
	 */
	public List<MeetingRoom> getMeetingRooms() throws SQLException {
		List<MeetingRoom> meetingRooms = new ArrayList<MeetingRoom>();
		String strSql = "SELECT roomid,roomnum,roomname,capacity,sta,description FROM meetingroom";
		ResultSet rs = dbutil.getResultSet(strSql);
		while (rs.next()) {
			MeetingRoom meetingroom = new MeetingRoom(rs.getInt("roomid"), rs.getInt("roomnum"),
					rs.getString("roomname"), rs.getInt("capacity"), rs.getString("sta"), rs.getString("description"));
			meetingRooms.add(meetingroom);
		}
		dbutil.CloseConn();
		return meetingRooms;
	}

	/**
	 * 根据会议室ID查询会议室信息
	 * 
	 * @param roomID
	 *            会议室ID
	 * @return 会议室对象
	 * @throws SQLException
	 */
	public MeetingRoom getMeetingRoomsByRoomID(int roomID) throws SQLException {
		MeetingRoom meetingroom = null;
		String strSql = "SELECT roomid,roomnum,roomname,capacity,sta,description FROM meetingroom WHERE roomid=?";
		ResultSet rs = dbutil.getResultSet(strSql, roomID);
		if (rs.next()) {
			meetingroom = new MeetingRoom();
			meetingroom.setRoomID(rs.getInt("roomid"));
			meetingroom.setRoomNum(rs.getInt("roomnum"));
			meetingroom.setRoomName(rs.getString("roomname"));
			meetingroom.setCapacity(rs.getInt("capacity"));
			meetingroom.setSta(rs.getString("sta"));
			meetingroom.setdescription(rs.getString("description"));
		}
		return meetingroom;
	}

	// 根据会议室名称获取会议室集合
}
