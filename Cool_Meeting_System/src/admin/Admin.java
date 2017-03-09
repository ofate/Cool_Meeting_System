package admin;

public class Admin {
	private int admin_ID;
	private String admin_name;
	private String admin_pw;
	
	public Admin() {
		// TODO Auto-generated constructor stub
	}
	
	public Admin(int admin_ID,String admin_name,String admin_pw) {
		// TODO Auto-generated constructor stub
		this.setAdmin_ID(admin_ID);
		this.setAdmin_name(admin_name);
		this.setAdmin_pw(admin_pw);
	}

	public int getAdmin_ID() {
		return admin_ID;
	}

	public void setAdmin_ID(int admin_ID) {
		this.admin_ID = admin_ID;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getAdmin_pw() {
		return admin_pw;
	}

	public void setAdmin_pw(String admin_pw) {
		this.admin_pw = admin_pw;
	}
	
}
