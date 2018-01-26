package com.ab.service;

import java.util.List;

import com.ab.type.NotificationType;
import com.ab.vo.candidate.Candidate;
import com.ab.vo.issuer.Issuer;

public interface AdminService {
	
	public List<Candidate> getCandidateListByActiveFlag(boolean isActive);
	
	public List<Issuer> getIssuerListByActiveFlag(boolean isActive);
	
	//Method to send notification to inactive candidates for Issuer
	public void sendNotificationToInactiveCandidatesForIssuer(Long issuerId, NotificationType notificationType);
	
	//Method to send notification to inactive candidates
	public void sendNotificationToInactiveCandidates(List<Long> candidateIdList, NotificationType notificationType);
	
	//Method to send notification to inactive issuers
	public void sendNotificationToInactiveIssuers(List<Long> issuerIdList, NotificationType notificationType);

}
