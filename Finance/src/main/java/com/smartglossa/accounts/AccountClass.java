package com.smartglossa.accounts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

public class AccountClass {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	
	public AccountClass() throws ClassNotFoundException, SQLException {
		openConnection();
	}
	public void addAccount(int cusId,String line, String collType, String amountGiven, String amountToPay) throws SQLException{
		try{
			String query = "insert into Accounts(cusId,line,amountGiven,amountToPay,collType) values(" + cusId
					+ ",'" + line + "','" + amountGiven + "','" + amountToPay + "','" + collType + "')";
			stmt.execute(query);
		}finally{
			closeConnection();
		}
	}
	public void updateAccount(int accId, int cusId, String line, String collType, String amountGiven, String amountToPay) throws SQLException{
		try{
			String query = "update Accounts set cusId='" + cusId + "',line='" + line + "',amountGiven='" + amountGiven + "',amountToPay='" + amountToPay + "',collType='" + collType + "'where accId ="+ accId;
			stmt.execute(query);
		}finally{
			closeConnection();
		}
	}
	public JSONObject getOneAccount(int accId) throws SQLException{
		JSONObject obj = new JSONObject();
		try{
			String query = "select * from Accounts where accId=" + accId;
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				obj.put("accId", rs.getInt("accId"));
				obj.put("line", rs.getString("line"));
				obj.put("collType", rs.getString("collType"));
				obj.put("amountGiven", rs.getString("amountGiven"));
				obj.put("amountToPay", rs.getString("amountToPay"));
				obj.put("cusId", rs.getInt("cusId"));

			}
		}finally{
			closeConnection();
		}
		return obj;
		
	}
	public void deleteAccount(int accId) throws SQLException{
		try{
			String query = "delete from Accounts where accId=" + accId;
			stmt.execute(query);
		}finally{
			closeConnection();
		}
	}
	public JSONArray getAllAccount() throws SQLException{
		JSONArray res = new JSONArray();
		try{
			String query = "Select *from Accounts";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				JSONObject obj = new JSONObject();
				obj.put("accId", rs.getInt("accId"));
				obj.put("line", rs.getString("line"));
				obj.put("collType", rs.getString("collType"));
				obj.put("amountGiven", rs.getString("amountGiven"));
				obj.put("amountToPay", rs.getString("amountToPay"));
				obj.put("cusId", rs.getInt("cusId"));
				res.put(obj);

			}
		}finally{
			closeConnection();
		}
		return res;
		
	}
	public JSONArray getStatement(int accId) throws SQLException{
		JSONObject obj = new JSONObject();
		JSONArray res = new JSONArray();
		int Balance = 0;
		try{
			String query = "Select Accounts.amountToPay,customer.cusName from Accounts,customer where accId="+ accId;
			rs = stmt.executeQuery(query);
			if(rs.next()){
				obj.put("cusName", rs.getString("cusName"));
				Balance = rs.getInt("amountToPay");
				obj.put("AmountToPay", Balance);
				res.put(obj);
				String qu = "Select amount,date from Payment where accId="+ accId;
				Statement stm = conn.createStatement();
				ResultSet rest = stm.executeQuery(qu);
				while(rest.next()){
					JSONObject ob = new JSONObject();
					int payment = rest.getInt("amount");
				    Balance = Balance - payment;
				    ob.put("Date", rest.getString("date"));
					ob.put("pay", payment);
					ob.put("Bal", Balance);
					res.put(ob);
				}
			}
		}finally{
			closeConnection();
		}
		return res;
		
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
