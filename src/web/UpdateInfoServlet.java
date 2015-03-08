package web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.ChOriBean;
import tool.JpOriBean;
import tool.UpdateTextBean;
import db.SelectUpdateInfo;

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
		String ori = request.getParameter("ori");
		String ch_id = request.getParameter("ch_id");
		String jp_id = request.getParameter("jp_id");
		
		SelectUpdateInfo updateInfo = new SelectUpdateInfo(ori, ch_id, jp_id);
		
		ArrayList<ChOriBean> chOriList = updateInfo.getChOriList();
		ArrayList<JpOriBean> jpOriList = updateInfo.getJpOriList();
		
		UpdateTextBean textBean = new UpdateTextBean();
		
		if (chOriList.size() == 0) {
			//update in jpOri
			JpOriBean jpOriBean = jpOriList.get(0);
			if (ch_id == null) {
				//update jp in jpOri
				jpOriUpdateJp(textBean, jpOriBean);
			} else {
				//update ch in jpOri
				jpOriUpdateCh(textBean, jpOriBean);
			}
		} else {
			//update in chOri
			ChOriBean chOriBean = chOriList.get(0);
			if (ch_id == null) {
				//update jp in chOri
				chOriUpdateJp(textBean, chOriBean);
			} else {
				//update ch in chOri
				chOriUpdateCh(textBean, chOriBean);
			}
		}
		
		request.setAttribute("textBean", textBean);
		
		RequestDispatcher view = request.getRequestDispatcher("update.jsp");
		view.forward(request, response);
	}
	
	private void chOriUpdateJp(UpdateTextBean textBean, ChOriBean chOriBean) {
			//update jp text in jp_trans
			textBean.setOri("ch_ori");
			textBean.setUpdateLan("jp");
			textBean.setUpdateTable("jp_trans");
			textBean.setText_id(chOriBean.getJt_id());
			textBean.setText_content(chOriBean.getJt_text());
			//no author
			textBean.setTranslator(chOriBean.getJt_translator());
			textBean.setPublisher(chOriBean.getJt_publisher());
			textBean.setPub_date(chOriBean.getJt_pub_date());
	}
	
	private void chOriUpdateCh(UpdateTextBean textBean, ChOriBean chOriBean) {
		//update ch text in ch_ori
		textBean.setOri("ch_ori");
		textBean.setUpdateLan("ch");
		textBean.setUpdateTable("ch_ori");
		textBean.setText_id(chOriBean.getCh_id());
		textBean.setText_content(chOriBean.getCh_text());
		//no translator
		textBean.setAuthor(chOriBean.getCh_author());
		textBean.setPublisher(chOriBean.getCh_publisher());
		textBean.setPub_date(chOriBean.getCh_pub_date());
	}
	
	private void jpOriUpdateCh(UpdateTextBean textBean, JpOriBean jpOriBean) {
		textBean.setOri("jp_ori");
		textBean.setUpdateLan("ch");
		textBean.setUpdateTable("ch_trans");
		textBean.setText_id(jpOriBean.getCt_id());
		textBean.setText_content(jpOriBean.getCt_text());
		//no author
		textBean.setTranslator(jpOriBean.getCt_translator());
		textBean.setPublisher(jpOriBean.getCt_publisher());
		textBean.setPub_date(jpOriBean.getCt_pub_date());
	}
	
	private void jpOriUpdateJp(UpdateTextBean textBean, JpOriBean jpOriBean) {
		//update jp text in jp_ori
		textBean.setOri("jp_ori");
		textBean.setUpdateLan("jp");
		textBean.setUpdateTable("jp_ori");
		textBean.setText_id(jpOriBean.getJp_id());
		textBean.setText_content(jpOriBean.getJp_text());
		//no translator
		textBean.setAuthor(jpOriBean.getJp_author());
		textBean.setPublisher(jpOriBean.getJp_publisher());
		textBean.setPub_date(jpOriBean.getJp_pub_date());
	}

}
