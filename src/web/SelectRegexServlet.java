package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Pagination;
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
		request.setCharacterEncoding("UTF-8");
		String keyword = request.getParameter("keyword");
		String language = request.getParameter("language");
		String jpOriIndex = request.getParameter("jpOriIndex");
		String chOriIndex = request.getParameter("chOriIndex");
		boolean regex = false;
//		String isRegex = request.getParameter("isRegex");
//		if (isRegex != null) {
//			regex = true;
//		}
		Integer jpIndex;
		Integer chIndex;
		//language = "ch" or "jp"
		HttpSession session = request.getSession();
		Boolean status = (Boolean) session.getAttribute("login_status");
		if (status == null) 
			status = false;

		SelectRegex sr = new SelectRegex(keyword, language, status, regex);
		Pagination pg = new Pagination();

		if (jpOriIndex != null && chOriIndex != null) {
			
         sr.setChOriIndex(Integer.parseInt(chOriIndex) - 1);
		 sr.setJpOriIndex(Integer.parseInt(jpOriIndex) - 1);
		 
		 pg.setChOriDbIndex(Integer.parseInt(chOriIndex));
		 pg.setJpOriDbIndex(Integer.parseInt(jpOriIndex));
		 
		 jpIndex = Integer.parseInt(jpOriIndex);
		 chIndex = Integer.parseInt(chOriIndex);
		 
		} 
		else {
		// do nothing	
			chIndex = 1;
			jpIndex = 1;
		}

		sr.selectContent();

		pg.setChOriPagesSum(sr.getChOriPageNum());
		pg.setJpOriPagesSum(sr.getJpOriPageNum());
		pg.process();
		
		request.setAttribute("isSearch", sr.getIsSearch());
		request.setAttribute("jp_ori", sr.getJpOriList());
		request.setAttribute("ch_ori", sr.getChOriList());
		request.setAttribute("jpOriPageNum", sr.getJpOriPageNum());
		request.setAttribute("chOriPageNum", sr.getChOriPageNum());
		
		// 1-10, 11-20
		request.setAttribute("jpOriPagesList", pg.getJpOriPageList());
		request.setAttribute("chOriPagesList", pg.getChOriPageList());

		request.setAttribute("jpCurrentIndex", jpIndex);
		request.setAttribute("jpPreviousIndex", jpIndex - 1);
		request.setAttribute("jpNextIndex", jpIndex + 1);
		request.setAttribute("jpFirstIndex", pg.getJpFirstPage());
		request.setAttribute("jpLastIndex", pg.getJpOriPageList().size());

		request.setAttribute("chCurrentIndex", chIndex);
		request.setAttribute("chPreviousIndex", chIndex - 1);
		request.setAttribute("chNextIndex", chIndex + 1);
		request.setAttribute("chFirstIndex", pg.getChFirstPage());
		request.setAttribute("chLastIndex", pg.getChOriPageList().size());

		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
