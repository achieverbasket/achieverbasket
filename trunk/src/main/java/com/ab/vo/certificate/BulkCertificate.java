package com.ab.vo.certificate;

public class BulkCertificate {
	
	private Long issuerId;
	private Long candidateId;
	private Long certificateTemplateId;
	private String certificateName;
	private String candidateFirstName;
	private String candidateLastName;
	private String candidateEmail;
	private Long candidateMobileNumber;
	private String certifcateIssueDate;
	private String certificateEndDate;
	
	
	public Long getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(Long issuerId) {
		this.issuerId = issuerId;
	}
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	public Long getCertificateTemplateId() {
		return certificateTemplateId;
	}
	public void setCertificateTemplateId(Long certificateTemplateId) {
		this.certificateTemplateId = certificateTemplateId;
	}
	public String getCertificateName() {
		return certificateName;
	}
	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}
	public String getCandidateFirstName() {
		return candidateFirstName;
	}
	public void setCandidateFirstName(String candidateFirstName) {
		this.candidateFirstName = candidateFirstName;
	}
	public String getCandidateLastName() {
		return candidateLastName;
	}
	public void setCandidateLastName(String candidateLastName) {
		this.candidateLastName = candidateLastName;
	}
	public String getCandidateEmail() {
		return candidateEmail;
	}
	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}
	public Long getCandidateMobileNumber() {
		return candidateMobileNumber;
	}
	public void setCandidateMobileNumber(Long candidateMobileNumber) {
		this.candidateMobileNumber = candidateMobileNumber;
	}
	public String getCertifcateIssueDate() {
		return certifcateIssueDate;
	}
	public void setCertifcateIssueDate(String certifcateIssueDate) {
		this.certifcateIssueDate = certifcateIssueDate;
	}
	public String getCertificateEndDate() {
		return certificateEndDate;
	}
	public void setCertificateEndDate(String certificateEndDate) {
		this.certificateEndDate = certificateEndDate;
	}

	
	@Override
	public String toString() {
		return "issuerId: "+issuerId+" candidateId: "+candidateId+" certificateTemplateId: "+certificateTemplateId+" certificateName: "+certificateName+" candidateEmail: "+
	candidateEmail+" candidateMobileNumber: "+candidateMobileNumber+" certifcateIssueDate: "+certifcateIssueDate+" certificateEndDate: "+certificateEndDate;
	}

}
