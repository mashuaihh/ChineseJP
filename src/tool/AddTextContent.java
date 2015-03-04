package tool;

public class AddTextContent {
	private String text = null;
	private String author = null;
	private String translator = null;
	private String publisher = null;
	private String pub_date = null;
	private String user_id = null;
	
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

	public String getAuthor() {
		return this.author;
	}

	public String getTranslator() {
		return this.translator;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public String getPub_date() {
		return this.pub_date;
	}
	
	public String getUser_id() {
		return this.user_id;
	}
}
