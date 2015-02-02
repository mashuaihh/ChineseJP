package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wordSearch.*;

/**
 * Servlet implementation class Transfer_Ch
 */
@WebServlet("/TransferCh.do")
public class TransferCh extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferCh() {
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
		String old_word = request.getParameter("word");
//		String word = new String(old_word.getBytes("ISO-8859-1"), "UTF-8");
		String word = old_word;
		DB_Chinese db_ch = new DB_Chinese(word);

		request.setAttribute("ch", db_ch.getCh());
		request.setAttribute("jp", db_ch.getJp());
		request.setAttribute("chs", db_ch.getChs());
		request.setAttribute("jps", db_ch.getJps());

		RequestDispatcher view = request.getRequestDispatcher("show_ch.jsp");

		view.forward(request, response);
	}

}
