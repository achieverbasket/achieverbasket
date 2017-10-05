package com.ab.vo.issuer;

import java.util.List;

import com.ab.type.IssuerType;
import com.ab.vo.activity.SocialActivity;
import com.ab.vo.certificate.Certificate;
import com.ab.vo.preference.PreferenceStatusType;

public class Issuer {

	private Long issuerId;
	private String issuerName;
	private IssuerType issuerType;
	private IssuerDetail issuerDetail;
	private IssuerRanking issuerRanking;
	private PreferenceStatusType preferenceStatusType;
	private SocialActivity socialActivity;
	private List<Certificate> issuerCertificateList;
	
	public Long getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(Long issuerId) {
		this.issuerId = issuerId;
	}
	public String getIssuerName() {
		return issuerName;
	}
	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}
	public IssuerType getIssuerType() {
		return issuerType;
	}
	public void setIssuerType(IssuerType issuerType) {
		this.issuerType = issuerType;
	}
	public IssuerDetail getIssuerDetail() {
		return issuerDetail;
	}
	public void setIssuerDetail(IssuerDetail issuerDetail) {
		this.issuerDetail = issuerDetail;
	}
	public IssuerRanking getIssuerRanking() {
		return issuerRanking;
	}
	public void setIssuerRanking(IssuerRanking issuerRanking) {
		this.issuerRanking = issuerRanking;
	}
	public PreferenceStatusType getPreferenceStatusType() {
		return preferenceStatusType;
	}
	public void setPreferenceStatusType(PreferenceStatusType preferenceStatusType) {
		this.preferenceStatusType = preferenceStatusType;
	}
	public SocialActivity getSocialActivity() {
		return socialActivity;
	}
	public void setSocialActivity(SocialActivity socialActivity) {
		this.socialActivity = socialActivity;
	}
	public List<Certificate> getIssuerCertificateList() {
		return issuerCertificateList;
	}
	public void setIssuerCertificateList(List<Certificate> issuerCertificateList) {
		this.issuerCertificateList = issuerCertificateList;
	}
	
}
