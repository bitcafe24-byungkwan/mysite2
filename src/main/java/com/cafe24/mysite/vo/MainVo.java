package com.cafe24.mysite.vo;

public class MainVo {
	private Long no;
	private String title;
	private String welcomeMessage;
	private String profile;
	private String description;
	public Long getNo() {
		return no;
	}
	@Override
	public String toString() {
		return "MainVo [no=" + no + ", title=" + title + ", welcomeMessage=" + welcomeMessage + ", profile=" + profile
				+ ", description=" + description + "]";
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWelcomeMessage() {
		return welcomeMessage;
	}
	public void setWelcomeMessage(String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String discription) {
		this.description = discription;
	}
	
}
