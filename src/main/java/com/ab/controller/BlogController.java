package com.ab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ab.type.UserType;
import com.ab.vo.Blog;
import com.ab.vo.User;

/**
 * @author Swapnil Singhai
 * @version 1
 * @since 17/09/2017
 */

@Controller
public class BlogController {
	
	
	@RequestMapping(value="/issuer/blog", method=RequestMethod.GET)
	public String getRegisterUserPage(@ModelAttribute Blog form,Model model ) {
		User user = UserController.getUserPrincipal();
		if(null != user){
			UserType userType = user.getUserType();
			model.addAttribute("username", user.getUserName());
			if(UserType.CANDIDATE.equals(userType)){
				model.addAttribute("type", "candidate");
			}else if(UserType.ISSUER.equals(userType)){
				model.addAttribute("type", "issuer");
			}
		}
		model.addAttribute("form", form);
		return com.ab.constant.config.ApplicationPageConstant.blog_page;
	}
	
}
