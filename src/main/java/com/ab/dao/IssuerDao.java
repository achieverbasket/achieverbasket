package com.ab.dao;

import java.util.List;

import com.ab.vo.candidate.Candidate;
import com.ab.vo.issuer.Issuer;

public interface IssuerDao {
	Issuer saveIssuer(Issuer issuer);

	void updateIssuer(Issuer issuer);
	
	Issuer getIssuer(Long issuerId);
	
	void removeIssuer(Long issuerId);
	
	public List<Issuer> getIssuerList();
	
	public List<Issuer> getIssuerAutoComplete(String data);	
	public long getIssuerId(Long userId);
	
	List<Issuer> getIssuerListByActiveFlag(boolean isActive);

}
