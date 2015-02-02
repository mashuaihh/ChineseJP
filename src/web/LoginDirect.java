package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import wordSearch.DB_Login;

/**
 * Servlet implementation class LoginProcess
 */
@WebServlet("/LoginDirect")
public class LoginDirect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	final int SUCCESS = 1;
	final int FAILURE = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginDirect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		DB_Login db_login = new DB_Login(username, password);
		HttpSession session = request.getSession();
		Integer suck = new Integer(SUCCESS);

		if (db_login.getSuccess() == SUCCESS) {
			request.setAttribute("name", username);
			session.setAttribute("username", username);
			session.setAttribute("login_status", suck);
//			RequestDispatcher view_success = request.getRequestDispatcher("login_success.jsp");
//			view_success.forward(request, response);
			response.sendRedirect("main_session.jsp");
		}
		else {
			RequestDispatcher view_failure = request.getRequestDispatcher("login_failure.jsp");
			session.setAttribute("login_status", FAILURE);
			view_failure.forward(request, response);
		}
	}

}
