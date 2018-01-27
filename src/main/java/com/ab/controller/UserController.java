package com.ab.controller;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ab.constant.config.ApplicationPageConstant;
import com.ab.form.UserEducationForm;
import com.ab.form.UserProfessionForm;
import com.ab.type.UserType;
import com.ab.vo.User;
import com.ab.vo.candidate.Candidate;

/**
 * @author Swapnil Singhai
 * @version 1
 * @since 10/09/2017
 */
@Controller
public class UserController {
	
	final static Logger logger = Logger.getLogger(UserController.class);
	
	@ResponseBody
	@RequestMapping(value="/user/save/personaldet" ,method=RequestMethod.POST)
	public String saveUserPersonalDetails(@ModelAttribute Candidate form) {
		System.out.println(form.getCandidateName());
		return "success";
	}
	
	
	@RequestMapping(value="/user/edu/add" ,method=RequestMethod.GET)
	public String createNewEducationRecord(Model model, @ModelAttribute UserEducationForm form) {
		model.addAttribute("form", form);
		return ApplicationPageConstant.newusereducationdet_page;
	}
	
	@RequestMapping(value="/user/pro/add" ,method=RequestMethod.GET)
	public String createNewProfessionRecord(Model model, @ModelAttribute UserProfessionForm form) {
		model.addAttribute("form", form);
		return ApplicationPageConstant.newuserprofessiondet_page;
	}
	
	@RequestMapping(value="/user/pro/edit/{id}" ,method=RequestMethod.GET)
	public String editProfessionRecord(Model model, @PathVariable(required = true) Integer id) {
		
		// get the data for id
		UserProfessionForm proForm = new UserProfessionForm();
		//get the data for id
		if(null != id && id == 1) {
			
			proForm.setId(1);
			proForm.setCompanyCity("Mumbai");
			proForm.setCompanyName("MSCI");
			proForm.setJoiningYear("2016");
			proForm.setDesignation("Associate");
			proForm.setLeavingYear("2018");
		}else if(null != id && id == 2) {
			
			proForm.setId(2);
			proForm.setCompanyCity("Mumbai");
			proForm.setCompanyName("MSCI");
			proForm.setJoiningYear("2016");
			proForm.setDesignation("Associate");
			proForm.setLeavingYear("2018");
		}
		
		model.addAttribute("form", proForm);
		return ApplicationPageConstant.newuserprofessiondet_page;
	}
	
	@RequestMapping(value="/user/edu/edit/{id}" ,method=RequestMethod.GET)
	public String editEducationRecord(Model model, @PathVariable(required = true) Integer id) {
		UserEducationForm eduForm = new UserEducationForm();
		//get the data for id
		if(null != id && id == 1) {
			
			eduForm.setId(1);
			eduForm.setInstituteCity("Bhopal");
			eduForm.setInstituteName("RGTU");
			eduForm.setPassoutYear("2010");
			eduForm.setPercentmarks("77");
			eduForm.setQualification("BE");
			eduForm.setRegistrationYear("2006");
		}else if(null != id && id == 2) {
			
			eduForm.setId(2);
			eduForm.setInstituteCity("Bhopal");
			eduForm.setInstituteName("RGTU");
			eduForm.setPassoutYear("2010");
			eduForm.setPercentmarks("77");
			eduForm.setQualification("BE");
			eduForm.setRegistrationYear("2006");
		}
		
		model.addAttribute("form", eduForm);
		return ApplicationPageConstant.newusereducationdet_page;
	}
	
	@RequestMapping(value="/user/per/edit/{id}" ,method=RequestMethod.GET)
	public String editPersonalRecord(@PathVariable(required = true) Integer id,Model model) {
		
		//get the data for id
		/*User userDetailObj = new User();
		userDetailObj.setUserId(1l);
		userDetailObj.setFirstName("Swapnil");
		userDetailObj.setLastName("Singhai");
		userDetailObj.setEmail("Swap@a.com");
		userDetailObj.setUserType(UserType.CANDIDATE);
		
		model.addAttribute("form", userDetailObj);*/
		return ApplicationPageConstant.newuserpersonaldet_page;
	}
	
	public static User getUserPrincipal(){
		if(null!= SecurityContextHolder.getContext().getAuthentication()&&
				null!= SecurityContextHolder.getContext().getAuthentication().getPrincipal()){
			if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User){
				return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			}
		}
		return null;
	}
	
}
