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
@WebServlet("/signup")
@MultipartConfig
// this is to make class as servlet class
public class CustomerSignup extends HttpServlet {
@Override
//when there is form and we want data to be secured so doPost...
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//logic to receive values from forntend	
	String fullname=req.getParameter("fullname");
	long mobile=Long.parseLong(req.getParameter("mobile"));
	String passward=req.getParameter("passward");
	String email=req.getParameter("email");
	String gender=req.getParameter("gender");
	String country=req.getParameter("country");
	LocalDate dob=LocalDate.parse(req.getParameter("dob"));
	//logic to receive the image and convertto byte[]
	int age=Period.between(dob, LocalDate.now()).getYears();
	Part pic=req.getPart("picture");
	byte[] picture=null;
	picture= new byte[pic.getInputStream().available()];
	pic.getInputStream().read(picture);
	
	
	MyDao dao= new MyDao();
	
	
	
	//logic to varify email and mobile number is not repeated...
	if(dao.fetchByemail(email)==null && dao.fetchBymobile(mobile)==null) {
		
	//loading value inside the object...
	Customer customer= new Customer();
	customer.setFullname(fullname);
	customer.setAge(age);
	customer.setCountry(country);
	customer.setDob(dob);
	customer.setEmail(email);
	customer.setGender(gender);
	customer.setMobile(mobile);
	customer.setPassward(passward);
	customer.setPicture(picture);
	
	//persisting
	
	dao.save(customer);
	resp.getWriter().print("<h1 style='color :green'>Account created successfully</h1>");
	req.getRequestDispatcher("hotel.html").include(req, resp);
}
	else {
		resp.getWriter().print("<h1 style='color :pink'>Email and Mobile  Number should be unique</h1>");
		req.getRequestDispatcher("Signup.html").include(req, resp);
	}
}
}

