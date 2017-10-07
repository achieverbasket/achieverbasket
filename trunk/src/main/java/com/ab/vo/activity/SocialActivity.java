package com.ab.vo.activity;

import java.util.List;

import com.google.common.collect.Lists;

public class SocialActivity {
	private Long socialActivityId = -1L;
	private SocialActivityType socialActivityType;
	private List<Like> like = Lists.newArrayList();
	private List<Comment> comment = Lists.newArrayList();
	
	public SocialActivity() {
	}

	public SocialActivity(SocialActivityType socialActivityType) {
		this.socialActivityType = socialActivityType;
	}

	public Long getSocialActivityId() {
		return socialActivityId;
	}

	public void setSocialActivityId(Long socialActivityId) {
		this.socialActivityId = socialActivityId;
	}

	public SocialActivityType getSocialActivityType() {
		return socialActivityType;
	}

	public void setSocialActivityType(SocialActivityType socialActivityType) {
		this.socialActivityType = socialActivityType;
	}

	public List<Like> getLike() {
		return like;
	}

	public void setLike(List<Like> like) {
		this.like = like;
	}

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "SocialActivity [socialActivityId=" + socialActivityId + ", socialActivityType=" + socialActivityType
				+ ", like=" + like + ", comment=" + comment + "]";
	}
}
