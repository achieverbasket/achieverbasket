package com.ab.type;

public enum ActionType {
	//Issuer
	ISSUE_CERTIFICATE,
	VALIDATE_CERTIFICATE,
	VALIDATE_CANDIDATE,
	UPLOAD_CERTIFICATE,
	BULK_UPLOAD_CERTIFICATE,
	APPROVE_CERTIFICATE,
	APPROVE_USER,
	REJECT,
	DELETE,
	CONCERN,
	VIEW,
	MODIFY,

	//Candidate
	REQUEST,
	USER_UPLOAD,
	USER_VIEW,
	USER_MODIFY,
	
	
	LIKE,
	COMMENT

}