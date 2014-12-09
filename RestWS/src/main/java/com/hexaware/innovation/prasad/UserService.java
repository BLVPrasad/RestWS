package com.hexaware.innovation.prasad;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
	public static UserService userService = new UserService();
	public static final String GET_USER = "SELECT * FROM USER1";
	public static final String INSERT_USER = "Insert into user1";

	public List<User> getUserAll() throws ClassNotFoundException, SQLException {
		List<User> ls = new ArrayList();
		ls = DataServiceHelper.getInstance().executeQuery(GET_USER);
		return ls;
	}

	public List<User> getUser(String name) throws ClassNotFoundException,
			SQLException {
		String SQL_WHERE_CAS = " where userName='" + name + "'";
		List<User> als = DataServiceHelper.getInstance().executeQuery(
				GET_USER + SQL_WHERE_CAS);
		return als;
	}
	

	public void CreateUser(User user) throws SQLException,
			ClassNotFoundException {
		String SQL_WHERE_CASE = " VALUES('" + user.getUserName() + "','"
				+ user.getUserPasswd() + "')";
		DataServiceHelper.getInstance().executeUpdateQuery(
				INSERT_USER + SQL_WHERE_CASE);
	}

	public static UserService getInstance() {
		return userService;
	}
}