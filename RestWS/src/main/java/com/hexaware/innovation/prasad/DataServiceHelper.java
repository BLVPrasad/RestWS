package com.hexaware.innovation.prasad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DataServiceHelper {
	public static DataServiceHelper dataServiceHelper = null;
	private Connection con = null;
	DataSource dataSource = null;
	InitialContext initialContext = null;
	public static final String DB_URL = "jdbc:mysql://localhost:3307/test";
	public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";

	/**
	 * 
	 * This method is used to create an object for the given DAO class name.
	 */

	public Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName(DRIVER_NAME);
		con = DriverManager.getConnection(DB_URL, "blvprasad", "prasad");
		return con;
	}

	public void closeConnection() throws SQLException {
		if (isConnectionOpen()) {
			con.close();
			con = null;
		}
	}

	public boolean isConnectionOpen() {
		return (con != null);
	}

	public static DataServiceHelper getInstance() {
		if (dataServiceHelper == null) {
			dataServiceHelper = new DataServiceHelper();
		}
		return dataServiceHelper;
	}

	public void executeUpdateQuery(String query) throws SQLException,
			ClassNotFoundException {
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		stmt.execute(query);
		closeConnection();
	}

	public List<User> executeQuery(String query) throws ClassNotFoundException,
			SQLException {
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		List<User> als = convertPojoList(rs);
		closeConnection();
		return als;
	}

	private List<User> convertPojoList(ResultSet rs) throws SQLException {
		List<User> asl = new ArrayList<User>();
		while (rs.next()) {
			User user = new User(rs.getString("userName"), rs.getString("userPasswd"));
			asl.add(user);
		}
		return asl;
	}

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		String query = "Select * from user1 where userName='prasad'";
		List<User> als = DataServiceHelper.getInstance().executeQuery(query);
		System.out.println("List==>" + als);
		
		UserResourceSample.testCreateUser();
	}
}
