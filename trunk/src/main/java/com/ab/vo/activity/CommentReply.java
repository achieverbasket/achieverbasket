package com.ab.vo.activity;

import org.joda.time.DateTime;

public class CommentReply {
	private Long commentReplyId;
	private Long commentId;
	private Long repliedBy;
	private boolean active;
	private String replyText;
	private DateTime repliedOn;
	private DateTime modifiedOn;

	public Long getCommentReplyId() {
		return commentReplyId;
	}

	public void setCommentReplyId(Long commentReplyId) {
		this.commentReplyId = commentReplyId;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Long getRepliedBy() {
		return repliedBy;
	}

	public void setRepliedBy(Long repliedBy) {
		this.repliedBy = repliedBy;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getReplyText() {
		return replyText;
	}

	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}

	public DateTime getRepliedOn() {
		return repliedOn;
	}

	public void setRepliedOn(DateTime repliedOn) {
		this.repliedOn = repliedOn;
	}

	public DateTime getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(DateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	@Override
	public String toString() {
		return "CommentReply [commentReplyId=" + commentReplyId + ", commentId=" + commentId + ", repliedBy="
				+ repliedBy + ", active=" + active + ", replyText=" + replyText + ", repliedOn=" + repliedOn
				+ ", modifiedOn=" + modifiedOn + "]";
	}
}
