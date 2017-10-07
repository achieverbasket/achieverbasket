package com.ab.spring.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ab.constant.config.ApplicationPageConstant;
import com.ab.constant.config.ApplicationStatusConstant;
import com.ab.spring.form.UserTwoWayAuthForm;
import com.ab.spring.service.LoginService;
import com.ab.vo.User;
import com.ab.vo.login.Login;
import com.ab.vo.login.Registration;

/**
 * @author Swapnil Singhai
 * @version 1
 * @since 3/09/2017
 */
@Controller
public class LoginController {
	
	
	@Autowired
	LoginService loginServiceImpl;
	
	
	@RequestMapping(value="/resetpassword", method=RequestMethod.GET)
	public String resetpasswordPage() {
		return com.ab.constant.config.ApplicationPageConstant.resetpassword_page;
	}
	
	@RequestMapping(value="/resetpassword", method=RequestMethod.POST)
	public String resetpassword(Model model, HttpServletRequest req) {
		String loginId = req.getParameter("loginId"); String email = req.getParameter("email");
		
		if((null != loginId && !loginId.equalsIgnoreCase(""))|| (null !=  email && !email.equalsIgnoreCase(""))) {
			// validate email and login id, send with error msg
			System.out.println(loginId+email);
			
			// if no error, goto reset password
			
		}else {
			model.addAttribute(ApplicationStatusConstant.msg, "kinldy provide Email or Account id");
		}
		
		return com.ab.constant.config.ApplicationPageConstant.resetpassword_page;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String getRegisterUserPage(@ModelAttribute Registration form,Model model ) {
		model.addAttribute("registerform", form);
		return com.ab.constant.config.ApplicationPageConstant.register_page;
	}
	
	@RequestMapping(value="/signin", method=RequestMethod.GET)
	public String getSignInUserPage(@ModelAttribute Login form,Model model) {
		model.addAttribute("signinform", form);
		return com.ab.constant.config.ApplicationPageConstant.signin_page;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public  String registerNewUser(Model model ,@Valid @ModelAttribute(name="registerform") Registration form,BindingResult br,RedirectAttributes ra) {
		boolean error = false;
		if(null != br && br.hasErrors()) {
			error = true;
			model.addAttribute(ApplicationStatusConstant.msg_error_generic, br.getFieldError());
		}else {
			Map<String,String> resultStatusMap = loginServiceImpl.registerNewUser(form);
			String key = resultStatusMap.get(ApplicationStatusConstant.status);
			if(null != key && key.equalsIgnoreCase(ApplicationStatusConstant.msg_success_generic)) {
				model.addAttribute("twaform", new UserTwoWayAuthForm());
				model.addAttribute("msg", resultStatusMap.get(ApplicationStatusConstant.msg));
				return ApplicationPageConstant.twowayauth_page;
			}else {
				model.addAttribute("msg", resultStatusMap.get(ApplicationStatusConstant.msg));
			}
		}
		
		if(error) {
			model.addAttribute("registerform", form);
		}
		return ApplicationPageConstant.register_page;
	}
	
	@RequestMapping(value="dashboard", method=RequestMethod.GET)
	public  String dashboard(Model model,@ModelAttribute("userDetailObjform") User form,HttpServletResponse response) {
			
		// check if user is logged in 
		// if no errors, get user object from db/cache, and send to user dashboard
		// user dashboard should be customized based on user type and rolse/permission user have
		
		// all exceptions to be caught at controller level as much as possible
		
		// we need to check first the type of user, based on that its form and page will decide
		 boolean error = false;
		try {
			// taking candidate for first dev
			
			form = loginServiceImpl.getUserDetails(form);
			response.setHeader("auth-token","124");
			//creating 1st dash board for candidate
			model.addAttribute("userDetailObjform", form);
			//ra.addFlashAttribute("userDetailObjform", userDetailObj);
		} catch (Exception e) {
			error = true;
			e.printStackTrace();// logger to be implemented
			// through custom generic error message , 
			model.addAttribute(ApplicationStatusConstant.msg_error_generic, ApplicationStatusConstant.msg_error_processing_request);
		}
		
		
			return ApplicationPageConstant.userdashboard_page;
	}
	
	@RequestMapping(value="/signin", method=RequestMethod.POST)
	public  String signinUser(Model model ,@Valid @ModelAttribute(name="signinform") Login form,BindingResult br,RedirectAttributes ra,HttpServletResponse response) {
		boolean error = false;
		if(null != form) {
			System.out.println("-- form.getUserName() --  "+ form.getUserName() + "-- form.getPassword() --  "+ form.getPassword());
			
			if(null != br && br.hasErrors()) {
				error = true;
				model.addAttribute(ApplicationStatusConstant.msg_error_generic, br.getFieldError());
			}else {
				// if no errors, get user object from db/cache, and send to user dashboard
				// user dashboard should be customized based on user type and rolse/permission user have
				
				// all exceptions to be caught at controller level as much as possible
				try {
					User form1 = loginServiceImpl.signinUser(form);
					
					//creating 1st dash board for candidate
					//model.addAttribute("userDetailObjform", userDetailObj);
					ra.addFlashAttribute("userDetailObjform", form1);
				} catch (Exception e) {
					error = true;
					e.printStackTrace();// logger to be implemented
					// through custom generic error message , 
					model.addAttribute(ApplicationStatusConstant.msg_error_generic, ApplicationStatusConstant.msg_error_processing_request);
				}
			}
		}
		if(error) {
			model.addAttribute("signinform", form);
			return ApplicationPageConstant.signin_page;
		}else {
			response.setHeader("auth-token", "12345");
			return "redirect:/dashboard";
		}
	}
		
	
	@RequestMapping(value="/register/twa", method=RequestMethod.POST)
	public  String twoWayAuthenticateUser(Model model ,@Valid @ModelAttribute(name="twaform") UserTwoWayAuthForm form,BindingResult br,RedirectAttributes ra) {
		if(null != br && br.hasErrors()) {
			model.addAttribute(ApplicationStatusConstant.msg_error_generic, br.getFieldError());
			model.addAttribute("twaform", form);
			return ApplicationPageConstant.twowayauth_page;
		}
		else {
			ra.addFlashAttribute(ApplicationStatusConstant.msg_success_generic,ApplicationStatusConstant.msg_account_verified_success);
			return "redirect:/signin";
		}
	}
}
