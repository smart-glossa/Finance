package com.smartglossa.payment;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;


public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("operation");
		if(op.equals("addPayment")){
			JSONObject obj = new JSONObject();
			String pId = request.getParameter("pid");
			String amount = request.getParameter("amount");
			String date = request.getParameter("date");
			String accId = request.getParameter("accid");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "Insert into payment(paymentId,amount,date,accountId) values('"+ pId +"','"+ amount +"','"+ date +"','"+ accId +"')";
				stmt.execute(query);
				obj.put("status", "success");
			} catch (Exception e) {
				obj.put("status", "Failure");
				e.printStackTrace();
			}
			response.getWriter().print(obj);
		}else if(op.equals("getAllPayment")){
			JSONArray result = new JSONArray();
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "select * from payment";
				ResultSet set = stmt.executeQuery(query);
				while(set.next()){
					JSONObject obj = new JSONObject();
					obj.put("pId",set.getString("pId"));
					obj.put("amount",set.getString("amount"));
					obj.put("date",set.getString("date"));
					obj.put("accId",set.getString("accId"));
					result.put(obj);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.getWriter().print(result);

		}else if(op.equals("updatePayment")){
			JSONObject obj = new JSONObject();
			String pId = request.getParameter("pid");
			String amount = request.getParameter("amount");
			String date = request.getParameter("date");
			String accId = request.getParameter("accid");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "update payment set pId='"+ pId +"',amount='"+ amount +"',date='"+ date +"where accId="+ accId;
				stmt.execute(query);
				obj.put("status", "success");
				
			} catch (Exception e) {
				obj.put("status", "Failure");
				e.printStackTrace();
			}
			response.getWriter().print(obj);
			
		}else if(op.equals("getOnePayment")){
			JSONObject obj = new JSONObject();
			String accId = request.getParameter("accid");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "select * from payment where accId="+ accId;
				ResultSet set = stmt.executeQuery(query);
				if(set.next()){
					obj.put("pId",set.getString("pId"));
					obj.put("amount",set.getString("amount"));
					obj.put("date",set.getString("date"));
					obj.put("accId",set.getString("accId"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.getWriter().print(obj);
		}else if(op.equals("deletePayment")){
			JSONObject obj = new JSONObject();
			String accId = request.getParameter("accid");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "delete from payment where accId="+ accId;
				stmt.execute(query);
				obj.put("status", "success");
				} catch (Exception e) {
					obj.put("status","Failure");
				e.printStackTrace();
			}
			 response.getWriter().print(obj);
		}
	}

}
