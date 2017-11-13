package com.ab.dao;

import java.util.List;

import com.ab.vo.certificate.Certificate;

public interface CertificateDao {
	Certificate saveCertificate(Certificate certificate);

	void updateCertificate(Certificate certificate);

	Certificate getCertificate(Long certificateId);
	
	List<Certificate> getCandidateCertificates(Long candidateId);
	
	List<Certificate> getIssuerCertificates(Long issuerId);
	
	boolean deleteCertificate(Long certificateId);
	
	public boolean deleteCertificatesForCandidate(long candidateId);
	
	public boolean deleteCertificatesForIssuer(long issuerId);

}
