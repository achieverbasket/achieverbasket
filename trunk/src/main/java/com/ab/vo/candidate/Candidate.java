package com.ab.vo.candidate;

import java.util.List;

import com.ab.type.CandidateType;
import com.ab.vo.activity.SocialActivity;
import com.ab.vo.certificate.Certificate;
import com.ab.vo.preference.Preference;
import com.ab.vo.preference.PreferenceStatusType;

public class Candidate{
	private Long candidateId;
	private String candidateName;
	private CandidateType candidateType;
	private CandidatePersonalDetail candidatePersonalDetail;
	private PreferenceStatusType preferenceStatusType;
	private Preference preference;
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

	public CandidatePersonalDetail getCandidatePersonalDetail() {
		return candidatePersonalDetail;
	}

	public void setCandidatePersonalDetail(CandidatePersonalDetail candidatePersonalDetail) {
		this.candidatePersonalDetail = candidatePersonalDetail;
	}

	public Preference getPreference() {
		return preference;
	}

	public void setPreference(Preference preference) {
		this.preference = preference;
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
}
