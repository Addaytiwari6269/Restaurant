package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyDao;
import dto.AddFoodItem;
import dto.Customer;
import dto.CustomerFoodItem;

@WebServlet("/viewcustomermenu")
public class CustomerViewMenu extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("customer") == null) {
			resp.getWriter().print("<h1 style='color:red'>Invalid Session</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		} else {

			// logic to fetch data from database
			MyDao dao = new MyDao();
			List<AddFoodItem> items = dao.fetchAllFoodItem();

//	 //logic to display data on frontend
//	 resp.getWriter().print("<html><body><h1>Menu</h1>");
//	 resp.getWriter().print("<table border='1'>");
//		resp.getWriter().print(
//				"<tr><th>Name</th><th>Type</th><th>Price</th><th>Quantity</th><th>Edit</th><th>Delete</th></tr>");
//		for (AddFoodItem item : items) {
//			resp.getWriter().print(
//					"<tr><th>"+item.getName()+"</th><th>"+item.getType()+"</th><th>Price</th><th>"+item.getQuantity()+"</th><th><button>Edit</button></th><th><button>Delete</button></th></tr>");
//		}
//		resp.getWriter().print("</table></body></html>");

			// logic to carry data to frontend

			if (items.isEmpty()) {
				resp.getWriter().print("<h1 style='color:red'>no items found</h1> ");
				req.getRequestDispatcher("CustomerHome.html").include(req, resp);
			} else {
				Customer customer = (Customer) req.getSession().getAttribute("customer");
				List<CustomerFoodItem> cartitems = null;
				if (customer.getCart() != null && customer.getCart().getFoodItems() != null)
					cartitems = customer.getCart().getFoodItems();
				req.setAttribute("cartitems", cartitems);
				req.setAttribute("list", items);
				req.getRequestDispatcher("CustomerViewMenu.jsp").include(req, resp);
			}
		}
	}
}
