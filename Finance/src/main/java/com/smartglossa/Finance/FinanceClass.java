package com.smartglossa.Finance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

public class FinanceClass {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	public FinanceClass() throws ClassNotFoundException, SQLException {
		openConnection();
	}

	public void addCustomer(String cusName, String addr, String conNo) throws SQLException {
		JSONObject obj = new JSONObject();
		try {

			String query = "Insert into customer(cusName,address,contactNo) values('" + cusName + "','" + addr + "','"
					+ conNo + "')";
			stmt.execute(query);
			obj.put("status", "success");
		} finally {
			closeConnection();
		}
	}

	public JSONArray getAllCustomer() throws SQLException {
		JSONArray result = new JSONArray();
		try {
			String query = "select * from customer";
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("cusId", rs.getInt("cusId"));
				obj.put("cusName", rs.getString("cusName"));
				obj.put("address", rs.getString("address"));
				obj.put("contactNo", rs.getString("contactNo"));
				result.put(obj);
			}
		} finally {
			closeConnection();
		}
		return result;
	}

	public void updateCustomer(int cusId, String cusName, String addr, String conNo) throws SQLException {
		try {
			String query = "update customer set cusName='" + cusName + "',address='" + addr + "',contactNo='" + conNo
					+ "'where cusId=" + cusId;
			stmt.execute(query);
		} finally {
			closeConnection();
		}

	}

	public JSONObject getOneCustomer(int cusId) throws SQLException {
		JSONObject obj = new JSONObject();
		try {
			String query = "select * from customer where cusId=" + cusId;
		    rs = stmt.executeQuery(query);
			if (rs.next()) {
				obj.put("cusId", rs.getInt("cusId"));
				obj.put("cusName", rs.getString("cusName"));
				obj.put("address", rs.getString("address"));
				obj.put("contactNo", rs.getString("contactNo"));
			}
		} finally {
			closeConnection();
		}
		return obj;

	}
	public void deleteCustomer(int cusId) throws SQLException{
		try{
			String query = "delete from customer where cusId="+ cusId;
			stmt.execute(query);
		}finally{
			closeConnection();
		}
	}

	private void openConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "root");
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
