package com.ab.service;

import java.util.List;

import com.ab.type.CertificateType;
import com.ab.vo.certificate.CertificateTemplate;

public interface CertificateTemplateService {
	
	CertificateTemplate saveCertificateTemplate(CertificateTemplate certificateTemplate);
	
	void updateCertificateTemplate(CertificateTemplate certificateTemplate);

	CertificateTemplate getCertificateTemplate(Long certificateTemplateId);
	
	List<CertificateTemplate> getCertificateTemplateList(Long issuerId, CertificateType certificateType);

	void removeCertificateTemplate(Long certificateTemplateId);

}
