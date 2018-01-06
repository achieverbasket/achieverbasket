package com.ab.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ab.dao.CertificateTemplateDao;
import com.ab.type.CertificateType;
import com.ab.vo.certificate.Certificate;
import com.ab.vo.certificate.CertificateTemplate;

@Repository
public class CertificateTemplateDaoImpl implements CertificateTemplateDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private SequenceDao sequenceDao;
	
	@Override
	public CertificateTemplate saveCertificateTemplate(CertificateTemplate template) {
		String sql = "INSERT INTO CERTIFICATE_TEMPLATE (CERTIFICATE_TEMPLATE_ID, TEMPLATE_NAME, ISSUER_ID, FILE_PATH, CERTIFICATE_DESC, CREATED_BY, CREATED_TIME) VALUES (?, ?, ?, ?, ?, 0, SYSDATE())";
		Long templateId = sequenceDao.getNextVal("CERTIFICATE_TEMPLATE_SEQ");
		jdbcTemplate.update(sql, templateId, template.getTemplateName(), template.getIssuerId(), template.getFilePath(), template.getCertificateDesc());
		template.setCertificateTemplateId(templateId);
		return template;
	}

	@Override
	public void updateCertificateTemplate(CertificateTemplate template) {
		String sql = "UPDATE CERTIFICATE_TEMPLATE SET TEMPLATE_NAME=?, FILE_PATH=?, CERTIFICATE_DESC=?, MODIFIED_BY=0, MODIFIED_TIME) WHERE CERTIFICATE_TEMPLATE_ID=?";
		jdbcTemplate.update(sql, template.getTemplateName(), template.getFilePath(), template.getCertificateDesc(), template.getCertificateTemplateId());
	}

	@Override
	public CertificateTemplate getCertificateTemplate(Long certificateTemplateId) {
		String sql = "SELECT TEMPLATE_NAME, ISSUER_ID, CERTIFICATE_TYPE_ID, CERTIFICATE_DESC, FILE_PATH, CREATED_BY, CREATED_TIME, MODIFIED_BY, MODIFIED_TIME FROM CERTIFICATE_TEMPLATE WHERE CERTIFICATE_TEMPLATE_ID=?";
		return jdbcTemplate.query(sql, new Object[] {certificateTemplateId}, (ResultSetExtractor<CertificateTemplate>) rs -> {
				rs.next();
				CertificateTemplate template = new CertificateTemplate();
				template.setCertificateTemplateId(certificateTemplateId);
				template.setTemplateName(rs.getString("TEMPLATE_NAME"));
				template.setIssuerId(rs.getLong("ISSUER_ID"));
				template.setFilePath(rs.getString("FILE_PATH"));
				template.setCertificateDesc(rs.getString("CERTIFICATE_DESC"));
				return template;
			});
	}
	
	@Override
	public List<CertificateTemplate> getCertificateTemplateList(Long issuerId, CertificateType certificateType) {
		String sql = "SELECT TEMPLATE_NAME, CERTIFICATE_TEMPLATE_ID, CERTIFICATE_TYPE_ID, CERTIFICATE_DESC, FILE_PATH CREATED_BY, CREATED_TIME, MODIFIED_BY, MODIFIED_TIME FROM CERTIFICATE_TEMPLATE WHERE ISSUER_ID=? AND CERTIFICATE_TYPE_ID=?";
		
		return jdbcTemplate.query(sql, new Object[] {issuerId,  certificateType.getCertificateTypeId()}, (RowMapper<CertificateTemplate>) (rs,arg) -> {
				rs.next();
				CertificateTemplate template = new CertificateTemplate();
				template.setCertificateTemplateId(rs.getLong("CERTIFICATE_TEMPLATE_ID"));
				template.setTemplateName(rs.getString("TEMPLATE_NAME"));
				template.setIssuerId(issuerId);
				template.setFilePath(rs.getString("FILE_PATH"));// hard coding by swapnil, chnage this accodinlgy
				//after aws s3 impl
				template.setCertificateDesc(rs.getString("CERTIFICATE_DESC"));
				return template;
			});

	}


	@Override
	public void removeCertificateTemplate(Long certificateTemplateId) {
		String sql = "DELETE FROM CERTIFICATE_TEMPLATE WHERE CERTIFICATE_TEMPLATE_ID=?";
		jdbcTemplate.update(sql, certificateTemplateId);
	}
}
