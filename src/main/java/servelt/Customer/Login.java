package servelt.Customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Customer;
import service.impl.CustomerControllerImpl;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		
		response.setContentType("text.html");
		PrintWriter out = response.getWriter();
		Customer customer = new Customer();
		
		customer.setUsername(request.getParameter("username"));
		customer.setPassword(request.getParameter("pw"));
		
		CustomerControllerImpl cusValidate = new CustomerControllerImpl();
		ResultSet value = cusValidate.loginValidate(customer);
		
		try {
			if(value.next()==true) {
				HttpSession session = request.getSession();    //unnecessary
				session.setAttribute("cusId", value.getString(1));
				session.setAttribute("Cus_name", value.getString(2));
				System.out.println("Login.jsp"+value.getString(2));
				//String cusId = value.getString(1);
				//response.sendRedirect("orderForm.jsp?cusId="+cusId);      //another way to pass the cusId value to orderForm(like php get method)
				RequestDispatcher dispatcher = request.getRequestDispatcher("OrderForm");
				dispatcher.forward(request, response);
			}else {
//				out.println("login.java");
//				out.println("<script type='text/javascript'>");     						 //script doesn't work
//				out.println("alert('Username or password is incorrect')");
//				out.println("</script>");	
				request.setAttribute("validate", false);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CustomerLogin.jsp");
				dispatcher.forward(request, response);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
