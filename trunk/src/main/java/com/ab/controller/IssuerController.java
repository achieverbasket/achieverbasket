package com.ab.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.ab.constant.config.ApplicationPageConstant;
import com.ab.service.CertificateService;
import com.ab.service.CertificateTemplateService;
import com.ab.service.IssuerService;
import com.ab.service.LoginService;
import com.ab.type.UserType;
import com.ab.type.VerificationStatusType;
import com.ab.util.CustomAWS;
import com.ab.vo.User;
import com.ab.vo.certificate.Certificate;
import com.ab.vo.certificate.CertificateTemplate;
import com.ab.vo.issuer.Issuer;
import com.google.api.client.util.Lists;

@Controller
@RequestMapping("/issuer")
public class IssuerController {
	
	@Autowired
	CertificateService certificateServiceImpl;
	
	@Autowired
	IssuerService issuerServiceImpl;
	
	@Autowired
	CertificateTemplateService certificateTemplateServiceImpl;
	
	@Autowired
	LoginService loginService;
	
	// get issue certifiate page
	@RequestMapping(path="/certificate/create" ,method=RequestMethod.GET)
	public String getcreateCertificatePage(@ModelAttribute Certificate certificate ,Model model)
	{
		User user = UserController.getUserPrincipal();
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
		return ApplicationPageConstant.createcertificate_page;
	}
	
	// get issue certifiate page
		@RequestMapping(path="/certificate/create" ,method=RequestMethod.POST)
		public String createCertificate(@ModelAttribute Certificate certificate ,Model model) throws Exception
		{
			System.out.println("certificate val----------"+certificate);
			User user = UserController.getUserPrincipal();
			if(null != user){
				model.addAttribute("username", user.getUserName());
				model.addAttribute("type", "issuer");
				Issuer issuer =loginService.getIssuer(user.getUserId());
				certificate.setIssuer(issuer);
				certificate.setIssuerId(issuer.getIssuerId());
			}
			
			certificate.setVerificationStatusType(VerificationStatusType.VERIFIED);
			certificate.setVerifiedBy(user.getUserId());
			certificate.setCertificateType(certificate.getCertificateTemplate().getCertificateType());
			
			certificateServiceImpl.saveCertificate(certificate);
				
			model.addAttribute("form", certificate);
			model.addAttribute("success", "Certificate created successfully");
			return ApplicationPageConstant.createcertificate_page;
		}
	
	@RequestMapping(path="/certificate/imagelist" ,method=RequestMethod.GET)
	public String getAvailableIssuerImageList(Model model)
	{
		User user = UserController.getUserPrincipal();
		if(null != user){
			UserType userType = user.getUserType();
			model.addAttribute("username", user.getUserName());
			if(UserType.CANDIDATE.equals(userType)){
				model.addAttribute("type", "candidate");
			}else if(UserType.ISSUER.equals(userType)){
				model.addAttribute("type", "issuer");
			}
		}
		List<CertificateTemplate> list = new ArrayList<CertificateTemplate>();
		Long issuerId = issuerServiceImpl.getIssuerId(user.getUserId());
		list = certificateTemplateServiceImpl.getCertificateTemplateList(issuerId, null);
		model.addAttribute("templatelist", list);
		return "/certificateimagelist";
	}
	
	//fetch list of templates available in repository to issuer to choose from
	@RequestMapping(path="/certificate/templates" ,method=RequestMethod.GET)
	public String getAvailableCertificateTemplates(
			Model model)
	{
		User user = UserController.getUserPrincipal();
		if(null != user){
			UserType userType = user.getUserType();
			model.addAttribute("username", user.getUserName());
			if(UserType.CANDIDATE.equals(userType)){
				model.addAttribute("type", "candidate");
			}else if(UserType.ISSUER.equals(userType)){
				model.addAttribute("type", "issuer");
			}
		}
		List<CertificateTemplate> list = new ArrayList<CertificateTemplate>();
		Long issuerId = issuerServiceImpl.getIssuerId(user.getUserId());
		list = certificateTemplateServiceImpl.getCertificateTemplateList(issuerId, null);
		model.addAttribute("list", list);
		return ApplicationPageConstant.certificatetemplates_page;
	}
	
	//fetch list of templates assigned to given issuer
	//@RequestMapping(path="/certificate/templates" ,method=RequestMethod.GET)
	public String getIssuerCertificateTemplates(
			Model  model)
	{
		User user = UserController.getUserPrincipal();
		if(null != user){
			UserType userType = user.getUserType();
			model.addAttribute("username", user.getUserName());
			if(UserType.CANDIDATE.equals(userType)){
				model.addAttribute("type", "candidate");
			}else if(UserType.ISSUER.equals(userType)){
				model.addAttribute("type", "issuer");
			}
		}
		//certificateTemplateServiceImpl.getCertificateTemplateList(issuerId, certificateType);
		return ApplicationPageConstant.certificatetemplates_page;
	}
	
	// get issue certifiate page
	@RequestMapping(path="/certificate/issue" ,method=RequestMethod.GET)
	public String issueCertificateToCandidate(Model model)
	{
		User user = UserController.getUserPrincipal();
		if(null != user){
			UserType userType = user.getUserType();
			model.addAttribute("username", user.getUserName());
			if(UserType.CANDIDATE.equals(userType)){
				model.addAttribute("type", "candidate");
			}else if(UserType.ISSUER.equals(userType)){
				model.addAttribute("type", "issuer");
			}
		}
		//certificateServiceImpl.saveCertificate(certificate);
		return ApplicationPageConstant.issuecertificate_page;
	}
	
	//search certificate
	@RequestMapping(path="/certificate/search" ,method=RequestMethod.GET)
	public String searchCertificates(Model model)
	{
		User user = UserController.getUserPrincipal();
		if(null != user){
			UserType userType = user.getUserType();
			model.addAttribute("username", user.getUserName());
			if(UserType.CANDIDATE.equals(userType)){
				model.addAttribute("type", "candidate");
			}else if(UserType.ISSUER.equals(userType)){
				model.addAttribute("type", "issuer");
			}
		}
		return ApplicationPageConstant.searchcertificate_page;
	}
	
	//bulk load page
	@RequestMapping(path="/certificate/bulkload" ,method=RequestMethod.GET)
	public String bulkLoadCertificates(@ModelAttribute Certificate certificate,Model model)
	{
		
		User user = UserController.getUserPrincipal();
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
		return ApplicationPageConstant.bulkloadcertificate_page;
	}
	
	@RequestMapping(path="/certificate/bulkload" ,method=RequestMethod.POST)
	public String bulkLoadCertificatesProcessing(@ModelAttribute File certificateXlsFile ,Model model)
	{
		System.out.println("file name ---------------  "+certificateXlsFile);
		User user = UserController.getUserPrincipal();
		if(null != user){
			UserType userType = user.getUserType();
			model.addAttribute("username", user.getUserName());
			if(UserType.CANDIDATE.equals(userType)){
				model.addAttribute("type", "candidate");
			}else if(UserType.ISSUER.equals(userType)){
				model.addAttribute("type", "issuer");
			}
		}
		//model.addAttribute("form", certificate);
		model.addAttribute("success", "Certificate file uploaded successfully");
		return ApplicationPageConstant.bulkloadcertificate_page;
	}
	
	@RequestMapping(path="/certificate/create/template" ,method=RequestMethod.GET)
	public String getCertificateTemplatePage(@ModelAttribute CertificateTemplate certtemp,Model model)
	{
		User user = UserController.getUserPrincipal();
		if(null != user){
			UserType userType = user.getUserType();
			model.addAttribute("username", user.getUserName());
			if(UserType.CANDIDATE.equals(userType)){
				model.addAttribute("type", "candidate");
			}else if(UserType.ISSUER.equals(userType)){
				model.addAttribute("type", "issuer");
			}
		}
		model.addAttribute("form", certtemp);
		return ApplicationPageConstant.createcertificatetemplate_page;
	}
	
	@RequestMapping(path="/certificate/create/template" ,method=RequestMethod.POST)
	public String createCertificateTemplate(@ModelAttribute CertificateTemplate certtemp,Model model,BindingResult br)
	{
		// null need to be check here in spring way
		User user = UserController.getUserPrincipal();
		if(null != user){
			UserType userType = user.getUserType();
			model.addAttribute("username", user.getUserName());
			if(UserType.CANDIDATE.equals(userType)){
				model.addAttribute("type", "candidate");
			}else if(UserType.ISSUER.equals(userType)){
				model.addAttribute("type", "issuer");
			}
		}
		System.out.println("in loadCertificateImage: "+certtemp.getCertificateType() + certtemp.getTemplateFile());
		
		Long issuerId = issuerServiceImpl.getIssuerId(user.getUserId());
		certtemp.setIssuerId(issuerId);
		/*AWS file save, more logic later*/
		try {
			CustomAWS.uploadDocument(convert(certtemp.getTemplateFile()), "achieverbasketimg", certtemp.getTemplateFile().getOriginalFilename());
			certificateTemplateServiceImpl.saveCertificateTemplate(certtemp);
			model.addAttribute("success", "Certificate Template Image uploaded successfully");
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("form", certtemp);
		return ApplicationPageConstant.createcertificatetemplate_page;
	}
	
	public static File convert(MultipartFile file) throws IOException {
	    File convFile = new File(file.getOriginalFilename());
	    convFile.createNewFile();
	    FileOutputStream fos = new FileOutputStream(convFile);
	    fos.write(file.getBytes());
	    fos.close();
	    return convFile;
	}
	
	public List<Certificate> getCertificateListFromXls(File xlsFile) {
		List<Certificate> certificateList = Lists.newArrayList();
		
		return certificateList;
	}
}
