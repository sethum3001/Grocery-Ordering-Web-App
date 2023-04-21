package servelt.cart;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartDetails;
import service.impl.CartControllerImpl;

/**
 * Servlet implementation class CartUpdate
 */
@WebServlet("/CartUpdate")
public class CartUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartUpdate() {
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
		String cusId = (String)session.getAttribute("cusId");
		String cartId = request.getParameter("cartId");
		String itemId = request.getParameter("itemId");
		String itemQty = request.getParameter("qty");
		//String itemPrice = request.getParameter("price");              this doesn't work & don't know why
		float unitPrice = Float.parseFloat(request.getParameter("unitPrice"));
		int quantity = Integer.parseInt(itemQty);
		int total = quantity * (int)unitPrice;
		
		CartControllerImpl cartCtrl = new CartControllerImpl();
		ArrayList<ArrayList<Object>> list = cartCtrl.cartUpdate(new CartDetails(
				Integer.valueOf(itemId),
				Integer.valueOf(cartId),
				total,
				quantity
				),cusId);
		
		request.setAttribute("list", list);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Cart.jsp");
		dispatcher.forward(request, response);
	}

}
