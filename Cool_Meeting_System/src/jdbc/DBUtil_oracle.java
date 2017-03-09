package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil_oracle {
	private static Connection conn;
	private static PreparedStatement pst;
	private static ResultSet rs;
	
	/**
	 * データベースを接続する
	 */
	static private void getConn(){
//	static {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");	//oracle database driver
//			Class.forName("com.mysql.jdbc.Driver");	//mysql database driver
//			System.out.println("データベースドライブをロード...");
			String url = "jdbc:oracle:thin:@192.168.1.110:1521:orcl.168.1.100";
//			String url = "jdbc:mysql://localhost:3306/coolmeeting";	//mysql database connection
//			System.out.println("データベース接続...");
			String user = "scott";
			String pw = "021059";
			conn = DriverManager.getConnection(url,user,pw);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
	}
	
	/**
	 * データベースから情報を探す
	 * @param sql sql本文
	 * @param params		sql変数
	 * @return	結果セット
	 */
	public static ResultSet getResultSet(String sql,Object...params){
		try {
			getConn();
			pst = conn.prepareStatement(sql);
			for(int i =0; i<params.length; i++){
				pst.setObject(i+1, params[i]);
			}
			rs = pst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		return rs;
	}
	
	/**
	 * データーベースを編集する
	 * @param sql　SQL本文
	 * @param params　SQL補完用の変数
	 * @return
	 */
	public static int editDatabase(String sql,Object...params){
		int rows = 0;
		
		try {
			getConn();
			pst = conn.prepareStatement(sql);
			for (int i=0; i<params.length; i++){
				pst.setObject(i+1, params[i]);
			}
			rows = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return rows;
	}
	
	/**
	 * データベースとの接続を切断する
	 */
	public static void CloseConn(){
		try {
			if(conn != null) conn.close();
			if(pst != null) pst.close();
			if(rs != null) rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
