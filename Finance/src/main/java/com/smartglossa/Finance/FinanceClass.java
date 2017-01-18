package com.smartglossa.Finance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import com.smartglossa.user.UserConstants;

public class FinanceClass {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	public FinanceClass() throws ClassNotFoundException, SQLException {
		openConnection();
	}

	public void addCustomer(int cusId, int cusAcc, String cusName, String address, String mobileNo, String referralName,
			String referralAddress, String referralContactNo, String landLine) throws SQLException {
		try {

			String query = "Insert into customer(customerId,customerName,address,mobileNumber,referralName,referralAddress,referralContactNo,landLine) values("
					+ cusId + ",'" + cusName + "','" + address + "','" + mobileNo + "','" + referralName + "','"
					+ referralAddress + "','" + referralContactNo + "','" + landLine + "')";
			stmt.execute(query);
			String query1 = "Insert into customerAccount(accountNumber,customerId) values(" + cusAcc + "," + cusId
					+ ")";
			stmt.execute(query1);
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
				obj.put("cusId", rs.getInt("customerId"));
				obj.put("cusName", rs.getString("customerName"));
				obj.put("address", rs.getString("address"));
				obj.put("mobileNo", rs.getString("mobileNumber"));
				obj.put("landLine", rs.getString("landLine"));
				obj.put("referenceName", rs.getString("referralName"));
				obj.put("referenceAddress", rs.getString("referralAddress"));
				obj.put("referenceContactNo", rs.getString("referralContactNo"));
				result.put(obj);
			}
		} finally {
			closeConnection();
		}
		return result;
	}

	public void updateCustomer(int cusId, int cusAcc, String cusName, String address, String mobileNo,
			String referralName, String referralAddress, String referralContactNo, String landLine) throws SQLException {
		try {
			String query = "update customer set customerName='" + cusName + "',address='" + address + "',mobileNumber='"
					+ mobileNo + "',referralName='" + referralName + "',referralAddress='" + referralAddress
					+ "',landLine='" + landLine +"',referralContactNo='" + referralContactNo + "'where customerId=" + cusId;
			stmt.execute(query);
		} finally {
			closeConnection();
		}

	}

	public JSONObject getOneCustomer(int cusId) throws SQLException {
		JSONObject obj = new JSONObject();
		try {
			String query = "select * from customer,customerAccount where customer.customerId=" + cusId +" AND customerAccount.customerId="+ cusId;
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				obj.put("cusId", rs.getInt("customerId"));
				obj.put("cusName", rs.getString("customerName"));
				obj.put("address", rs.getString("address"));
				obj.put("mobileNo", rs.getString("mobileNumber"));
				obj.put("landLine", rs.getString("landLine"));
				obj.put("referenceName", rs.getString("referralName"));
				obj.put("referenceAddress", rs.getString("referralAddress"));
				obj.put("referenceContactNo", rs.getString("referralContactNo"));
				obj.put("accNo", rs.getInt("accountNumber"));
			}
		} finally {
			closeConnection();
		}
		return obj;

	}

	public void deleteCustomer(int cusId) throws SQLException {
		try {
			String query = "delete from customer where customerId=" + cusId;
			stmt.execute(query);
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
