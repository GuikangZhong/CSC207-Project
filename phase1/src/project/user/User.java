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
	public abstract Type getType();
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
