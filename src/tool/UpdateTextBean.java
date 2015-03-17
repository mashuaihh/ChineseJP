package tool;

public class UpdateTextBean {
	
	private String updateTable = null;
	private String updateLan = null;
	private String ori = null; //jp_ori or ch_ori
	
	private String text_id = null;
	private String text_content = null;
	private String author = null;
	private String publisher = null;
	private String translator = null;
	private String pub_date = null;
	
	public UpdateTextBean() {
		
	}
	
	public void setOri(String str) {
		this.ori = str;
	}
	
	public void setUpdateTable(String str) {
		this.updateTable = str;
	}

	public void setUpdateLan(String str) {
		this.updateLan = str;
	}

	public void setText_id(String str) {
		this.text_id = str;
	}

	public void setText_content(String str) {
		this.text_content = str;
	}

	public void setAuthor(String str) {
		this.author = str;
	}

	public void setPublisher(String str) {
		this.publisher = str;
	}

	public void setTranslator(String str) {
		this.translator = str;
	}

	public void setPub_date(String str) {
		this.pub_date = str;
	}
	
	//get methods
	
	public String getOri() {
		return this.ori;
	}
	
	public String getUpdateTable() {
		return this.updateTable;
	}

	public String getUpdateLan() {
		return this.updateLan;
	}

	public String getText_id() {
		return this.text_id;
	}

	public String getText_content() {
		return this.text_content;
	}

	public String getAuthor() {
		return this.author;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public String getTranslator() {
		return this.translator;
	}

	public String getPub_date() {
		return this.pub_date;
	}
	
}
