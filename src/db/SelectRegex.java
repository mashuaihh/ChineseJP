package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tool.ChOriBean;
import tool.JpOriBean;

public class SelectRegex {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private String language = null;
	private String[] keywords = null;
	private String oriKeyword = null;
	private ArrayList<JpOriBean>  jpOriList = new ArrayList<JpOriBean>();
	private ArrayList<ChOriBean>  chOriList = new ArrayList<ChOriBean>();
	private Boolean isSearch = false;
	private int jpOriPagesNum = 0;
	private int chOriPagesNum = 0;
	private int jpOriIndex = 0;
	private int chOriIndex = 0;
	private int span = 10;
	private boolean hasLimit = true;
	private boolean isLogin = false;
	private boolean isRegex = false;
	
	public SelectRegex(String keyword, String language, boolean status, boolean isRegex) {
		conn = new NewConnect().getConnection();
		this.language = language;
		this.oriKeyword = keyword;
		this.keywords = processKey(keyword);
		this.isSearch = true;
		this.isLogin = status;
		this.isRegex = isRegex;
			}
	
	public void selectContent() {
		selectJpOri();
		selectChOri();
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void selectAllContent() {
		this.hasLimit = false;
		
		selectJpOri();
		selectChOri();
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Boolean getIsSearch() {
		return this.isSearch;
	}
	
	public ArrayList<JpOriBean> getJpOriList() {
		return this.jpOriList;
	}
	
	public ArrayList<ChOriBean> getChOriList() {
		return this.chOriList;
	}
	
	private String[] processKey(String keyword) {
		//input: " key   word  "
		//output: String[] = {"key", "word"}
		String cleanKey = keyword.trim();
//		cleanKey = cleanKey.replaceAll("\\s+", " ");
		String delimiter = "[ ]+";
		String[] parts = cleanKey.split(delimiter);
		String[] after = new String[parts.length];
		for (int i = 0; i < parts.length; i++) {
			after[i] = "%" + parts[i] + "%";
		}
		return after;
	}
	
	private String processSql(String sql, String column) {
		for (int i = 0; i < this.keywords.length; i++) {
			sql += column + " LIKE ? AND ";
		}
		int last = sql.lastIndexOf("AND");
		sql = sql.substring(0, last);
		sql = sql.trim();
		return sql;
	}
	
	/**
	 * 
	 * @param baseSql
	 * @param column
	 * @return
	 */
	private String getSql(String baseSql, String column, String ori) {
		this.pstmt = null;
		String sql;
		if (this.isRegex == true) {
//			sql = baseSql + column + " REGEXP '" + this.oriKeyword + "'";
//			sql = baseSql + column + " REGEXP ?";
			sql = baseSql + " PREG_RLIKE(?, " + column + ") = 1";
		} else {
			sql = processSql(baseSql, column);
		}
		
		if (this.hasLimit) {
			if (ori.equals("ch")) {
				sql += " ORDER BY ch.ch_id LIMIT " + this.getChOriOffset();
			} else {
				sql += " ORDER BY jp.jp_id LIMIT " + this.getJpOriOffset();
			}
		}
		try {
			pstmt = conn.prepareStatement(sql);
			if (this.isRegex == false) {
				for (int i = 0; i < this.keywords.length; i++) {
					pstmt.setString(i+1, this.keywords[i]);	
				}
			} else {
				pstmt.setString(1, this.oriKeyword);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sql;
	}
	
	/**
	 * 
	 * @param baseSql
	 * @param column
	 * @param boolean for identifying count select
	 * @return
	 */
	private String getSql(String baseSql, String column, String ori, boolean a) {
		this.pstmt = null;
		String sql;
		if (this.isRegex == true) {
//			sql = baseSql + column + " REGEXP '" + this.oriKeyword + "'";
//			sql = baseSql + column + " REGEXP ?";
			sql = baseSql + " PREG_RLIKE(?, " + column + ") = 1";
		} else {
			sql = processSql(baseSql, column);
		}

		try {
			pstmt = conn.prepareStatement(sql);
			if (this.isRegex == false) {
				for (int i = 0; i < this.keywords.length; i++) {
					pstmt.setString(i+1, this.keywords[i]);	
				}
			} else {
				pstmt.setString(1, this.oriKeyword);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sql;
	}

	private void selectJpOri() {
		//ch ÔòËÑË÷ ch_transÖÐct_text (using LIKE)
		//jp       jp_ori jp_text (using Like)
		String ori = "jp";
		try {
			if (this.language.equals("jp")) {
				String column = "jp.jp_text";
				String sql1 = "SELECT * FROM jp_ori AS jp, ch_trans AS ch " + 
						"WHERE jp.jp_id = ch.jp_num AND ";

				this.getSql(sql1, column, ori);

			} else { //language is "ch"
				String column = "ch.ct_text";
				String sql2 = "SELECT * FROM jp_ori AS jp, ch_trans AS ch " + 
						"WHERE jp.jp_id = ch.jp_num AND ";

				this.getSql(sql2, column, ori);
			}
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String jp_id = rs.getString("jp_id");
				String jp_text = rs.getString("jp_text");
				String jp_author = rs.getString("author");
				String jp_publisher = rs.getString("publisher");
				String jp_pub_date = rs.getString("pub_date");
				String ct_id = rs.getString("ct_id");
				String ct_text = rs.getString("ct_text");
				String ct_translator = rs.getString("ct_translator");
				String ct_publisher = rs.getString("ct_publisher");
				String ct_pub_date = rs.getString("ct_pub_date");
				String ct_jp_num = rs.getString("jp_num");
				JpOriBean jpOriContent = new JpOriBean(jp_id,
						jp_text,
						jp_author,
						jp_publisher,
						jp_pub_date,
						ct_id,
						ct_text,
						ct_translator,
						ct_publisher,
						ct_pub_date,
						ct_jp_num);
				jpOriList.add(jpOriContent);
			}
			this.countJpOriPageNum();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private void selectChOri() {
		String ori = "ch";
		try {
			if (this.language.equals("jp")) {
				String column = "jp.jt_text";
				String sql1 = "SELECT * FROM ch_ori AS ch, jp_trans AS jp " + 
						"WHERE ch.ch_id = jp.ch_num AND ";

				this.getSql(sql1, column, ori);
			} else {
				String column = "ch.ch_text";
				String sql2 = "SELECT * FROM ch_ori AS ch, jp_trans AS jp " + 
						"WHERE ch.ch_id = jp.ch_num AND ";

				this.getSql(sql2, column, ori);
			}
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String ch_id = rs.getString("ch_id");
				String ch_text = rs.getString("ch_text");
				String ch_author = rs.getString("author");
				String ch_publisher = rs.getString("publisher");
				String ch_pub_date = rs.getString("pub_date");
				String jt_id = rs.getString("jt_id");
				String jt_text = rs.getString("jt_text");
				String jt_translator = rs.getString("jt_translator");
				String jt_publisher = rs.getString("jt_publisher");
				String jt_pub_date = rs.getString("jt_pub_date");
				String jt_ch_num = rs.getString("ch_num");
				ChOriBean chOriContent = new ChOriBean(ch_id,
						ch_text,
						ch_author,
						ch_publisher,
						ch_pub_date,
						jt_id,
						jt_text,
						jt_translator,
						jt_publisher,
						jt_pub_date,
						jt_ch_num);
				chOriList.add(chOriContent);
			}
			this.countChOriPageNum();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void countChOriPageNum() {
		String ori = "ch";
		try {
			if (this.language.equals("jp")) {
				String column = "jp.jt_text";
				String sql1 = "SELECT COUNT(*) FROM ch_ori AS ch, jp_trans AS jp " + 
						"WHERE ch.ch_id = jp.ch_num AND ";
				this.getSql(sql1, column, ori, true);
			} else {
				String column = "ch.ch_text";
				String sql2 = "SELECT COUNT(*) FROM ch_ori AS ch, jp_trans AS jp " + 
						"WHERE ch.ch_id = jp.ch_num AND ";
				this.getSql(sql2, column, ori, true);
			}
			
			ResultSet rs = pstmt.executeQuery();
			
			int pages = 0;
			while (rs.next()) {
				pages = rs.getInt("COUNT(*)");
			}
			this.chOriPagesNum = this.getAllPagesNum(pages);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void countJpOriPageNum() {
		String ori = "jp";
		try {
			if (this.language.equals("jp")) {
				String column = "jp.jp_text";
				String sql1 = "SELECT COUNT(*) FROM jp_ori AS jp, ch_trans AS ch " + 
						"WHERE jp.jp_id = ch.jp_num AND ";
				
				this.getSql(sql1, column, ori, true);
			} else { //language is "ch"
				String column = "ch.ct_text";
				String sql2 = "SELECT COUNT(*) FROM jp_ori AS jp, ch_trans AS ch " + 
						"WHERE jp.jp_id = ch.jp_num AND ";

				this.getSql(sql2, column, ori, true);
			}
			
			ResultSet rs = pstmt.executeQuery();
			
			int pages = 0;
			while (rs.next()) {
				pages = rs.getInt("COUNT(*)");
			}
			this.jpOriPagesNum = this.getAllPagesNum(pages);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Integer getJpOriPageNum() {
		return this.jpOriPagesNum;
	}
	
	public Integer getChOriPageNum() {
		return this.chOriPagesNum;
	}
	
	public void setJpOriIndex(int i) {
		this.jpOriIndex = i;
	}
	
	public int getJpOriIndex() {
		return this.jpOriIndex;
	}
	
	public void setChOriIndex(int i) {
		this.chOriIndex = i;
	}
	
	public int getChOriIndex() {
		return this.chOriIndex;
	}
	
	private String getChOriOffset() {
		return "" + this.chOriIndex * 10 + " , " + this.span + " " ;
	}
	
	private String getJpOriOffset() {
		return "" + this.jpOriIndex * 10 + " , " + this.span + " " ;
	}
	
	private int getAllPagesNum(int resultNum) {
		int remain = resultNum % span;
		int divResult = resultNum / span;
		int result;
		if (remain == 0) {
			result = divResult;
		} else {
			result = divResult + 1;
		}
		return result;
	}
	
	public static void main(String[] args) {
	}
}
