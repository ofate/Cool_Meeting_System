package service;

import java.sql.SQLException;
import java.util.List;

import dao.MeetingRoomDao;
import entity.MeetingRoom;

public class MeetingRoomService {
	MeetingRoomDao meetingRoomDao = new MeetingRoomDao();
	/**
	 * 获取会议室集合
	 * @return 会议室集合
	 * @throws SQLException
	 */
	public List<MeetingRoom> getMeetingRooms() throws SQLException{
		return meetingRoomDao.getMeetingRooms();
	}
}
