package com.ab.vo;

import java.sql.Date;
import java.util.List;

import com.ab.vo.activity.SocialActivity;

public class Story {
	private int storyId;
	private int createdById;
	private Date createdOnDate;
	private boolean isPublic;
	private SocialActivity socialActivity;
	
	public int getStoryId() {
		return storyId;
	}
	public void setStoryId(int storyId) {
		this.storyId = storyId;
	}
	public int getCreatedById() {
		return createdById;
	}
	public void setCreatedById(int createdById) {
		this.createdById = createdById;
	}
	public Date getCreatedOnDate() {
		return createdOnDate;
	}
	public void setCreatedOnDate(Date createdOnDate) {
		this.createdOnDate = createdOnDate;
	}
	public boolean isPublic() {
		return isPublic;
	}
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	public SocialActivity getSocialActivity() {
		return socialActivity;
	}
	public void setSocialActivity(SocialActivity socialActivity) {
		this.socialActivity = socialActivity;
	}
	


}
