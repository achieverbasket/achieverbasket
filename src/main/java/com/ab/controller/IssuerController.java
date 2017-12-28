package com.ab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ab.constant.config.ApplicationPageConstant;

@Controller
@RequestMapping("/issuer")
public class IssuerController {
	
	// get issue certifiate page
	@RequestMapping(path="/certificate/create" ,method=RequestMethod.GET)
	public String createCertificate()
	{
		return ApplicationPageConstant.createcertificate_page;
	}
	
	//fetch list of templates available in repository to issuer to choose from
	@RequestMapping(path="/certificate/templates" ,method=RequestMethod.GET)
	public String getCertificateTemplates()
	{
		return ApplicationPageConstant.certificatetemplates_page;
	}
	
	// get issue certifiate page
	@RequestMapping(path="/certificate/issue" ,method=RequestMethod.GET)
	public String issueCertificateToCandidate()
	{
		return ApplicationPageConstant.issuecertificate_page;
	}
	
	
	
	//search certificate
	@RequestMapping(path="/certificate/search" ,method=RequestMethod.GET)
	public String searchCertificates()
	{
		return ApplicationPageConstant.searchcertificate_page;
	}
	
	//bulk load page
	@RequestMapping(path="/certificate/bulkload" ,method=RequestMethod.GET)
	public String bulkLoadCertificates()
	{
		return ApplicationPageConstant.bulkloadcertificate_page;
	}
	
	// get issue certifiate page
		@RequestMapping(path="/certificate/loadimage" ,method=RequestMethod.GET)
		public String loadCertificateImage()
		{
			return ApplicationPageConstant.loadcertificateimage_page;
		}
}
