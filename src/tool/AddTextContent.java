package tool;

public class AddTextContent {
	private String text = null;
	private String author = null;
	private String translator = null;
	private String publisher = null;
	private String pub_date = null;
	private String user_id = null;
	
	public AddTextContent() {
		
	}
	
	public AddTextContent(String text, String author, String translator,
			String publisher, String pub_date, String user_id) {
		this.text = text;
		this.author = author;
		this.translator = translator;
		this.publisher = publisher;
		this.pub_date = pub_date;
		this.user_id = user_id;
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String str) {
		this.text = str;
	}

	public String getAuthor() {
		return this.author;
	}
	
	public void setAuthor(String str) {
		this.author = str;
	}

	public String getTranslator() {
		return this.translator;
	}
	
	public void setTranslator(String str) {
		this.translator = str;
	}

	public String getPublisher() {
		return this.publisher;
	}
	
	public void setPublisher(String str) {
		this.publisher = str;
	}

	public String getPub_date() {
		return this.pub_date;
	}
	
	public void setPub_date(String str) {
		this.pub_date = str;
	}
	
	public String getUser_id() {
		return this.user_id;
	}
	
	public void setUser_id(String str) {
		this.user_id = str;
	}
}
