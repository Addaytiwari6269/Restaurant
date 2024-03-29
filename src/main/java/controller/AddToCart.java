
package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyDao;
import dto.AddFoodItem;
import dto.Cart;
import dto.Customer;
import dto.CustomerFoodItem;

@WebServlet("/addtocart")
public class AddToCart extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("customer") == null) {
			resp.getWriter().print("<h1 style='color:red'>Invalid Session</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		} else {
			int id = Integer.parseInt(req.getParameter("id"));

			MyDao dao = new MyDao();
			AddFoodItem foodItem = dao.find(id);

			// checking if stock is there...
			if (foodItem.getStock() > 0) {
				Customer customer = (Customer) req.getSession().getAttribute("customer");


				Cart cart = customer.getCart();

				if (cart == null) {
					cart = new Cart();
					customer.setCart(cart);
				}
				if (cart.getFoodItems() == null) {
					cart.setFoodItems(new ArrayList<CustomerFoodItem>());
				}
				boolean flag = true;
				for (CustomerFoodItem item : cart.getFoodItems()) {
					if (item.getName().equals(foodItem.getName())) {
						item.setQuantity(item.getQuantity() + 1);
						item.setPrice(item.getPrice() + foodItem.getPrice());

						flag = false;
						break;
					}

				}
				if (flag) {
					CustomerFoodItem item = new CustomerFoodItem();
					item.setName(foodItem.getName());
					item.setPicture(foodItem.getPicture());
					item.setPrice(foodItem.getPrice());
					item.setQuantity(1);
					item.setType(foodItem.getType());
					cart.getFoodItems().add(item);
				}
				dao.update(customer);

				req.getSession().removeAttribute("customer");
				req.getSession().setAttribute("customer", dao.finds(customer.getId()));

				// logic to reduce Stock
				foodItem.setStock(foodItem.getStock() - 1);
				dao.update(foodItem);

				resp.getWriter().print("<h1 style='color:red'>Added SuccessFully</h1>");
				req.setAttribute("cartitems", cart.getFoodItems());
				req.getRequestDispatcher("viewcustomermenu").include(req, resp);
			} else {
				resp.getWriter().print("<h1 style='color:red'>Out of Stock</h1>");
				req.getRequestDispatcher("viewcustomermenu").include(req, resp);
			}
		}
	}
}
