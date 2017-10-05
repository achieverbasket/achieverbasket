package com.ab.vo;

import java.sql.Date;
import java.util.List;

public class Blog {
	
	/*changes by Swapnil*/
	
	private String heading;
	private String content;
	
	private String commentorname;
	private String commentoremail;
	private String commentorText;
	
	
	
	private int blogId;
	private int createdById;
	private Date createdOnDate;
	private boolean isPublic;
	private List<Integer> likeIdList;//list of likes received on this blog
	private List<Integer> commentIdList;//list of comments received on this blog
	
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
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
	public List<Integer> getLikeIdList() {
		return likeIdList;
	}
	public void setLikeIdList(List<Integer> likeIdList) {
		this.likeIdList = likeIdList;
	}
	public void addLikeId(Integer likeId) {
		this.likeIdList.add(likeId);
	}
	public void removeLikeId(int likeId) {
		this.likeIdList.remove(likeId);
	}
	public List<Integer> getCommentIdList() {
		return commentIdList;
	}
	public void setCommentIdList(List<Integer> commentIdList) {
		this.commentIdList = commentIdList;
	}
	public void addCommentId(Integer commentId) {
		this.commentIdList.add(commentId);
	}	
	public void removeCommentId(Integer commentId) {
		this.commentIdList.remove(commentId);
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCommentorname() {
		return commentorname;
	}
	public void setCommentorname(String commentorname) {
		this.commentorname = commentorname;
	}
	public String getCommentoremail() {
		return commentoremail;
	}
	public void setCommentoremail(String commentoremail) {
		this.commentoremail = commentoremail;
	}
	public String getCommentorText() {
		return commentorText;
	}
	public void setCommentorText(String commentorText) {
		this.commentorText = commentorText;
	}
}
