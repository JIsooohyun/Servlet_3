package com.sh.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnector {
	//Connection Method
	
	//DB연결 객체를 리턴
	public static Connection getConnect() throws Exception{
		String user="user03";
		String password="user03";
		String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String driver="oracle.jdbc.driver.OracleDriver";
		
		Class.forName(driver);
		
		Connection conn = DriverManager.getConnection(url, user, password);
		
		return conn;
	}
	
	public static void disConnect(Connection conn)throws Exception{
		conn.close();
	}
	public static void disConnect(Connection conn, PreparedStatement st) throws Exception{
		st.close();
		conn.close();
	}
	
	public static void disConnect(Connection conn, PreparedStatement st, ResultSet rs) throws Exception{
		rs.close();
		DBConnector.disConnect(conn, st);
	}
	
}
