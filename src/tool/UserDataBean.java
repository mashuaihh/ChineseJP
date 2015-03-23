package tool;

public class UserDataBean {
	private int user_id;
	private String username;
	private String email;
	private String role;
	private int activated;
	private String note;
	
	public UserDataBean() {
		
	}
	
	public void setUser_id(int num) {
		this.user_id = num;
	}
	
	public int getUser_id() {
		return this.user_id;
	}
	
	public void setUsername(String str) {
		this.username = str;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setEmail(String str) {
		this.email = str;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setRole(String str) {
		this.role = str;
	}
	
	public String getRole() {
		return this.role;
	}
	
	public void setActivated(int num) {
		this.activated = num;
	}
	
	public int getActivated() {
		return this.activated;
	}
	
	public void setNote(String str) {
		this.note = str;
	}
	
	public String getNote() {
		return this.note;
	}
	
}


