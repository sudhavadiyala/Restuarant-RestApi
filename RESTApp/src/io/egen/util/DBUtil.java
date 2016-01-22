package io.egen.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	private static final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/vadiyalas9388";
	private static final String DB_USER = "vadiyalas9388";
	private static final String DB_PASSWORD = "1378113";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("MySQL JDBC Driver loaded");
		} catch (ClassNotFoundException e) {
			System.err.println("Error loading JDBC Driver");
			e.printStackTrace();
		}
	}
	
	public static Connection connect() {
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
		} catch (SQLException e) {
			System.err.println("Error getting connection");
			e.printStackTrace();
		}
		
		return con;
	}
	
}
