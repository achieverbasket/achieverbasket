package com.ab.spring.form;

import java.io.Serializable;

import com.ab.type.ProfileType;
import com.ab.type.StatusType;
import com.ab.type.UserType;

public class UserCertificate implements Serializable{
	
	private static final long serialVersionUID = -8587621519916169321L;
	private String name;
	private String issueDate;
	private String validTill;
	private String verifiedBy;
	private String verifiedOn;
	private UserType initiatedBy;
	private StatusType currentStatus;
	private String thumbnailPath;
	private Integer id;
	private ProfileType profile;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getValidTill() {
		return validTill;
	}
	public void setValidTill(String validTill) {
		this.validTill = validTill;
	}
	public String getVerifiedBy() {
		return verifiedBy;
	}
	public void setVerifiedBy(String verifiedBy) {
		this.verifiedBy = verifiedBy;
	}
	public String getVerifiedOn() {
		return verifiedOn;
	}
	public void setVerifiedOn(String verifiedOn) {
		this.verifiedOn = verifiedOn;
	}
	public UserType getInitiatedBy() {
		return initiatedBy;
	}
	public void setInitiatedBy(UserType initiatedBy) {
		this.initiatedBy = initiatedBy;
	}
	public StatusType getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(StatusType currentStatus) {
		this.currentStatus = currentStatus;
	}
	public String getThumbnailPath() {
		return thumbnailPath;
	}
	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ProfileType getProfile() {
		return profile;
	}
	public void setProfile(ProfileType profile) {
		this.profile = profile;
	}
	
}
