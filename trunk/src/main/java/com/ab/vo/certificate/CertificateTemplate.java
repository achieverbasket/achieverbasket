package com.ab.vo.certificate;

import java.sql.Blob;

public class CertificateTemplate {
	
	private Long certificateTemplateId;
	private String templateName;
	private String certificateDesc;
	private CertificateType certificateType;
	private Long issuerId;
	private Blob scannedCopy;
	private String certificateTemplateXml;

	public Long getCertificateTemplateId() {
		return certificateTemplateId;
	}

	public void setCertificateTemplateId(Long certificateTemplateId) {
		this.certificateTemplateId = certificateTemplateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getCertificateDesc() {
		return certificateDesc;
	}

	public void setCertificateDesc(String certificateDesc) {
		this.certificateDesc = certificateDesc;
	}

	public CertificateType getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(CertificateType certificateType) {
		this.certificateType = certificateType;
	}

	public Long getIssuerId() {
		return issuerId;
	}

	public void setIssuerId(Long issuerId) {
		this.issuerId = issuerId;
	}

	public Blob getScannedCopy() {
		return scannedCopy;
	}

	public void setScannedCopy(Blob scannedCopy) {
		this.scannedCopy = scannedCopy;
	}

	public String getCertificateTemplateXml() {
		return certificateTemplateXml;
	}

	public void setCertificateTemplateXml(String certificateTemplateXml) {
		this.certificateTemplateXml = certificateTemplateXml;
	}
}
