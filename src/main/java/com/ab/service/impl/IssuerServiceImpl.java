package com.ab.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.dao.CertificateDao;
import com.ab.dao.IssuerDao;
import com.ab.dao.IssuerDetailDao;
import com.ab.service.IssuerService;
import com.ab.vo.issuer.Issuer;

@Service
public class IssuerServiceImpl implements IssuerService{
	
	@Autowired
	IssuerDao issuerDao;

	@Autowired
	IssuerDetailDao issuerDetailDao;

	
	public Issuer saveIssuer(Issuer issuer) {
		
		issuerDetailDao.saveIssuerDetail(issuer.getIssuerDetail());
		return issuerDao.saveIssuer(issuer);
	}

	public void updateIssuer(Issuer issuer) {
		issuerDetailDao.updateIssuerDetail(issuer.getIssuerDetail());
		issuerDao.updateIssuer(issuer);
		
	}
	
	public Issuer getIssuer(Long issuerId) {
		return issuerDao.getIssuer(issuerId);
	}
	
	public List<Issuer> getIssuerList() {
		return issuerDao.getIssuerList();
	}
	
	public void removeIssuer(Long issuerId) {
		issuerDao.removeIssuer(issuerId);
	}

	@Override
	public long getIssuerId(Long userId) {
		return issuerDao.getIssuerId(userId);
	}


}
