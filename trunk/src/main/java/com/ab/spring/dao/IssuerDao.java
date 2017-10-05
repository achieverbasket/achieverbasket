package com.ab.spring.dao;

import com.ab.vo.issuer.Issuer;

public interface IssuerDao {
	Issuer saveIssuer(Issuer issuer);

	void updateIssuer(Issuer issuer);
	
	Issuer getIssuer(Long issuerId);
	
	void removeIssuer(Long issuerId);
}
