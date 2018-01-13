package com.ab.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.dao.CandidateDao;
import com.ab.dao.CertificateDao;
import com.ab.dao.CertificateTemplateDao;
import com.ab.dao.IssuerDao;
import com.ab.dao.IssuerDetailDao;
import com.ab.dao.impl.SequenceDao;
import com.ab.service.IssuerService;
import com.ab.type.CandidateType;
import com.ab.type.VerificationStatusType;
import com.ab.vo.Address;
import com.ab.vo.activity.SocialActivity;
import com.ab.vo.activity.SocialActivityType;
import com.ab.vo.candidate.Candidate;
import com.ab.vo.candidate.CandidatePersonalDetail;
import com.ab.vo.certificate.BulkCertificate;
import com.ab.vo.certificate.Certificate;
import com.ab.vo.certificate.CertificateTemplate;
import com.ab.vo.issuer.Issuer;
import com.ab.vo.preference.PreferenceStatusType;

@Service
public class IssuerServiceImpl implements IssuerService{
	
	@Autowired
	IssuerDao issuerDao;

	@Autowired
	IssuerDetailDao issuerDetailDao;
	
	@Autowired
	CandidateDao candidateDao;
	
	@Autowired
	CertificateDao certificateDao;
	
	@Autowired
	CertificateTemplateDao certificateTemplateDao;
	
	@Autowired
	private SequenceDao sequenceDao;

	@Override
	public Issuer saveIssuer(Issuer issuer) {
		
		issuerDetailDao.saveIssuerDetail(issuer.getIssuerDetail());
		return issuerDao.saveIssuer(issuer);
	}

	@Override
	public void updateIssuer(Issuer issuer) {
		issuerDetailDao.updateIssuerDetail(issuer.getIssuerDetail());
		issuerDao.updateIssuer(issuer);
		
	}
	
	@Override
	public Issuer getIssuer(Long issuerId) {
		return issuerDao.getIssuer(issuerId);
	}
	
	@Override
	public List<Issuer> getIssuerList() {
		return issuerDao.getIssuerList();
	}
	
	@Override
	public void removeIssuer(Long issuerId) {
		issuerDao.removeIssuer(issuerId);
	}

	@Override
	public long getIssuerId(Long userId) {
		return issuerDao.getIssuerId(userId);
	}
	
	@Override
	public void saveBulkCertificate(List<BulkCertificate> bulkCertificateList) {
		
		for(BulkCertificate bulkCertificate: bulkCertificateList) {
			certificateDao.saveCertificate(bulkCertificateToCertificateConverter(bulkCertificate));
		}
	}
	
	private Certificate bulkCertificateToCertificateConverter(BulkCertificate bulkCertificate) {
		
		System.out.println("in bulkCertificateToCertificateConverter: "+bulkCertificate);
		Certificate certificate = new Certificate();
		
		if(null==bulkCertificate.getCandidateId()) {
			bulkCertificate.setCandidateId(candidateDao.getCandidateIdByEmailOrMobile(bulkCertificate.getCandidateEmail(), bulkCertificate.getCandidateMobileNumber()));
		}
		if(null==bulkCertificate.getCandidateId()) {//if candidate does not exist in system then create one
			Candidate candidate = getCandidate(bulkCertificate);
			candidateDao.saveCandidate(candidate);
			bulkCertificate.setCandidateId(candidate.getCandidateId());
		}
		
		certificate.setCandidateId(bulkCertificate.getCandidateId());
		certificate.setCertificateName(bulkCertificate.getCertificateName());
		
		CertificateTemplate certificateTemplate =certificateTemplateDao.getCertificateTemplate(bulkCertificate.getCertificateTemplateId());
		
		certificate.setCertificateTemplate(certificateTemplate);
		certificate.setCertificateType(certificateTemplate.getCertificateType());
		certificate.setEndDate(bulkCertificate.getCertificateEndDate());
		certificate.setIssueDate(bulkCertificate.getCertifcateIssueDate());
		
		Issuer issuer = new Issuer();
		issuer.setIssuerId(bulkCertificate.getIssuerId());
		
		certificate.setIssuerId(bulkCertificate.getIssuerId());
		certificate.setIssuer(issuer);
		certificate.setVerificationDate(bulkCertificate.getCertifcateIssueDate());
		certificate.setVerifiedBy(bulkCertificate.getIssuerId());
		certificate.setVerificationStatusType(VerificationStatusType.VERIFIED);
		return certificate;
	}
	
	private Candidate getCandidate(BulkCertificate bulkCertificate) {
		Candidate candidate = new Candidate();
//		Long candidateId = sequenceDao.getNextVal("CANDIDATE_SEQ");
//		Long candidatePersonalDetailId = sequenceDao.getNextVal("CANDIDATE_PERSONAL_DETAIL_SEQ");
		
		//candidate.setCandidateId(candidateId);
		candidate.setCandidateName(bulkCertificate.getCandidateName());
		candidate.setCandidateType(CandidateType.CANDIDATE);
		
		candidate.setPreferenceStatusType(PreferenceStatusType.PUBLIC);
		candidate.setSocialActivity(new SocialActivity(SocialActivityType.PROFILE));
		
		CandidatePersonalDetail candidatePersonalDetail = new CandidatePersonalDetail();
		//candidatePersonalDetail.setCandidateId(candidateId);
		candidatePersonalDetail.setFirstName(bulkCertificate.getCandidateName());
		candidatePersonalDetail.setLastName(bulkCertificate.getCandidateName());
		//candidatePersonalDetail.setCandidatePersonalDetailId(candidatePersonalDetailId);
		candidatePersonalDetail.setEmail(bulkCertificate.getCandidateEmail());
		candidatePersonalDetail.setMobileNumber(bulkCertificate.getCandidateMobileNumber());
		candidatePersonalDetail.setAddress(new Address());
		
		candidate.setCandidatePersonalDetail(candidatePersonalDetail);
		
		return candidate;
	}


}
