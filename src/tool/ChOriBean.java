package tool;

public class ChOriBean {
	private String ch_id;
	private String ch_text;
	private String ch_author;
	private String ch_publisher;
	private String ch_pub_date;
	
	private String jt_id;
	private String jt_text;
	private String jt_translator;
	private String jt_publisher;
	private String jt_pub_date;
	private String jt_ch_num;
	
	public ChOriBean() {
		
	}
	
	public ChOriBean(String ch_id,
							String ch_text,
							String ch_author,
							String ch_publisher,
							String ch_pub_date,
							String jt_id,
							String jt_text,
							String jt_translator,
							String jt_publisher,
							String jt_pub_date,
							String jt_ch_num) {
		this.ch_id = ch_id;
		this.ch_text = ch_text;
		this.ch_author = ch_author;
		this.ch_publisher = ch_publisher;
		this.ch_pub_date = ch_pub_date;
		this.jt_id = jt_id;
		this.jt_text = jt_text;
		this.jt_translator = jt_translator;
		this.jt_publisher = jt_publisher;
		this.jt_pub_date = jt_pub_date;
		this.jt_ch_num = jt_ch_num;
	}
	
	public void setCh_id(String str) {
		this.ch_id = str;
	}

	public void setCh_text(String str) {
		this.ch_text = str;
	}

	public void setCh_author(String str) {
		this.ch_author = str;
	}

	public void setCh_publisher(String str) {
		this.ch_publisher = str;
	}

	public void setCh_pub_date(String str) {
		this.ch_pub_date = str;
	}

	public void setJt_id(String str) {
		this.jt_id = str;
	}

	public void setJt_text(String str) {
		this.jt_text = str;
	}

	public void setJt_translator(String str) {
		this.jt_translator = str;
	}

	public void setJt_publisher(String str) {
		this.jt_publisher = str;
	}

	public void setJt_pub_date(String str) {
		this.jt_pub_date = str;
	}

	public void setJt_ch_num(String str) {
		this.jt_ch_num = str;
	}
	
	public String getCh_id() {
		return this.ch_id;
	}

	public String getCh_text() {
		return this.ch_text;
	}

	public String getCh_author() {
		return this.ch_author;
	}

	public String getCh_publisher() {
		return this.ch_publisher;
	}

	public String getCh_pub_date() {
		return this.ch_pub_date;
	}

	public String getJt_id() {
		return this.jt_id;
	}

	public String getJt_text() {
		return this.jt_text;
	}

	public String getJt_translator() {
		return this.jt_translator;
	}

	public String getJt_publisher() {
		return this.jt_publisher;
	}

	public String getJt_pub_date() {
		return this.jt_pub_date;
	}

	public String getJt_ch_num() {
		return this.jt_ch_num;
	}

}
