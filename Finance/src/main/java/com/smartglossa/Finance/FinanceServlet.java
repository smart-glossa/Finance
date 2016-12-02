package com.smartglossa.Finance;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class FinanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("operation");
		if(op.equals("addCustomer")){
			JSONObject obj = new JSONObject();
			String cusName = request.getParameter("cname");
			String addr = request.getParameter("add");
			String conNo = request.getParameter("cno");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "Insert into customer(cusName,address,contactNo) values('"+ cusName +"','"+ addr +"','"+ conNo +"')";
				stmt.execute(query);
				obj.put("status", "success");
			} catch (Exception e) {
				obj.put("status", "Failure");
				e.printStackTrace();
			}
			response.getWriter().print(obj);
		}else if(op.equals("getAllCustomer")){
			JSONArray result = new JSONArray();
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "select * from customer";
				ResultSet set = stmt.executeQuery(query);
				while(set.next()){
					JSONObject obj = new JSONObject();
					obj.put("cusId" ,set.getInt("cusId"));
					obj.put("cusName",set.getString("cusName"));
					obj.put("address",set.getString("address"));
					obj.put("contactNo",set.getString("contactNo"));
					result.put(obj);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().print(result);

		}else if(op.equals("updateCustomer")){
			JSONObject obj = new JSONObject();
		 	int cusId = Integer.parseInt(request.getParameter("cusId"));
			String cusName = request.getParameter("cname");
			String addr = request.getParameter("add");
			String conNo = request.getParameter("cno");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "update customer set cusName='"+ cusName +"',address='"+ addr +"',contactNo='"+ conNo +"'where cusId="+ cusId;
				stmt.execute(query);
				obj.put("status", "success");
				
			} catch (Exception e) {
				obj.put("status", "Failure");
				e.printStackTrace();
			}
			response.getWriter().print(obj);
			
		}else if(op.equals("getOneCustomer")){
			JSONObject obj = new JSONObject();
			int cusId = Integer.parseInt(request.getParameter("cusId"));
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "select * from customer where cusId="+ cusId;
				ResultSet set = stmt.executeQuery(query);
				if(set.next()){
					obj.put("cusId" ,set.getInt("cusId"));
					obj.put("cusName",set.getString("cusName"));
					obj.put("address",set.getString("address"));
					obj.put("contactNo",set.getString("contactNo"));
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.getWriter().print(obj);
		}else if(op.equals("deleteCustomer")){
			JSONObject obj = new JSONObject();
			int cusId = Integer.parseInt(request.getParameter("cusId"));
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "delete from customer where cusId="+ cusId;
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
 