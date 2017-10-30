package com.ab.vo.certificate;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

import com.ab.vo.activity.SocialActivity;
import com.ab.vo.preference.PreferenceStatusType;

public class Certificate implements Serializable{

	private static final long serialVersionUID = 1538383346294300998L;
	private	Long certificateId;
	private Long issuerId;
	private Long candidateId;
	private String certificateName;
	private PreferenceStatusType preferenceStatusType;
	private String issueDate;
	private String endDate;
	private CertificateTemplate certificateTemplate;
	private String filePath;
	private boolean verified;
	private Long verifiedBy;
	private DateTime verificationDate;
	private SocialActivity socialActivity;

	public Long getCertificateId() {
		return certificateId;
	}

	public void setCertificateId(Long certificateId) {
		this.certificateId = certificateId;
	}

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

	public String getCertificateName() {
		return certificateName;
	}

	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public CertificateTemplate getCertificateTemplate() {
		return certificateTemplate;
	}

	public void setCertificateTemplate(CertificateTemplate certificateTemplate) {
		this.certificateTemplate = certificateTemplate;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public Long getVerifiedBy() {
		return verifiedBy;
	}

	public void setVerifiedBy(Long verifiedBy) {
		this.verifiedBy = verifiedBy;
	}

	public DateTime getVerificationDate() {
		return verificationDate;
	}

	public void setVerificationDate(DateTime verificationDate) {
		this.verificationDate = verificationDate;
	}

	public SocialActivity getSocialActivity() {
		return socialActivity;
	}

	public void setSocialActivity(SocialActivity socialActivity) {
		this.socialActivity = socialActivity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PreferenceStatusType getPreferenceStatusType() {
		return preferenceStatusType;
	}

	public void setPreferenceStatusType(PreferenceStatusType preferenceStatusType) {
		this.preferenceStatusType = preferenceStatusType;
	}
}
