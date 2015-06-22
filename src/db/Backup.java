package db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;


public class Backup {
	
	NewConnect newConn = new NewConnect();
	Connection conn = newConn.getConnection();
	static String fileName = "chjpBackup";
	
	public Backup() {
	}
	
	public boolean executeBackup() {
//		String cmd = "cmd.exe /c mysqldump -u " + newConn.getUser() + " -p" + newConn.getPassword()
		String cmd = "cmd.exe /c mysqldump -u " + newConn.getUser() + " -p" + newConn.getPassword()
				+ " chjp " + "> " + "d:\\" + Backup.fileName +".sql";
//				+ " chjp";
		try {

			Process pro = Runtime.getRuntime().exec(cmd);
			int processComplete = pro.waitFor();
			if (processComplete == 0) {
				return true;
			} else {
				return false;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public InputStream getBackupStream() {
		File file = new File("d:\\" + Backup.fileName + ".sql");
		try {
			InputStream input = new FileInputStream(file);
			return input;
			} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		Backup back = new Backup();
	}
}
