package com.smartglossa.payment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("operation");
		if (op.equals("addPayment")) {
			JSONObject obj = new JSONObject();
			float amount = Float.parseFloat(request.getParameter("amt"));
			String collectionDate = request.getParameter("cdate");
			String entryDate = request.getParameter("eDate");
			int accId = Integer.parseInt(request.getParameter("accId"));
			String uName = request.getParameter("uname");
			try {
				PaymentClass pay = new PaymentClass();
				pay.addPayment(amount, collectionDate, entryDate, accId, uName);
				obj.put("status", "success");
			} catch (Exception e) {
				obj.put("status", "Failure");
				e.printStackTrace();
			}
			response.getWriter().print(obj);
		} else if (op.equals("getAllPayment")) {
			JSONArray result = new JSONArray();

			try {

				PaymentClass pay = new PaymentClass();
				result = pay.getAllPayment();

			} catch (Exception e) {
				e.printStackTrace();
			}
			response.getWriter().print(result);

		} else if (op.equals("updatePayment")) {
			JSONObject obj = new JSONObject();
			float amount = Float.parseFloat(request.getParameter("amount"));
			String collectionDate = request.getParameter("collDate");
			String entryDate = request.getParameter("entryDate");
			int accId = Integer.parseInt(request.getParameter("accId"));
			String uName = request.getParameter("uName");
			try {
				PaymentClass pay = new PaymentClass();
                pay.updatePayment(amount, collectionDate, entryDate, accId, uName);
				obj.put("status", "success");

			} catch (Exception e) {
				obj.put("status", "Failure");
				e.printStackTrace();
			}
			response.getWriter().print(obj);

		} else if (op.equals("getOnePayment")) {
			JSONObject obj = new JSONObject();
			int accId = Integer.parseInt(request.getParameter("accId"));
			try {
				PaymentClass pay = new PaymentClass();
				obj = pay.getOnePayment(accId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.getWriter().print(obj);
		} else if (op.equals("deletePayment")) {
			JSONObject obj = new JSONObject();
			int accId = Integer.parseInt(request.getParameter("accId"));
			try {
				PaymentClass pay = new PaymentClass();
				pay.deletePayment(accId);
				obj.put("status", "success");
			} catch (Exception e) {
				obj.put("status", "Failure");
				e.printStackTrace();
			}
			response.getWriter().print(obj);
		}
	}

}
