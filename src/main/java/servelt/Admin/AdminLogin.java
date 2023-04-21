package servelt.Admin;

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
import service.impl.AdminControllerImpl;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
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
		PrintWriter out = response.getWriter();
		response.setContentType("text.html");
		Customer customer = new Customer();
		
		String username = request.getParameter("username");
		String pw = request.getParameter("pw");
		
		//customer.setUsername(request.getParameter("username"));
		//customer.setPassword(request.getParameter("pw"));
		
		AdminControllerImpl adminValidate = new AdminControllerImpl();
		ResultSet value = adminValidate.loginValidate(username,pw);
		
		try {
			if(value.next()==true) {
				HttpSession session = request.getSession();    //unnecessary
				session.setAttribute("adminId", value.getString(1));
				//String cusId = value.getString(1);
				//response.sendRedirect("orderForm.jsp?cusId="+cusId);      //another way to pass the cusId value to orderForm(like php get method)
				RequestDispatcher dispatcher = request.getRequestDispatcher("itemManagement.jsp");
				dispatcher.forward(request, response);
			}else {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/adminLogin.jsp");
				dispatcher.forward(request, response);
				out.println("<script type='text/javascript'>");     						 //script doesn't work
				out.println("alert('Username or password is incorrect')");
				out.println("</script>");	
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
