package com.arenglish.domain;

import java.util.List;
import java.util.Set;

public class User {
	private String userId;
	private String userName;//(昵称)
	private String userAccount;//(用注册邮箱作为账号)
	private String userPassword;
	private String userImage;//(头像) (外键 image ?)
	private String userPersonalizedSignature;//(个性签名)
	private Integer userCredit;//(积分/金币)
	private Set<CheckPoint> checkpointList;//list<are_checkpoint_records>(外键->checkpoint_records)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public String getUserPersonalizedSignature() {
		return userPersonalizedSignature;
	}
	public void setUserPersonalizedSignature(String userPersonalizedSignature) {
		this.userPersonalizedSignature = userPersonalizedSignature;
	}

	public Integer getUserCredit() {
		return userCredit;
	}
	public void setUserCredit(Integer userCredit) {
		this.userCredit = userCredit;
	}
	public void setCheckpointList(Set<CheckPoint> checkpointList) {
		this.checkpointList = checkpointList;
	}
	public Set<CheckPoint> getCheckpointList() {
		return checkpointList;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userAccount=" + userAccount + ", userPassword="
				+ userPassword + ", userImage=" + userImage + ", userPersonalizedSignature=" + userPersonalizedSignature
				+ ", userCredit=" + userCredit + "]";
	}
	
	
	
	
	
	
}
