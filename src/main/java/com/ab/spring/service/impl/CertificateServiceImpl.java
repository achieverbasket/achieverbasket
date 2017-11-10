package com.ab.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.spring.dao.CertificateDao;
import com.ab.spring.service.CertificateService;
import com.ab.vo.certificate.Certificate;

@Service
public class CertificateServiceImpl implements CertificateService{
	
	@Autowired
	CertificateDao certificateDaoImpl;
	
	@Override
	public Certificate getCertificate(long certificateId)
	{
		return null;
	}
	
	@Override
	public boolean saveCertificate(Certificate certificate)
	{
		certificateDaoImpl.saveCertificate(certificate);
		return true;
	}
	
	@Override
	public boolean uploadCertificate(Certificate certificate)
	{
		saveCertificate(certificate);
		return true;
	}

}