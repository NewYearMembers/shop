package cn.hsl.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseDriver {
	private static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/mldn?useSSL=false&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "mysqladmin";
	private static final ThreadLocal<Connection> threadlocal = new ThreadLocal<>();
	public static Connection getConnection(){
		Connection conn = threadlocal.get();
		if(conn == null){
			conn = resetConnection();
			threadlocal.set(conn);
		}
		return conn;
	}
	public static void close(){
		Connection conn = threadlocal.get();
		if(conn != null){
			try {
				conn.close();
				threadlocal.remove();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static Connection resetConnection(){
		Connection conn = null;
		try {
			Class.forName(DBDRIVER);
			conn = DriverManager.getConnection(DBURL,USER,PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
