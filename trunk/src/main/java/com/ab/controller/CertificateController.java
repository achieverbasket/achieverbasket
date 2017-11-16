package com.ab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ab.constant.config.ApplicationPageConstant;
import com.ab.service.CertificateService;
import com.ab.vo.candidate.Candidate;
import com.ab.vo.certificate.Certificate;

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

	@ResponseBody
	@RequestMapping(path="/certificates/user/{id}" ,method=RequestMethod.GET)
	public Candidate getCertificatDetailsForCandidate(@PathVariable String id,@ModelAttribute Candidate candidate) {
		
		 System.out.println("getting Certificate data for id:"+id+" candidate: "+candidate);
		 
		 List<Certificate> certificateList = certificateServiceImpl.getCertificatesForCandidate(candidate.getCandidateId());
		 candidate.setCandidateCertificateList(certificateList);
		 return candidate;
	}
	
	@RequestMapping(path="/certificate" ,method=RequestMethod.GET)
	public String getCertificate(@ModelAttribute Certificate certificate,Model model){
		model.addAttribute("form", certificate);
		return ApplicationPageConstant.newcertificate_page;
	}
	
	@RequestMapping(path="/certificate" ,method=RequestMethod.POST)
	public String createCertificate(@ModelAttribute Certificate certificate,  Model model) {
		
		System.out.println("Certificate: "+certificate+" id: "+certificate.getCertificateId());
		System.out.println("posting certificate file: "+certificate.getCertificateFile().getOriginalFilename());
		System.out.println("posting certificate filename: "+certificate.getCertificateFile().getOriginalFilename());
		System.out.println("posting certificate filesize: "+certificate.getCertificateFile().getSize());

		model.addAttribute("certificate", certificate);
		certificateServiceImpl.saveCertificate(certificate);
		return certificate.getCertificateType().getWebPageLink();
	}
/*	@RequestMapping(path="/certificate/academic" ,method=RequestMethod.POST)
	public String createCertificate(@ModelAttribute Certificate certificate,  Model model) {
		
		System.out.println("Certificate: "+certificate+" id: "+certificate.getCertificateId());
		System.out.println("posting certificate file: "+certificate.getCertificateFile().getOriginalFilename());
		System.out.println("posting certificate filename: "+certificate.getCertificateFile().getOriginalFilename());
		System.out.println("posting certificate filesize: "+certificate.getCertificateFile().getSize());

		model.addAttribute("certificate", certificate);
		certificateServiceImpl.saveCertificate(certificate);
		return certificate.getCertificateType().getWebPageLink();
	}*/
	
	
	
	
//	@ResponseBody
//	@RequestMapping(path="/certificates/user/{id}" ,method=RequestMethod.GET)
//	public Candidate getCertificatDetailsForUser(@PathVariable String id,@ModelAttribute Candidate obj) {
//		
//		 System.out.println("getting Certificate data for id:"+id+" candidate: "+obj);
//		 List<ProfessionalCertificate> professionalCertificateList = new ArrayList<>();
//		 List<AcademicCertificate> academicCertificateList = new ArrayList<>();
//		 List<ExtraCurriculamCertificate> extraCurricularCertificateList = new ArrayList<>();
//		 
//
//		 /*start*/
//		 ProfessionalCertificate p = new ProfessionalCertificate();//p.setCertificateId(1);p.setCertificateName("MSCI JAVA 1.9");
//		 Organization o = new Organization();o.setOrganizationName("Bhopal");o.setOrganizationId(1);
//		 p.setOrganization(o);
//		 
//		 SocialActivity s = new SocialActivity();
//		 Comment c = new Comment();
////		 c.setCommentIdList(new ArrayList<>(25));
//	//	 s.setComment(c);
//		 
//		 Like l = new Like();
//		// l.setLikeIdList(new ArrayList<>(30));
//		 //s.setLike(l);
//		 
//		 p.setSocialActivity(s);
//		 
//		 professionalCertificateList.add(p);
//		 /*end*/
//		 
//		 /*start*/
//		 AcademicCertificate a1 = new AcademicCertificate();//a1.setCertificateId(1);a1.setCertificateName("HSC Examination");
//		 Organization o1 = new Organization();o1.setOrganizationName("MSCI");o1.setOrganizationId(1);
//		 p.setOrganization(o1);
//		 
//		 SocialActivity s1 = new SocialActivity();
//		 Comment c1 = new Comment();//c1.setCommentIdList(new ArrayList<>(58));
//		// s.setComment(c1);
//		 
//		 Like l1 = new Like();//l1.setLikeIdList(new ArrayList<>(30));
//		// s.setLike(l1);
//		 
//		 p.setSocialActivity(s1);
//		 
//		 academicCertificateList.add(a1);
//		 /*end*/
//		 
//		 /*start*/
//		 ExtraCurriculamCertificate e1 = new ExtraCurriculamCertificate();//e1.setCertificateId(1);e1.setCertificateName("Top Coder Ranking 50000");
//		 Organization o2 = new Organization();o2.setOrganizationName("TopCoder");o2.setOrganizationId(1);
//		 p.setOrganization(o1);
//		 
//		 SocialActivity s2 = new SocialActivity();
//		 Comment c2 = new Comment();//c2.setCommentIdList(new ArrayList<>(580));
//		// s.setComment(c2);
//		 
//		 Like l2 = new Like();//l2.setLikeIdList(new ArrayList<>(30));
//		// s.setLike(l2);
//		 
//		 p.setSocialActivity(s2);
//		 
////		 extraCurricularCertificateList.add(e1);
//		 /*end*/
//		 
////		 obj.setCandidateCertificateList(academicCertificateList);
////		 obj.setCandidateExtraCurricularCertificateList(extraCurricularCertificateList);
////		 obj.setCandidateProfessionalCertificateList(professionalCertificateList);
//		 
//		return obj;
//	}
	
//	@RequestMapping(path="/certificates/{id}" ,method=RequestMethod.GET)
//	public String getCertificatListByUserId(@PathVariable String id,@RequestParam(required=false) String fdate,@RequestParam(required=false) String tdate,
//			@RequestParam(required=false) Integer pageno,Model model,@ModelAttribute Candidate obj ,HttpServletResponse response,HttpServletRequest req) {
//		
//	
//		List<Certificate> list = new ArrayList<Certificate>();
//		
//		
//		Certificate ac = new Certificate();
//		ac.setCandidateId(1L);
//		ac.setCertificateId(1L);
//		ac.setCertificateName("Board");
//		ac.setIssueDate("21/10/2017");
//		ac.setVerified(true);
//		//ac.setVerifiedBy("swapnil");
//		//ac.setVerificationDate("");
//		ac.setFilePath("");
//		SocialActivity sa = new SocialActivity();
//		ac.setSocialActivity(sa);
//		
//		list.add(ac);
//		
//		 model.addAttribute("list", list);
//		return ApplicationPageConstant.certificatelist_page;
//	}
//	
//	@RequestMapping(path="/certificate/academic" ,method=RequestMethod.GET)
//	public String academicCertificate(@ModelAttribute AcademicCertificate a,Model model) {
//		a.setPreferenceStatusType(PreferenceStatusType.PUBLIC);
//		model.addAttribute("form", a);
//		model.addAttribute("preftype", PreferenceStatusType.values());
//		return ApplicationPageConstant.academiccertificate_page;
//	}
//	
//	@RequestMapping(path="/certificate/academic" ,method=RequestMethod.POST)
//	public String createAcademicCertificate(@ModelAttribute AcademicCertificate form,  Model model) {
//		
//		System.out.println("posting academic form"+" file: "+form.getCertificateFile().getOriginalFilename());
//		System.out.println("posting academic form"+" filename: "+form.getCertificateFile().getOriginalFilename());
//		System.out.println("posting academic form"+" filesize: "+form.getCertificateFile().getSize());
//
//		AcademicCertificate a = new AcademicCertificate();
//		model.addAttribute("form", a);
//		certificateServiceImpl.saveCertificate(form);
//		return ApplicationPageConstant.academiccertificate_page;
//	}
//	
//	@RequestMapping(path="/certificate/professional" ,method=RequestMethod.GET)
//	public String ProfessionalCertificate(@ModelAttribute ProfessionalCertificate a,Model model) {
//		model.addAttribute("form", a);
//		return ApplicationPageConstant.professionalcertificate_page;
//	}
//	
//	@RequestMapping(path="/certificate/professional" ,method=RequestMethod.POST)
//	public String createProfessionalCertificate(@ModelAttribute ProfessionalCertificate form,  Model model) {
//		
//		// 
//		System.out.println("posting professional form"+form.getCertificateName()+form.getCertificateFile().getOriginalFilename()
//				+form.getIssuer().getIssuerName()+form.getOrganization().getOrganizationName());
//		ProfessionalCertificate a = new ProfessionalCertificate();
//		model.addAttribute("form", a);
//		return ApplicationPageConstant.professionalcertificate_page;
//	}
//	@RequestMapping(path="/certificate/extracurriculam" ,method=RequestMethod.GET)
//	public String ExtraCurriculamCertificate(@ModelAttribute ExtraCurriculamCertificate a,Model model) {
//		model.addAttribute("form", a);
//		return ApplicationPageConstant.extracurriculamcertificate_page;
//	}
//	
//	@RequestMapping(path="/certificate/extracurriculam" ,method=RequestMethod.POST)
//	public String createExtraCurriculamCertificate(@ModelAttribute ExtraCurriculamCertificate form,  Model model) {
//		
//		// 
//		System.out.println("posting extracurriculam form"+form.getIssuer().getIssuerName()
//				+form.getCertificateName()+form.getCertificateFile().getOriginalFilename()+form.getPreferenceStatusType());
//		ExtraCurriculamCertificate a = new ExtraCurriculamCertificate();
//		model.addAttribute("form", a);
//		
//		return ApplicationPageConstant.extracurriculamcertificate_page;
//	}
//	
//	
//	@RequestMapping(path="/certificate" ,method=RequestMethod.GET)
//	public String getCertificatePage(Model model) {
//		Certificate obj = new Certificate(); 
//		
//		model.addAttribute("form", obj);
//		model.addAttribute("type", CertificateType.values());
//		return ApplicationPageConstant.certificate_page;
//	}
//	
//	
//	
//	@RequestMapping(path="/certificate/new" ,method=RequestMethod.GET)
//	public String createNewCertificate(Model model) {
//		
//		ProfessionalCertificate p = new ProfessionalCertificate();
//		AcademicCertificate a = new AcademicCertificate();
//		ExtraCurriculamCertificate e = new ExtraCurriculamCertificate();
//		
//		model.addAttribute("pform", p);
//		model.addAttribute("aform", a);
//		model.addAttribute("eform", e);
//		return ApplicationPageConstant.newcertificate_page;
//	}
//	
//	@ResponseBody
//	@RequestMapping(path="/certificate/upload" ,method=RequestMethod.POST,consumes = {"multipart/form-data"})
//	public String uploadertificate(@RequestPart(value = "file", required = false) MultipartFile file,
//		@RequestParam(value="dataparam") String aform , @RequestParam(value="type") String type) {
//		System.out.println("file path: "+file.getOriginalFilename());
//		Certificate certificate=null;
//		// based on type do the conversion
//		
//		if(null != type && type.equalsIgnoreCase("save-acd-cert")){
//			try {
//				certificate = new ObjectMapper().readValue(aform,     AcademicCertificate.class);
//				System.out.println("academic certifiacate "+certificate.getCertificateName()+certificate.getIssueDate()+certificate.getEndDate()+certificate.getPreferenceStatusType());
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}else if(null != type && type.equalsIgnoreCase("save-prf-cert")){
//			try {
//				certificate = new ObjectMapper().readValue(aform,     ProfessionalCertificate.class);
//				System.out.println("ProfessionalCertificate "+certificate.getCertificateName()+certificate.getIssueDate()+certificate.getEndDate()+certificate.getPreferenceStatusType());
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}else if(null != type && type.equalsIgnoreCase("save-extra-cert")){
//			try {
//				certificate = new ObjectMapper().readValue(aform,     ExtraCurriculamCertificate.class);
//				System.out.println("ExtraCurriculam certifiacate "+certificate.getCertificateName()+certificate.getIssueDate()+certificate.getEndDate()+certificate.getPreferenceStatusType());
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//	   certificateServiceImpl.saveCertificate(certificate);
//		
//		return "true";
//		
//	}
//	
//	@RequestMapping(path="/certificate/edit/{id}" ,method=RequestMethod.GET)
//	public String editCertificate(@PathVariable Integer id,Model model) {
//		
//		// based on the type of certificate, go to the client page
//		//hardcoding now for prefessional page
//		ProfessionalCertificate p = new ProfessionalCertificate();
//		p.setCertificateId(1L);   p.setCertificateName("MSCI JAVA 1.9");p.setIssueDate("28/10/2017");p.setEndDate("28/10/2017");
//		 Organization o = new Organization();o.setOrganizationName("Bhopal");o.setOrganizationId(1);
//		 o.setImagePath("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/Sun_poster.svg/500px-Sun_poster.svg.png");
//		 p.setOrganization(o);p.setSalary(10000d);
//
//		 SocialActivity s = new SocialActivity();
//		 Comment c = new Comment();//c.setCommentIdList(new ArrayList<>(25));
//	//	 s.setComment(c);
//		 
//		 Like l = new Like();//l.setLikeIdList(new ArrayList<>(30));
//	//	 s.setLike(l);
//		 
//		 p.setSocialActivity(s);
//		
//		model.addAttribute("form", p);
//		return ApplicationPageConstant.professionalcertificate_page;
//	}
	
}