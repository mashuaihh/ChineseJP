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
		Integer jpIndex;
		Integer chIndex;
		//language = "ch" or "jp"
		HttpSession session = request.getSession();
		Boolean status = (Boolean) session.getAttribute("login_status");
		if (status == null) 
			status = false;

		SelectRegex sr = new SelectRegex(keyword, language, status);
		Pagination pg = new Pagination();

		if (jpOriIndex != null && chOriIndex != null) {
			
         sr.setChOriIndex(Integer.parseInt(jpOriIndex) - 1);
		 sr.setJpOriIndex(Integer.parseInt(chOriIndex) - 1);
		 
		 pg.setChOriIndex(Integer.parseInt(chOriIndex));
		 pg.setJpOriIndex(Integer.parseInt(jpOriIndex));
		 
		 jpIndex = Integer.parseInt(jpOriIndex);
		 chIndex = Integer.parseInt(chOriIndex);
		 
		} else if (jpOriIndex != null && chOriIndex == null) {

			sr.setJpOriIndex(Integer.parseInt(jpOriIndex) - 1);
			pg.setJpOriIndex(Integer.parseInt(jpOriIndex) - 1);
			
			jpIndex = Integer.parseInt(jpOriIndex);
			chIndex = 1;

		} else if (jpOriIndex == null && chOriIndex != null) {

			sr.setChOriIndex(Integer.parseInt(chOriIndex) - 1);
			pg.setChOriIndex(Integer.parseInt(chOriIndex));
			
			chIndex = Integer.parseInt(chOriIndex);
			jpIndex = 1;

		} else {
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
		
		request.setAttribute("jpOriPagesList", pg.getJpOriPageList());
		request.setAttribute("chOriPagesList", pg.getChOriPageList());

		request.setAttribute("jpCurrentIndex", jpIndex);
		request.setAttribute("jpPreviousIndex", jpIndex - 1);
		request.setAttribute("jpNextIndex", jpIndex + 1);
		request.setAttribute("jpFirstIndex", pg.getJpOriPageList().size() - 9);
		request.setAttribute("jpLastIndex", pg.getJpOriPageList().size());

		request.setAttribute("chCurrentIndex", chIndex);
		request.setAttribute("chPreviousIndex", chIndex - 1);
		request.setAttribute("chNextIndex", chIndex + 1);
		request.setAttribute("chFirstIndex", pg.getChOriPageList().size() - 9);
		request.setAttribute("chLastIndex", pg.getChOriPageList().size());

		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request, response);
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
		Integer jpIndex = Integer.parseInt(jpOriIndex) + 1;
		Integer chIndex = Integer.parseInt(chOriIndex) + 1;
		//language = "ch" or "jp"
		HttpSession session = request.getSession();
		Boolean status = (Boolean) session.getAttribute("login_status");
		if (status == null) 
			status = false;
		
		SelectRegex sr = new SelectRegex(keyword, language, status);

		if (jpOriIndex != null && chOriIndex != null) {
         sr.setChOriIndex(Integer.parseInt(jpOriIndex) - 1);
		 sr.setJpOriIndex(Integer.parseInt(chOriIndex) - 1);
		} else if (jpOriIndex != null && chOriIndex == null) {
			sr.setJpOriIndex(Integer.parseInt(jpOriIndex) - 1);
		} else if (jpOriIndex == null && chOriIndex != null) {
			sr.setChOriIndex(Integer.parseInt(chOriIndex) - 1);
		} else {
		// do nothing	
		}
		sr.selectContent();
		
		request.setAttribute("isSearch", sr.getIsSearch());
		request.setAttribute("jp_ori", sr.getJpOriList());
		request.setAttribute("ch_ori", sr.getChOriList());
		request.setAttribute("jpOriPageNum", sr.getJpOriPageNum());
		request.setAttribute("chOriPageNum", sr.getChOriPageNum());
		
		request.setAttribute("jpCurrentIndex", jpIndex);
		request.setAttribute("chCurrentIndex", chIndex);
		

		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request, response);
	}

}
