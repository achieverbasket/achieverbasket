package com.ab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ab.service.AdminService;
import com.ab.type.NotificationType;

public class AdminController {
	
	@Autowired
	AdminService adminServiceImpl;
	
	//Method to send notification to inactive candidates for Issuer
	public void sendNotificationToInactiveCandidatesForIssuer(Long issuerId, NotificationType notificationType) {
		adminServiceImpl.sendNotificationToInactiveCandidatesForIssuer(issuerId, notificationType);
	}

	
	//Method to send notification to inactive candidates
	public void sendNotificationToInactiveCandidates(List<Long> candidateIdList, NotificationType notificationType) {
		//if candidateIdList is null then send notification to all inactive candidate
		adminServiceImpl.sendNotificationToInactiveCandidates(candidateIdList, notificationType);
	}
	
	//Method to send notification to inactive issuers
	public void sendNotificationToInactiveIssuers(List<Long> issuerIdList, NotificationType notificationType) {
		//if issuer id list is null then send notification to all inactive issuers
		adminServiceImpl.sendNotificationToInactiveIssuers(issuerIdList, notificationType);
	}


}
