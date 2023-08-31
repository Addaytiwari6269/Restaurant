package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyDao;
import dto.AddFoodItem;
import dto.Customer;

@WebServlet("/deleteCustomer")
public class DeleteCustomer extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
int id =Integer.parseInt(req.getParameter("id"));
	
	MyDao dao= new MyDao();
	//Finding the object because remove method accepts object
	Customer customer=dao.finds(id);
	dao.deleteCustomer(customer);
	resp.getWriter().print("<h1 style='color:red'>Data Deleted Successfull</h1>");
	req.getRequestDispatcher("ViewCustomer").include(req, resp);
}
}
