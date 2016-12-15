package com.smartglossa.user;

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

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("operation");
		if(op.equals("addUser")){
			JSONObject obj = new JSONObject();
			String uname = request.getParameter("uname");
			String pass = request.getParameter("pass");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "insert into user(userName,password) values('"+ uname +"','"+ pass +"')";
				stmt.execute(query);
				obj.put("status", "success");
				

			} catch (Exception e) {
				obj.put("status", "Failure");
				e.printStackTrace();
			}
			response.getWriter().print(obj);
	}else if(op.equals("login")){
		JSONObject obj = new JSONObject();
		String uname = request.getParameter("user");
		String pass = request.getParameter("passw");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance", "root", "root");
			Statement stmt = conn.createStatement();
			String query = "Select *from user where userName='"+ uname +"'AND password="+ pass;
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				obj.put("user", rs.getString("userName"));
				obj.put("status", "success");
			}
		} catch (Exception e) {
			obj.put("status", "Failure");
			e.printStackTrace();
		}
		response.getWriter().print(obj);
	}

	}
}
