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
import javax.servlet.http.HttpSession;

import entity.Employee;
import jdbc.RowCount;
import net.sf.json.JSONArray;
import service.EmpService;

/**
 * Servlet implementation class ShowSearchEmployees
 */
@WebServlet("/admin/SearchEmployees")
public class SearchEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmpService empService = new EmpService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEmployees() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String searchCondition = request.getParameter("condition");
		
		List<Employee> employees = null;
		int rows = 0;
		int pages = 0;
		String currentPage = request.getParameter("currentpage");
		try {
			rows = empService.getRows();
			pages = (rows%RowCount.ROWS == 0)?rows/RowCount.ROWS:rows/RowCount.ROWS+1;
			if (currentPage != null) {
				employees = empService.getEmployees(Integer.parseInt(currentPage),searchCondition);
			} else {
				currentPage = "1";
				employees = empService.getEmployees(1,searchCondition);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONArray jsonarray = JSONArray.fromObject(employees);
		response.setContentType("text/html;charset=UTF-8");
		
		out.write(jsonarray.toString());
		out.flush();
		out.close();
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
