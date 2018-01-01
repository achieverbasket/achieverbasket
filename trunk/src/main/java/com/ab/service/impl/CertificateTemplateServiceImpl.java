package com.ab.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.dao.CertificateTemplateDao;
import com.ab.service.CertificateTemplateService;
import com.ab.type.CertificateType;
import com.ab.vo.certificate.CertificateTemplate;

@Service
public class CertificateTemplateServiceImpl implements CertificateTemplateService {
	
	@Autowired
	CertificateTemplateDao certificateTemplateDao;
	
	public CertificateTemplate saveCertificateTemplate(CertificateTemplate certificateTemplate) {
		return certificateTemplateDao.saveCertificateTemplate(certificateTemplate);
	}
	
	public void updateCertificateTemplate(CertificateTemplate certificateTemplate) {
		certificateTemplateDao.updateCertificateTemplate(certificateTemplate);
	}

	public CertificateTemplate getCertificateTemplate(Long certificateTemplateId) {
		return certificateTemplateDao.getCertificateTemplate(certificateTemplateId);
	}
	
	public List<CertificateTemplate> getCertificateTemplateList(Long issuerId, CertificateType certificateType) {
		
		if(issuerId==null) {
			issuerId = 99999l;//Default issuer Id for available templates to everyone
		}
		return certificateTemplateDao.getCertificateTemplateList(issuerId, certificateType);
	}

	public void removeCertificateTemplate(Long certificateTemplateId) {
		certificateTemplateDao.removeCertificateTemplate(certificateTemplateId);
	}

}
