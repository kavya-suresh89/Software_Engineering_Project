package com.itubuzz.valueobjects;

public class ReplyVO {
  
	private long reply_id;
	private String reply_text;
	private long immparent_id;
	private int post_id;
	private int log_user_id;
	private long reply_number;
	private String log_reply_name;
	
	
	
	
	public String getLog_reply_name() {
		return log_reply_name;
	}
	public void setLog_reply_name(String log_reply_name) {
		this.log_reply_name = log_reply_name;
	}
	public long getImmparent_id() {
		return immparent_id;
	}
	public void setImmparent_id(long immparent_id) {
		this.immparent_id = immparent_id;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public long getReply_number() {
		return reply_number;
	}
	public void setReply_number(long reply_number) {
		this.reply_number = reply_number;
	}
	public long getReply_id() {
		return reply_id;
	}
	public void setReply_id(long reply_id) {
		this.reply_id = reply_id;
	}
	public String getReply_text() {
		return reply_text;
	}
	public void setReply_text(String reply_text) {
		this.reply_text = reply_text;
	}
	public long getimmparent_id() {
		return immparent_id;
	}
	public void immparent_id(long immparent_id) {
		this.immparent_id = immparent_id;
	}
	public int getpost_id() {
		return post_id;
	}
	public void setpost_id(int post_id) {
		this.post_id = post_id;
	}
	
	public int getLog_user_id() {
		return log_user_id;
	}
	
	public void setLog_user_id(int log_user_id) {
		this.log_user_id = log_user_id;
	}	
}
