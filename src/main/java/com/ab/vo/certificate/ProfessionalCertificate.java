package com.ab.vo.certificate;

import org.springframework.web.multipart.MultipartFile;

import com.ab.vo.certificate.score.Score;
import com.ab.vo.issuer.Issuer;
import com.ab.vo.issuer.organization.Organization;


public class ProfessionalCertificate extends Certificate {

	private static final long serialVersionUID = -8379574349413515616L;
	private Organization organization;
	private MultipartFile file;
	
	private Double salary;

	public Score getScore() {
		throw new UnsupportedOperationException("Scoring is not supported for Professional Certificates.");
	}

	public Issuer getIssuer() {
		return organization;
	}
	
	
	
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Organization getOrganization() {
		return organization;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
