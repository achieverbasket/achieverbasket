package com.ab.service;

import com.ab.vo.issuer.Issuer;

public interface IssuerService {
	
	Issuer saveIssuer(Issuer issuer);

	void updateIssuer(Issuer issuer);
	
	Issuer getIssuer(Long issuerId);
	
	void removeIssuer(Long issuerId);

}
