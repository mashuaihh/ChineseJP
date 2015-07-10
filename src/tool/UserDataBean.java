package tool;

public class UserDataBean {
	private int user_id;
	private String username;
	private String email;
	private String role;
	private int activated;
	private String institute;
	private String memo;
	private int percent;
	
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
	
	public void setInstitute(String str) {
		this.institute = str;
	}
	
	public String getInstitute() {
		return this.institute;
	}
	
	public void setMemo(String str) {
		this.memo = str;
	}
	
	public String getMemo() {
		return this.memo;
	}
	
	public void setActivated(int num) {
		this.activated = num;
	}
	
	public int getActivated() {
		return this.activated;
	}
	
	public void setPercent(int i) {
		this.percent = i;
	}
	
	public int getPercent() {
		return this.percent;
	}
	
}


