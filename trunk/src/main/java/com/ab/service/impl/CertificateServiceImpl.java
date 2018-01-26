package com.ab.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.dao.CandidateDao;
import com.ab.dao.CertificateDao;
import com.ab.dao.CertificateTemplateDao;
import com.ab.service.CertificateService;
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
public class CertificateServiceImpl implements CertificateService{
	
	@Autowired
	CertificateDao certificateDaoImpl;
	
	@Autowired
	CertificateTemplateDao certificateTemplateDao;
	
	@Autowired
	CandidateDao candidateDao;
	
	@Override
	public Certificate getCertificate(long certificateId)
	{
		return certificateDaoImpl.getCertificate(certificateId);
	}
	
	@Override
	public List<Certificate> getCertificatesForCandidate(long candidateId)
	{
		return certificateDaoImpl.getCandidateCertificates(candidateId);
	}
	
	@Override
	public List<Certificate> getCertificatesForIssuer(long issuerId)
	{
		return certificateDaoImpl.getIssuerCertificates(issuerId);
	}

	
	@Override
	public boolean saveCertificate(Certificate certificate)
	{
		certificateDaoImpl.saveCertificate(certificate);
		return true;
	}

	@Override
	public boolean updateCertificate(Certificate certificate)
	{
		certificateDaoImpl.updateCertificate(certificate);
		return true;
	}
	
	@Override
	public boolean deleteCertificate(long certificateId)
	{
		return certificateDaoImpl.deleteCertificate(certificateId);
	}
	
	@Override
	public boolean deleteCertificatesForCandidate(long candidateId)
	{
		return certificateDaoImpl.deleteCertificatesForCandidate(candidateId);
	}
	
	@Override
	public boolean deleteCertificatesForIssuer(long issuerId)
	{
		return certificateDaoImpl.deleteCertificatesForIssuer(issuerId);
	}
	
	@Override
	public VerificationStatusType getCertificateVerificationStatusType(Long certificateId)
	{
		return certificateDaoImpl.getCertificateVerificationStatusType(certificateId);
	}
	
	@Override
	public boolean updateCertificateVerificationStatus(Long certificateId, VerificationStatusType verificationStatusType)
	{
		return certificateDaoImpl.updateCertificateVerificationStatus(certificateId, verificationStatusType);
	}
	
	@Override
	public boolean saveBulkCertificate(BulkCertificate bulkCertificate)
	{
		certificateDaoImpl.saveCertificate(bulkCertificateToCertificateConverter(bulkCertificate));
		return true;
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
		certificate.setTemplateBased(true);
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
		candidate.setCandidateName(bulkCertificate.getCandidateFirstName());
		candidate.setCandidateType(CandidateType.CANDIDATE);
		candidate.setActive(false);//As this is Issuer generated candidate, its not active yet.
		
		candidate.setPreferenceStatusType(PreferenceStatusType.PUBLIC);
		candidate.setSocialActivity(new SocialActivity(SocialActivityType.PROFILE));
		
		CandidatePersonalDetail candidatePersonalDetail = new CandidatePersonalDetail();
		candidatePersonalDetail.setFirstName(bulkCertificate.getCandidateFirstName());
		candidatePersonalDetail.setLastName(bulkCertificate.getCandidateLastName());
		candidatePersonalDetail.setEmail(bulkCertificate.getCandidateEmail());
		candidatePersonalDetail.setMobileNumber(bulkCertificate.getCandidateMobileNumber());
		candidatePersonalDetail.setAddress(new Address());
		
		candidate.setCandidatePersonalDetail(candidatePersonalDetail);
		
		return candidate;
	}
	
}
