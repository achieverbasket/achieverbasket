package com.ab.service;

import com.ab.vo.issuer.Issuer;

public interface IssuerService {
	
	public Issuer saveIssuer(Issuer issuer);

	public void updateIssuer(Issuer issuer);
	
	public Issuer getIssuer(Long issuerId);
	
	public void removeIssuer(Long issuerId);
	 
	public long getIssuerId(Long userId);

}
