package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import constant.JDBCConstant;

public class JDBCUtil {

	private static String driver = JDBCConstant.JDBC_DRIVER;
	private static String url = JDBCConstant.JDBC_CONNECTION_URL;
	private static String username = JDBCConstant.JDBC_CONNECTION_USERNAME;
	private static String password = JDBCConstant.JDBC_CONNECTION_PASSWORD;
	
	//×¢²áÇý¶¯
	static{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError("×¢²áÇý¶¯Ê§°Ü");
		}
	}
	
	
	
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			System.out.println("»ñÈ¡Á¬½ÓÊ§°Ü");
			e.printStackTrace();
		}
		return conn;
	}
	
	
	public static void closeAll(Connection conn,PreparedStatement ps,ResultSet rs){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps != null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
