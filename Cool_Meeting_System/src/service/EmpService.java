package service;

import java.sql.SQLException;
import java.util.List;

import dao.EmployeeDao;
import entity.Employee;
import jdbc.RowCount;

public class EmpService {
	//声明并实例化员工Dao对象
	EmployeeDao empDao = new EmployeeDao();
	
	/**
	 * 通过登录名和密码来获取员工信息
	 * @param loginname 登录名
	 * @param pw 登陆密码
	 * @return 员工对象
	 * @throws SQLException
	 */
	public Employee getEmployeeByLoginInfo(String loginname,String pw) throws SQLException{
		return empDao.getEmployeeByLoginInfo(loginname, pw);
	}
	
	/**
	 * 获取员工集合
	 * @return 员工集合
	 * @throws SQLException
	 */
	public List<Employee> getEmployees() throws SQLException{
		return empDao.getEmployees();
	}
	
	/**
	 * 获取指定数量的员工信息列表
	 * @param currentPage 当前页码
	 * @return 员工集合
	 * @throws SQLException
	 */
	public List<Employee> getEmployees(int currentPage,Object ...params) throws SQLException {
		int start = (currentPage-1)*RowCount.ROWS;
		if(params != null) {
			return empDao.getEmployees(start, params);
		}
		/*String searchCondition = (String) params[0];
		if(searchCondition != null){
			return empDao.getEmployees(start,searchCondition);
		}*/
		return empDao.getEmployees(start);
	}
	
	/**
	 * 获取分页数量
	 * @return 分页数量
	 * @throws SQLException
	 */
	public int getRows(String ...condition) throws SQLException {
		return empDao.getRows(condition);
	}
	
	/**
	 * 关闭员工账号
	 * @param employeeid 员工账号
	 * @return 修改的行数
	 */
	public int CloseAccount(int employeeid){
		return empDao.CloseAccount(employeeid);
	}
	
	/**
	 * 激活员工账号
	 * @param employeeid 员工账号
	 * @return 修改的行数
	 */
	public int ActiveAccount(int employeeid){
		return empDao.ActiveAccount(employeeid);
	}
}
