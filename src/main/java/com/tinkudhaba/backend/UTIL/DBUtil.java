package com.tinkudhaba.backend.UTIL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	private final String URL = "jdbc:mysql://localhost:3306/my_restro";
	private final String USERNAME = "root";
	private final String PASSWORD = "root";
	private final String DRIVER = "com.mysql.cj.jdbc.Driver";

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		System.out.println("Connecting....");

		Class.forName(DRIVER);
		Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		System.out.println("Connected.");
		return connection;
	}

	public void close(Connection connection, PreparedStatement pstmt) throws SQLException {
		System.out.println("Closing...");
		if (pstmt != null)
			pstmt.close();

		if (connection != null)
			connection.close();
		System.out.println("Closed.");
	}

	public void close(Connection conn, PreparedStatement pstmt, ResultSet rs) throws SQLException {
		System.out.println("Closing....");
		if (rs != null)
			rs.close();

		if (pstmt != null)
			pstmt.close();

		if (conn != null)
			conn.close();

		System.out.println("Closed.");
	}

}