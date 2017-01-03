package com.smartglossa.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONObject;

public class UserApplication {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	public UserApplication() throws ClassNotFoundException, SQLException, IOException {
		openConnection();
	}

	public void addUser(String name, String uname, String pass, String address, String mobileNumber)
			throws SQLException {
		try {
			String query = "insert into user(name,userName,password,address,mobileNumber) values('" + name + "','"
					+ uname + "','" + pass + "','" + address + "','" + mobileNumber + "')";
			stmt.execute(query);
		} finally {
			closeConnection();
		}
	}

	public JSONObject login(String uname, String pass) throws SQLException {
		JSONObject obj = new JSONObject();
		try {
			String query = "Select * from user where userName='" + uname + "'AND password=" + pass;
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				obj.put("user", rs.getString("userName"));
				obj.put("status", 1);
			}
			return obj;
		} finally {
			closeConnection();
		}
	}

	public JSONObject getName(String uname) throws SQLException {
		JSONObject obj = new JSONObject();
		try {
			String query = "Select name from user where userName='" + uname + "'";
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				obj.put("name", rs.getString("name"));
				obj.put("status", 1);
			}
			return obj;
		} finally {
			closeConnection();
		}
	}

	private void openConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://" + UserConstants.MYSQL_SERVER + "/" + UserConstants.DATABASE,
				UserConstants.USERNAME, UserConstants.PASSWORD);
		stmt = conn.createStatement();
	}

	private void closeConnection() throws SQLException {
		if (conn != null) {
			conn.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (ps != null) {
			ps.close();
		}
		if (rs != null) {
			rs.close();
		}
	}
}
