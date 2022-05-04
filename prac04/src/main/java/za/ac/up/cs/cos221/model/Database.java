package za.ac.up.cs.cos221.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private Connection con;

//	private String	SAKILA_DB_PROTO = "";
	private static String	SAKILA_DB_HOST = "localhost";
	private static String	SAKILA_DB_PORT = "3306";
	private static String	SAKILA_DB_NAME = "u19025492_u20450207_sakila";
	private static String	SAKILA_DB_USERNAME = "root";
	private static String	SAKILA_DB_PASSWORD = "";
	
	public Connection connect() throws Exception {

		if (con != null)
			return con;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not found");
		}

//		String url = "jdbc:mariadb://localhost:3307/u19025492_u20450207_sakila";
		String url = "jdbc:mariadb://"+SAKILA_DB_HOST+":"+SAKILA_DB_PORT+"/"+SAKILA_DB_NAME;

//		con = DriverManager.getConnection(url, "root", "root");
		con = DriverManager.getConnection(url, SAKILA_DB_USERNAME, SAKILA_DB_PASSWORD);
		
		return con;
	}

	public void disconnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Can't close connection");
			}
		}
	}

}