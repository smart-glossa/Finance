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
			String payment = request.getParameter("payment");
			int accId =Integer.parseInt(request.getParameter("accId"));
		    String paydate = request.getParameter("paydate");
			try {
				PaymentClass pay = new PaymentClass();
				pay.addPayment(payment, accId, paydate);
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
			String payment = request.getParameter("payment");
			int accId = Integer.parseInt(request.getParameter("accId"));
			String paydate = request.getParameter("paydate");
			try {
				PaymentClass pay = new PaymentClass();
				pay.updatePayment(payment,accId,paydate);
				obj.put("status", "success");

			} catch (Exception e) {
				obj.put("status", "Failure");
				e.printStackTrace();
			}
			response.getWriter().print(obj);

		} else if (op.equals("getOnePayment")) {
			JSONObject obj = new JSONObject();
			int pId = Integer.parseInt(request.getParameter("payId"));
			try {
				PaymentClass pay = new PaymentClass();
				obj = pay.getOnePayment(pId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.getWriter().print(obj);
		} else if (op.equals("deletePayment")) {
			JSONObject obj = new JSONObject();
			int pId = Integer.parseInt(request.getParameter("pId"));
			try {
				PaymentClass pay = new PaymentClass();
				pay.deletePayment(pId);
				obj.put("status", "success");
			} catch (Exception e) {
				obj.put("status", "Failure");
				e.printStackTrace();
			}
			response.getWriter().print(obj);
		}
	}

}
