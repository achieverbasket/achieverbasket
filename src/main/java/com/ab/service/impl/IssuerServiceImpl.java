package com.ab.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.dao.CertificateDao;
import com.ab.dao.IssuerDao;
import com.ab.dao.IssuerDetailDao;
import com.ab.vo.issuer.Issuer;

@Service
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
	
	List<Issuer> getIssuerList() {
		return issuerDao.getIssuerList();
	}
	
	void removeIssuer(Long issuerId) {
		issuerDao.removeIssuer(issuerId);
	}


}
