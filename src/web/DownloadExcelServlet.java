package web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;

import tool.ChOriBean;
import tool.DownloadExcel;
import tool.JpOriBean;
import db.SelectRegex;

/**
 * Servlet implementation class DownloadExcelServlet
 */
@WebServlet("/DownloadExcelServlet")
public class DownloadExcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadExcelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=ChJpData.xls");

		request.setCharacterEncoding("UTF-8");
		String keyword = request.getParameter("keyword");
		String language = request.getParameter("language");
		//language = "ch" or "jp"
		HttpSession session = request.getSession();
		Boolean status = (Boolean) session.getAttribute("login_status");
		if (status == null) 
			status = false;

		SelectRegex sr = new SelectRegex(keyword, language, status);
		sr.selectAllContent();
		ArrayList<JpOriBean> jpOriList = sr.getJpOriList();
		ArrayList<ChOriBean> chOriList = sr.getChOriList();
		
		DownloadExcel downExcel = new DownloadExcel(jpOriList, chOriList);
		Workbook wb = downExcel.getWorkbook();

		ServletOutputStream out = response.getOutputStream();
		wb.write(out);
		out.flush();
		out.close();
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
