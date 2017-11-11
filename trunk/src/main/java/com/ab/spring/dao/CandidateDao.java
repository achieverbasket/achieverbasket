package com.ab.spring.dao;

import com.ab.vo.candidate.Candidate;


public interface CandidateDao {
	
	Candidate saveCandidate(Candidate candidate);

	void updateCandidate(Candidate candidate);
	
	Candidate getCandidate(Long candidateId);
	
	void removeCandidate(Long candidateId);
}