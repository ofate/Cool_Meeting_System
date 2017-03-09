package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Department;
import service.DepartmentService;

/**
 * Servlet implementation class AddDepartment
 */
@WebServlet("/admin/AddDepartment")
public class AddDepartment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DepartmentService departmentService = new DepartmentService();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDepartment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String newDeptName = request.getParameter("departmentname");
		
		PrintWriter out = response.getWriter();
		try {
			int newId = departmentService.getMaxDepartmentID()+1;
			
			Department department = new Department(newId, newDeptName);
			int rows = departmentService.AddDepartment(department);
			if(rows > 0){
				out.write("success");
				response.sendRedirect("ShowDepartments");
			} else {
				out.write("fail");
			}
			out.flush();
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
