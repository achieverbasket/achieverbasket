package com.ab.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ab.constant.config.ApplicationPageConstant;

@Controller
public class SettingsController {
	
	final static Logger logger = Logger.getLogger(SettingsController.class);
	
	@RequestMapping(value = "/setting", method = RequestMethod.GET)
	public String getSettingsPage(Model model) {
		return ApplicationPageConstant.setting_page;
	}
}
