package tool;

public class JpOriBean {
	private String jp_id;
	private String jp_text;
	private String jp_author;
	private String jp_publisher;
	private String jp_pub_date;
	
	private String ct_id;
	private String ct_text;
	private String ct_translator;
	private String ct_publisher;
	private String ct_pub_date;
	private String ct_jp_num;
	
	public JpOriBean() {
		
	}
	
	public JpOriBean(String jp_id,
							String jp_text,
							String jp_author,
							String jp_publisher,
							String jp_pub_date,
							String ct_id,
							String ct_text,
							String ct_translator,
							String ct_publisher,
							String ct_pub_date,
							String ct_jp_num) {
		this.jp_id = jp_id;
		this.jp_text = jp_text;
		this.jp_author = jp_author;
		this.jp_publisher = jp_publisher;
		this.jp_pub_date = jp_pub_date;
		this.ct_id = ct_id;
		this.ct_text = ct_text;
		this.ct_translator = ct_translator;
		this.ct_publisher = ct_publisher;
		this.ct_pub_date = ct_pub_date;
		this.ct_jp_num = ct_jp_num;
	}
	
	public void setJp_id(String str) {
		this.jp_id = str;
	}

	public void setJp_text(String str) {
		this.jp_text = str;
	}

	public void setJp_author(String str) {
		this.jp_author = str;
	}

	public void setJp_publisher(String str) {
		this.jp_publisher = str;
	}

	public void setJp_pub_date(String str) {
		this.jp_pub_date = str;
	}

	public void setCt_id(String str) {
		this.ct_id = str;
	}

	public void setCt_text(String str) {
		this.ct_text = str;
	}

	public void setCt_translator(String str) {
		this.ct_translator = str;
	}

	public void setCt_publisher(String str) {
		this.ct_publisher = str;
	}

	public void setCt_pub_date(String str) {
		this.ct_pub_date = str;
	}

	public void setCt_jp_num(String str) {
		this.ct_jp_num = str;
	}
	
	public String getJp_id() {
		return this.jp_id;
	}

	public String getJp_text() {
		return this.jp_text;
	}

	public String getJp_author() {
		return this.jp_author;
	}

	public String getJp_publisher() {
		return this.jp_publisher;
	}

	public String getJp_pub_date() {
		return this.jp_pub_date;
	}

	public String getCt_id() {
		return this.ct_id;
	}

	public String getCt_text() {
		return this.ct_text;
	}

	public String getCt_translator() {
		return this.ct_translator;
	}

	public String getCt_publisher() {
		return this.ct_publisher;
	}

	public String getCt_pub_date() {
		return this.ct_pub_date;
	}

	public String getCt_jp_num() {
		return this.ct_jp_num;
	}
	
}
