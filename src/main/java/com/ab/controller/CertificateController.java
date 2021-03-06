package com.ab.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ab.constant.config.ApplicationPageConstant;
import com.ab.service.CertificateService;
import com.ab.service.LoginService;
import com.ab.type.UserType;
import com.ab.type.VerificationStatusType;
import com.ab.vo.User;
import com.ab.vo.candidate.Candidate;
import com.ab.vo.certificate.Certificate;
import com.ab.vo.certificate.CertificateTemplate;
import com.google.common.collect.Lists;

//
///**
// * @author Swapnil Singhai
// * @version 1
// * @since 16/09/2017
// 
@Controller
public class CertificateController {

	@Autowired
	CertificateService certificateServiceImpl;

	@Autowired
	LoginService loginService;

	final static Logger logger = Logger.getLogger(CertificateController.class);

	@RequestMapping(path="/certificate" ,method=RequestMethod.GET)
	public String getCertificate(@ModelAttribute Certificate certificate,Model model, HttpServletRequest request){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(null != user){
			UserType userType = user.getUserType();
			model.addAttribute("username", user.getUserName());
			if(UserType.CANDIDATE.equals(userType)){
				model.addAttribute("type", "candidate");
			}else if(UserType.ISSUER.equals(userType)){
				model.addAttribute("type", "issuer");
			}
		}
		model.addAttribute("form", certificate);
		return ApplicationPageConstant.newcertificate_page;
	}

	@RequestMapping(path="/certificate" ,method=RequestMethod.POST)
	public String createCertificate(@ModelAttribute Certificate certificate,  Model model, HttpServletRequest request) throws Exception {

		logger.info("*** in create Certificate " + certificate);

		logger.info("posting certificate file: "+certificate.getCertificateFile().getOriginalFilename());
		logger.info("posting certificate filename: "+certificate.getCertificateFile().getOriginalFilename());
		logger.info("posting certificate filesize: "+certificate.getCertificateFile().getSize());
		//User user = (User) request.getSession().getAttribute("user");
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.info("Session User :" + user);
		if(null != user){
			Long candidateId = loginService.getCandidate(user.getUserId()).getCandidateId();
			certificate.setCandidateId(candidateId);
			model.addAttribute("certificate", certificate);
			certificate.setCertificateTemplate(CertificateTemplate.defaultTemplate());
			if(certificate.getCertificateId() == null) {
				certificate.setVerificationStatusType(VerificationStatusType.VER_NOT_REQUESTED);
				certificateServiceImpl.saveCertificate(certificate);
			} else { 
				certificateServiceImpl.updateCertificate(certificate);
			}
		}
		model.addAttribute("form", certificate);
		return ApplicationPageConstant.newcertificate_page;
		//return certificate.getCertificateType().getWebPageLink();
	}
	
	@RequestMapping(path="/certificates/{id}" ,method=RequestMethod.GET)
	public String getCertificatListByUserId(@PathVariable String id,@RequestParam(required=false) String fdate,@RequestParam(required=false) String tdate,
			@RequestParam(required=false) Integer pageno,Model model,@ModelAttribute Candidate obj ,HttpServletResponse response,HttpServletRequest request) throws Exception {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(null != user){
			UserType userType = user.getUserType();
			model.addAttribute("username", user.getUserName());
			if(UserType.CANDIDATE.equals(userType)){
				model.addAttribute("type", "candidate");
			}else if(UserType.ISSUER.equals(userType)){
				model.addAttribute("type", "issuer");
			}
		}
		List<Certificate> certificateList = Lists.newArrayList();
		logger.info("***** in getCertificatListByUserId for Session User :" + user);
		if(user.getUserType().equals(UserType.CANDIDATE))
		{
			Long candidateId = loginService.getCandidate(user.getUserId()).getCandidateId();
			logger.info("getting Certificate data for candidateId:" + candidateId);
			certificateList = certificateServiceImpl.getCertificatesForCandidate(candidateId);
		}
		else if(user.getUserType().equals(UserType.ISSUER))
		{
			Long issuerId = loginService.getIssuer(user.getUserId()).getIssuerId();
			logger.info("getting Certificate data for issuer Id:" + issuerId);
			certificateList = certificateServiceImpl.getCertificatesForIssuer(issuerId);
		}
		model.addAttribute("list", certificateList);
		return ApplicationPageConstant.certificatelist_page;
	}
	
	@RequestMapping(path="/certificate/{id}" ,method=RequestMethod.GET)
	public String getCertificatDetailId(@PathVariable Integer id,@RequestParam(required=false) String fdate,@RequestParam(required=false) String tdate,
			@RequestParam(required=false) Integer pageno,Model model,@ModelAttribute Candidate obj ,HttpServletResponse response,HttpServletRequest request) throws Exception {
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(null != user){
			UserType userType = user.getUserType();
			model.addAttribute("username", user.getUserName());
			if(UserType.CANDIDATE.equals(userType)){
				model.addAttribute("type", "candidate");
			}else if(UserType.ISSUER.equals(userType)){
				model.addAttribute("type", "issuer");
			}
		}
		Long candidateId = loginService.getCandidate(user.getUserId()).getCandidateId();
		logger.info("getting Certificate data for candidateId:" + candidateId);
		Certificate certificate = certificateServiceImpl.getCertificate(id);
		model.addAttribute("form", certificate);
		return ApplicationPageConstant.certificate_det_page;
		
	}
	
	@RequestMapping(path="/certificate/edit/{id}" ,method=RequestMethod.GET)
	public String editCertificate(@PathVariable Integer id,Model model, HttpServletRequest request) throws Exception {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(null != user){
			UserType userType = user.getUserType();
			model.addAttribute("username", user.getUserName());
			if(UserType.CANDIDATE.equals(userType)){
				model.addAttribute("type", "candidate");
			}else if(UserType.ISSUER.equals(userType)){
				model.addAttribute("type", "issuer");
			}
		}

		Certificate certificate = null;
		logger.info("**** in editCertificate for Session User :" + user);
		if(user.getUserType().equals(UserType.CANDIDATE))
		{
			Long candidateId = loginService.getCandidate(user.getUserId()).getCandidateId();
			logger.info("getting Certificate data for candidateId:" + candidateId);
			certificate = certificateServiceImpl.getCertificate(id);
		}
		else if(user.getUserType().equals(UserType.ISSUER))
		{
			Long issuerId = loginService.getIssuer(user.getUserId()).getIssuerId();
			logger.info("getting Certificate data for issuer Id:" + issuerId);
			certificate = certificateServiceImpl.getCertificate(id);
		}
		
		model.addAttribute("form", certificate);
		return ApplicationPageConstant.newcertificate_page;
	}
	
	public VerificationStatusType getCertificateVerificationStatusType(Long certificateId)
	{
		return certificateServiceImpl.getCertificateVerificationStatusType(certificateId);
	}
	
	public boolean updateCertificateVerificationStatus(Long certificateId, VerificationStatusType verificationStatusType)
	{
		return certificateServiceImpl.updateCertificateVerificationStatus(certificateId, verificationStatusType);
	}
	
}