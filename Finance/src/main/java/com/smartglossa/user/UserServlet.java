package com.smartglossa.user;

import java.io.IOException;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("operation");
		if (op.equals("addUser")) {
			JSONObject obj = new JSONObject();
			String name = request.getParameter("name");
			String uname = request.getParameter("uname");
			String pass = request.getParameter("pass");
			String address = request.getParameter("address");
			String mobileNumber = request.getParameter("mobileNumber");
			try {
				UserApplication user = new UserApplication();
				user.addUser(name, uname, pass, address, mobileNumber);
				obj.put("status", 1);
			} catch (Exception e) {
				obj.put("status", 0);
				e.printStackTrace();
			}
			response.getWriter().print(obj);
		} else if (op.equals("login")) {
			JSONObject result = new JSONObject();
			String uname = request.getParameter("user");
			String pass = request.getParameter("passw");
			try {
				UserApplication user = new UserApplication();
				result = user.login(uname, pass);
				result.put("status", 1);
				response.getWriter().print(result);
			} catch (Exception e) {
				result.put("status", 0);
				response.getWriter().print(result);
				e.printStackTrace();
			}
		} else if (op.equals("getName")) {
			JSONObject result = new JSONObject();
			String uname = request.getParameter("uname");
			try {
				UserApplication user = new UserApplication();
				result = user.getName(uname);
				result.put("status", 1);
				result.put("message", "uname");
				response.getWriter().print(result);
			} catch (Exception e) {
				result.put("status", 0);
				response.getWriter().print(result);
				e.printStackTrace();
			}
		}

	}
}
