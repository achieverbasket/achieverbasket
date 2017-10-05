package com.ab.spring.dao;

import com.ab.vo.activity.Comment;
import com.ab.vo.activity.CommentReply;
import com.ab.vo.activity.Like;
import com.ab.vo.activity.SocialActivity;

public interface SocialActivityDao {
	
	SocialActivity saveSocialActivity(SocialActivity socialActivity);
	
	SocialActivity getSocialActivity(Long socialActivityId);
	
	Like addLike(Like like);
	
	void removeLike(Like like);
	
	Comment addComment(Comment comment);
	
	void removeComment(Comment comment);
	
	Comment updateComment(Comment editedComment);
	
	CommentReply addCommentReply(CommentReply commentReply);
	
	void removeCommentReply(CommentReply commentReply);
	
	CommentReply updateCommentReply(CommentReply commentReply);
}
