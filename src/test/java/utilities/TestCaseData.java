package utilities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestCaseData {
	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String user) {
		this.username = user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String pw) {
		this.password = pw;
	}
}