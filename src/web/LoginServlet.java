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
       
	final boolean SUCCESS = true;
	final boolean FAILURE = false;
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
		String user_id = login.getUserid();
		Integer percent = login.getPercent();
		
		HttpSession session = request.getSession();
		
		Boolean status = new Boolean("false");
		if (login.getLogin_result() == SUCCESS) {
			status = new Boolean("true");
			session.setAttribute("login_status", status);
			session.setAttribute("login_name", username);
			session.setAttribute("user_id", user_id);
			session.setAttribute("user_percent", percent);
			session.setAttribute("role", login.getRole());
			response.sendRedirect("index.jsp");
		}
		else {
			status = new Boolean("false");
			session.setAttribute("login_status", status);
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
//			response.setHeader("Refresh", "4;URL=" + basePath + "/index.jsp");
			response.sendRedirect("ErrLogin.html");
		}
	}

}
