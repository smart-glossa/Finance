package com.smartglossa.payment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

public class PaymentClass {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	
	public PaymentClass() throws ClassNotFoundException, SQLException {
		openConnection();
	}
	public void addPayment(String amount, String date, String accId) throws SQLException{
		try{
			String query = "Insert into Payment(accId,amount,date) values('"+ accId +"','"+ amount +"','"+ date +"')";
			stmt.execute(query);
		}finally{
			closeConnection();
		}
	}
	public JSONArray getAllPayment() throws SQLException{
		JSONArray res = new JSONArray();
		try{
			String query = "select * from Payment";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				JSONObject obj = new JSONObject();
				obj.put("pId",rs.getString("payId"));
				obj.put("accId",rs.getString("accId"));
				obj.put("amount",rs.getString("amount"));
				obj.put("date",rs.getString("date"));
				res.put(obj);
			}
		}finally{
			closeConnection();
		}
		return res;
		
	}
	public void updatePayment(int pId, String amount, String date, String accId) throws SQLException{
		try{
			String query = "update Payment set accId="+ accId +",amount='"+ amount +"',date='"+ date +"'where payId="+ pId;
			stmt.execute(query);
		}finally{
			closeConnection();
		}
	}
	public JSONObject getOnePayment(int pId) throws SQLException{
		JSONObject obj = new JSONObject();
		try{
			String query = "select * from Payment where payId="+ pId;
			rs = stmt.executeQuery(query);
			if(rs.next()){
				obj.put("pId",rs.getString("payId"));
				obj.put("amount",rs.getString("amount"));
				obj.put("date",rs.getString("date"));
				obj.put("accId",rs.getString("accId"));
			}
		}finally{
			closeConnection();
		}
		return obj;
		
	}
	public void deletePayment(int pId) throws SQLException{
		try{
			String query = "delete from Payment where payId="+ pId;
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
