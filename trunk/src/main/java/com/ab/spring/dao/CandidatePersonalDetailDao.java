package com.ab.spring.dao;

import com.ab.vo.candidate.CandidatePersonalDetail;

public interface CandidatePersonalDetailDao {
	
	CandidatePersonalDetail saveCandidatePersonalDetail(CandidatePersonalDetail candidatePersonalDetail);
	
	void updateCandidatePersonalDetail(CandidatePersonalDetail candidatePersonalDetail);
	
	CandidatePersonalDetail getPersonalDetailByCandidateId(Long candidateId);

	CandidatePersonalDetail getPersonalDetailById(Long personalDetailId);

	void removeCandidatePersonalDetail(Long candidatePersonalDetailId);
}
