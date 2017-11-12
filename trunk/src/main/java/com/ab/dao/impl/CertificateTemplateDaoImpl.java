package com.ab.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ab.dao.CertificateTemplateDao;
import com.ab.vo.certificate.CertificateTemplate;
import com.ab.vo.certificate.CertificateType;

@Repository
public class CertificateTemplateDaoImpl implements CertificateTemplateDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private SequenceDao sequenceDao;
	
	@Override
	public CertificateTemplate saveCertificateTemplate(CertificateTemplate template) {
		String sql = "INSERT INTO CERTIFICATE_TEMPLATE (CERTIFICATE_TEMPLATE_ID, TEMPLATE_NAME, ISSUER_ID, CERTIFICATE_TYPE_ID, CERTIFICATE_DESC, CREATED_BY, CREATED_TIME) VALUES (?, ?, ?, ?, ?, 0, SYSDATE())";
		Long templateId = sequenceDao.getNextVal("CERTIFICATE_TEMPLATE_SEQ");
		jdbcTemplate.update(sql, templateId, template.getTemplateName(), template.getIssuerId(), template.getCertificateType().getCertificateTypeId(), template.getCertificateDesc());
		template.setCertificateTemplateId(templateId);
		return template;
	}

	@Override
	public void updateCertificateTemplate(CertificateTemplate template) {
		String sql = "UPDATE CERTIFICATE_TEMPLATE SET TEMPLATE_NAME=?, CERTIFICATE_TYPE_ID=?, CERTIFICATE_DESC=?, MODIFIED_BY=0, MODIFIED_TIME) WHERE CERTIFICATE_TEMPLATE_ID=?";
		jdbcTemplate.update(sql, template.getTemplateName(), template.getCertificateType().getCertificateTypeId(), template.getCertificateDesc(), template.getCertificateTemplateId());
	}

	@Override
	public CertificateTemplate getCertificateTemplate(Long certificateTemplateId) {
		String sql = "SELECT TEMPLATE_NAME, ISSUER_ID, CERTIFICATE_TYPE_ID, CERTIFICATE_DESC, CREATED_BY, CREATED_TIME, MODIFIED_BY, MODIFIED_TIME FROM CERTIFICATE_TEMPLATE WHERE CERTIFICATE_TEMPLATE_ID=?";
		return jdbcTemplate.query(sql, new Object[] {certificateTemplateId}, (ResultSetExtractor<CertificateTemplate>) rs -> {
				rs.next();
				CertificateTemplate template = new CertificateTemplate();
				template.setCertificateTemplateId(certificateTemplateId);
				template.setTemplateName(rs.getString("TEMPLATE_NAME"));
				template.setIssuerId(rs.getLong("ISSUER_ID"));
				template.setCertificateType(CertificateType.fromId(rs.getInt("CERTIFICATE_TYPE_ID")));
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
