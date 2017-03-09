package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.net.httpserver.HttpContext;

import dao.MeetingDao;
import entity.Employee;
import entity.Meeting;
import young.ajax.json.JsonUtil;

public class MeetingAction implements ModelDriven<Meeting> {
	Meeting meeting;

	public String meetingID;

	private HttpContext context;
	private HttpServletRequest request;
	private HttpServletResponse response;

	@Override
	public Meeting getModel() {
		meeting = new Meeting();
		return meeting;
	}

	public HttpContext getContext() {
		return context;
	}

	public void setContext(HttpContext context) {
		this.context = context;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	Map<String, Object> session = ActionContext.getContext().getSession();

	/**
	 * Get last 3 meetings by employee ID
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	public void getLast3MeetingsByEmployeeId() {
		// 获取action上下文
		ActionContext context = ActionContext.getContext();
		// 获取请求对象
		request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		// 获取响应对象
		response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/html;charset=UTF-8");

		try {
			Employee employee = (Employee) session.get("employee");
			int emoloyeeId = employee.getEmployeeid();
			MeetingDao meetingDao = new MeetingDao();
			List<List<Meeting>> allMeetings = new ArrayList<>();
			List<Meeting> meetings = meetingDao.getLast3MeetingsByEmployeeID(emoloyeeId);

			List<Meeting> canceledMeetings = meetingDao.getCanceledMeetings(emoloyeeId);
			allMeetings.add(meetings);
			allMeetings.add(canceledMeetings);

			String jsonStr = "";
			if (meetings.size() != 0) {
				jsonStr = JsonUtil.list2json(allMeetings);
			} else {
				jsonStr = MyActionSupport.FAILURE;
			}
			PrintWriter out = response.getWriter();
			out.write(jsonStr);
			out.flush();
			out.close();
		} catch (Exception ex) {
			System.out.println("异常：登陆信息丢失" + ex);
			ex.printStackTrace();
		}
	}

	/**
	 * Get all meetings by booker ID
	 */
	public void getAllMeetingsByBookerID() {
		// 获取action上下文
		ActionContext context = ActionContext.getContext();
		// 获取请求对象
		request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		// 获取响应对象
		response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/html;charset=UTF-8");

		try {
			Employee employee = (Employee) session.get("employee");
			int emoloyeeId = employee.getEmployeeid();
			MeetingDao meetingDao = new MeetingDao();
			List<Meeting> meetings = meetingDao.getAllMeetingsByBookerID(emoloyeeId);
			String jsonStr = JsonUtil.list2json(meetings);

			PrintWriter out = response.getWriter();
			out.write(jsonStr);
			out.flush();
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Get meetings by employee`s ID
	 */
	public void getMeetingsByEmpID() {
		// 获取action上下文
		ActionContext context = ActionContext.getContext();
		// 获取请求对象
		request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		// 获取响应对象
		response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/html;charset=UTF-8");

		try {
			Employee emp = (Employee) session.get("employee");
			int empID = emp.getEmployeeid();
			MeetingDao mDao = new MeetingDao();
			List<Meeting> meetings = mDao.getMeetingsByEmpID(empID);
			String jsonStr = JsonUtil.list2json(meetings);

			PrintWriter out = response.getWriter();
			out.write(jsonStr);
			out.flush();
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
