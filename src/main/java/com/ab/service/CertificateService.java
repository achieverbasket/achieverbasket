package com.ab.service;

import com.ab.vo.certificate.Certificate;

public interface CertificateService {
	
	public Certificate getCertificate(long certificateId);
	public boolean saveCertificate(Certificate certificate);
	public boolean uploadCertificate(Certificate certificate);

}
