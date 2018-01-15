package com.ab.vo.candidate;

import java.util.List;

import com.ab.type.CandidateType;
import com.ab.vo.User;
import com.ab.vo.activity.SocialActivity;
import com.ab.vo.activity.SocialActivityType;
import com.ab.vo.certificate.Certificate;
import com.ab.vo.preference.PreferenceStatusType;

public class Candidate{
	private Long candidateId;
	private String candidateName;
	private CandidateType candidateType;
	private boolean isActive;
	private CandidatePersonalDetail candidatePersonalDetail;
	private PreferenceStatusType preferenceStatusType;
	private SocialActivity socialActivity;
	private List<Certificate> candidateCertificateList;

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public CandidateType getCandidateType() {
		return candidateType;
	}

	public void setCandidateType(CandidateType candidateType) {
		this.candidateType = candidateType;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public CandidatePersonalDetail getCandidatePersonalDetail() {
		return candidatePersonalDetail;
	}

	public void setCandidatePersonalDetail(CandidatePersonalDetail candidatePersonalDetail) {
		this.candidatePersonalDetail = candidatePersonalDetail;
	}

	public SocialActivity getSocialActivity() {
		return socialActivity;
	}

	public void setSocialActivity(SocialActivity socialActivity) {
		this.socialActivity = socialActivity;
	}

	public List<Certificate> getCandidateCertificateList() {
		return candidateCertificateList;
	}

	public void setCandidateCertificateList(List<Certificate> candidateCertificateList) {
		this.candidateCertificateList = candidateCertificateList;
	}

	public PreferenceStatusType getPreferenceStatusType() {
		return preferenceStatusType;
	}

	public void setPreferenceStatusType(PreferenceStatusType preferenceStatusType) {
		this.preferenceStatusType = preferenceStatusType;
	}
	
	public static Candidate from(User user) {
		Candidate candidate = new Candidate();
		candidate.setCandidateName(user.getFirstName());
		candidate.setCandidateType(CandidateType.CANDIDATE);
		candidate.setActive(true);//All Register user are active
		candidate.setCandidatePersonalDetail(CandidatePersonalDetail.from(user));
		candidate.setPreferenceStatusType(PreferenceStatusType.PUBLIC);
		candidate.setSocialActivity(new SocialActivity(SocialActivityType.PROFILE));
		return candidate;
	}
}
