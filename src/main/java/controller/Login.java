package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyDao;
import dto.Customer;

@WebServlet("/login")
public class Login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// receive values from forntend
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		// verify if email exists
		MyDao dao = new MyDao();
		if(email.equals("admin@jsp.com")&&password.equals("admin")) {
			resp.getWriter().print("<h1 style='color :green'>Login success</h1>");
			//this is logic to send to next page
			req.getRequestDispatcher("AdminHome.html").include(req, resp);
		}else {
		
		Customer customer = dao.fetchByemail(email);
		if (customer == null) {
			resp.getWriter().print("<h1 style='color :red'> Invalid Email</h1>");
			req.getRequestDispatcher("hotel.html").include(req, resp);
		} else {
			if (password.equals(customer.getPassward())) {
				resp.getWriter().print("<h1 style='color :Green'>Login success</h1>");
				req.getRequestDispatcher("CustomerHome.html").include(req, resp);
			} else {
				//if response should be both text amnd html.
				//resp.setContentType("text/html");   
				resp.getWriter().print("<h1 style='color :yellow'>Invalid passward</h1>");
				req.getRequestDispatcher("hotel.html").forward(req, resp);
			}
		}

	}
	}
}
