package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Meeting;
import jdbc.RowCount;
import net.sf.json.JSONArray;
import service.MeetingService;

/**
 * Servlet implementation class Searchmeetings
 */
@WebServlet("/admin/Searchmeetings")
public class Searchmeetings extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MeetingService meetingService = new MeetingService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Searchmeetings() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		PrintWriter out = response.getWriter();

		String currentpage = request.getParameter("currentpage");
		String meetingname = request.getParameter("meetingname");
		String roomname = request.getParameter("roomname");
		String reservationistname = request.getParameter("reservationistname");

		String condition = "";
		int rows = 0;
		int pages = 0;
		List<Meeting> meetings = new ArrayList<Meeting>();

		if (meetingname != "") {
			condition += " AND meetingname LIKE '%" + meetingname + "%'";
		}

		if (roomname != "") {

		}

		try {
			rows = meetingService.getRows(condition);
			pages = (rows % RowCount.ROWS == 0) ? rows / RowCount.ROWS : rows / RowCount.ROWS + 1;
			if (currentpage == null) {
				currentpage = "1";
			}
			meetings = meetingService.getMeetings(Integer.parseInt(currentpage), condition);

		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONArray jsonarray = JSONArray.fromObject(meetings);

		jsonarray.add(jsonarray.size(), currentpage);
		jsonarray.add(jsonarray.size(), pages);
		jsonarray.add(jsonarray.size(), rows);

		out.write(jsonarray.toString());
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
