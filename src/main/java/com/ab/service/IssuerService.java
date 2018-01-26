package com.ab.service;

import java.util.List;

import com.ab.vo.certificate.BulkCertificate;
import com.ab.vo.issuer.Issuer;

public interface IssuerService {
	
	public Issuer saveIssuer(Issuer issuer);

	public void updateIssuer(Issuer issuer);
	
	public Issuer getIssuer(Long issuerId);
	
	public List<Issuer> getIssuerList();
	
	public void removeIssuer(Long issuerId);
	 
	public long getIssuerId(Long userId);
	
	public void saveBulkCertificateList(List<BulkCertificate> bulkCertificateList);
	
	public void saveBulkCertificate(BulkCertificate bulkCertificate);
	
	public List<Issuer> getIssuerListByActiveFlag(boolean isActive);

}
