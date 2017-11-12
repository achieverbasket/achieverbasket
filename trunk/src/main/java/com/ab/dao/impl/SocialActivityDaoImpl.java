package com.ab.dao.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ab.dao.SocialActivityDao;
import com.ab.vo.activity.Comment;
import com.ab.vo.activity.CommentReply;
import com.ab.vo.activity.Like;
import com.ab.vo.activity.SocialActivity;
import com.ab.vo.activity.SocialActivityType;

@Repository
public class SocialActivityDaoImpl implements SocialActivityDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SequenceDao sequenceDao;
	
	@Override
	public SocialActivity saveSocialActivity(SocialActivity socialActivity) {
		final String sql = "INSERT INTO SOCIAL_ACTIVITY (SOCIAL_ACTIVITY_ID, SOCIAL_ACTIVITY_TYPE, CREATED_BY, CREATED_TIME) VALUES (?, ?, 0, SYSDATE())";
		Long socialActivityId = sequenceDao.getNextVal("SOCIAL_ACTIVITY_SEQ");
		jdbcTemplate.update(sql, socialActivityId, socialActivity.getSocialActivityType().getActivityTypeId());
		socialActivity.setSocialActivityId(socialActivityId);
		return socialActivity;
	}

	@Override
	public SocialActivity getSocialActivity(final Long socialActivityId) {
		final String sql = "SELECT SOCIAL_ACTIVITY_ID, SOCIAL_ACTIVITY_TYPE FROM SOCIAL_ACTIVITY WHERE SOCIAL_ACTIVITY_ID = ?";
		SocialActivity socialActivity = jdbcTemplate.query(sql, new Object[] {socialActivityId}, (ResultSetExtractor<SocialActivity>) rs -> {
			rs.next();
			SocialActivity like = new SocialActivity();
			like.setSocialActivityId(socialActivityId);
			like.setSocialActivityType(SocialActivityType.fromId(rs.getInt("SOCIAL_ACTIVITY_TYPE")));
			return like;
		});
		socialActivity.setLike(getLikes(socialActivityId));
		socialActivity.setComment(getComments(socialActivityId));
		return socialActivity;
	}

	@Override
	public Like addLike(Like like) {
		final String sql = "INSERT INTO LIKES (LIKE_ID, SOCIAL_ACTIVITY_ID, IS_ACTIVE, CREATED_BY, CREATED_TIME) VALUES (?, ?, 1, ?, SYSDATE())";
		Long likeId = sequenceDao.getNextVal("LIKES_SEQ");
		jdbcTemplate.update(sql, likeId, like.getSocialActivityId(), like.getLikedBy());
		like.setLikeId(likeId);
		return like;
	}

	public List<Like> getLikes(final Long socialActivityId) {
		final String sql = "SELECT LIKE_ID, CREATED_BY, CREATED_TIME FROM LIKES WHERE SOCIAL_ACTIVITY_ID = ? AND IS_ACTIVE=1 ORDER BY CREATED_TIME";
		return jdbcTemplate.query(sql, new Object[] {socialActivityId}, (RowMapper<Like>) (rs, arg)-> {
			Like like = new Like();
			like.setActive(true);
			like.setSocialActivityId(socialActivityId);
			like.setLikeId(rs.getLong("LIKE_ID"));
			like.setLikedBy(rs.getLong("CREATED_BY"));
			like.setLikedOn(new DateTime(rs.getDate("CREATED_TIME")));
			return like;
		});
	}

	@Override
	public void removeLike(Like like) {
		final String sql = "UPDATE LIKES SET IS_ACTIVE = 0, MODIFIED_TIME=SYSDATE() WHERE LIKE_ID = ? ";
		jdbcTemplate.update(sql, like.getLikeId());
	}

	@Override
	public Comment addComment(Comment comment) {
		final String sql = "INSERT INTO COMMENTS (COMMENTS_ID, SOCIAL_ACTIVITY_ID, IS_ACTIVE, COMMENT_TEXT, CREATED_BY, CREATED_TIME) VALUES (?, ?, 1, ?, ?, SYSDATE())";
		Long commentsId = sequenceDao.getNextVal("COMMENTS_SEQ");
		jdbcTemplate.update(sql, commentsId, comment.getSocialActivityId(), comment.getCommentText(), comment.getCommentBy());
		comment.setCommentId(commentsId);
		return comment;
	}

	public List<Comment> getComments(final Long socialActivityId) {
		final String sql = "SELECT COMMENTS_ID, COMMENT_TEXT, CREATED_BY, CREATED_TIME FROM COMMENTS WHERE SOCIAL_ACTIVITY_ID = ? AND IS_ACTIVE=1 ORDER BY CREATED_TIME";
		List<Comment> commentList = jdbcTemplate.query(sql, new Object[] {socialActivityId}, (RowMapper<Comment>) (rs, arg)-> {
			Comment comment = new Comment();
			comment.setActive(true);
			comment.setSocialActivityId(socialActivityId);
			comment.setCommentId(rs.getLong("COMMENTS_ID"));
			comment.setCommentBy(rs.getLong("CREATED_BY"));
			comment.setCommentText(rs.getString("COMMENT_TEXT"));
			comment.setCommentedOn(new DateTime(rs.getDate("CREATED_TIME")));
			return comment;
		});
		
		commentList.forEach(comment-> comment.setCommentReplyList(getCommentReply(comment.getCommentId())));
		return commentList;
	}

	@Override
	public void removeComment(Comment comment) {
		final String sql = "UPDATE COMMENTS SET IS_ACTIVE = 0, MODIFIED_TIME=SYSDATE() WHERE COMMENTS_ID = ? ";
		jdbcTemplate.update(sql, comment.getCommentId());
	}

	@Override
	public Comment updateComment(Comment comment) {
		final String sql = "UPDATE COMMENTS_REPLY SET REPLY_TEXT = ?, MODIFIED_TIME=SYSDATE() WHERE COMMENTS_ID = ? ";
		jdbcTemplate.update(sql, comment.getCommentText(), comment.getCommentId());
		return comment;
	}

	@Override
	public CommentReply addCommentReply(CommentReply reply) {
		final String sql = "INSERT INTO COMMENTS_REPLY (COMMENTS_REPLY_ID, COMMENTS_ID, IS_ACTIVE, REPLY_TEXT, CREATED_BY, CREATED_TIME) VALUES (?, ?, 1, ?, ?, SYSDATE())";
		Long commentReplyId = sequenceDao.getNextVal("COMMENTS_REPLY_SEQ");
		jdbcTemplate.update(sql, commentReplyId, reply.getCommentId(), reply.getReplyText(), reply.getRepliedBy());
		reply.setCommentReplyId(commentReplyId);
		return reply;
	}

	public List<CommentReply> getCommentReply(final Long commentId) {
		final String sql = "SELECT COMMENTS_REPLY_ID, REPLY_TEXT, CREATED_BY, CREATED_TIME FROM COMMENTS_REPLY WHERE COMMENTS_ID = ? AND IS_ACTIVE=1 ORDER BY CREATED_TIME";
		return jdbcTemplate.query(sql, new Object[] {commentId}, (RowMapper<CommentReply>) (rs, arg)-> {
			CommentReply comment = new CommentReply();
			comment.setActive(true);
			comment.setCommentId(commentId);
			comment.setRepliedBy(rs.getLong("CREATED_BY"));
			comment.setReplyText(rs.getString("REPLY_TEXT"));
			comment.setRepliedOn(new DateTime(rs.getDate("CREATED_TIME")));
			return comment;
		});
	}

	@Override
	public void removeCommentReply(CommentReply reply) {
		final String sql = "UPDATE COMMENTS_REPLY SET IS_ACTIVE = 0, MODIFIED_TIME=SYSDATE() WHERE COMMENTS_REPLY_ID = ? ";
		jdbcTemplate.update(sql, reply.getCommentReplyId());
	}

	@Override
	public CommentReply updateCommentReply(CommentReply reply) {
		final String sql = "UPDATE COMMENTS_REPLY SET REPLY_TEXT = ?, MODIFIED_TIME=SYSDATE() WHERE COMMENTS_REPLY_ID = ? ";
		jdbcTemplate.update(sql, reply.getReplyText(), reply.getCommentReplyId());
		return reply;
	}
}
