package com.ab.type;

import com.ab.constant.config.ApplicationPageConstant;

public enum CertificateType {
	ACADEMIC(1)
	{
		public String getWebPageLink()
		{
			return ApplicationPageConstant.academiccertificate_page;
		}
	},
	PROFESSIONAL(2)
	{
		public String getWebPageLink()
		{
			return ApplicationPageConstant.professionalcertificate_page;
		}
	},
	EXTRA_CURICULAM(3)
	{
		public String getWebPageLink()
		{
			return ApplicationPageConstant.extracurriculamcertificate_page;
		}
	},
	OTHER(99)
	{
		public String getWebPageLink()
		{
			return "";
		}
	};
	
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
	
	public abstract String getWebPageLink();
}
