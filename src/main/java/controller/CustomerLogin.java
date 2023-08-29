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
public class CustomerLogin extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Recieve values from frontend
		String email=req.getParameter("email");
		String pass=req.getParameter("password");
		
		//verify if email exists
		MyDao dao=new MyDao();
		
		if(email.equals("admin@jsp.com")&& pass.equals("admin@1998"))
		{
			resp.getWriter().print("<h1 style='color:green'>Login success</h1>");
			//this is the logic to send to next page
			req.getRequestDispatcher("AdminHome.html").include(req, resp);
		}
		else {
		Customer customer=dao.fetchByEmail(email);
		if(customer==null)
		{
			//if we dont write print statement in between<h1></h1>tag it will print full html structure and another way is written below:
			resp.getWriter().print("<h1 style='color:red'>Invalid Email</h1>");
			//OR  
			 //resp.setContentType("text/html");
			req.getRequestDispatcher("Login.html").include(req, resp);
		}
		else
		{
			if(pass.equals(customer.getPassword()))
			{
				resp.getWriter().print("<h1 style='color:green'>Login Successful</h1>");
				req.getRequestDispatcher("CustomerHome.html").include(req, resp);
			}
			else
			{
				resp.getWriter().print("<h1 style='color:red'>Invalid Password</h1>");
				req.getRequestDispatcher("Login.html").include(req, resp);
			}
		}
		}
	}

}
