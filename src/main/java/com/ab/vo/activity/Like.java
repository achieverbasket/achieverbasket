package com.ab.vo.activity;

import org.joda.time.DateTime;

public class Like {
	private Long likeId;
	private Long socialActivityId;
	private Long likedBy;
	private boolean active;
	private DateTime likedOn;
	private DateTime modifiedOn;

	public Long getLikeId() {
		return likeId;
	}

	public void setLikeId(Long likeId) {
		this.likeId = likeId;
	}

	public Long getSocialActivityId() {
		return socialActivityId;
	}

	public void setSocialActivityId(Long socialActivityId) {
		this.socialActivityId = socialActivityId;
	}

	public Long getLikedBy() {
		return likedBy;
	}

	public void setLikedBy(Long likedBy) {
		this.likedBy = likedBy;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public DateTime getLikedOn() {
		return likedOn;
	}

	public void setLikedOn(DateTime likedOn) {
		this.likedOn = likedOn;
	}

	public DateTime getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(DateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	@Override
	public String toString() {
		return "Like [likeId=" + likeId + ", socialActivityId=" + socialActivityId + ", likedBy=" + likedBy
				+ ", active=" + active + ", likedOn=" + likedOn + ", modifiedOn=" + modifiedOn + "]";
	}
}
