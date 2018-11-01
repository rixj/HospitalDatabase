package model;

public class User {
	String user;
	String pw;
	Integer id;
	Integer access = 0;
	
	public User () {	}
	
	public User(String name, String password, int id, int access) {
		this.user = name;
		this.pw = password;
		this.id = id;
		this.access = access;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAccess() {
		return access;
	}

	public void setAccess(Integer access) {
		this.access = access;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

}
