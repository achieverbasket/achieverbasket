package com.ab.vo.certificate;

public enum CertificateType {
	ACADEMIC(1),
	EXTRA_CURICULAM(2),
	OTHER(9);
	
	private int CertificateTypeId;
	private CertificateType(int CertificateTypeId) {
		this.CertificateTypeId = CertificateTypeId;
	}
	
	public static CertificateType fromId(int CertificateTypeId) {
		for (CertificateType certificateType : CertificateType.values()) {
			if(certificateType.CertificateTypeId == CertificateTypeId) {
				return certificateType;
			}
		}
		return CertificateType.OTHER;
	}

	public int getCertificateTypeId() {
		return CertificateTypeId;
	}
}
