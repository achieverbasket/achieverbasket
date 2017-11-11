package com.ab.vo.certificate;

import com.ab.vo.certificate.score.AcademicScore;
import com.ab.vo.certificate.score.Score;
import com.ab.vo.issuer.institute.AcademicInstitute;

public class AcademicCertificate extends Certificate {
		
	private static final long serialVersionUID = 4869037164698035618L;
	private AcademicScore academicScore;
	private AcademicInstitute academicInstitute;


	public Score getScore() {
		return academicScore;
	}

	public void setScore(AcademicScore academicScore) {
		this.academicScore = academicScore;
	}

	/*public Issuer getIssuer() {
		return academicInstitute;
	}*/

	/*public void setIssuer(AcademicInstitute academicInstitute) {
		this.academicInstitute = academicInstitute;

	}*/
}
