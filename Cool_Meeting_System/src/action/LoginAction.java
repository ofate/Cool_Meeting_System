package action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import entity.Employee;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.EmpService;

public class LoginAction implements ModelDriven<Employee> {
	EmpService empService = new EmpService();
	Employee employee = null;

	public String accountname;
	public String password;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletContext context;

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

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public ServletContext getContext() {
		return context;
	}

	@Override
	public Employee getModel() {
		// TODO Auto-generated method stub
		employee = new Employee();
		return employee;
	}

	java.util.Map<String, Object> session = ActionContext.getContext().getSession();

	public void login() throws Throwable {
		// 获取action上下文
		ActionContext context = ActionContext.getContext();
		// 获取请求对象
		request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		// 获取响应对象
		response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);

		response.setContentType("text/html;charset=UTF-8");

		employee = empService.getEmployeeByLoginInfo(accountname, password);
		JSONArray ja = JSONArray.fromObject(employee);
		JSONObject jo = new JSONObject();
		PrintWriter out = response.getWriter();

		if (employee != null) {
			session.put("employee", employee);
			jo.put("loginRes", ActionSupport.SUCCESS);
		} else {
			jo.put("loginRes", ActionSupport.ERROR);
		}

		ja.add(jo);
		out.write(ja.toString());
		out.flush();
		out.close();
	}
}
