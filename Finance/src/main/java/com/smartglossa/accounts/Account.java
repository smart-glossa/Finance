package com.smartglossa.accounts;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class Account extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Account() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operation = request.getParameter("operation");
		if (operation.equals("addAccounts")) {
			JSONObject obj = new JSONObject();
			int cusId = Integer.parseInt(request.getParameter("cusId"));
			String line = request.getParameter("line");
			String collType = request.getParameter("collType");
			String amountGiven = request.getParameter("amountGiven");
			String amountToPay = request.getParameter("amountToPay");
			try {
				AccountClass acc = new AccountClass();
				acc.addAccount(cusId, line, collType, amountGiven, amountToPay);
				obj.put("status", "success");
			} catch (Exception e) {
				obj.put("status", "failure");
				e.printStackTrace();
			}
			response.getWriter().print(obj);
		} else if (operation.equals("updateAccount")) {
			JSONObject obj = new JSONObject();
			int accId = Integer.parseInt(request.getParameter("accId"));
			int cusId = Integer.parseInt(request.getParameter("cusId"));
			String line = request.getParameter("line");
			String collType = request.getParameter("collType");
			String amountGiven = request.getParameter("amountGiven");
			String amountToPay = request.getParameter("amountToPay");
			try {
				AccountClass acc = new AccountClass();
				acc.updateAccount(accId, cusId, line, collType, amountGiven, amountToPay);
				obj.put("status", "success");
			} catch (Exception e) {
				obj.put("status", "failure");
				e.printStackTrace();
			}
			response.getWriter().print(obj);

		} else if (operation.equals("getOneAccount")) {
			JSONObject obj = new JSONObject();
			int accId = Integer.parseInt(request.getParameter("accId"));
			try {
				AccountClass acc = new AccountClass();
				obj = acc.getOneAccount(accId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.getWriter().print(obj);
		} else if (operation.equals("deleteAccounts")) {
			JSONObject obj = new JSONObject();
			int accId = Integer.parseInt(request.getParameter("accId"));
			try {
				AccountClass acc = new AccountClass();
				acc.deleteAccount(accId);
				obj.put("status", "success");
			} catch (Exception e) {
				obj.put("status", "Failure");
				e.printStackTrace();
			}
			response.getWriter().print(obj);
		} else if (operation.equals("getAllAccount")) {
			JSONArray result = new JSONArray();
			try {
				AccountClass acc = new AccountClass();
				result = acc.getAllAccount();
			} catch (Exception e) {
				JSONObject obj = new JSONObject();
				obj.put("status", "failure");
				result.put(obj);
				e.printStackTrace();
			}
			response.getWriter().print(result);
		} else if (operation.equals("getStatement")) {
			JSONObject obj = new JSONObject();
			JSONArray array = new JSONArray();
			int accId = Integer.parseInt(request.getParameter("accId"));

			try {
				AccountClass acc = new AccountClass();
				array = acc.getStatement(accId);

			} catch (Exception e) {
				obj.put("status", "Failure");
				e.printStackTrace();
			}
			response.getWriter().print(array);

		}

	}
}