package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.net.httpserver.HttpContext;

import dao.EmployeeDao;
import entity.Employee;
import young.ajax.json.JsonUtil;

public class EmployeeAction implements ModelDriven<Employee> {

	Employee employee;
	Map<String, Object> session = ActionContext.getContext().getSession();
	public String meetingID;
	private HttpContext context;
	private HttpServletRequest request;
	private HttpServletResponse response;

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

	@Override
	public Employee getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get employees by meetingID
	 */
	public void getEmployeesByMeetingID() {
		ActionContext context = ActionContext.getContext();
		request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/html;charset=UTF-8");

		EmployeeDao ed = new EmployeeDao();
		try {
			List<Employee> meetings = ed.getEmployeesMeetingID(meetingID);
			String jsonStr = JsonUtil.list2json(meetings);
			PrintWriter out = response.getWriter();
			out.write(jsonStr);
			out.flush();
			out.close();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
}
