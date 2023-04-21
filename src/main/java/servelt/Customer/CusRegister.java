package servelt.Customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import service.impl.CustomerControllerImpl;

/**
 * Servlet implementation class CusRegister
 */
@WebServlet("/CusRegister")
public class CusRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CusRegister() {
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
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String pw = request.getParameter("pw");
		Customer cus = new Customer(null,name,username,pw);
		CustomerControllerImpl cusCtrl = new CustomerControllerImpl();
		cusCtrl.customerRegister(cus);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CustomerLogin.jsp");
		dispatcher.forward(request, response);
	}

}
