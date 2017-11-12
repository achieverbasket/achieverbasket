package com.ab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ab.vo.Blog;

/**
 * @author Swapnil Singhai
 * @version 1
 * @since 17/09/2017
 */

@Controller
public class BlogController {
	
	
	@RequestMapping(value="/blog", method=RequestMethod.GET)
	public String getRegisterUserPage(@ModelAttribute Blog form,Model model ) {
		model.addAttribute("form", form);
		return com.ab.constant.config.ApplicationPageConstant.blog_page;
	}
	
}
