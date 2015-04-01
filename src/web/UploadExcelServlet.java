package web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import db.AddText;
import tool.AddTextContent;
import tool.UploadExcel;

/**
 * Servlet implementation class UploadExcelServlet
 */
@WebServlet("/UploadExcelServlet")
public class UploadExcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadExcelServlet() {
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
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			ServletContext servletContext = this.getServletConfig().getServletContext();
			System.out.println(servletContext);
			try {
				List<FileItem> items = upload.parseRequest(request);

				PrintWriter out = response.getWriter();
				
				String path = this.getServletContext().getRealPath("/");
				System.out.println("real " + path);
//				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
				
				String language = null;
				UploadExcel upExcel = null;
				for (FileItem item : items) {
					if (item.isFormField()) {
						language = item.getString();
					} else {
						InputStream inputStream = item.getInputStream();
						System.out.println("avd   " + inputStream.available());
						FileInputStream fileIn = (FileInputStream) inputStream;
						System.out.println("avd   " + fileIn.available());
						upExcel = new UploadExcel(fileIn, language, user_id);
						Boolean inin = upExcel.readExcel();
						System.out.println("ohhh " + inin);
						inputStream.close();
						fileIn.close();
					}
				}
				
				
				ArrayList<AddTextContent> chList = upExcel.getChList();
				ArrayList<AddTextContent> jpList = upExcel.getJpList();
				
				for (int i = 0; i < chList.size(); i++) {
					AddTextContent chContent = chList.get(i);
					AddTextContent jpContent = jpList.get(i);
					AddText addtext = new AddText(chContent, jpContent, language);
				}
				
				out.close();
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
