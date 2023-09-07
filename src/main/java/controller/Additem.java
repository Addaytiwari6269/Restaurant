package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyDao;
import dto.AddFoodItem;

@WebServlet("/additem")

@MultipartConfig
public class AddItem extends HttpServlet {
@Override

protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		//logic to receive values from front end
		String name=req.getParameter("name");
		Double price=Double.parseDouble(req.getParameter("price"));
	    int quantity=Integer.parseInt(req.getParameter("quantity"));
	    String type=req.getParameter("type");
	    
	    //to take picture from the frontend or UI
	   byte[] picture =new byte[req.getPart("pic").getInputStream().available()];
	   req.getPart("pic").getInputStream().read(picture);
	   
	   //loading value inside object	
	   AddFoodItem foodItem=new AddFoodItem();
	   foodItem.setName(name);
	   foodItem.setPrice(price);
	   foodItem.setStock(quantity);
	   foodItem.setType(type);
	   foodItem.setPicture(picture);
	   
	 //to access method of MyDao class by object creation
	 //persisting or saving the value inside database
	   MyDao dao=new MyDao();
	   dao.save(foodItem);
	   
	   resp.getWriter().print("<h1 style='color:green'>Item Added Successfully</h1>");
	   //to travel from one servlet to another servlet or html
	   req.getRequestDispatcher("AdminHome.html").include(req, resp);
	  
}
}
