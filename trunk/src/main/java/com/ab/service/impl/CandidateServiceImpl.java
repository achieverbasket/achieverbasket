package com.ab.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ab.dao.CandidateDao;
import com.ab.service.CandidateService;
import com.ab.vo.candidate.Candidate;

public class CandidateServiceImpl implements CandidateService{
	
	@Autowired
	CandidateDao candidateDao;
	
	@Override
	public List<Candidate> getCandidateListByActiveFlag(boolean isActive) {
		
		return candidateDao.getCandidateListByActiveFlag(isActive);
	}

}
