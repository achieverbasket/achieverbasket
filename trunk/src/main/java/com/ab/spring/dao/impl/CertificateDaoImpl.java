package com.ab.spring.dao.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.ab.spring.dao.CertificateDao;
import com.ab.spring.dao.CertificateTemplateDao;
import com.ab.spring.dao.SocialActivityDao;
import com.ab.vo.activity.SocialActivity;
import com.ab.vo.activity.SocialActivityType;
import com.ab.vo.certificate.Certificate;

@Repository
public class CertificateDaoImpl implements CertificateDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SequenceDao sequenceDao;

	@Autowired
	private CertificateTemplateDao certificateTemplateDao;
	
	@Autowired 
	private SocialActivityDao socialActivityDao;
	
	private String certifcateLocation ="C:/Users/Sara/Google Drive/DigitalResume/certificates/candidates";
	
	@Override
	public Certificate saveCertificate(Certificate certificate) {
		String sql = "INSERT INTO CERTIFICATE (CERTIFICATE_ID, CERTIFICATE_NAME, CANDIDATE_ID, ISSUE_DATE, END_DATE, CERTIFICATE_TEMPLATE_ID, FILE_PATH, VERIFICATION_STATUS, "
				+ "VERIFIED_BY_ID, VERIFIED_DATE, SOCIAL_ACTIVITY_ID, CREATED_BY, CREATED_TIME) VALUES (?, ?, ?, SYSDATE(), ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE())";

		Long certificateId = sequenceDao.getNextVal("CERTIFICATE_SEQ");
		
		SocialActivity socialActivity = socialActivityDao.saveSocialActivity(new SocialActivity(SocialActivityType.CERTIFICATE));

		String filePath = saveCertificateInFile(certificate.getCandidateId(), certificateId, certificate.getCertificateFile());

		System.out.println("certificateId: "+certificateId+" filePath: "+filePath+" socialActivityid: "+socialActivity.getSocialActivityId());
		
		jdbcTemplate.update(sql, certificateId, 
				certificate.getCertificateName(),
				100,//certificate.getCandidateId(), 
				//null,
				null,
				//new DateTime(certificate.getIssueDate()).toDate().getTime(), 
				//new DateTime(certificate.getEndDate()).toDate().getTime(), 
				100,
				filePath,
				certificate.isVerified(),
				certificate.getVerifiedBy(),
				//new Date(certificate.getVerificationDate().toDate().getTime()),
				null,
				socialActivity.getSocialActivityId(),
				101);
		certificate.setCertificateId(certificateId);
		certificate.setSocialActivity(socialActivity);
		
		
		return certificate;
	}
	
	private String saveCertificateInFile(Long candidateId, Long certificateId, MultipartFile certificateFile)
	{
		String filePath = certifcateLocation+"/"+candidateId+"/"+certificateId+".pdf";
		//String filePath = certifcateLocation+"/test/test";
		File certificateFilePath = new File(filePath);
		try
		{
			if(!certificateFilePath.exists())
			{
				certificateFilePath.mkdirs();
			}
			
			System.out.println("filePath: "+filePath);
			System.out.println("file: "+certificateFile);
			certificateFile.transferTo(certificateFilePath);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filePath;
	}
	

	@Override
	public void updateCertificate(Certificate certificate) {
		String sql = "UPDATE CERTIFICATE SET CERTIFICATE_NAME=?, ISSUE_DATE=?, END_DATE=?, FILE_PATH=?, VERIFICATION_STATUS=?, VERIFIED_BY_ID=?, VERIFIED_DATE=?, MODIFIED_BY=0, MODIFIED_TIME) WHERE CERTIFICATE_ID=?";
		jdbcTemplate.update(sql, certificate.getCertificateName(), 
			//	new Date(certificate.getIssueDate().toDate().getTime()), 
				//new Date(certificate.getEndDate().toDate().getTime()), 
				certificate.getFilePath(),
				certificate.isVerified(),
				certificate.getVerifiedBy(),
				new Date(certificate.getVerificationDate().toDate().getTime()),
				certificate.getCertificateId());
	}

	@Override
	public Certificate getCertificate(Long certificateId) {
		String sql = "SELECT CERTIFICATE_NAME, CANDIDATE_ID, ISSUE_DATE, END_DATE, CERTIFICATE_TEMPLATE_ID, FILE_PATH, VERIFICATION_STATUS, VERIFIED_BY_ID, VERIFIED_DATE, SOCIAL_ACTIVITY_ID, CREATED_BY, CREATED_TIME, MODIFIED_BY, MODIFIED_TIME FROM CERTIFICATE WHERE CERTIFICATE_ID=?";
		return jdbcTemplate.query(sql, new Object[] {certificateId}, (ResultSetExtractor<Certificate>) rs -> {
				rs.next();
				Certificate certificate = new Certificate();
				certificate.setCertificateId(certificateId);
				certificate.setCertificateName(rs.getString("CERTIFICATE_NAME"));
				certificate.setCandidateId(rs.getLong("CANDIDATE_ID"));
				//certificate.setIssueDate(new DateTime(rs.getDate("ISSUE_DATE")));
				//certificate.setEndDate(new DateTime(rs.getDate("END_DATE")));
				//certificate.setCertificateTemplate(certificateTemplateDao.getCertificateTemplate(rs.getLong("CERTIFICATE_TEMPLATE_ID")));
				certificate.setFilePath(rs.getString("FILE_PATH"));
				certificate.setVerified(rs.getBoolean("VERIFICATION_STATUS"));
				certificate.setVerifiedBy(rs.getLong("VERIFIED_BY_ID"));
				certificate.setVerificationDate(new DateTime(rs.getDate("VERIFIED_DATE")));
				certificate.setSocialActivity(socialActivityDao.getSocialActivity(rs.getLong("SOCIAL_ACTIVITY_ID")));
				return certificate;
			});
	}

	@Override
	public List<Certificate> getCandidateCertificates(Long candidateId) {
		String sql = "SELECT CERTIFICATE_NAME, CERTIFICATE_ID, ISSUE_DATE, END_DATE, CERTIFICATE_TEMPLATE_ID, FILE_PATH, VERIFICATION_STATUS, VERIFIED_BY_ID, VERIFIED_DATE, SOCIAL_ACTIVITY_ID, CREATED_BY, CREATED_TIME, MODIFIED_BY, MODIFIED_TIME FROM CERTIFICATE WHERE CANDIDATE_ID=?";
		return jdbcTemplate.query(sql, new Object[] {candidateId}, (RowMapper<Certificate>) (rs, arg) -> {
				rs.next();
				Certificate certificate = new Certificate();
				certificate.setCandidateId(candidateId);
				certificate.setCertificateName(rs.getString("CERTIFICATE_NAME"));
				certificate.setCertificateId(rs.getLong("CERTIFICATE_ID"));
				//certificate.setIssueDate(new DateTime(rs.getDate("ISSUE_DATE")));
				//certificate.setEndDate(new DateTime(rs.getDate("END_DATE")));
				certificate.setCertificateTemplate(certificateTemplateDao.getCertificateTemplate(rs.getLong("CERTIFICATE_TEMPLATE_ID")));
				//certificate.setFilePath(rs.getString("FILE_PATH"));
				certificate.setVerified(rs.getBoolean("VERIFICATION_STATUS"));
				certificate.setVerifiedBy(rs.getLong("VERIFIED_BY_ID"));
				certificate.setVerificationDate(new DateTime(rs.getDate("VERIFIED_DATE")));
				certificate.setSocialActivity(socialActivityDao.getSocialActivity(rs.getLong("SOCIAL_ACTIVITY_ID")));
				return certificate;
			});
	}
	
	@Override
	public List<Certificate> getIssuerCertificates(Long issuerId) {
		String sql = "SELECT CERTIFICATE_NAME, CERTIFICATE_ID, ISSUE_DATE, END_DATE, CERTIFICATE_TEMPLATE_ID, FILE_PATH, VERIFICATION_STATUS, VERIFIED_BY_ID, VERIFIED_DATE, SOCIAL_ACTIVITY_ID, CREATED_BY, CREATED_TIME, MODIFIED_BY, MODIFIED_TIME FROM CERTIFICATE WHERE ISSUER_ID=?";
		return jdbcTemplate.query(sql, new Object[] {issuerId}, (RowMapper<Certificate>) (rs, arg) -> {
				rs.next();
				Certificate certificate = new Certificate();
				certificate.setIssuerId(issuerId);
				certificate.setCertificateName(rs.getString("CERTIFICATE_NAME"));
				certificate.setCertificateId(rs.getLong("CERTIFICATE_ID"));
				//certificate.setIssueDate(new DateTime(rs.getDate("ISSUE_DATE")));
				//certificate.setEndDate(new DateTime(rs.getDate("END_DATE")));
				certificate.setCertificateTemplate(certificateTemplateDao.getCertificateTemplate(rs.getLong("CERTIFICATE_TEMPLATE_ID")));
				//certificate.setFilePath(rs.getString("FILE_PATH"));
				certificate.setVerified(rs.getBoolean("VERIFICATION_STATUS"));
				certificate.setVerifiedBy(rs.getLong("VERIFIED_BY_ID"));
				certificate.setVerificationDate(new DateTime(rs.getDate("VERIFIED_DATE")));
				certificate.setSocialActivity(socialActivityDao.getSocialActivity(rs.getLong("SOCIAL_ACTIVITY_ID")));
				return certificate;
			});
	}
	
	

	@Override
	public void removeCertificate(Long certificateId) {
		String sql = "DELETE FROM CERTIFICATE WHERE CERTIFICATE_ID=?";
		jdbcTemplate.update(sql, certificateId);
	}
}
