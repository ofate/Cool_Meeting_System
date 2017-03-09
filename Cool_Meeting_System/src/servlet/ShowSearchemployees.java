package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Employee;
import jdbc.RowCount;
import net.sf.json.JSONArray;
import service.EmpService;

/**
 * Servlet implementation class Searchemployees
 */
@WebServlet("/admin/ShowSearchemployees")
public class ShowSearchemployees extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmpService empService = new EmpService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowSearchemployees() {
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
		
		List<Employee> employees = null;
		int rows = 0;
		int pages = 0;
		String currentPage = request.getParameter("currentpage");
		String condition = request.getParameter("condition");
		try {
			rows = empService.getRows(condition);
			pages = (rows%RowCount.ROWS == 0)?rows/RowCount.ROWS:rows/RowCount.ROWS+1;
			if (currentPage != null) {
				employees = empService.getEmployees(Integer.parseInt(currentPage),condition);
			} else {
				currentPage = "1";
				employees = empService.getEmployees(1,condition);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONArray jsonarray = JSONArray.fromObject(employees);
		response.setContentType("text/html;charset=UTF-8");
		
		jsonarray.add(jsonarray.size(), Integer.parseInt(currentPage));
		jsonarray.add(jsonarray.size(), pages);
		jsonarray.add(jsonarray.size(), rows);
		
		
		/*System.out.println("currentpage:"+currentPage+"\n"+"pages:"+pages);
		System.out.println(jsonarray.toString());*/
		
		out.write(jsonarray.toString());
		out.flush();
		out.close();
		return;
		
		/*request.setAttribute("rows", rows);
		request.setAttribute("currentpage", currentPage);
		request.setAttribute("employees", employees);
		request.setAttribute("pages", pages);
		
		request.getRequestDispatcher("searchemployees.jsp").forward(request, response);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
