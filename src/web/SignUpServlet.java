package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.InsertUser;
import tool.*;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
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
		request.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		String psw = request.getParameter("psw");
		String email = request.getParameter("email");
		String institute = request.getParameter("institute");
		String memo = request.getParameter("memo");
		
		String hashed = BCrypt.hashpw(psw, BCrypt.gensalt());
		
		InsertUser uu = new InsertUser();
		uu.setActivated(0);
		uu.setEmail(email);
		uu.setInstitute(institute);
		uu.setMemo(memo);
		uu.setPsw(hashed);
		uu.setUsername(username);
		uu.updateUser();
		
		response.sendRedirect("AfterSignUp.jsp");
	}

}
