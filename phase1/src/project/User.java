package project;

import java.io.Serializable;

class User implements Serializable {
	public String getUsername() {
		return username;
	}


	private String username;
	private String password;

	User(String username, String password){
		this.username = username;
		this.password = password;
	}

	final boolean verifyPassword(String password){
		return this.password.equals(password);
	}

}
