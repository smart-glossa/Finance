package com.smartglossa.customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class CustomerAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CustomerAccount() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("operation");
		if(op.equals("addCusAccount")){
			JSONObject obj = new JSONObject();
			int cusId = Integer.parseInt(request.getParameter("cusId"));
			int accNum = Integer.parseInt(request.getParameter("accNumber"));
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "Insert into customerAccount(cusId,accNo)values("+ cusId +","+ accNum +")";
				stmt.execute(query);
				obj.put("status", 1);
			} catch (Exception e) {
				obj.put("status", 0);
				e.printStackTrace();
			}
			response.getWriter().print(obj);
		}
	}

}
