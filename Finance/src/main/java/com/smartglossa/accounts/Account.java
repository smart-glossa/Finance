package com.smartglossa.accounts;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;


public class Account extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Account() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operation = request.getParameter("operation");
		if(operation.equals("addAccounts")){
			JSONObject obj = new JSONObject();
			String line = request.getParameter("line");
			String collType = request.getParameter("collType");
			String amountGiven = request.getParameter("amountGiven");
			String amountToPay = request.getParameter("amountToPay");
			int cusId = Integer.parseInt(request.getParameter("cusId"));
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "insert into Accounts(line,collType,amountToPay,cusId) values('" + line + "','" + collType +"','"+ amountGiven +"','"+ amountToPay +"',"+ cusId +")";
				stmt.execute(query);
				obj.put("status", "success");
			} catch (Exception e) {
				obj.put("status","failure");
				e.printStackTrace();
			}
				response.getWriter().print(obj);			 
	}else if(operation.equals("updateCustomer")){
		JSONObject obj = new JSONObject();
		int accId = Integer.parseInt(request.getParameter("accId"));
		String line = request.getParameter("line");
		String collType = request.getParameter("collType");
		String amountGiven = request.getParameter("amountGiven");
		String amountToPay = request.getParameter("amountToPay");
		int cusId = Integer.parseInt(request.getParameter("cusId"));
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "root");
			Statement stmt = conn.createStatement();
			String query = "update Accounts set (line,collType,amountToPay,cusId) values('" + line + "','" + collType +"','"+ amountGiven +"','"+ amountToPay +"',"+ cusId +") where accid = "+ accId;
			stmt.execute(query);
			obj.put("status", "success");
		} catch (Exception e) {
			obj.put("status","failure");
			e.printStackTrace();
		}
   response.getWriter().print(obj);
		
	}else if(operation.equals("getOne")){
		JSONObject obj = new JSONObject();
		int accId = Integer.parseInt(request.getParameter("accId"));
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "root");
			Statement stmt = conn.createStatement();
			String query = "select * from Accounts where accId="+ accId;
			ResultSet set = stmt.executeQuery(query);
			if(set.next()){
				obj.put("accId" ,set.getInt("accId"));
				obj.put("line",set.getString("line"));
				obj.put("collType",set.getString("collType"));
				obj.put("amountGiven",set.getString("amountGiven"));
				obj.put("amountToPay",set.getString("amountToPay"));
				obj.put("cusId", set.getInt("cusId"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().print(obj);
	}else if(operation.equals("deleteAccounts")){
		JSONObject obj = new JSONObject();
		int accId = Integer.parseInt(request.getParameter("accId"));
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "root");
			Statement stmt = conn.createStatement();
			String query = "delete from Accounts where accId="+ accId;
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