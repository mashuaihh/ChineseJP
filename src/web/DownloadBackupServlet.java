package web;

import java.io.BufferedInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Backup;

/**
 * Servlet implementation class DownloadBackupServlet
 */
@WebServlet("/DownloadBackupServlet")
public class DownloadBackupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadBackupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=chjpDataBaseBackup.sql");
		
		Backup backup = new Backup();
		if (backup.executeBackup()) {
			BufferedInputStream bis = new BufferedInputStream(backup.getBackupStream());
			ServletOutputStream output = response.getOutputStream();

			int r = 0;
			while ((r = bis.read()) != -1) {
				output.write(r);
			}
			
			bis.close();
			output.flush();
			output.close();
		} else {
			//redirect 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
