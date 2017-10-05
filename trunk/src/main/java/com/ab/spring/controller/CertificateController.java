//package com.ab.spring.controller;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.ab.constant.config.ApplicationPageConstant;
//import com.ab.spring.form.UserCertificate;
//import com.ab.type.ProfileType;
//import com.ab.type.StatusType;
//import com.ab.type.UserType;
//import com.ab.vo.activity.Comment;
//import com.ab.vo.activity.Like;
//import com.ab.vo.activity.SocialActivity;
//import com.ab.vo.candidate.Candidate;
//import com.ab.vo.certificate.AcademicCertificate;
//import com.ab.vo.certificate.Certificate;
//import com.ab.vo.certificate.ExtraCurriculamCertificate;
//import com.ab.vo.certificate.ProfessionalCertificate;
//import com.ab.vo.issuer.institute.AcademicInstitute;
//import com.ab.vo.issuer.organization.Organization;
//import com.ab.vo.preference.CertificatePreference;
//import com.sendgrid.Personalization;
//
//
///**
// * @author Swapnil Singhai
// * @version 1
// * @since 16/09/2017
// */
//@Controller
//public class CertificateController {
//	
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
//		 ProfessionalCertificate p = new ProfessionalCertificate();p.setCertificateId(1);p.setCertificateName("MSCI JAVA 1.9");
//		 Organization o = new Organization();o.setOrganizationName("Bhopal");o.setOrganizationId(1);
//		 p.setOrganization(o);
//		 
//		 SocialActivity s = new SocialActivity();
//		 Comment c = new Comment();
//		 c.setCommentIdList(new ArrayList<>(25));
//		 s.setComment(c);
//		 
//		 Like l = new Like();
//		 l.setLikeIdList(new ArrayList<>(30));
//		 s.setLike(l);
//		 
//		 p.setSocialActivity(s);
//		 
//		 professionalCertificateList.add(p);
//		 /*end*/
//		 
//		 /*start*/
//		 AcademicCertificate a1 = new AcademicCertificate();a1.setCertificateId(1);a1.setCertificateName("HSC Examination");
//		 Organization o1 = new Organization();o1.setOrganizationName("MSCI");o1.setOrganizationId(1);
//		 p.setOrganization(o1);
//		 
//		 SocialActivity s1 = new SocialActivity();
//		 Comment c1 = new Comment();c1.setCommentIdList(new ArrayList<>(58));
//		 s.setComment(c1);
//		 
//		 Like l1 = new Like();l1.setLikeIdList(new ArrayList<>(30));
//		 s.setLike(l1);
//		 
//		 p.setSocialActivity(s1);
//		 
//		 academicCertificateList.add(a1);
//		 /*end*/
//		 
//		 /*start*/
//		 ExtraCurriculamCertificate e1 = new ExtraCurriculamCertificate();e1.setCertificateId(1);e1.setCertificateName("Top Coder Ranking 50000");
//		 Organization o2 = new Organization();o2.setOrganizationName("TopCoder");o2.setOrganizationId(1);
//		 p.setOrganization(o1);
//		 
//		 SocialActivity s2 = new SocialActivity();
//		 Comment c2 = new Comment();c2.setCommentIdList(new ArrayList<>(580));
//		 s.setComment(c2);
//		 
//		 Like l2 = new Like();l2.setLikeIdList(new ArrayList<>(30));
//		 s.setLike(l2);
//		 
//		 p.setSocialActivity(s2);
//		 
//		 extraCurricularCertificateList.add(e1);
//		 /*end*/
//		 
////		 obj.setCandidateCertificateList(academicCertificateList);
////		 obj.setCandidateExtraCurricularCertificateList(extraCurricularCertificateList);
////		 obj.setCandidateProfessionalCertificateList(professionalCertificateList);
//		 
//		return obj;
//	}
//	
//	@RequestMapping(path="/certificates/{id}" ,method=RequestMethod.GET)
//	public String getCertificatListByUserId(@PathVariable String id,@RequestParam(required=false) String fdate,@RequestParam(required=false) String tdate,
//			@RequestParam(required=false) Integer pageno,Model model,@ModelAttribute Candidate obj ,HttpServletResponse response,HttpServletRequest req) {
//		
//		System.out.println(req.getHeader("auth-token"));
//		List<ProfessionalCertificate> professionalCertificateList = new ArrayList<>();
//		 List<AcademicCertificate> academicCertificateList = new ArrayList<>();
//		 List<ExtraCurriculamCertificate> extraCurricularCertificateList = new ArrayList<>();
//		 
//
//		 /*start*/
//		 ProfessionalCertificate p = new ProfessionalCertificate();p.setCertificateId(1);p.setCertificateName("MSCI JAVA 1.9");
//		 Organization o = new Organization();o.setOrganizationName("Bhopal");o.setOrganizationId(1);o.setImagePath("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/Sun_poster.svg/500px-Sun_poster.svg.png");
//		 p.setOrganization(o);
//		 
//		 SocialActivity s = new SocialActivity();
//		 Comment c = new Comment();c.setCommentIdList(new ArrayList<>(25));
//		 s.setComment(c);
//		 
//		 Like l = new Like();l.setLikeIdList(new ArrayList<>(30));
//		 s.setLike(l);
//		 
//		 p.setSocialActivity(s);
//		 
//		 professionalCertificateList.add(p);
//		 /*end*/
//		 
//		 /*start*/
//		 AcademicCertificate a1 = new AcademicCertificate();a1.setCertificateId(1);a1.setCertificateName("HSC Examination");
//		 
//		 SocialActivity s1 = new SocialActivity();
//		 Comment c1 = new Comment();c1.setCommentIdList(new ArrayList<>(58));
//		 s1.setComment(c1);
//		 
//		 Like l1 = new Like();l1.setLikeIdList(new ArrayList<>(30));
//		 s1.setLike(l1);
//		 
//		 a1.setSocialActivity(s1);
//		 
//		 academicCertificateList.add(a1);
//		 /*end*/
//		 
//		 /*start*/
//		 ExtraCurriculamCertificate e1 = new ExtraCurriculamCertificate();e1.setCertificateId(1);e1.setCertificateName("Top Coder Ranking 50000");
//		
//		 
//		 SocialActivity s2 = new SocialActivity();
//		 Comment c2 = new Comment();c2.setCommentIdList(new ArrayList<>(580));
//		 s2.setComment(c2);
//		 
//		 Like l2 = new Like();l2.setLikeIdList(new ArrayList<>(30));
//		 s2.setLike(l2);
//		 
//		 e1.setSocialActivity(s2);
//		 
//		 extraCurricularCertificateList.add(e1);
//		 /*end*/
//		 
////		 obj.setCandidateAcademicCertificateList(academicCertificateList);
////		 obj.setCandidateExtraCurricularCertificateList(extraCurricularCertificateList);
////		 obj.setCandidateProfessionalCertificateList(professionalCertificateList);
//		 model.addAttribute("obj", obj);
//		return ApplicationPageConstant.certificatelist_page;
//	}
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
//	@RequestMapping(path="/certificate/edit/{id}" ,method=RequestMethod.GET)
//	public String editCertificate(@PathVariable Integer id,Model model) {
//		
//		
//		// find out the certificate type and send it to appropriate listing page if they are not same.
//		
//		// creating professional one here
//		
//		ProfessionalCertificate p = new ProfessionalCertificate();
//		p.setCertificateId(1);p.setCertificateName("MSCI JAVA 1.9");p.setStartDate("20/03/1988");p.setEndDate("20/03/1988");
//		 Organization o = new Organization();o.setOrganizationName("Bhopal");o.setOrganizationId(1);
//		 o.setImagePath("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/Sun_poster.svg/500px-Sun_poster.svg.png");
//		 p.setOrganization(o);p.setSalary(10000d);
//		 
//		 SocialActivity s = new SocialActivity();
//		 Comment c = new Comment();c.setCommentIdList(new ArrayList<>(25));
//		 s.setComment(c);
//		 
//		 Like l = new Like();l.setLikeIdList(new ArrayList<>(30));
//		 s.setLike(l);
//		 
//		 p.setSocialActivity(s);
//		
//		model.addAttribute("form", p);
//		return ApplicationPageConstant.professionalcertificate_page;
//	}
//	
//}
