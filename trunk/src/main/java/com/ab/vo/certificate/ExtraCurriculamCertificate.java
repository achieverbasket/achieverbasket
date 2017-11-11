package com.ab.vo.certificate;

import com.ab.vo.certificate.score.ExtraCurricularScore;
import com.ab.vo.certificate.score.Score;
import com.ab.vo.issuer.institute.ExtraCurriculamInstitute;

public class ExtraCurriculamCertificate extends Certificate {
	private static final long serialVersionUID = 4869037164698035618L;
	private ExtraCurricularScore extraCurricularScore;
	private ExtraCurriculamInstitute extraCurriculamInstitute;
	
	
	public Score getScore() {
		return extraCurricularScore;
	}

	/*public Issuer getIssuer() {
		return extraCurriculamInstitute;
	}*/

}
