package com.smartglossa.accounts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import com.smartglossa.user.UserConstants;

public class AccountClass {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	public AccountClass() throws ClassNotFoundException, SQLException {
		openConnection();
	}

	public void addAccount(int accNo, String line, String duration, String modeOfPayment, String amountGiven,
			String amountToPay, String date, boolean currentAccount) throws SQLException {
		try {
			String query = "insert into accounts(accountNumber,line,duration,modeOfPayment,amountGiven,amountToPay,givenDate) values("
					+ accNo + ",'" + line + "','" + duration + "','" + modeOfPayment + "','" + amountGiven + "','"
					+ amountToPay + "','" + date + "')";
			stmt.execute(query);
		} finally {
			closeConnection();
		}
	}

	public void updateAccount(int accId, int accNo, String line, String duration, String modeOfPayment,
			String amountGiven, String amountToPay, String date, String currentAccount) throws SQLException {
		try {
			String query = "update accounts set accountNumber='" + accNo + "',line='" + line + "',duration='" + duration
					+ "',modeOfPayment='" + modeOfPayment + "',amountGiven='" + amountGiven + "',amountToPay='"
					+ amountToPay + "',givenDate='" + date + "',currentAccount='" + currentAccount + "'where accId ="
					+ accId;
			stmt.execute(query);
		} finally {
			closeConnection();
		}
	}

	public JSONObject getOneAccount(int accId) throws SQLException {
		JSONObject obj = new JSONObject();
		try {
			String query = "select * from accounts where accId=" + accId;
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				obj.put("accId", rs.getInt("accId"));
				obj.put("accNo", rs.getInt("accountNumber"));
				obj.put("line", rs.getString("line"));
				obj.put("duration", rs.getString("duration"));
				obj.put("modeOfPayment", rs.getString("modeOfPayment"));
				obj.put("amountGiven", rs.getFloat("amountGiven"));
				obj.put("amountToPay", rs.getFloat("amountToPay"));
				obj.put("date", rs.getString("givenDate"));
				obj.put("currentAccount", rs.getString("currentAccount"));
			}
		} finally {
			closeConnection();
		}
		return obj;

	}
	public void deleteAccount(int accId) throws SQLException {
		try {
			String query = "delete from accounts where accId=" + accId;
			stmt.execute(query);
		} finally {
			closeConnection();
		}
	}
	public JSONArray getAllAccount() throws SQLException {
		JSONArray res = new JSONArray();
		try {
			String query = "Select *from accounts";
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("accId", rs.getInt("accId"));
				obj.put("accNo", rs.getInt("accountNumber"));
				obj.put("line", rs.getString("line"));
				obj.put("duration", rs.getString("duration"));
				obj.put("modeOfPayment", rs.getString("modeOfPayment"));
				obj.put("amountGiven", rs.getFloat("amountGiven"));
				obj.put("amountToPay", rs.getFloat("amountToPay"));
				obj.put("date", rs.getString("givenDate"));
				res.put(obj);

			}
		} finally {
			closeConnection();
		}
		return res;

	}

	public JSONArray getDetailByLine(String line) throws SQLException {
		JSONArray res = new JSONArray();
		try {
			String query = "Select *from accounts where line=" + line;
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("accId", rs.getInt("accId"));
				obj.put("duration", rs.getString("duration"));
				obj.put("modeOfPayment", rs.getString("modeOfPayment"));
				obj.put("amountGiven", rs.getFloat("amountGiven"));
				obj.put("amountToPay", rs.getFloat("amountToPay"));
				obj.put("date", rs.getString("givenDate"));
				int accNo = rs.getInt("accountNumber");
				String query1 = "Select *from customerAccount where accountNumber=" + accNo;
				rs = stmt.executeQuery(query1);
				while (rs.next()) {
					int customerId = rs.getInt("customerId");
					String query2 = "Select *from customer where customerId=" + customerId;
					rs = stmt.executeQuery(query2);
					while (rs.next()) {
						obj.put("customerName", rs.getString("customerName"));
						obj.put("address", rs.getString("address"));
						obj.put("mobileNumber", rs.getString("mobileNumber"));
						obj.put("referralName", rs.getString("referralName"));
						obj.put("referralAddress", rs.getString("referralAddress"));
						obj.put("referralContactNo", rs.getString("referralContactNo"));
						res.put(obj);
					}
				}
			}

		} finally {
			closeConnection();
		}
		return res;
	}

	/*
	 * public JSONArray getStatement(int accId) throws SQLException{ JSONObject
	 * obj = new JSONObject(); JSONArray res = new JSONArray(); int Balance = 0;
	 * try{ String query =
	 * "Select Accounts.amountToPay,customer.cusName from Accounts,customer where accId="
	 * + accId; rs = stmt.executeQuery(query); if(rs.next()){ obj.put("cusName",
	 * rs.getString("cusName")); Balance = rs.getInt("amountToPay");
	 * obj.put("AmountToPay", Balance); res.put(obj); String qu =
	 * "Select amount,date from Payment where accId="+ accId; Statement stm =
	 * conn.createStatement(); ResultSet rest = stm.executeQuery(qu);
	 * while(rest.next()){ JSONObject ob = new JSONObject(); int payment =
	 * rest.getInt("amount"); Balance = Balance - payment; ob.put("Date",
	 * rest.getString("date")); ob.put("pay", payment); ob.put("Bal", Balance);
	 * res.put(ob); } } }finally{ closeConnection(); } return res;
	 * 
	 * }
	 */
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
		if (rs != null) {
			rs.close();
		}
	}

}
