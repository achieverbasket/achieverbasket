package com.ab.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.ab.dao.CertificateDao;
import com.ab.dao.IssuerDao;
import com.ab.dao.IssuerDetailDao;
import com.ab.vo.issuer.Issuer;

public class IssuerServiceImpl {
	
	@Autowired
	IssuerDao issuerDao;

	@Autowired
	IssuerDetailDao issuerDetailDao;

	
	Issuer saveIssuer(Issuer issuer) {
		
		issuerDetailDao.saveIssuerDetail(issuer.getIssuerDetail());
		return issuerDao.saveIssuer(issuer);
	}

	void updateIssuer(Issuer issuer) {
		issuerDetailDao.updateIssuerDetail(issuer.getIssuerDetail());
		issuerDao.updateIssuer(issuer);
		
	}
	
	Issuer getIssuer(Long issuerId) {
		return issuerDao.getIssuer(issuerId);
	}
	
	void removeIssuer(Long issuerId) {
		issuerDao.removeIssuer(issuerId);
	}


}
