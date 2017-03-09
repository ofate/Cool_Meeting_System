package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.constants.Cons;

public class DBUtil {
	private static final DBUtil INSTANCE = new DBUtil();

	private DBUtil() {
	}

	public static DBUtil getInstance() {
		return INSTANCE;
	}

	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;

	private String jdbcClassName;
	private String userName;
	private String passWord;
	private static StringBuffer urlStr;

	{
		try {
			jdbcClassName = getProp(Cons.JDBCNAME);
			String dbUrl = getProp(Cons.DBURL);
			String dbPort = getProp(Cons.DBPORT);
			userName = getProp(Cons.USERNAME);
			passWord = getProp(Cons.PASSWORD);
			String dataBase = getProp(Cons.DATABASE);
			String useSSL = getProp(Cons.USESSL);

			urlStr = new StringBuffer();
			urlStr.append("jdbc:mysql://");
			urlStr.append(dbUrl);
			urlStr.append(":");
			urlStr.append(dbPort);
			urlStr.append("/");
			urlStr.append(dataBase);
			urlStr.append("?useSSL=");
			urlStr.append(useSSL);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	/**
	 * データベースを接続する
	 */
	private void getConn() {
		try {
			Class.forName(jdbcClassName);
			String url = urlStr.toString();
			String user = userName;
			String pw = passWord;
			conn = DriverManager.getConnection(url, user, pw);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	private String getProp(String key) throws IOException {
		return GetProperties.getProp(key);
	}

	/**
	 * データベースから情報を探す
	 * 
	 * @param sql
	 *            sql本文
	 * @param params
	 *            sql変数
	 * @return 結果セット
	 */
	public ResultSet getResultSet(String sql, Object... params) {
		try {
			// getConn();
			Class.forName(jdbcClassName);
			String url = urlStr.toString();
			String user = userName;
			String pw = passWord;
			conn = DriverManager.getConnection(url, user, pw);
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				pst.setObject(i + 1, params[i]);
			}
			rs = pst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * データーベースを編集する
	 * 
	 * @param sql
	 *            SQL本文
	 * @param params
	 *            SQL補完用の変数
	 * @return
	 */
	public int editDatabase(String sql, Object... params) {
		int rows = 0;

		try {
			getConn();
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				pst.setObject(i + 1, params[i]);
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
	public void CloseConn() {
		try {
			if (conn != null)
				conn.close();
			if (pst != null)
				pst.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
