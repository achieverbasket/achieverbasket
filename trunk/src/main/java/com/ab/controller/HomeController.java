package com.ab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ab.constant.config.ApplicationPageConstant;
import com.ab.type.UserType;
import com.ab.vo.User;

/**
 * @author Swapnil Singhai
 * @version 1
 * @since 3/09/2017
 */
@Controller
public class HomeController {
	
	/**
	 * @return new page to client
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getIndexPage(Model model) {
		
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
		return ApplicationPageConstant.home_page;
	}
	
	
	
	
	
}
