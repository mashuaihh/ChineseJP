package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.AddText;
import tool.AddTextContent;

/**
 * Servlet implementation class AddTextMemServlet
 */
@WebServlet("/AddTextMemServlet")
public class AddTextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTextServlet() {
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
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		String language = request.getParameter("language");//ch or jp
		
		String jp_text = request.getParameter("jp_text");
		String jp_author = request.getParameter("jp_author");
		String jp_trans = request.getParameter("jp_trans");
		String jp_publisher = request.getParameter("jp_publisher");
		String jp_pub_date = request.getParameter("jp_pub_date");
		AddTextContent jpContent = new AddTextContent(jp_text, jp_author, jp_trans, jp_publisher, jp_pub_date, user_id);
		
		String ch_text = request.getParameter("ch_text");
		String ch_author = request.getParameter("ch_author");
		String ch_trans = request.getParameter("ch_trans");
		String ch_publisher = request.getParameter("ch_publisher");
		String ch_pub_date = request.getParameter("ch_pub_date");
		AddTextContent chContent = new AddTextContent(ch_text, ch_author, ch_trans, ch_publisher, ch_pub_date, user_id);
		
		AddText addtext = new AddText(chContent, jpContent, language);
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
		response.sendRedirect(basePath + "/member/add_text_mem.jsp");

	}
	


}
