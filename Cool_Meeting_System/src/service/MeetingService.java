package service;

import java.sql.SQLException;
import java.util.List;

import dao.MeetingDao;
import entity.Meeting;
import jdbc.RowCount;

public class MeetingService {
	MeetingDao meetingDao = new MeetingDao();

	/**
	 * 获取会议集合
	 * 
	 * @param start
	 *            搜索的开始
	 * @return 会议集合
	 * @throws Throwable
	 */
	public List<Meeting> getMeetings(int currentpage, String... codition) throws Throwable {
		int start = (currentpage - 1) * RowCount.ROWS;
		return meetingDao.getMeetings(start);
	}

	/**
	 * 获取数据数量
	 * 
	 * @return 数据数量
	 * @throws SQLException
	 */
	public int getRows(String... condition) throws SQLException {
		return meetingDao.getRows();
	}
}
