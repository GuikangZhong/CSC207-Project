package project.user;

import project.user.UserHistory;

import java.io.Serializable;

public abstract class User<T extends UserHistory> implements Serializable {
	public enum Type{
		NONE,
		APPLICANT,
		HR,
		INTERVIEWER
	}

	private T history;
	private String username;
	private String password;

	public User(T history, String username, String password){
		this.username = username;
		this.password = password;
	}

	public abstract Type getType();
	
	public String getUsername() {
		return this.username;
	}

	public T getHistory() {
		return this.history;
	}

	public final boolean verifyPassword(String password){
		return this.password.equals(password);
	}

}
