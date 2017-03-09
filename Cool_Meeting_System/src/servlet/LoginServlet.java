package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import entity.Employee;
import service.EmpService;

/**
 * Servlet implementation class Login
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//实例化empservice对象
	EmpService empService = new EmpService();
	//实例化employee对象
	Employee emp = new Employee();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//从网页请求中获取登陆信息
		String loginname = request.getParameter("accountname");	//登录名
		String password = request.getParameter("password");	//登陆密码
		PrintWriter out = response.getWriter(); //获取文字输出流
		
		try {
			//获取员工对象
			emp = empService.getEmployeeByLoginInfo(loginname, password);
			//判断员工对象是否为空
			if(emp != null){
				//如果非空
				HttpSession session = request.getSession();	//获取session会话
				session.setAttribute("employeename", emp.getEmployeename());	//向session中保存数据
				Cookie ckloginname = new Cookie("loginname", loginname); //创建登录名cookie
				Cookie ckpassword = new Cookie("password", password);	//创建登陆密码cookie
				ckloginname.setMaxAge(3600);	//设置登录名cookie有效时长
				ckpassword.setMaxAge(3600);		//设置登陆密码cookie有效时长
				response.addCookie(ckloginname);	//向response中添加cookie
				response.addCookie(ckpassword);		//向response中添加cookie
				
				//获取session上下文
				ServletContext context = session.getServletContext();
				//从上下文中读取用户数量统计
				Integer usercount = (Integer)context.getAttribute("usercount");
				//判断用户数量变量是否为空
				if(usercount != null){
					//如果非空，则累加并存储
					usercount += 1;
					context.setAttribute("usercount", usercount);
				} else {
					//如果为空，则存储为1
					context.setAttribute("usercount", 1);
				}
				//向页面输出“success”，通知页面登陆成功
				out.write("success");
			} else {
				//向页面输出“fail”，通知页面登陆失败
				out.write("fail");
			}
			//清空输出流
			out.flush();
			//关闭输出流
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
