package com.columbusstate.web.model;

public class UserRegistration {

	private String name;
	private String email;
	private String passphrase;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassphrase() {
		return passphrase;
	}

	public void setPassphrase(String passphrase) {
		this.passphrase = passphrase;
	}

	@Override
	public String toString() {
		return "UserRegistration [name=" + name + ", email=" + email + ", passphrase=" + passphrase + "]";
	}

}
