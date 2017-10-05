package com.ab.spring.dao;

import java.util.List;

import com.ab.vo.certificate.Certificate;

public interface CertificateDao {
	Certificate saveCertificate(Certificate certificate);

	void updateCertificate(Certificate certificate);

	Certificate getCertificate(Long certificateId);
	
	List<Certificate> getCandidateCertificates(Long candidateId);
	
	List<Certificate> getIssuerCertificates(Long issuerId);
	
	void removeCertificate(Long certificateId);
}
