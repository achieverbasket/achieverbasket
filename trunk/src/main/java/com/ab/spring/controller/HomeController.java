package com.ab.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ab.constant.config.ApplicationPageConstant;

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
	public String getIndexPage() {
		return ApplicationPageConstant.home_page;
	}
	
	
	
	
	
}
