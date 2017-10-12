package com.columbusstate.web.model;

import java.util.List;

public class MailList {

	private List<String> mails;
	private String userEmail;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public List<String> getMails() {
		return mails;
	}

	public void setMails(List<String> mails) {
		this.mails = mails;
	}

	@Override
	public String toString() {
		return "MailList [mails=" + mails + ", userEmail=" + userEmail + "]";
	}

}
