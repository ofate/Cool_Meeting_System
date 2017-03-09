package entity;

public class Department {
	private int departmentid;
	private String departmentname;
	
	public Department() {
		// TODO Auto-generated constructor stub
	}
	
	public Department(int departmentid,String departmentname) {
		// TODO Auto-generated constructor stub
		this.setDepartmentid(departmentid);
		this.setDepartmentname(departmentname);
	}

	public int getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(int departmentid) {
		this.departmentid = departmentid;
	}

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	
	
	
}
