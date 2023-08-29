package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.MyDao;
import dto.Customer;

// this is to map the action url to this class(should be same as action -case sensitive )
@WebServlet("/signup")

//to recieve image  we need to use this enctype...
@MultipartConfig

//this is to make class as servlet class
public class CostumerSignup extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//logic to receive values from front end	
		String fullname=req.getParameter("fullname");
		long mobile=Long.parseLong(req.getParameter("mobile"));
		String password=req.getParameter("password");
		String email=req.getParameter("email");
		String gender=req.getParameter("gender");
		String country=req.getParameter("country");
		LocalDate dob=LocalDate.parse(req.getParameter("dob"));
		//logic to calculate the age in year from the date of birth
		int age=Period.between(dob, LocalDate.now()).getYears();
		
		//logic to receive the image and convert to byte[]
		Part pic=req.getPart("picture");
		byte[] picture=null;
		picture= new byte[pic.getInputStream().available()];
		pic.getInputStream().read(picture);
		
		//to access multiple method of MyDao class with single object creation we paste this here
		MyDao dao=new MyDao();
		
		//logic to verify email and mobile no is not repeated
		if(dao.fetchByEmail(email)==null && dao.fetchByMobile(mobile)==null){
			
	   //loading value inside object	
		Customer customer=new Customer();
		customer.setFullname(fullname);
		customer.setEmail(email);
		customer.setMobile(mobile);
		customer.setPassword(password);
		customer.setAge(age);
		customer.setDob(dob);
		customer.setCountry(country);
		customer.setGender(gender);
		customer.setPicture(picture);
		
		//persisting or saving the value inside database
		//MyDao dao=new MyDao();
		dao.save(customer);
		
		resp.getWriter().print("<h1 style='color:green'>Account created successfully</h1>");
		req.getRequestDispatcher("Login.html").include(req, resp);
		//("Login.html")is address it may be either file name or url
		}
		else {
			resp.getWriter().print("<h1style='color:blue'>Email and mobile no should be unique</h1>");
			req.getRequestDispatcher("Signup.html").include(req, resp);
			
		}
	}

}
