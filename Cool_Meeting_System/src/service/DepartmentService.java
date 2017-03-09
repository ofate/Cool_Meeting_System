package service;

import java.sql.SQLException;
import java.util.List;

import dao.DepartmentDao;
import entity.Department;

public class DepartmentService {
	DepartmentDao departmentDao = new DepartmentDao();
	
	/**
	 * 获取部门集合
	 * @return 部门集合
	 * @throws SQLException
	 */
	public List<Department> getDpartments() throws SQLException{
		return departmentDao.getDpartments();
	}
	
	/**
	 * 添加部门
	 * @param department 部门对象
	 * @return 添加的条目数量
	 */
	public int AddDepartment(Department department){
		return departmentDao.AddDepartment(department);
	}
	
	/**
	 * 获取最大员工编号
	 * @return 最大员工编号
	 * @throws SQLException
	 */
	public int getMaxDepartmentID() throws SQLException{
		return departmentDao.getMaxDepartmentID();
	}
	
	/**
	 * 编辑部门信息
	 * @param department 部门对象
	 * @return 编辑的行数
	 */
	public int EditDepartmentByID(Department department){
		return departmentDao.EditDepartmentByID(department);
	}
	
	/**
	 * 删除部门
	 * @param deprtmentid 部门编号
	 * @return 删除的行数
	 */
	public int DelDepartmentByID(int departmentid){
		return departmentDao.DelDepartmentByID(departmentid);
	}
}
