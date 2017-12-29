package com.ab.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.ab.dao.CertificateDao;
import com.ab.dao.CertificateTemplateDao;
import com.ab.dao.SocialActivityDao;
import com.ab.type.CertificateType;
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

		try {

			String sql = "INSERT INTO CERTIFICATE (CERTIFICATE_ID, CERTIFICATE_NAME, CANDIDATE_ID, CERTIFICATE_TYPE, ISSUE_DATE, END_DATE, CERTIFICATE_TEMPLATE_ID, FILE_PATH, VERIFICATION_STATUS, "
					+ "VERIFIED_BY_ID, VERIFIED_DATE, SOCIAL_ACTIVITY_ID, CREATED_BY, CREATED_TIME) VALUES (?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE())";
	
			Long certificateId = sequenceDao.getNextVal("CERTIFICATE_SEQ");
			SocialActivity socialActivity = socialActivityDao.saveSocialActivity(new SocialActivity(SocialActivityType.CERTIFICATE));
	
			certificate.setCertificateId(certificateId);
			certificate.setSocialActivity(socialActivity);

			System.out.println("certificate to save is: "+certificate);
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			System.out.println("date original: "+(certificate.getIssueDate()));
			System.out.println("issue date formated: "+new Date(format.parse(certificate.getIssueDate()).getTime()));
			System.out.println("end date formated: "+new Date(format.parse(certificate.getEndDate()).getTime()));

			
			String filePath = saveCertificateInFile(certificate.getCandidateId(), certificate.getCertificateId(), certificate.getCertificateFile());
			certificate.setFilePath(filePath);
			
			jdbcTemplate.update(sql, certificateId, 
					certificate.getCertificateName(),
					certificate.getCandidateId(),
					certificate.getCertificateType().getCertificateTypeId(),
					new Date(format.parse(certificate.getIssueDate()).getTime()), 
					new Date(format.parse(certificate.getEndDate()).getTime()),
					certificate.getCertificateTemplate().getCertificateTemplateId(),
					certificate.getFilePath(),
					certificate.isVerified(),
					certificate.getVerifiedBy(),
					certificate.getVerificationDate() == null ? null : new Date(format.parse(certificate.getVerificationDate()).getTime()), 
					socialActivity.getSocialActivityId(),
					1);
						
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return certificate;
	}
	

	@Override
	public void updateCertificate(Certificate certificate) {
		String sql = "UPDATE CERTIFICATE SET CERTIFICATE_NAME=?, ISSUE_DATE=?, END_DATE=?,"
				+ " FILE_PATH=?, VERIFICATION_STATUS=?, VERIFIED_BY_ID=?, VERIFIED_DATE=?, MODIFIED_BY=0 , "
				+ " CERTIFICATE_TYPE=?, MODIFIED_TIME=SYSDATE() WHERE CERTIFICATE_ID=?";
		
		try
		{
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			System.out.println("in updateCertificate");
			System.out.println("date original: "+(certificate.getIssueDate()));
			System.out.println("issue date formated: "+new Date(format.parse(certificate.getIssueDate()).getTime()));
			System.out.println("end date formated: "+new Date(format.parse(certificate.getEndDate()).getTime()));
			System.out.println("verification date formated: "+(certificate.getVerificationDate() == null ? null : new Date(format.parse(certificate.getVerificationDate()).getTime())));
	
			
			jdbcTemplate.update(sql, certificate.getCertificateName(), 
				new Date(format.parse(certificate.getIssueDate()).getTime()), 
				new Date(format.parse(certificate.getEndDate()).getTime()),
				certificate.getFilePath(),
				certificate.isVerified(),
				certificate.getVerifiedBy(),
				certificate.getVerificationDate() == null ? null : new Date(format.parse(certificate.getVerificationDate()).getTime()), 
				certificate.getCertificateType().getCertificateTypeId(),
				certificate.getCertificateId());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public Certificate getCertificate(Long certificateId) {
		String sql = "SELECT CERTIFICATE_NAME, CANDIDATE_ID, ISSUE_DATE, END_DATE, CERTIFICATE_TEMPLATE_ID, FILE_PATH, VERIFICATION_STATUS, VERIFIED_BY_ID, VERIFIED_DATE, CERTIFICATE_TYPE, SOCIAL_ACTIVITY_ID, CREATED_BY, CREATED_TIME, MODIFIED_BY, MODIFIED_TIME FROM CERTIFICATE WHERE CERTIFICATE_ID=?";
		
		System.out.println("in get certificate for certificateId: "+certificateId);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		return jdbcTemplate.query(sql, new Object[] {certificateId}, (ResultSetExtractor<Certificate>) rs -> {
				rs.next();
				Certificate certificate = new Certificate();
				certificate.setCertificateId(certificateId);
				certificate.setCertificateName(rs.getString("CERTIFICATE_NAME"));
				certificate.setCandidateId(rs.getLong("CANDIDATE_ID"));
				certificate.setIssueDate(dateFormat.format(rs.getDate("ISSUE_DATE")));
				certificate.setEndDate(dateFormat.format(rs.getDate("END_DATE")));
				certificate.setCertificateTemplate(certificateTemplateDao.getCertificateTemplate(rs.getLong("CERTIFICATE_TEMPLATE_ID")));
				certificate.setFilePath(rs.getString("FILE_PATH"));
				certificate.setVerified(rs.getBoolean("VERIFICATION_STATUS"));
				certificate.setVerifiedBy(rs.getLong("VERIFIED_BY_ID"));
				certificate.setVerificationDate(rs.getObject("VERIFIED_DATE") == null ? null : dateFormat.format(rs.getDate("VERIFIED_DATE")));
				certificate.setCertificateFile(getCertificateFile(certificate.getFilePath()));
				certificate.setCertificateType(CertificateType.fromId(rs.getInt("CERTIFICATE_TYPE")));
				certificate.setSocialActivity(socialActivityDao.getSocialActivity(rs.getLong("SOCIAL_ACTIVITY_ID")));
				
				System.out.println("certificate: "+certificate);
				
				return certificate;
			});
	}

	@Override
	public List<Certificate> getCandidateCertificates(Long candidateId) {
		
		String sql = "SELECT CERTIFICATE_NAME, CERTIFICATE_ID, ISSUE_DATE, END_DATE, CERTIFICATE_TEMPLATE_ID, FILE_PATH, VERIFICATION_STATUS, VERIFIED_BY_ID, VERIFIED_DATE, SOCIAL_ACTIVITY_ID, CREATED_BY, CREATED_TIME, MODIFIED_BY, MODIFIED_TIME, CERTIFICATE_TYPE FROM CERTIFICATE WHERE CANDIDATE_ID=?";

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("getting certificate list for candidate id: "+candidateId);
		return jdbcTemplate.query(sql, new Object[] {candidateId}, (RowMapper<Certificate>) (rs, arg) -> {

				Certificate certificate = new Certificate();
				certificate.setCandidateId(candidateId);
				certificate.setCertificateName(rs.getString("CERTIFICATE_NAME"));
				certificate.setCertificateId(rs.getLong("CERTIFICATE_ID"));
				certificate.setIssueDate(dateFormat.format(rs.getDate("ISSUE_DATE")));
				certificate.setEndDate(dateFormat.format(rs.getDate("END_DATE")));
				certificate.setCertificateTemplate(certificateTemplateDao.getCertificateTemplate(rs.getLong("CERTIFICATE_TEMPLATE_ID")));
				certificate.setFilePath(rs.getString("FILE_PATH"));
				certificate.setVerified(rs.getBoolean("VERIFICATION_STATUS"));
				certificate.setVerifiedBy(rs.getLong("VERIFIED_BY_ID"));
				certificate.setVerificationDate(rs.getObject("VERIFIED_DATE") == null ? null : dateFormat.format(rs.getDate("VERIFIED_DATE")));
				certificate.setCertificateFile(getCertificateFile(certificate.getFilePath()));
				certificate.setSocialActivity(socialActivityDao.getSocialActivity(rs.getLong("SOCIAL_ACTIVITY_ID")));
				certificate.setCertificateType(CertificateType.fromId(rs.getInt("CERTIFICATE_TYPE")));
				
				System.out.println("certificate: "+certificate);
				
				return certificate;
			});
	}
	
	@Override
	public List<Certificate> getIssuerCertificates(Long issuerId) {
		String sql = "SELECT CERTIFICATE_NAME, CERTIFICATE_ID, ISSUE_DATE, END_DATE, CERTIFICATE_TEMPLATE_ID, FILE_PATH, VERIFICATION_STATUS, VERIFIED_BY_ID, VERIFIED_DATE, CERTIFICATE_TYPE, SOCIAL_ACTIVITY_ID, CREATED_BY, CREATED_TIME, MODIFIED_BY, MODIFIED_TIME FROM CERTIFICATE WHERE ISSUER_ID=?";

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("getting issuer certificate list for issuer id: "+issuerId);
		
		return jdbcTemplate.query(sql, new Object[] {issuerId}, (RowMapper<Certificate>) (rs, arg) -> {
				Certificate certificate = new Certificate();
				certificate.setIssuerId(issuerId);
				certificate.setCertificateName(rs.getString("CERTIFICATE_NAME"));
				certificate.setCertificateId(rs.getLong("CERTIFICATE_ID"));
				certificate.setIssueDate(dateFormat.format(rs.getDate("ISSUE_DATE")));
				certificate.setEndDate(dateFormat.format(rs.getDate("END_DATE")));
				certificate.setCertificateTemplate(certificateTemplateDao.getCertificateTemplate(rs.getLong("CERTIFICATE_TEMPLATE_ID")));
				certificate.setFilePath(rs.getString("FILE_PATH"));
				certificate.setVerified(rs.getBoolean("VERIFICATION_STATUS"));
				certificate.setVerifiedBy(rs.getLong("VERIFIED_BY_ID"));
				certificate.setVerificationDate(rs.getObject("VERIFIED_DATE") == null ? null : dateFormat.format(rs.getDate("VERIFIED_DATE")));
				certificate.setCertificateFile(getCertificateFile(certificate.getFilePath()));
				certificate.setSocialActivity(socialActivityDao.getSocialActivity(rs.getLong("SOCIAL_ACTIVITY_ID")));
				
				System.out.println("certificate: "+certificate);
				
				return certificate;
			});
	}

	@Override
	public boolean deleteCertificate(Long certificateId) {
		String sql = "DELETE FROM CERTIFICATE WHERE CERTIFICATE_ID=?";
		jdbcTemplate.update(sql, certificateId);
		return true;
	}
	
	@Override
	public boolean deleteCertificatesForCandidate(long candidateId)
	{
		String sql = "DELETE FROM CERTIFICATE WHERE CANDIDATE_ID=?";
		jdbcTemplate.update(sql, candidateId);
		return true;
	}
	
	@Override
	public boolean deleteCertificatesForIssuer(long issuerId)
	{
		String sql = "DELETE FROM CERTIFICATE WHERE ISSUER_ID=?";
		jdbcTemplate.update(sql, issuerId);
		return true;
	}
	
	private String saveCertificateInFile(Long candidateId, Long certificateId, MultipartFile certificateFile)
	{
		String filePath = certifcateLocation+"/"+candidateId+"/"+certificateId+".pdf";
		File certificateFilePath = new File(filePath);
		try
		{
			if(!certificateFilePath.exists())
			{
				certificateFilePath.mkdirs();
			}
			
			System.out.println("filePath: "+filePath);
			certificateFile.transferTo(certificateFilePath);
			//FileUtils.copyFileToDirectory(certificateFile, certificateFilePath);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filePath;
	}
	
	private MultipartFile getCertificateFile(String filePath)
	{
		MultipartFile multipartFile=null;
		try
		{
//			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//		    multipartResolver.setMaxUploadSize(20971520);   // 20MB
//		    multipartResolver.setMaxInMemorySize(1048576);  // 1MB

		    System.out.println("filepath: "+filePath);
		    if(null!=filePath)
		    {
				File file = new File(filePath);
			    FileInputStream input = new FileInputStream(file);
			    multipartFile = new MockMultipartFile("file",
			            file.getName(), "text/plain", IOUtils.toByteArray(input));
		    }
		    else
		    {
		    	System.out.println("file hard copy location is null");
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("multipartFile: "+multipartFile);
		return multipartFile;
	}
}
