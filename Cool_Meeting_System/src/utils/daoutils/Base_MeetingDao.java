package utils.daoutils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.EmployeeDao;
import dao.MeetingRoomDao;
import entity.Meeting;
import jdbc.DBUtil;

public class Base_MeetingDao {

	public final static String MEETINGID = "meetingID";
	public final static String MEETINGNAME = "meetingName";
	public final static String RESERVATIONISTID = "reservationist_id";
	public final static String ROOMID = "roomID";
	public final static String NOP = "numberOfParticipants";
	public final static String STARTTIME = "startTime";
	public final static String ENDTIME = "endTime";
	public final static String RESERVATIONTIME = "reservationTime";
	public final static String CANCELTIME = "cancelTime";
	public final static String DESCRIPTION = "description";
	public final static String STATUS = "status";
	public final static String BOOKER = "booker";

	public MeetingRoomDao meetingRoomDao = new MeetingRoomDao();
	public EmployeeDao empDao = new EmployeeDao();
	// public List<Meeting> meetings = new ArrayList<Meeting>();
	public StringBuffer strSql = new StringBuffer();
	public ResultSet rs = null;

	private DBUtil dbutil;

	public Base_MeetingDao() {
		dbutil = DBUtil.getInstance();
	}

	/**
	 * Clean var
	 */
	public void CleanVar() {
		// meetings.clear();
		strSql.setLength(0);
		rs = null;
	}

	public List<Meeting> executeQuery() throws SQLException {
		List<Meeting> meetings = new ArrayList<Meeting>();
		try {
			rs = dbutil.getResultSet(strSql.toString());
			while (rs.next()) {
				Meeting meeting = new Meeting();
				if (isExisted(MEETINGID))
					meeting.setMeetingid(rs.getInt(MEETINGID));
				if (isExisted(MEETINGNAME))
					meeting.setMeetingname(rs.getString(MEETINGNAME));
				if (isExisted(ROOMID))
					meeting.setMeetingroom(meetingRoomDao.getMeetingRoomsByRoomID(rs.getInt(ROOMID)));
				if (isExisted(NOP))
					meeting.setNumberofparticipants(rs.getInt(NOP));
				if (isExisted(STARTTIME))
					meeting.setStarttime(rs.getDate(STARTTIME));
				if (isExisted(ENDTIME))
					meeting.setEndtime(rs.getDate(ENDTIME));
				if (isExisted(RESERVATIONTIME))
					meeting.setReservationtime(rs.getDate(RESERVATIONTIME));
				if (isExisted(CANCELTIME))
					meeting.setCanceledtime(rs.getDate("CANCELTIME"));
				if (isExisted(DESCRIPTION))
					meeting.setDescription(rs.getString(DESCRIPTION));
				if (isExisted(STATUS))
					meeting.setStatus(rs.getByte(STATUS));
				if (isExisted(BOOKER))
					meeting.setBooker(empDao.getEmpByEmpID(rs.getString(BOOKER)));
				meetings.add(meeting);
			}
		} catch (SQLException ex) {
			throw ex;
		} finally {
			// CleanVar();
		}
		dbutil.CloseConn();
		return meetings;
	}

	private boolean isExisted(String arg) {
		boolean flag;
		try {
			// flag = rs.getBoolean(arg);
			rs.getBoolean(arg);
			flag = true;
		} catch (SQLException e) {
			flag = false;
		}
		return flag;
	}
}
