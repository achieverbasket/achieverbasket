package com.ab.vo.activity;

import java.util.List;

import org.joda.time.DateTime;

public class Comment {
	private Long commentId;
	private Long socialActivityId;
	private Long commentBy;
	private boolean active;
	private String commentText;
	private DateTime commentedOn;
	private DateTime modifiedOn;
	private List<CommentReply> CommentReplyList;

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Long getSocialActivityId() {
		return socialActivityId;
	}

	public void setSocialActivityId(Long socialActivityId) {
		this.socialActivityId = socialActivityId;
	}

	public Long getCommentBy() {
		return commentBy;
	}

	public void setCommentBy(Long commentBy) {
		this.commentBy = commentBy;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public DateTime getCommentedOn() {
		return commentedOn;
	}

	public void setCommentedOn(DateTime commentedOn) {
		this.commentedOn = commentedOn;
	}

	public DateTime getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(DateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public List<CommentReply> getCommentReplyList() {
		return CommentReplyList;
	}

	public void setCommentReplyList(List<CommentReply> commentReplyList) {
		CommentReplyList = commentReplyList;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", socialActivityId=" + socialActivityId + ", commentBy=" + commentBy
				+ ", active=" + active + ", commentText=" + commentText + ", commentedOn=" + commentedOn
				+ ", modifiedOn=" + modifiedOn + ", CommentReplyList=" + CommentReplyList + "]";
	}
}
