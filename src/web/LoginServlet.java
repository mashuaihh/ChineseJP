package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.Login;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	final int SUCCESS = 1;
	final int FAILURE = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//用get方法处理logout
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String psw = request.getParameter("password");
		Login login = new Login(username, psw);
		
		HttpSession session = request.getSession();
		Integer status = null;
		if (login.getLogin_result() == SUCCESS) {
			status = new Integer(SUCCESS);
			session.setAttribute("login_status", status);
			session.setAttribute("login_name", username);
			response.sendRedirect("index.jsp");
		}
		else {
			status = new Integer(FAILURE);
			session.setAttribute("login_status", status);
			response.setHeader("Refresh", "3;URL=index.jsp");
			response.sendRedirect("loginFailure.html");
		}
	}

}
