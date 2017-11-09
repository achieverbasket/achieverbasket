package com.ab.vo.certificate;

import org.springframework.web.multipart.MultipartFile;

import com.ab.vo.certificate.score.AcademicScore;
import com.ab.vo.certificate.score.Score;
import com.ab.vo.issuer.Issuer;
import com.ab.vo.issuer.institute.AcademicInstitute;

public class AcademicCertificate extends Certificate {
		
	private static final long serialVersionUID = 4869037164698035618L;
	private AcademicScore academicScore;
	private AcademicInstitute academicInstitute;
//	private MultipartFile file;


	public Score getScore() {
		return academicScore;
	}

	public void setScore(AcademicScore academicScore) {
		this.academicScore = academicScore;
	}

	public Issuer getIssuer() {
		return academicInstitute;
	}

	public void setIssuer(AcademicInstitute academicInstitute) {
		this.academicInstitute = academicInstitute;
	}

//	public MultipartFile getFile() {
//		return file;
//	}
//
//	public void setFile(MultipartFile file) {
//		this.file = file;
//	}
}
