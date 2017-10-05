package com.ab.spring.dao;

import com.ab.vo.issuer.IssuerDetail;

public interface IssuerDetailDao {

	public IssuerDetail saveIssuerDetail(IssuerDetail issuerDetail);
	public void updateIssuerDetail(IssuerDetail issuerDetail);
	public IssuerDetail getIssuerDetail(Long issuerDetailId);
	public IssuerDetail getIssuerDetailByIssuerId(Long issuerId);
	public void removeIssuerDetail(Long issuerDetailId);
}
