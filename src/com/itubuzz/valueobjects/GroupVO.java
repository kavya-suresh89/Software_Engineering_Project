package com.itubuzz.valueobjects;

public class GroupVO {

	
	private long userId;
	private long groupId;
	private String groupName;
	private String firstName;
	private String lastName;
	
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getGroupId() {
		return groupId;
	}
	public long setGroupId(long groupId) {
		return this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
	 
}
