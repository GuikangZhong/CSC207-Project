package project.user;

import project.user.UserHistory;

import java.io.Serializable;

public class User<T extends UserHistory> implements Serializable {
	public String getUsername() {
		return username;
	}


	private String username;
	private String password;

	public T getHistory() {
		return history;
	}

	private T history;

	public User(T history, String username, String password){
		this.username = username;
		this.password = password;
	}

	public final boolean verifyPassword(String password){
		return this.password.equals(password);
	}

}
