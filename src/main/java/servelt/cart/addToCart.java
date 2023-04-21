package servelt.cart;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartDetails;
import model.Customer;
import service.impl.CartControllerImpl;

/**
 * Servlet implementation class addToCart
 */
@WebServlet("/addToCart")
public class addToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addToCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		CartDetails cartDetails = new CartDetails();
		cartDetails.setItemcode(Integer.parseInt(request.getParameter("itemcode")));
		cartDetails.setQty(Integer.parseInt(request.getParameter("qty")));
		
		Customer customer = new Customer();
		customer.setCusId(session.getAttribute("cusId").toString());
		
		CartControllerImpl cartCtrl = new CartControllerImpl();
		boolean value = cartCtrl.addToCart(cartDetails, customer);
		
		request.setAttribute("list", request.getAttribute("list"));
		request.setAttribute("value", value);
		System.out.println("addToCart"+value);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/orderForm.jsp"); //error which is caused by customer adding the same item twice(primary key of cart_details repeat)
		dispatcher.forward(request, response);
		
	}

}
