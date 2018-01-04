package com.ab.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ab.constant.config.ApplicationPageConstant;
import com.ab.constant.config.ApplicationStatusConstant;
import com.ab.datastructure.TwoTuple;
import com.ab.form.UserTwoWayAuthForm;
import com.ab.service.LoginService;
import com.ab.type.UserType;
import com.ab.vo.User;
import com.ab.vo.candidate.Candidate;
import com.ab.vo.issuer.Issuer;
import com.ab.vo.login.IssuerRegistration;
import com.ab.vo.login.Login;
import com.ab.vo.login.UserRegistration;

/**
 * @author Swapnil Singhai
 * @version 1
 * @since 3/09/2017
 */
@Controller
public class LoginController {

	@Autowired
	LoginService loginServiceImpl;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(@ModelAttribute Login loginForm, Model model,HttpServletRequest req,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "expired", required = false) String expired,
			@RequestParam(value = "session", required = false) String session,HttpServletResponse resp) {
		if(null != req.getSession() && req.getSession().getAttribute("error") != null){
			req.setAttribute("error", req.getSession().getAttribute("error"));
			req.getSession().setAttribute("error", null);
		}
		if(null != session){
			model.addAttribute(ApplicationStatusConstant.msg_error_generic, ApplicationStatusConstant.SESSION_INVALID_ERR);
		}else if(null != logout ){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
			if(null != auth){
				new SecurityContextLogoutHandler().logout(req, resp, auth);
				req.getSession().invalidate();
			}
			model.addAttribute(ApplicationStatusConstant.msg_success_generic, ApplicationStatusConstant.LOGOUT_SUCCESS_MSG);
		}else if(null != expired ){
			model.addAttribute(ApplicationStatusConstant.msg_error_generic, ApplicationStatusConstant.LOGGED_OUT_ERR);
		}
		model.addAttribute("loginForm", loginForm);
		return ApplicationPageConstant.login_page;
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(@ModelAttribute Login loginForm, Model model){
			
		// get user
		User user = UserController.getUserPrincipal();
		try{
			if(null != user){
				UserType userType = user.getUserType();
				if(UserType.CANDIDATE.equals(userType)){
					Candidate candidate = loginServiceImpl.getCandidateDetail(user);
					model.addAttribute("form", candidate);
					model.addAttribute("username", candidate.getCandidateName());
					model.addAttribute("type", "candidate");
					return ApplicationPageConstant.candidate_dashboard_page;
				}else if(UserType.ISSUER.equals(userType)){
					Issuer issuer = loginServiceImpl.getIssuer(user.getUserId());
					model.addAttribute("form", issuer);
					model.addAttribute("username", issuer.getIssuerName());
					model.addAttribute("type", "issuer");
					return ApplicationPageConstant.issuer_dashboard_page;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute(ApplicationStatusConstant.msg_error_generic, ApplicationStatusConstant.msg_error_processing_request);
		}
		model.addAttribute("loginForm", loginForm);
		return ApplicationPageConstant.login_page;
	}
	
	
	
	/*
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginUser(@Valid @ModelAttribute(name = "loginForm") Login loginForm, BindingResult br, Model model,
			RedirectAttributes ra, HttpServletResponse response, HttpServletRequest request) {
		boolean error = false;
		UserType userType = UserType.CANDIDATE;
		
		if (null != loginForm) {
			System.out.println("UserName: " + loginForm.getUsername() + "Password:  " + loginForm.getPassword());

			if (null != br && br.hasErrors()) {
				error = true;
				model.addAttribute(ApplicationStatusConstant.msg_error_generic, br.getFieldError());
			} else {
				// if no errors, get user object from db/cache, and send to user
				// dashboard
				// user dashboard should be customized based on user type and
				// rolse/permission user have

				// all exceptions to be caught at controller level as much as
				// possible
				try {
					User form1 = loginServiceImpl.loginUser(loginForm);
					if (form1 == null) {
						error = true;
						model.addAttribute(ApplicationStatusConstant.msg_error_generic,
								ApplicationStatusConstant.msg_error_processing_request);
					} else {

						// creating 1st dash board for candidate
						// model.addAttribute("userDetailObjform",
						// userDetailObj);
						ra.addFlashAttribute("userDetailObjform", form1);
						request.getSession().setAttribute("user", form1);
						userType = form1.getUserType();
					}
				} catch (Exception e) {
					error = true;
					e.printStackTrace();// logger to be implemented
					// through custom generic error message ,
					model.addAttribute(ApplicationStatusConstant.msg_error_generic,
							ApplicationStatusConstant.msg_error_processing_request);
				}
			}
		}
		if (error) {
			model.addAttribute("loginForm", loginForm);
			return ApplicationPageConstant.login_page;
		} else {
			response.setHeader("auth-token", "12345");
			if(UserType.CANDIDATE == userType) {
				return "redirect:/candidateDashboard";
			} else if (UserType.ISSUER == userType) {
				return "redirect:/issuerDashboard";
			} else {
				return "redirect:/error";
			}
		}
	}
*/
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String getRegisterUserPage(@ModelAttribute UserRegistration userRegisterForm,
			@ModelAttribute IssuerRegistration issuerRegisterForm,Model model) {
		model.addAttribute("userRegisterForm", userRegisterForm);
		model.addAttribute("issuerRegisterForm", issuerRegisterForm);
		return com.ab.constant.config.ApplicationPageConstant.register_page;
	}
	
	// issuer registration
	@RequestMapping(value = "/register/issuer", method = RequestMethod.POST)
	public String registerNewIssuer(Model model,
			@Valid @ModelAttribute(name = "issuerRegisterForm") IssuerRegistration issuerRegisterForm, BindingResult br,
			RedirectAttributes ra) {
		boolean error = false;
		if (null != br && br.hasErrors()) {
			error = true;
			model.addAttribute(ApplicationStatusConstant.msg_error_generic, br.getFieldError());
		} else {
			
			System.out.println("in issuer registration");
			System.out.println("in registerNewUser of loginController");
			TwoTuple<Boolean, String> result = loginServiceImpl.registerNewIssuer(issuerRegisterForm);
			if (result.getX()) {
				model.addAttribute("twaform", new UserTwoWayAuthForm());
				model.addAttribute("msg", result.getY());
				return ApplicationPageConstant.twowayauth_page;
			} else {
				model.addAttribute("msg", result.getY());
			}
		}
		
		model.addAttribute("userRegisterForm", new UserRegistration());
		model.addAttribute("issuerRegisterForm", issuerRegisterForm);
	
		return ApplicationPageConstant.register_page;
	}

	
	@RequestMapping(value = "/register/user", method = RequestMethod.POST)
	public String registerNewUser(Model model,
			@Valid @ModelAttribute(name = "userRegisterForm") UserRegistration userRegisterForm, BindingResult br,
			RedirectAttributes ra) {
		boolean error = false;
		if (null != br && br.hasErrors()) {
			error = true;
			model.addAttribute(ApplicationStatusConstant.msg_error_generic, br.getFieldError());
		} else {
			System.out.println("in registerNewUser of loginController");
			TwoTuple<Boolean, String> result = loginServiceImpl.registerNewUser(userRegisterForm);
			if (result.getX()) {
				model.addAttribute("twaform", new UserTwoWayAuthForm());
				model.addAttribute("msg", result.getY());
				return ApplicationPageConstant.twowayauth_page;
			} else {
				model.addAttribute("msg", result.getY());
			}
		}
	
		model.addAttribute("userRegisterForm", userRegisterForm);
		model.addAttribute("issuerRegisterForm", new IssuerRegistration());
		return ApplicationPageConstant.register_page;
	}

	@RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
	public String resetpasswordPage() {
		return ApplicationPageConstant.resetpassword_page;
	}

	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
	public String resetpassword(Model model, HttpServletRequest req) {
		String loginId = req.getParameter("loginId");
		String email = req.getParameter("email");

		if ((null != loginId && !loginId.equalsIgnoreCase("")) || (null != email && !email.equalsIgnoreCase(""))) {
			// validate email and login id, send with error msg
			System.out.println(loginId + email);

			// if no error, goto reset password

		} else {
			model.addAttribute(ApplicationStatusConstant.msg, "kinldy provide Email or Account id");
		}

		return com.ab.constant.config.ApplicationPageConstant.resetpassword_page;
	}
	/*@RequestMapping(value = "issuerDashboard", method = RequestMethod.GET)
	public String issuerDashboard(Model model, @ModelAttribute("userDetailObjform") User form, HttpServletResponse response, HttpServletRequest request) {

		// check if user is logged in
		// if no errors, get user object from db/cache, and send to user
		// dashboard
		// user dashboard should be customized based on user type and
		// rolse/permission user have

		// all exceptions to be caught at controller level as much as possible

		// we need to check first the type of user, based on that its form and
		// page will decide
		boolean error = false;
		try {
			Issuer issuer = loginServiceImpl.getIssuer(form.getUserId());
			response.setHeader("auth-token", "124");
			// creating 1st dash board for candidate
			System.out.println("in issuerDashboard for: "+issuer);
			model.addAttribute("issuerObjform", issuer);
			// ra.addFlashAttribute("userDetailObjform", userDetailObj);
		} catch (Exception e) {
			error = true;
			e.printStackTrace();// logger to be implemented
			// through custom generic error message ,
			model.addAttribute(ApplicationStatusConstant.msg_error_generic, ApplicationStatusConstant.msg_error_processing_request);
		}

		return ApplicationPageConstant.issuer_dashboard_page;
	}

	
	@RequestMapping(value = "candidateDashboard", method = RequestMethod.GET)
	public String candidateDashboard(Model model, @ModelAttribute("userDetailObjform") User form, HttpServletResponse response, HttpServletRequest request) {

		// check if user is logged in
		// if no errors, get user object from db/cache, and send to user
		// dashboard
		// user dashboard should be customized based on user type and
		// rolse/permission user have

		// all exceptions to be caught at controller level as much as possible

		// we need to check first the type of user, based on that its form and
		// page will decide
		boolean error = false;
		try {
			Candidate candidate = loginServiceImpl.getCandidateDetail(form);
			response.setHeader("auth-token", "124");
			// creating 1st dash board for candidate
			model.addAttribute("candidateObjform", candidate);
			// ra.addFlashAttribute("userDetailObjform", userDetailObj);
		} catch (Exception e) {
			error = true;
			e.printStackTrace();// logger to be implemented
			// through custom generic error message ,
			model.addAttribute(ApplicationStatusConstant.msg_error_generic, ApplicationStatusConstant.msg_error_processing_request);
		}

		return ApplicationPageConstant.candidate_dashboard_page;
	}
*/
	@RequestMapping(value = "/register/twa", method = RequestMethod.POST)
	public String twoWayAuthenticateUser(Model model, @Valid @ModelAttribute(name = "twaform") UserTwoWayAuthForm form,
			BindingResult br, RedirectAttributes ra) {
		if (null != br && br.hasErrors()) {
			model.addAttribute(ApplicationStatusConstant.msg_error_generic, br.getFieldError());
			model.addAttribute("twaform", form);
			return ApplicationPageConstant.twowayauth_page;
		} else {
			ra.addFlashAttribute(ApplicationStatusConstant.msg_success_generic,
					ApplicationStatusConstant.msg_account_verified_success);
			return "redirect:/login";
		}
	}
}
