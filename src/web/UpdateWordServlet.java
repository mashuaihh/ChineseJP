package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.UpdateWord;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet(description = "upload the update content to DB to update", urlPatterns = { "/UpdateServlet" })
public class UpdateWordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateWordServlet() {
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
		String nation = request.getParameter("nation");
		String text_id = request.getParameter("text_id");
		String text = request.getParameter("text");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		String pub_date = request.getParameter("pub_date");
		String trans = request.getParameter("trans");
		
		UpdateWord uw = new UpdateWord(nation, text_id, text, author, publisher, pub_date, trans);
	}

}
