package com.ab.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ab.constant.config.ApplicationPageConstant;
import com.ab.type.CertificateType;
import com.ab.type.StatusType;
import com.ab.vo.User;
import com.ab.vo.certificate.Certificate;
import com.ab.vo.certificate.CertificateTemplate;
import com.ab.vo.issuer.Issuer;

public class IssuerController {
	

	@RequestMapping(path="/issuer" ,method=RequestMethod.GET)
	public String addIssuer(Issuer issuer) {
		System.out.println("*** in addIssuer for :" + issuer);
		return ApplicationPageConstant.newcertificate_page;
	}
	
	//fetch list of templates available in repository to issuer to choose from
	@RequestMapping(path="/gettemplate" ,method=RequestMethod.GET)
	public String getCertificateTemplates(CertificateType certificateType)
	{
		System.out.println("*** in getCertificateTemplates for :" + certificateType);
		return ApplicationPageConstant.newcertificate_page;
	}
	
	//add issuer's own template to our repository
	@RequestMapping(path="/addtemplate" ,method=RequestMethod.GET)
	public String addCertificateTemplate(Long issuerId, CertificateTemplate certificateTemplate, CertificateType certificateType)
	{
		System.out.println("*** in addCertificateTemplate for :" + certificateType);
		return ApplicationPageConstant.newcertificate_page;
	}
	
	//assign choosen template by issuer to himself
	@RequestMapping(path="/assigntemplate" ,method=RequestMethod.GET)
	public String assignCertificateTemplates(Long issuerId,CertificateType certificateType, Long templateId)
	{
		System.out.println("*** in assignCertificateTemplates for issuer:" + issuerId+" certificateType"+certificateType+ "templateId "+templateId);
		return ApplicationPageConstant.newcertificate_page;
	}
	
	//facilitate bulk addition of certificate list
	@RequestMapping(path="/addBulkCertificates" ,method=RequestMethod.GET)
	public String addBulkCertificates(Long issuerId, Long templateId, List<Certificate> certificateList)
	{
		System.out.println("*** in addBulkCertificates for :" + issuerId);
		return ApplicationPageConstant.newcertificate_page;
	}
	
	//facilitate bulk addition of certification via xls
	@RequestMapping(path="/addBulkCertificatesFromXLS" ,method=RequestMethod.GET)
	public String addBulkCertificatesFromXLS(Long issuerId, Long templateId, File xlsFile)
	{
		System.out.println("*** in addBulkCertificatesFromXLS for :" + issuerId);
		return ApplicationPageConstant.newcertificate_page;
	}
	
	//update approval status for given certificate
	@RequestMapping(path="/issuer" ,method=RequestMethod.GET)
	public String updateApprovalStatus(Long certificateId, Long issuerId, StatusType statusType, String comment) {
		System.out.println("*** in addIssuer for :" + issuerId+ " certificateId: "+ certificateId+ " statusType "+statusType);
		return ApplicationPageConstant.newcertificate_page;
	}
	
	//update approval status for given certificate list
	@RequestMapping(path="/issuer" ,method=RequestMethod.GET)
	public String bulkUpdateApprovalStatus(List<Long> certificateIdList, Long issuerId, StatusType statusType, String comment) {
		System.out.println("*** in addIssuer for :" + issuerId+  " statusType "+statusType);
		return ApplicationPageConstant.newcertificate_page;
	}
}
