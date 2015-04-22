package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.SelectRegex;

/**
 * Servlet implementation class SelectRegexServlet
 */
@WebServlet("/SelectRegexServlet")
public class SelectRegexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectRegexServlet() {
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
		String keyword = request.getParameter("keyword");
		String language = request.getParameter("language");
		String jpOriIndex = request.getParameter("jpOriIndex");
		String chOriIndex = request.getParameter("chOriIndex");
		//language = "ch" or "jp"
		HttpSession session = request.getSession();
		Boolean status = (Boolean) session.getAttribute("login_status");
		if (status == null) 
			status = false;
		
		SelectRegex sr = new SelectRegex(keyword, language, status);

		if (jpOriIndex != null && chOriIndex != null) {
         sr.setChOriIndex(Integer.parseInt(jpOriIndex));
		 sr.setJpOriIndex(Integer.parseInt(chOriIndex));
		} else if (jpOriIndex != null && chOriIndex == null) {
			sr.setJpOriIndex(Integer.parseInt(jpOriIndex));
		} else if (jpOriIndex == null && chOriIndex != null) {
			sr.setChOriIndex(Integer.parseInt(chOriIndex));
		} else {
		// do nothing	
		}
		sr.selectContent();
		
		request.setAttribute("isSearch", sr.getIsSearch());
		request.setAttribute("jp_ori", sr.getJpOriList());
		request.setAttribute("ch_ori", sr.getChOriList());
		
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request, response);
	}

}
