package com.ab.spring.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.constant.config.ApplicationStatusConstant;
import com.ab.spring.dao.LoginDao;
import com.ab.spring.dao.SocialActivityDao;
import com.ab.spring.dao.impl.SequenceDao;
import com.ab.spring.service.LoginService;
import com.ab.vo.User;
import com.ab.vo.activity.Comment;
import com.ab.vo.activity.CommentReply;
import com.ab.vo.activity.Like;
import com.ab.vo.activity.SocialActivity;
import com.ab.vo.activity.SocialActivityType;
import com.ab.vo.login.Login;
import com.ab.vo.login.Registration;

/**
 * @author Swapnil Singhai
 * @version 1
 * @since 3/09/2017
 */
@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	LoginDao loginDaoImpl;
	
	@Autowired
	SocialActivityDao seqDao;
	
	@Override
	public User signinUser(Login form) throws Exception {
		System.out.println("In Signin User========");
		SocialActivity socialActivity = new SocialActivity();
		socialActivity.setSocialActivityType(SocialActivityType.PROFILE);
		socialActivity = seqDao.saveSocialActivity(socialActivity);
		System.out.println(socialActivity.getSocialActivityId() + " " + socialActivity.getSocialActivityType());
		Comment comment = new Comment();
		comment.setCommentBy(1l);
		comment.setCommentText("Hello");
		comment.setSocialActivityId(socialActivity.getSocialActivityId());
		comment = seqDao.addComment(comment);
		
		Like like = new Like();
		like.setLikedBy(1l);
		like.setSocialActivityId(socialActivity.getSocialActivityId());
		like = seqDao.addLike(like);
		
		CommentReply commentReply = new CommentReply();
		commentReply.setCommentId(comment.getCommentId());
		commentReply.setRepliedBy(2l);
		commentReply.setReplyText("Hello 1");
		
		commentReply = seqDao.addCommentReply(commentReply);
		
		SocialActivity socialActivity1 = seqDao.getSocialActivity(socialActivity.getSocialActivityId());
		System.out.println(socialActivity1);
		
		return loginDaoImpl.signinUser(form).getZ().orElse(null);
	}


	@Override
	public User getUserDetails(User form) throws Exception {
		return loginDaoImpl.getUser(form.getUserId());
	}


	@Override
	public Map<String, String> registerNewUser(Registration form){
		Map<String, String> resultStatusMap = new HashMap<String,String>(4);
		try { 
			int status = 0;//TODO update correct implementation here.
//			loginDaoImpl.registerUser(form);
			
			if(status > 0) {
				resultStatusMap.put(ApplicationStatusConstant.msg, ApplicationStatusConstant.msg_account_created_success);
				resultStatusMap.put(ApplicationStatusConstant.status, ApplicationStatusConstant.msg_success_generic);
			}else {
				resultStatusMap.put(ApplicationStatusConstant.msg, ApplicationStatusConstant.msg_account_created_error);
				resultStatusMap.put(ApplicationStatusConstant.status, ApplicationStatusConstant.msg_error_generic);
			}
		} catch(Exception e) {
			e.printStackTrace();
			resultStatusMap.put(ApplicationStatusConstant.msg, ApplicationStatusConstant.msg_account_created_error);
			resultStatusMap.put(ApplicationStatusConstant.status, ApplicationStatusConstant.msg_error_generic);
		}
		
		return resultStatusMap;
	}

}
