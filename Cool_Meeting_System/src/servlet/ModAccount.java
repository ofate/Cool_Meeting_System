package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.EmpService;

/**
 * Servlet implementation class CloseAccount
 */
@WebServlet("/admin/ModAccount")
public class ModAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmpService empService = new EmpService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		int employeeid = Integer.parseInt(request.getParameter("employeeid"));
		String type = request.getParameter("type");
		PrintWriter out = response.getWriter();
		int rows = 0;
		
		
		if ("close".equals(type)) {
			rows = empService.CloseAccount(employeeid);
		} else if("active".equals(type)) {
			rows = empService.ActiveAccount(employeeid);
		}
		
		if (rows>0) {
			out.write("success");
		} else {
			out.write("fail");
		}
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
