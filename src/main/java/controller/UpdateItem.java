package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.MyDao;
import dto.AddFoodItem;


@WebServlet("/update")
@MultipartConfig
public class UpdateItem extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int id=Integer.parseInt(req.getParameter("id"));
	String name=req.getParameter("name");
	Double price=Double.parseDouble(req.getParameter("price"));
    int quantity=Integer.parseInt(req.getParameter("quantity"));
    String type=req.getParameter("type");
    
    Part part = req.getPart("pic");
    byte[] picture=null;
    MyDao dao=new MyDao();
    AddFoodItem item1=dao.find(id);
    if(part==null)
    {
    	picture=item1.getPicture();
    }
    else
    {
    	picture=new byte[part.getInputStream().available()];
    	part.getInputStream().read(picture);
    }
    
    AddFoodItem item=new AddFoodItem();
       item.setId(id);
	   item.setName(name);
	   item.setPrice(price);
	   item.setQuantity(quantity);
	   item.setType(type);
	  if(picture.length==0) {
		  item.setPicture(dao.find(id).getPicture());
		  
	  }else {
		  item.setPicture(picture);
	  }
	   dao.update(item);
	   resp.getWriter().print("<h1 style='color: orange'>Data Update Successfully</h1>");
	   req.getRequestDispatcher("ViewMenu").include(req, resp);
}
  
}
