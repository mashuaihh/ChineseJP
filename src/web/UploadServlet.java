package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.Upload;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
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
		String jp_trans = request.getParameter("jp_trans");
		String jp_publisher = request.getParameter("jp_publisher");
		String jp_pubdate = request.getParameter("jp_pubdate");
		String[] jps = {jp_text, jp_author, jp_trans, jp_publisher, jp_pubdate};
		
		String ch_text = request.getParameter("ch_text");
		String ch_author = request.getParameter("ch_author");
		String ch_trans = request.getParameter("ch_trans");
		String ch_publisher = request.getParameter("ch_publisher");
		String ch_pubdate = request.getParameter("ch_pubdate");
		String[] chs = {ch_text, ch_author, ch_trans, ch_publisher, ch_pubdate};
		
//		Upload upload = new Upload(ch_text, ch_author, ch_trans, ch_publisher, ch_update, 
//				jp_text, jp_author, jp_trans, jp_publisher, jp_update);
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");

		Upload upload = new Upload(chs, jps, user_id);
	}

}
