package com.ab.service;

import java.util.List;

import com.ab.type.VerificationStatusType;
import com.ab.vo.certificate.BulkCertificate;
import com.ab.vo.certificate.Certificate;

public interface CertificateService {
	
	
	public Certificate getCertificate(long certificateId);
	public List<Certificate> getCertificatesForCandidate(long candidateId);
	public List<Certificate> getCertificatesForIssuer(long issuerId);
	public boolean saveCertificate(Certificate certificate);
	public boolean saveBulkCertificate(BulkCertificate bulkCertificate);
	public boolean updateCertificate(Certificate certificate);
	public boolean deleteCertificate(long certificateId);
	public boolean deleteCertificatesForCandidate(long candidateId);
	public boolean deleteCertificatesForIssuer(long issuerId);
	public VerificationStatusType getCertificateVerificationStatusType(Long certificateId);
	public boolean updateCertificateVerificationStatus(Long certificateId, VerificationStatusType verificationStatusType);



}
