package spring5_webmvc_study.controller;

public class LoginCommand {
	private String email;
	private String password;
	private boolean rememberEmail;
	
	public LoginCommand() {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRememberEmail() {
		return rememberEmail;
	}

	public void setRememberEmail(boolean rememberEmail) {
		this.rememberEmail = rememberEmail;
	}

	@Override
	public String toString() {
		return String.format("LoginCommand [email=%s, password=%s, rememberEmail=%s]", email, password, rememberEmail);
	}
	
	
	
}
