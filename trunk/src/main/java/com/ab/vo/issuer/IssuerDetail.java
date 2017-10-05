package com.ab.vo.issuer;

import org.joda.time.DateTime;

import com.ab.vo.Address;

public class IssuerDetail {

	private Long issuerDetailId;
	private Long issuerId;
	private DateTime issuerInceptionDate;
	private DateTime issuerEndDate;
	private Address address;
	private String emailId;
	
	public Long getIssuerDetailId() {
		return issuerDetailId;
	}
	public void setIssuerDetailId(Long issuerDetailId) {
		this.issuerDetailId = issuerDetailId;
	}
	public Long getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(Long issuerId) {
		this.issuerId = issuerId;
	}
	public DateTime getIssuerInceptionDate() {
		return issuerInceptionDate;
	}
	public void setIssuerInceptionDate(DateTime issuerInceptionDate) {
		this.issuerInceptionDate = issuerInceptionDate;
	}
	public DateTime getIssuerEndDate() {
		return issuerEndDate;
	}
	public void setIssuerEndDate(DateTime issuerEndDate) {
		this.issuerEndDate = issuerEndDate;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	
}
