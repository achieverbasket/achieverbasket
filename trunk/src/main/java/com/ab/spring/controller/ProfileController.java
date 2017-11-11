package com.ab.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ab.constant.config.ApplicationPageConstant;

@Controller
public class ProfileController {
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String getSettingsPage(Model model) {
		return ApplicationPageConstant.profile_page;
	}
}
