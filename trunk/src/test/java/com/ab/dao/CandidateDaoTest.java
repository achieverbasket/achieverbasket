//package com.ab.dao;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import com.ab.spring.application.config.SpringApp;
//import com.ab.spring.dao.CandidateDao;
//import com.ab.spring.dao.CandidatePersonalDetailDao;
//import com.ab.type.CandidateType;
//import com.ab.vo.candidate.Candidate;
//import com.ab.vo.candidate.CandidatePersonalDetail;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = SpringApp.class)
//@WebAppConfiguration
//public class CandidateDaoTest {
//
//	@Autowired
//	private CandidateDao candidateDao;
//	
//	@Autowired
//	private CandidatePersonalDetailDao personalDetailDao;
//	
//	@Test
//	public void testCreateCandidate() {
//		Candidate candidate = new Candidate();
//		candidate.setCandidateName("Vinay Jain");
//		candidate.setCandidateType(CandidateType.CANDIDATE);
//		candidate = candidateDao.saveCandidate(candidate);
//		
//		System.out.println(candidate.getCandidateId());
//	}
//	
//	@Test
//	public void testCreateCandidatePersonalDetail() {
//		CandidatePersonalDetail candidatePersonalDetail = new CandidatePersonalDetail();
//		candidatePersonalDetail.setCandidateId(1l);
//		candidatePersonalDetail.setFirstName("Vinay");
//		candidatePersonalDetail.setLastName("Jain");
//		personalDetailDao.saveCandidatePersonalDetail(candidatePersonalDetail);
//		
//	}
//}
