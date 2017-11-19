package com.ab.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.dao.CertificateDao;
import com.ab.service.CertificateService;
import com.ab.vo.certificate.Certificate;

@Service
public class CertificateServiceImpl implements CertificateService{
	
	@Autowired
	CertificateDao certificateDaoImpl;
	
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
	
}
