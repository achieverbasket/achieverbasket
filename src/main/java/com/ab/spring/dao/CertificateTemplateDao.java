package com.ab.spring.dao;

import com.ab.vo.certificate.CertificateTemplate;

public interface CertificateTemplateDao {
	
	CertificateTemplate saveCertificateTemplate(CertificateTemplate certificateTemplate);
	
	void updateCertificateTemplate(CertificateTemplate certificateTemplate);

	CertificateTemplate getCertificateTemplate(Long certificateTemplateId);

	void removeCertificateTemplate(Long certificateTemplateId);
}
