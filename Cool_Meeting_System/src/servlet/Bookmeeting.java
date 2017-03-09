package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MeetingRoomDao;
import entity.Department;
import entity.Employee;
import entity.MeetingRoom;
import service.DepartmentService;
import service.EmpService;
import service.MeetingRoomService;

/**
 * Servlet implementation class Bookmeeting
 */
@WebServlet("/admin/Bookmeeting")
public class Bookmeeting extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//实例化MeetingRoomService
	MeetingRoomService meetingRoomService = new MeetingRoomService();
	DepartmentService departmentService = new DepartmentService();
	EmpService empService = new EmpService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bookmeeting() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//获取会议室集合
		try {
			List<MeetingRoom> meetingRooms = meetingRoomService.getMeetingRooms();
			List<Department> departments = departmentService.getDpartments();
			List<Employee> employees = empService.getEmployees();
			
			request.setAttribute("meetingrooms", meetingRooms);
			request.setAttribute("departments", departments);
			request.setAttribute("employees", employees);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("bookmeeting.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
