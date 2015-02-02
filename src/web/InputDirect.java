package web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wordSearch.DB_Upload;

/**
 * Servlet implementation class InputDirect
 */
@WebServlet("/InputDirect")
public class InputDirect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InputDirect() {
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
		String jp_text = request.getParameter("jp_text");
		String jp_author = request.getParameter("jp_author");
		String jp_translator = request.getParameter("jp_trans");
		String jp_date = request.getParameter("jp_publish");
		
		DB_Upload db_upload = new DB_Upload(jp_text, jp_author, jp_translator, jp_date);
		
		
	}

}
