package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.UpdateWord;
import tool.UpdateTextBean;

/**
 * Servlet implementation class UpdateWordServlet
 */
@WebServlet("/UpdateWordServlet")
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
		
		String updateTable = request.getParameter("updateTable");
		String updateLan = request.getParameter("updateLan");
		String ori = request.getParameter("ori");
		String text_id = request.getParameter("text_id");
		String text_content = request.getParameter("text_content");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		String translator = request.getParameter("translator");
		String pub_date = request.getParameter("pub_date");
		
		UpdateTextBean textBean = new UpdateTextBean();
		textBean.setOri(ori);
		textBean.setUpdateLan(updateLan);
		textBean.setUpdateTable(updateTable);
		textBean.setText_id(text_id);
		textBean.setText_content(text_content);
		textBean.setAuthor(author);
		textBean.setTranslator(translator);
		textBean.setPublisher(publisher);
		textBean.setPub_date(pub_date);
	
		UpdateWord uw = new UpdateWord(textBean);
		
		response.sendRedirect("member/textMember.jsp");
	}

}
