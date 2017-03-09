package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Meeting;
import jdbc.DBUtil;
import jdbc.RowCount;
import utils.daoutils.Base_MeetingDao;

public class MeetingDao extends Base_MeetingDao {

	public List<Meeting> meetings = new ArrayList<Meeting>();
	private DBUtil dbutil;

	public MeetingDao() {
		dbutil = DBUtil.getInstance();
		CleanVar();
	}

	/**
	 * 获取最大会议ID
	 * 
	 * @return 最大会议ID
	 * @throws SQLException
	 */
	public int getMaxMeetingID() throws SQLException {
		CleanVar();
		int maxID = 0;
		try {
			strSql.append("SELECT MAX(meeting_id) AS MAXID FROM meetings");
			rs = dbutil.getResultSet(strSql.toString());
			if (rs.next()) {
				maxID = rs.getInt("MAXID");
			}
			dbutil.CloseConn();
			return maxID;
		} catch (Exception e) {
			throw e;
		} finally {
			CleanVar();
		}
	}

	/**
	 * 搜索会议
	 * 
	 * @param start
	 *            搜索的开始
	 * @return 会议集合
	 * @throws SQLException
	 */
	public List<Meeting> getMeetings(int start, String... condition) throws SQLException {
		CleanVar();
		strSql.append(
				"SELECT meeting_id,meeting_name,room_id,reservationist_id,number_of_participants,start_time,end_time,reservation_time,canceled_time,description,sta ");
		strSql.append("FROM meetings WHERE 1=1");
		strSql.append(" " + condition[0].toString());
		strSql.append(" LIMIT ?,?");
		rs = dbutil.getResultSet(strSql.toString(), start, RowCount.ROWS);
		while (rs.next()) {
			Meeting meeting = new Meeting();
			meeting.setMeetingid(rs.getInt("meetingid"));
			meeting.setMeetingname(rs.getString("meeting_name"));
			meeting.setMeetingroom(meetingRoomDao.getMeetingRoomsByRoomID(rs.getInt("room_id")));
			meeting.setNumberofparticipants(rs.getInt("number_of_participants"));
			meeting.setStarttime(rs.getDate("start_time"));
			meeting.setEndtime(rs.getDate("end_time"));
			meeting.setReservationtime(rs.getDate("reservation_time"));
			meeting.setCanceledtime(rs.getDate("canceled_time"));
			meeting.setDescription(rs.getString("description"));
			meeting.setStatus(rs.getByte("sta"));
			meetings.add(meeting);
		}
		return meetings;
	}

	/**
	 * Gte all meetings by booker ID
	 * 
	 * @param employeeId
	 * @return
	 * @throws Exception
	 */
	public List<Meeting> getAllMeetingsByBookerID(int bookerID) throws SQLException {

		CleanVar();

		strSql.append("SELECT m.meeting_id " + MEETINGID);
		strSql.append(",m.meeting_name " + MEETINGNAME);
		strSql.append(",m.reservationist_id " + RESERVATIONISTID);
		strSql.append(",m.room_id " + ROOMID);
		strSql.append(",m.number_of_participants " + NOP);
		strSql.append(",m.start_time " + STARTTIME);
		strSql.append(",m.end_time " + ENDTIME);
		strSql.append(",m.reservation_time " + RESERVATIONTIME);
		strSql.append(",m.canceled_time " + CANCELTIME);
		strSql.append(",m.description " + DESCRIPTION);
		strSql.append(",m.sta " + STATUS);
		strSql.append(" FROM meetings m");
		strSql.append(" where m.booker = ");
		strSql.append(bookerID);
		strSql.append(" AND m.canceled_time IS NULL");

		return executeQuery();

		// return meetings;
	}

	/**
	 * Get last 3 meetings by employee ID
	 * 
	 * @param employeeId
	 * @return
	 * @throws SQLException
	 * @throws Throwable
	 */
	public List<Meeting> getLast3MeetingsByEmployeeID(int employeeId) throws SQLException {

		CleanVar();

		strSql.append("SELECT m.meeting_id " + MEETINGID);
		strSql.append(",m.meeting_name " + MEETINGNAME);
		strSql.append(",m.reservationist_id " + RESERVATIONISTID);
		strSql.append(",m.room_id " + ROOMID);
		strSql.append(",m.number_of_participants " + NOP);
		strSql.append(",m.start_time " + STARTTIME);
		strSql.append(",m.end_time " + ENDTIME);
		strSql.append(",m.sta " + STATUS);
		strSql.append(" FROM meetings m");
		strSql.append(" INNER JOIN meetingparticipants");
		strSql.append(" INNER JOIN employee");
		strSql.append(" WHERE m.meeting_id = meetingparticipants.meetingid");
		strSql.append(" AND meetingparticipants.employeeid = employee.employeeid");
		strSql.append(" AND employee.employeeid = ");
		strSql.append(employeeId);
		strSql.append(" AND m.canceled_time IS NULL");
		strSql.append(" LIMIT 0,3");

		return executeQuery();

		// return meetings;
	}

	/**
	 * Get canceled meetings by employee ID
	 * 
	 * @param employeeId
	 * @return
	 * @throws SQLException
	 */
	public List<Meeting> getCanceledMeetings(int employeeId) throws SQLException {

		CleanVar();

		strSql.append("SELECT m.meeting_id " + MEETINGID);
		strSql.append(",m.meeting_name " + MEETINGNAME);
		strSql.append(",m.reservationist_id " + RESERVATIONISTID);
		strSql.append(",m.room_id " + ROOMID);
		strSql.append(",m.number_of_participants " + NOP);
		strSql.append(",m.start_time " + STARTTIME);
		strSql.append(",m.end_time " + ENDTIME);
		strSql.append(",m.reservation_time " + RESERVATIONTIME);
		strSql.append(",m.canceled_time " + CANCELTIME);
		strSql.append(",m.description " + DESCRIPTION);
		strSql.append(",m.sta " + STATUS);
		strSql.append(",m.booker " + BOOKER);
		strSql.append(" FROM meetings m");
		strSql.append(" INNER JOIN meetingparticipants");
		strSql.append(" INNER JOIN employee");
		strSql.append(" WHERE m.meeting_id = meetingparticipants.meetingid");
		strSql.append(" AND meetingparticipants.employeeid = employee.employeeid");
		strSql.append(" AND employee.employeeid = ");
		strSql.append(employeeId);
		strSql.append(" AND m.canceled_time IS NOT NULL");
		strSql.append(" LIMIT 0,3");

		return executeQuery();

		// return meetings;
	}

	// public List<Meeting> getMeetingsByEmpID(String empID) {
	// }

	/**
	 * Get meeting by meeting ID
	 * 
	 * @param meetingID
	 * @return
	 * @throws SQLException
	 */
	public List<Meeting> getMeetingsByMeetingID(String meetingID) throws SQLException {
		List<Meeting> meetings = new ArrayList<Meeting>();
		StringBuffer strSql = new StringBuffer();
		strSql.append(
				"SELECT meeting_id,meeting_name,room_id,reservationist_id,number_of_participants,start_time,end_time,reservation_time,canceled_time,description,sta ");
		strSql.append(" FROM meetings WHERE 1=1");
		strSql.append(" AND meeting_id = ");
		strSql.append(meetingID);

		ResultSet rs = dbutil.getResultSet(strSql.toString());

		while (rs.next()) {
			Meeting meeting = new Meeting();
			meeting.setMeetingid(rs.getInt("meeting_id"));
			meeting.setMeetingname(rs.getString("meeting_name"));
			meeting.setMeetingroom(meetingRoomDao.getMeetingRoomsByRoomID(rs.getInt("room_id")));
			meeting.setStarttime(rs.getDate("start_time"));
			meeting.setEndtime(rs.getDate("end_time"));
			meetings.add(meeting);
		}
		dbutil.CloseConn();
		return meetings;
	}

	public List<Meeting> getMeetingsByEmpID(int empID) throws SQLException {

		strSql.append("SELECT m.meeting_id " + MEETINGID);
		strSql.append(",m.meeting_name " + MEETINGNAME);
		strSql.append(",m.reservationist_id " + RESERVATIONISTID);
		strSql.append(",m.room_id " + ROOMID);
		strSql.append(",m.number_of_participants " + NOP);
		strSql.append(",m.start_time " + STARTTIME);
		strSql.append(",m.end_time " + ENDTIME);
		strSql.append(",m.reservation_time " + RESERVATIONTIME);
		strSql.append(",m.canceled_time " + CANCELTIME);
		strSql.append(",m.description " + DESCRIPTION);
		strSql.append(",m.sta " + STATUS);
		strSql.append(",m.booker " + BOOKER);
		strSql.append(" FROM meetings m");
		strSql.append(" INNER JOIN meetingparticipants mp");
		strSql.append(" WHERE m.meeting_id = mp.meetingid");
		strSql.append(" AND mp.employeeid = ");
		strSql.append(empID);
		strSql.append(" AND m.canceled_time IS NULL");

		return executeQuery();
	}

	/**
	 * 获取会议数量
	 * 
	 * @return 会议数量
	 * @throws SQLException
	 */
	public int getRows(String... condition) throws SQLException {
		String strSql = "SELECT COUNT(meeting_id) COUNT FROM meetings";
		int rows = 0;
		ResultSet rs = dbutil.getResultSet(strSql);
		if (rs.next()) {
			rows = rs.getInt("COUNT");
		}
		dbutil.CloseConn();
		return rows;
	}
}
