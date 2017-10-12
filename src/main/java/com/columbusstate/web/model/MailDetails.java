package com.columbusstate.web.model;

public class MailDetails {

	public String to;
	public String from;
	public String subject;
	public String content;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "|to=" + to + "|from=" + from + "|subject=" + subject + "|content=" + content + "|";
	}

}
