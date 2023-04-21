package servelt.item;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.item;
import service.impl.ItemControllerImpl;

/**
 * Servlet implementation class AddItem
 */
@WebServlet("/AddItem")
public class AddItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		item item = new item();
		
		item.setName(request.getParameter("name"));
		item.setQtyOnHand(Integer.parseInt(request.getParameter("qtyOnHand")));
		item.setUnitPrice(Float.parseFloat(request.getParameter("price")));
		
		ItemControllerImpl itemCtrl = new ItemControllerImpl();
		itemCtrl.addItem(item);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/itemManagement.jsp");
		dispatcher.forward(request, response);
	}

}
