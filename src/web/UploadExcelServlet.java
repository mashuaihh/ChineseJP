package web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			ServletContext servletContext = this.getServletConfig().getServletContext();
			System.out.println(servletContext);
			try {
				List<FileItem> items = upload.parseRequest(request);
				
				Iterator<FileItem> iter = items.iterator();
				
				PrintWriter out = response.getWriter();
				
				String path = this.getServletContext().getRealPath("/");
				System.out.println("real " + path);
//				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
				String pa = path + "/excel/";
				
				while (iter.hasNext()) {
				    FileItem item = iter.next();
				    if (item.isFormField()) {
				        out.println("This is form field" + item.getString());
				    } else {
				    	out.println("This is the file! " + item.getName());
				    	try {
							File dir = new File(pa);
				            File savedFile = File.createTempFile(item.getName(), "", dir);
				            item.write(savedFile);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    }
				}
				
				out.close();
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
