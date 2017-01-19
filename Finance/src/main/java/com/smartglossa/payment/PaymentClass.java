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
	public void addPayment(float amount,String collectionDate,String entryDate,int accId,String uName) throws SQLException{
		try{
			String query = "Insert into payment(amount,collectionDate,EntryDate,accountId,userName) values('"+ amount +"','"+ collectionDate+"','"+ entryDate +"',"+ accId +",'"+uName+"')";
			stmt.execute(query);
		}finally{
			closeConnection();
		}
	}
	public JSONArray getAllPayment() throws SQLException{
		JSONArray result = new JSONArray();
		try{
			String query = "select * from payment";
			rs = stmt.executeQuery(query);
			while(rs.next()){
				JSONObject obj = new JSONObject();
				obj.put("amount",rs.getString("amount"));
				obj.put("collectionDate",rs.getString("collectionDate"));
				obj.put("EntryDate",rs.getString("EntryDate"));
				obj.put("accountId",rs.getString("accountId"));
				obj.put("userName",rs.getString("userName"));
				result.put(obj);
			}
		}finally{
			closeConnection();
		}
		return result;
		
	}
	public void updatePayment( float amount,String collectionDate , String entryDate, int accId,String uName ) throws SQLException{
		try{
			String query = "update payment set  amount='" + amount +"',collectionDate='"+ collectionDate +"',EntryDate='"+ entryDate +"',userName='"+ uName +"',where accountId="+accId ;
			stmt.execute(query);
		}finally{
			closeConnection();
		}
	}
	public JSONObject getOnePayment(int accId) throws SQLException{
		JSONObject obj = new JSONObject();
		try{
			String query = "select * from payment where accountId="+ accId;
			rs = stmt.executeQuery(query);
			if(rs.next()){
				obj.put("amount",rs.getFloat("amount"));
				obj.put("collectionDate",rs.getString("collectionDate"));
				obj.put("EntryDate",rs.getString("EntryDate"));
				obj.put("accountId", rs.getInt("accountId"));
				obj.put("userName",rs.getString("userName"));
			}
		}finally{
			closeConnection();
		}
		return obj;
	}
	public void deletePayment(int accId) throws SQLException{
		try{
			String query = "delete from payment where accountId="+ accId;
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



