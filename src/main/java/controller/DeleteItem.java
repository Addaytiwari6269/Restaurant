package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyDao;
import dto.AddFoodItem;

@WebServlet("/delete")
public class DeleteItem extends HttpServlet
{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//getting Id from the Url
	int id =Integer.parseInt(req.getParameter("id"));
	
	MyDao dao= new MyDao();
	//Finding the object because remove method accepts object
	AddFoodItem item=dao.find(id);
	dao.delete(item);
	resp.getWriter().print("<h1 style='color:red'>Data Deleted Successfull</h1>");
	req.getRequestDispatcher("ViewMenu").include(req, resp);
}
}

