package project;

import java.io.Serializable;

class User<T extends UserHistory> implements Serializable {
	public String getUsername() {
		return username;
	}


	private String username;
	private String password;

	public T getHistory() {
		return history;
	}

	private T history;

	User(T history, String username, String password){
		this.username = username;
		this.password = password;
	}

	final boolean verifyPassword(String password){
		return this.password.equals(password);
	}

}
