package com.ab.vo.issuer.organization;

import java.io.Serializable;

import com.ab.vo.Address;
import com.ab.vo.issuer.Issuer;

public class Organization extends Issuer implements Serializable{
	private static final long serialVersionUID = -5624256941166368074L;
	private int organizationId;
	private String organizationName;
	private Address organizationAddress;
	private OrganizationType organizationType;
	
	private byte[] organizationLogo; // this should be a path else entire login required at side to convert to image using base64
	private String imagePath; // using this for now

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public Address getOrganizationAddress() {
		return organizationAddress;
	}

	public void setOrganizationAddress(Address organizationAddress) {
		this.organizationAddress = organizationAddress;
	}

	public OrganizationType getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(OrganizationType organizationType) {
		this.organizationType = organizationType;
	}

	public byte[] getOrganizationLogo() {
		return organizationLogo;
	}

	public void setOrganizationLogo(byte[] organizationLogo) {
		this.organizationLogo = organizationLogo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
