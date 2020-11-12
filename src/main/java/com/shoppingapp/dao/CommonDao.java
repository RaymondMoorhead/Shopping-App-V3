package com.shoppingapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CommonDao {
	private static final String username = "root";
	private static final String password = "root";
	private static final String url = "jdbc:mysql://localhost";
	private static final String options = "useSSL=false";
	private static final String dbName = "shopping_app";
	private static final String driver = "com.mysql.jdbc.Driver";
	
	public static void initialize() {
		try {
			createDatabaseIfMissing();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ItemDao.initialize();
		UserDao.initialize();
	}
	
	public static boolean createDatabaseIfMissing() throws SQLException, ClassNotFoundException{
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url + "?" + options, username, password);
		ResultSet resultSet = conn.getMetaData().getCatalogs();
		boolean found = false;
		boolean created = false;

		while (resultSet.next()) {
			// Get the database name, which is at position 1
			if(dbName.equals(resultSet.getString(1))) {
				found = true;
				break;
			}
		}
		resultSet.close();
		
		if(!found) {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("create database " + dbName);
		}
		return !found;
	}
	
	public static boolean createTableIfMissing(String table, String values) throws SQLException{
		Connection conn = getConnection();
		ResultSet resultSet = conn.getMetaData().getTables(dbName, null, table, new String[] {"TABLE"});
		boolean created = false;
		
		if (!resultSet.next()) {
			String sql = "create table " + table + "(" + values + ")";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			created = true;
		}
		resultSet.close();
		return created;
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url + "/" + dbName + "?" + options, username, password);
		} catch(Exception e) {
			System.out.println(e);
		}
		return conn;
	}
}
