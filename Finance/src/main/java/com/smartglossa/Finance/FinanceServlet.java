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
			String line = request.getParameter("line");
			String addr = request.getParameter("add");
			String conNo = request.getParameter("cno");
			String collType = request.getParameter("ctype");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "Insert into customer(customerName,line,address,contactNo,collectionType) values('"+ cusName +"','"+ line +"','"+ addr +"','"+ conNo +"','"+ collType +"')";
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
					obj.put("accountNo" ,set.getInt("accountNo"));
					obj.put("customerName",set.getString("customerName"));
					obj.put("line",set.getString("line"));
					obj.put("address",set.getString("address"));
					obj.put("contactNo",set.getString("contactNo"));
					obj.put("collectionType", set.getString("collectionType"));
					result.put(obj);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().print(result);

		}else if(op.equals("updateCustomer")){
			JSONObject obj = new JSONObject();
		 	int accNo = Integer.parseInt(request.getParameter("accno"));
			String cusName = request.getParameter("cname");
			String line = request.getParameter("line");
			String addr = request.getParameter("add");
			String conNo = request.getParameter("cno");
			String collType = request.getParameter("ctype");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "update customer set customerName='"+ cusName +"',line='"+ line +"',address='"+ addr +"',contactNo='"+ conNo +"',collectionType='"+ collType + "'where accountNo="+ accNo;
				stmt.execute(query);
				obj.put("status", "success");
				
			} catch (Exception e) {
				obj.put("status", "Failure");
				e.printStackTrace();
			}
			response.getWriter().print(obj);
			
		}else if(op.equals("getOneCustomer")){
			JSONObject obj = new JSONObject();
			int accNo = Integer.parseInt(request.getParameter("accno"));
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "select * from customer where accountNo="+ accNo;
				ResultSet set = stmt.executeQuery(query);
				if(set.next()){
					obj.put("accountNo" ,set.getInt("accountNo"));
					obj.put("customerName",set.getString("customerName"));
					obj.put("line",set.getString("line"));
					obj.put("address",set.getString("address"));
					obj.put("contactNo",set.getString("contactNo"));
					obj.put("collectionType", set.getString("collectionType"));
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.getWriter().print(obj);
		}else if(op.equals("deleteCustomer")){
			JSONObject obj = new JSONObject();
			int accNo = Integer.parseInt(request.getParameter("accno"));
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "delete from customer where accountNo="+ accNo;
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
 