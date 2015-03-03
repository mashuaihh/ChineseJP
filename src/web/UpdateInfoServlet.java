package web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.SearchUpdateInfo;

/**
 * Servlet implementation class UpdateInfoServlet
 */
@WebServlet(description = "Find the information in db for updating", urlPatterns = { "/UpdateInfoServlet" })
public class UpdateInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInfoServlet() {
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
		String ch_id = request.getParameter("ch_id");
		String jp_id = request.getParameter("jp_id");
		
		SearchUpdateInfo updateInfo = new SearchUpdateInfo(ch_id, jp_id);
		ArrayList<String> infoList = updateInfo.getInfoList();
		String nation = updateInfo.getNation();
		String text_id = updateInfo.getTextId();
		
		request.setAttribute("infoList", infoList);
		request.setAttribute("nation", nation);
		request.setAttribute("text_id", text_id);
		
		RequestDispatcher view = request.getRequestDispatcher("update.jsp");
		view.forward(request, response);
	}

}
