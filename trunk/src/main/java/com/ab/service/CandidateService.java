package com.ab.service;

import java.util.List;

import com.ab.vo.candidate.Candidate;

public interface CandidateService {
	
	
	public List<Candidate> getCandidateListByActiveFlag(boolean isActive);

}
