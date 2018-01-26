package com.ab.dao;

import java.util.List;

import com.ab.vo.candidate.Candidate;


public interface CandidateDao {
	
	Candidate saveCandidate(Candidate candidate);

	void updateCandidate(Candidate candidate);
	
	Candidate getCandidate(Long candidateId);
	
	void removeCandidate(Long candidateId);
	
	Long getCandidateIdByEmailOrMobile(String emailId, Long mobileNumber);
	
	List<Candidate> getCandidateListByActiveFlag(boolean isActive);

}
