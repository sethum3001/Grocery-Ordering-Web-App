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
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
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
		response.setContentType("text/html");
		
		item item = new item();
		
		item.setCode(request.getParameter("code"));
		item.setName(request.getParameter("name"));
		item.setUnitPrice(Float.parseFloat(request.getParameter("price")));
		item.setQtyOnHand(Integer.parseInt(request.getParameter("qtyOnHand")));
		
		ItemControllerImpl itemController = new ItemControllerImpl();
		itemController.updateItem(item);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/itemManagement.jsp");
		dispatcher.forward(request, response);
	}

}
