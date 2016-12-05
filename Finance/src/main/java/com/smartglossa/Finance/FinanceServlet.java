package com.smartglossa.Finance;

import java.io.IOException;
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
				FinanceClass cus = new FinanceClass();
				cus.addCustomer(cusName, addr, conNo);
				obj.put("status", "success");
			} catch (Exception e) {
				e.printStackTrace();
				obj.put("status", "success");
			}
			response.getWriter().print(obj);
		}else if(op.equals("getAllCustomer")){
			JSONArray result = new JSONArray();
			try {
				FinanceClass cus = new FinanceClass();
				result = cus.getAllCustomer();
			} catch (Exception e) {
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
				FinanceClass cus = new FinanceClass();
				cus.updateCustomer(cusId, cusName, addr, conNo);
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
				FinanceClass cus = new FinanceClass();
				obj = cus.getOneCustomer(cusId);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.getWriter().print(obj);
		}else if(op.equals("deleteCustomer")){
			JSONObject obj = new JSONObject();
			int cusId = Integer.parseInt(request.getParameter("cusId"));
			try {
				FinanceClass cus = new FinanceClass();
				cus.deleteCustomer(cusId);
				obj.put("status", "success");
				} catch (Exception e) {
					obj.put("status","Failure");
				e.printStackTrace();
			}
			 response.getWriter().print(obj);
		}
	}

}
 