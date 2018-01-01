package com.ab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ab.constant.config.ApplicationPageConstant;
import com.ab.service.CertificateService;
import com.ab.service.CertificateTemplateService;
import com.ab.type.CertificateType;
import com.ab.vo.certificate.Certificate;

@Controller
@RequestMapping("/issuer")
public class IssuerController {
	
	@Autowired
	CertificateService certificateServiceImpl;
	
	@Autowired
	CertificateTemplateService certificateTemplateServiceImpl;
	
	// get issue certifiate page
	@RequestMapping(path="/certificate/create" ,method=RequestMethod.GET)
	public String createCertificate()
	{
		System.out.println("in create Certificate");
		return ApplicationPageConstant.createcertificate_page;
	}
	
	//fetch list of templates available in repository to issuer to choose from
	@RequestMapping(path="/certificate/templates" ,method=RequestMethod.GET)
	public String getAvailableCertificateTemplates(CertificateType certificateType)
	{
		System.out.println("in getAvailableCertificateTemplates");
		certificateTemplateServiceImpl.getCertificateTemplateList(null, certificateType);
		return ApplicationPageConstant.certificatetemplates_page;
	}
	
	//fetch list of templates assigned to given issuer
	//@RequestMapping(path="/certificate/templates" ,method=RequestMethod.GET)
	public String getIssuerCertificateTemplates(Long issuerId, CertificateType certificateType)
	{
		System.out.println("in getIssuerCertificateTemplates");
		certificateTemplateServiceImpl.getCertificateTemplateList(issuerId, certificateType);
		return ApplicationPageConstant.certificatetemplates_page;
	}
	
	// get issue certifiate page
	@RequestMapping(path="/certificate/issue" ,method=RequestMethod.GET)
	public String issueCertificateToCandidate(Long issuerId, Long candidateId, Certificate certificate)
	{
		System.out.println("in issueCertificateToCandidate");
		certificate.setCandidateId(candidateId);
		certificate.setIssuerId(issuerId);
		certificateServiceImpl.saveCertificate(certificate);
		return ApplicationPageConstant.issuecertificate_page;
	}
	
	//search certificate
	@RequestMapping(path="/certificate/search" ,method=RequestMethod.GET)
	public String searchCertificates()
	{
		System.out.println("in searchCertificates");
		return ApplicationPageConstant.searchcertificate_page;
	}
	
	//bulk load page
	@RequestMapping(path="/certificate/bulkload" ,method=RequestMethod.GET)
	public String bulkLoadCertificates()
	{
		System.out.println("in bulkLoadCertificates");
		return ApplicationPageConstant.bulkloadcertificate_page;
	}
	
	// get issue certifiate page
	@RequestMapping(path="/certificate/loadimage" ,method=RequestMethod.GET)
	public String loadCertificateImage()
	{
		System.out.println("in loadCertificateImage");
		return ApplicationPageConstant.loadcertificateimage_page;
	}
}
