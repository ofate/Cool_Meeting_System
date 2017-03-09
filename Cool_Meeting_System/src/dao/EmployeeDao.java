package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Employee;
import jdbc.DBUtil;
import jdbc.RowCount;

public class EmployeeDao {

	// 实例化一个部门dao的对象，用于调用其方法
	DepartmentDao deptDao = new DepartmentDao();
	private DBUtil dbutil;

	public EmployeeDao() {
		dbutil = DBUtil.getInstance();
	}

	/**
	 * 获取员工集合
	 * 
	 * @return 员工集合
	 * @throws SQLException
	 */
	public List<Employee> getEmployees() throws SQLException {
		Employee emp = null;
		List<Employee> employees = new ArrayList<Employee>();
		String strSql = "SELECT EMPLOYEEID,EMPLOYEENAME,USERNAME,PHONE,EMAIL,STA,DEPARTMENTID,PW,ROLE FROM employee";
		ResultSet rs = dbutil.getResultSet(strSql);
		while (rs.next()) {
			emp = new Employee();
			emp.setEmployeeid(rs.getInt("EMPLOYEEID"));
			emp.setEmployeename(rs.getString("EMPLOYEENAME"));
			emp.setUsername(rs.getString("USERNAME"));
			emp.setPhone(rs.getString("PHONE"));
			emp.setEmail(rs.getString("EMAIL"));
			emp.setStatus(rs.getString("STA"));
			emp.setPw(rs.getString("PW"));
			emp.setRole(rs.getString("ROLE"));
			emp.setDept(deptDao.getDpartmentByID(rs.getInt("DEPARTMENTID")));
			employees.add(emp);
		}
		dbutil.CloseConn();
		return employees;
	}

	/**
	 * 通过登录名和密码来获取员工信息
	 * 
	 * @param loginname
	 *            登录名
	 * @param pw
	 *            登陆密码
	 * @return 员工对象
	 * @throws SQLException
	 */
	public Employee getEmployeeByLoginInfo(String loginname, String pw) throws SQLException {
		Employee emp = null;
		String strSql = null;
		strSql = "SELECT EMPLOYEEID,EMPLOYEENAME,USERNAME,PHONE,EMAIL,STA,DEPARTMENTID,PW,ROLE FROM employee"
				+ " WHERE USERNAME=? AND PW=?";
		ResultSet rs = dbutil.getResultSet(strSql, loginname, pw);
		if (rs.next()) {
			emp = new Employee();
			emp.setEmployeeid(rs.getInt("EMPLOYEEID"));
			emp.setEmployeename(rs.getString("EMPLOYEENAME"));
			emp.setUsername(rs.getString("USERNAME"));
			emp.setPhone(rs.getString("PHONE"));
			emp.setEmail(rs.getString("EMAIL"));
			emp.setStatus(rs.getString("STA"));
			emp.setPw(rs.getString("PW"));
			emp.setRole(rs.getString("ROLE"));
			emp.setDept(deptDao.getDpartmentByID(rs.getInt("DEPARTMENTID")));
		}
		dbutil.CloseConn();
		return emp;
	}

	/**
	 * 指定された数量の社員リストをゲット
	 * 
	 * @param start
	 *            スタート
	 * @return 社員リスト
	 * @throws SQLException
	 */
	public List<Employee> getEmployees(int start, Object... params) throws SQLException {
		Employee emp = null;
		List<Employee> employees = new ArrayList<Employee>();
		StringBuffer strSql = new StringBuffer();
		strSql.append(
				"SELECT EMPLOYEEID,EMPLOYEENAME,USERNAME,PHONE,EMAIL,STA,DEPARTMENTID,PW,ROLE FROM employee WHERE 1=1");
		if (params != null) {
			strSql.append(" " + (String) params[0]);
		}
		strSql.append(" LIMIT ?,?");
		ResultSet rs = dbutil.getResultSet(strSql.toString(), start, RowCount.ROWS);
		while (rs.next()) {
			emp = new Employee();
			emp.setEmployeeid(rs.getInt("EMPLOYEEID"));
			emp.setEmployeename(rs.getString("EMPLOYEENAME"));
			emp.setUsername(rs.getString("USERNAME"));
			emp.setPhone(rs.getString("PHONE"));
			emp.setEmail(rs.getString("EMAIL"));
			emp.setStatus(rs.getString("STA"));
			emp.setPw(rs.getString("PW"));
			emp.setRole(rs.getString("ROLE"));
			emp.setDept(deptDao.getDpartmentByID(rs.getInt("DEPARTMENTID")));
			employees.add(emp);
		}
		dbutil.CloseConn();
		return employees;
	}

	/**
	 * Get employee by meeting ID;
	 * 
	 * @param MeetingID
	 * @return
	 * @throws SQLException
	 */
	public List<Employee> getEmployeesMeetingID(String MeetingID) throws SQLException {
		Employee emp = null;
		List<Employee> employees = new ArrayList<Employee>();
		StringBuffer strSql = new StringBuffer();
		strSql.append("SELECT distinct");
		strSql.append(" e.EMPLOYEEID EMPLOYEEID");
		strSql.append(",e.EMPLOYEENAME EMPLOYEENAME");
		strSql.append(",e.USERNAME USERNAME");
		strSql.append(",e.PHONE PHONE");
		strSql.append(",e.EMAIL EMAIL");
		strSql.append(",e.STA STA");
		strSql.append(",e.PW PW");
		strSql.append(",e.ROLE ROLE");
		strSql.append(" FROM employee e");
		strSql.append(" INNER JOIN");
		strSql.append(" (SELECT");
		strSql.append(" meetingid,employeeid");
		strSql.append(" FROM");
		strSql.append(" meetingparticipants");
		strSql.append(" WHERE");
		strSql.append(" meetingid = ");
		strSql.append(MeetingID);
		strSql.append(" ) mp");
		strSql.append(" WHERE");
		strSql.append(" e.employeeid = mp.employeeid");

		ResultSet rs = dbutil.getResultSet(strSql.toString());
		while (rs.next()) {
			emp = new Employee();
			emp.setEmployeeid(rs.getInt("EMPLOYEEID"));
			emp.setEmployeename(rs.getString("EMPLOYEENAME"));
			emp.setUsername(rs.getString("USERNAME"));
			emp.setPhone(rs.getString("PHONE"));
			emp.setEmail(rs.getString("EMAIL"));
			emp.setStatus(rs.getString("STA"));
			emp.setPw(rs.getString("PW"));
			emp.setRole(rs.getString("ROLE"));
			employees.add(emp);
		}
		dbutil.CloseConn();
		return employees;
	}

	/**
	 * Get employee info by employee ID
	 * 
	 * @param empID
	 * @return
	 */
	public Employee getEmpByEmpID(String empID) {
		Employee emp = new Employee();

		StringBuffer strSql = new StringBuffer();
		strSql.append("SELECT");
		strSql.append(" employeeid");
		strSql.append(",employeename");
		strSql.append(",username");
		strSql.append(",phone");
		strSql.append(",email");
		strSql.append(",sta");
		strSql.append(",departmentid");
		strSql.append(",role from employee");
		strSql.append(" WHERE employeeid = ");
		strSql.append(empID);

		ResultSet rs = dbutil.getResultSet(strSql.toString());

		try {
			while (rs.next()) {
				emp.setEmployeeid(rs.getInt("employeeid"));
				emp.setEmployeename(rs.getString("employeename"));
				emp.setUsername(rs.getString("username"));
				emp.setPhone(rs.getString("phone"));
				emp.setRole(rs.getString("role"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		dbutil.CloseConn();
		return emp;
	}

	/**
	 * 获取数据数量
	 * 
	 * @return 数据数量
	 * @throws SQLException
	 */
	public int getRows(String... condition) throws SQLException {
		int rows = 0;
		String strSql = "SELECT COUNT(EMPLOYEEID) COUNT FROM employee WHERE 1=1";
		strSql += " " + condition[0];
		ResultSet rs = dbutil.getResultSet(strSql);
		if (rs.next()) {
			rows = rs.getInt("COUNT");
		}
		dbutil.CloseConn();
		return rows;
	}

	/**
	 * 关闭员工账号
	 * 
	 * @param employeeid
	 *            员工ID
	 * @return 修改的行数
	 */
	public int CloseAccount(int employeeid) {
		String strSql = "UPDATE employee SET sta=2 WHERE employeeid= ?";
		int rows = dbutil.editDatabase(strSql, employeeid);
		dbutil.CloseConn();
		return rows;
	}

	/**
	 * 激活员工账号
	 * 
	 * @param employeeid
	 *            员工ID
	 * @return 修改的行数
	 */
	public int ActiveAccount(int employeeid) {
		String strSql = "UPDATE employee SET sta=1 WHERE employeeid= ?";
		int rows = dbutil.editDatabase(strSql, employeeid);
		dbutil.CloseConn();
		return rows;
	}
}
