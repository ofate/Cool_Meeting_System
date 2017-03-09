package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Department;
import jdbc.DBUtil;

public class DepartmentDao {
	private DBUtil dbutil;

	public DepartmentDao() {
		dbutil = DBUtil.getInstance();
	}

	/**
	 * 获取部门集合
	 * 
	 * @return 部门集合
	 * @throws SQLException
	 */
	public List<Department> getDpartments() throws SQLException {
		List<Department> departments = new ArrayList<Department>();
		String strSql = "SELECT DEPARTMENTID,DEPARTMENTNAME FROM department";
		ResultSet rs = dbutil.getResultSet(strSql);
		while (rs.next()) {
			Department dept = new Department(rs.getInt("DEPARTMENTID"), rs.getString("DEPARTMENTNAME"));
			departments.add(dept);
		}
		dbutil.CloseConn();
		return departments;
	}

	/**
	 * 通过部门ID获取部门信息
	 * 
	 * @param deptID
	 *            部门ID
	 * @return 部门对象
	 * @throws SQLException
	 */
	public Department getDpartmentByID(int deptID) throws SQLException {
		Department dept = null;
		String strSql = "SELECT DEPARTMENTID,DEPARTMENTNAME FROM department WHERE DEPARTMENTID=?";
		ResultSet rs = dbutil.getResultSet(strSql, deptID);
		if (rs.next()) {
			dept = new Department();
			dept.setDepartmentid(rs.getInt("DEPARTMENTID"));
			dept.setDepartmentname(rs.getString("DEPARTMENTNAME"));
		}
		dbutil.CloseConn();
		return dept;
	}

	/**
	 * 添加部门
	 * 
	 * @param department
	 *            部门对象
	 * @return 添加的数量
	 */
	public int AddDepartment(Department department) {
		String strSql = "INSERT INTO department VALUES(?,?)";
		int rows = dbutil.editDatabase(strSql, department.getDepartmentid(), department.getDepartmentname());
		dbutil.CloseConn();
		return rows;
	}

	/**
	 * 获取最大部门编号
	 * 
	 * @return 最大部门编号
	 * @throws SQLException
	 */
	public int getMaxDepartmentID() throws SQLException {
		int maxID = 0;
		String strSql = "SELECT MAX(departmentid) maxid from department";
		ResultSet rs = dbutil.getResultSet(strSql);
		if (rs.next()) {
			maxID = rs.getInt("maxid");
		}
		dbutil.CloseConn();
		return maxID;
	}

	/**
	 * 编辑部门信息
	 * 
	 * @param department
	 *            部门对象
	 * @return
	 */
	public int EditDepartmentByID(Department department) {
		String strSql = "UPDATE department SET departmentname=? WHERE departmentid=?";
		int rows = dbutil.editDatabase(strSql, department.getDepartmentname(), department.getDepartmentid());
		dbutil.CloseConn();
		return rows;
	}

	/**
	 * 删除部门
	 * 
	 * @param deprtmentid
	 *            部门编号
	 * @return 删除的行数
	 */
	public int DelDepartmentByID(int departmentid) {
		String strSql = "DELETE FROM department WHERE departmentid=?";
		int rows = dbutil.editDatabase(strSql, departmentid);
		dbutil.CloseConn();
		return rows;
	}

}
