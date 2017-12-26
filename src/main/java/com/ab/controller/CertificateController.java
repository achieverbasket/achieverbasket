package com.ab.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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


	@RequestMapping(path="/certificate" ,method=RequestMethod.GET)
	public String getCertificate(@ModelAttribute Certificate certificate,Model model, HttpServletRequest request){
		model.addAttribute("form", certificate);
		User user = (User) request.getSession().getAttribute("user");
		System.out.println("*** in getCertificate for Session User :" + user);
		return ApplicationPageConstant.newcertificate_page;
	}

	@RequestMapping(path="/certificate" ,method=RequestMethod.POST)
	public String createCertificate(@ModelAttribute Certificate certificate,  Model model, HttpServletRequest request) throws Exception {

		System.out.println("*** in create Certificate " + certificate);

		System.out.println("posting certificate file: "+certificate.getCertificateFile().getOriginalFilename());
		System.out.println("posting certificate filename: "+certificate.getCertificateFile().getOriginalFilename());
		System.out.println("posting certificate filesize: "+certificate.getCertificateFile().getSize());
		User user = (User) request.getSession().getAttribute("user");
		System.out.println("Session User :" + user);
		Long candidateId = loginService.getCandidate(user.getUserId()).getCandidateId();
		certificate.setCandidateId(candidateId);
		model.addAttribute("certificate", certificate);
		certificate.setCertificateTemplate(CertificateTemplate.defaultTemplate());
		if(certificate.getCertificateId() == null) {
			certificateServiceImpl.saveCertificate(certificate);
		} else { 
			certificateServiceImpl.updateCertificate(certificate);
		}
		model.addAttribute("form", certificate);
		return ApplicationPageConstant.newcertificate_page;
		//return certificate.getCertificateType().getWebPageLink();
	}
	
	@RequestMapping(path="/certificates/{id}" ,method=RequestMethod.GET)
	public String getCertificatListByUserId(@PathVariable String id,@RequestParam(required=false) String fdate,@RequestParam(required=false) String tdate,
			@RequestParam(required=false) Integer pageno,Model model,@ModelAttribute Candidate obj ,HttpServletResponse response,HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		List<Certificate> certificateList = Lists.newArrayList();
		System.out.println("***** in getCertificatListByUserId for Session User :" + user);
		if(user.getUserType().equals(UserType.CANDIDATE))
		{
			Long candidateId = loginService.getCandidate(user.getUserId()).getCandidateId();
			System.out.println("getting Certificate data for candidateId:" + candidateId);
			certificateList = certificateServiceImpl.getCertificatesForCandidate(candidateId);
		}
		else if(user.getUserType().equals(UserType.ISSUER))
		{
			Long issuerId = loginService.getIssuer(user.getUserId()).getIssuerId();
			System.out.println("getting Certificate data for issuer Id:" + issuerId);
			certificateList = certificateServiceImpl.getCertificatesForIssuer(issuerId);
		}
		model.addAttribute("list", certificateList);
		return ApplicationPageConstant.certificatelist_page;
	}
	
	@RequestMapping(path="/certificate/{id}" ,method=RequestMethod.GET)
	public String getCertificatDetailId(@PathVariable Integer id,@RequestParam(required=false) String fdate,@RequestParam(required=false) String tdate,
			@RequestParam(required=false) Integer pageno,Model model,@ModelAttribute Candidate obj ,HttpServletResponse response,HttpServletRequest request) throws Exception {
		
		User user = (User) request.getSession().getAttribute("user");
		System.out.println("**** in getCertificatDetailId for Session User :" + user);
		Long candidateId = loginService.getCandidate(user.getUserId()).getCandidateId();
		System.out.println("getting Certificate data for candidateId:" + candidateId);
		Certificate certificate = certificateServiceImpl.getCertificate(id);
		model.addAttribute("form", certificate);
		return ApplicationPageConstant.certificate_det_page;
		
	}
	
	@RequestMapping(path="/certificate/edit/{id}" ,method=RequestMethod.GET)
	public String editCertificate(@PathVariable Integer id,Model model, HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute("user");

		Certificate certificate = null;
		System.out.println("**** in editCertificate for Session User :" + user);
		if(user.getUserType().equals(UserType.CANDIDATE))
		{
			Long candidateId = loginService.getCandidate(user.getUserId()).getCandidateId();
			System.out.println("getting Certificate data for candidateId:" + candidateId);
			certificate = certificateServiceImpl.getCertificate(id);
		}
		else if(user.getUserType().equals(UserType.ISSUER))
		{
			Long issuerId = loginService.getIssuer(user.getUserId()).getIssuerId();
			System.out.println("getting Certificate data for issuer Id:" + issuerId);
			certificate = certificateServiceImpl.getCertificate(id);
		}
		
		model.addAttribute("form", certificate);
		return ApplicationPageConstant.newcertificate_page;
	}
	

}