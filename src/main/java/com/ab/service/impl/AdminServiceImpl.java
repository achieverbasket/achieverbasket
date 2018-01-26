package com.ab.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ab.dao.CandidateDao;
import com.ab.dao.IssuerDao;
import com.ab.service.AdminService;
import com.ab.type.NotificationType;
import com.ab.vo.candidate.Candidate;
import com.ab.vo.issuer.Issuer;

public class AdminServiceImpl implements AdminService{

	@Autowired
	private CandidateDao candidateDaoImpl;
	
	@Autowired
	private IssuerDao issuerDaoImpl;
	
	@Override
	public List<Candidate> getCandidateListByActiveFlag(boolean isActive) {
		return candidateDaoImpl.getCandidateListByActiveFlag(isActive);
	}
	
	@Override
	public List<Issuer> getIssuerListByActiveFlag(boolean isActive) {
		return issuerDaoImpl.getIssuerListByActiveFlag(isActive);
	}
	
	//Method to send notification to inactive candidates for Issuer
	@Override
	public void sendNotificationToInactiveCandidatesForIssuer(Long issuerId, NotificationType notificationType) {
		//if issuer id is null then send notification to all inactive candidate
	}
	
	//Method to send notification to inactive candidates
	@Override
	public void sendNotificationToInactiveCandidates(List<Long> candidateIdList, NotificationType notificationType) {
		//if candidateIdList is null then send notification to all inactive candidate
	}
	
	//Method to send notification to inactive issuers
	@Override
	public void sendNotificationToInactiveIssuers(List<Long> issuerIdList, NotificationType notificationType) {
		//if issuer id list is null then send notification to all inactive issuers
	}
	
}
