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
	public void addPayment(String payment,int accId,String paydate,int userId) throws SQLException{
		try{
			String query = "Insert into payment(payment,accId,paydate,userId) values('"+ payment +"',"+ accId +",'"+ paydate +"',"+ userId +")";
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
				obj.put("payment",rs.getString("payment"));
				obj.put("accId",rs.getString("accId"));
				obj.put("paydate",rs.getString("paydate"));
				res.put(obj);
			}
		}finally{
			closeConnection();
		}
		return res;
		
	}
	public void updatePayment( String payment,int accId , String paydate, int userId) throws SQLException{
		try{
			String query = "update payment set  payment='" + payment +"',accId="+ accId +",paydate='"+ paydate +"',userId="+ userId;
			stmt.execute(query);
		}finally{
			closeConnection();
		}
	}
	public JSONObject getOnePayment(int pId) throws SQLException{
		JSONObject obj = new JSONObject();
		try{
			String query = "select * from payment where payId="+ pId;
			rs = stmt.executeQuery(query);
			if(rs.next()){
				obj.put("amount",rs.getString("amount"));
				obj.put("accId",rs.getString("accId"));
				obj.put("paydate",rs.getString("paydate"));
			}
		}finally{
			closeConnection();
		}
		return obj;
		
	}
	public void deletePayment(int accId) throws SQLException{
		try{
			String query = "delete from payment where accId="+ accId;
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



