package com.ab.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ab.spring.application.config.SpringApp;
import com.ab.spring.dao.CandidateDao;
import com.ab.type.CandidateType;
import com.ab.vo.candidate.Candidate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringApp.class)
@WebAppConfiguration
public class CandidateDaoTest {

	@Autowired
	private CandidateDao candidateDao;
	
	@Test
	public void testCreateCandidate() {
		Candidate candidate = new Candidate();
		candidate.setCandidateName("Vinay Jain");
		candidate.setCandidateType(CandidateType.CANDIDATE);
		candidate = candidateDao.saveCandidate(candidate);
		
		System.out.println(candidate.getCandidateId());
	}
}
