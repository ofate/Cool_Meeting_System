package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.DepartmentService;

/**
 * Servlet implementation class DelDepartment
 */
@WebServlet("/admin/DelDepartment")
public class DelDepartment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DepartmentService departmentService = new DepartmentService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelDepartment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		int departmentid = Integer.parseInt(request.getParameter("departmentid"));
		int rows = departmentService.DelDepartmentByID(departmentid);
		
		if (rows>0) {
			out.write("success");
		} else {
			out.write("fail");
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
