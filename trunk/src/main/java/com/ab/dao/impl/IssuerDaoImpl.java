package com.ab.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ab.dao.CertificateDao;
import com.ab.dao.IssuerDao;
import com.ab.dao.IssuerDetailDao;
import com.ab.dao.SocialActivityDao;
import com.ab.type.IssuerType;
import com.ab.vo.activity.SocialActivity;
import com.ab.vo.activity.SocialActivityType;
import com.ab.vo.issuer.Issuer;


@Repository
public class IssuerDaoImpl implements IssuerDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SequenceDao sequenceDao;
	
	@Autowired
	private SocialActivityDao socialActivityDao;

	@Autowired
	private CertificateDao certificateDao;
	
	@Autowired
	private IssuerDetailDao issuerDetailDao;

	@Override
	public Issuer saveIssuer(Issuer issuer) {
		String sql = "INSERT INTO ISSUER (ISSUER_ID, ISSUER_NAME, ISSUER_TYPE_ID, SOCIAL_ACTIVITY_ID, CREATED_BY, CREATED_TIME) VALUES (?, ?, ?, ?, 0, SYSDATE())";

		Long issuerId = sequenceDao.getNextVal("ISSUER_SEQ");
		
		SocialActivity socialActivity = socialActivityDao.saveSocialActivity(new SocialActivity(SocialActivityType.PROFILE));

		jdbcTemplate.update(sql, issuerId, issuer.getIssuerName(), issuer.getIssuerType().getIssuerTypeId(), socialActivity.getSocialActivityId());
		
		issuer.setIssuerId(issuerId);
		issuer.setSocialActivity(socialActivity);
		return issuer;
	}

	@Override
	public void updateIssuer(Issuer issuer) {
		String sql = "UPDATE ISSUER (ISSUER_NAME=?, MODIFIED_BY=0, MODIFIED_TIME=SYSDATE() WHERE ISSUER_ID=?";
		jdbcTemplate.update(sql, issuer.getIssuerName(), issuer.getIssuerId());
	}

	@Override
	public Issuer getIssuer(Long issuerId) {
		String sql = "SELECT ISSUER_NAME, ISSUER_TYPE_ID, SOCIAL_ACTIVITY_ID, CREATED_BY, CREATED_TIME, MODIFIED_BY, MODIFIED_TIME FROM ISSUER WHERE ISSUER_ID=?";
		// every where when no null check is performed on using lambda java 8 rs feature , error will occur
		// either dont use it or do null check handling
		// i have made changes to 1 place for line 73 -- inside getIssuerDetailByIssuerId method
		Issuer issu = jdbcTemplate.query(sql, new Object[] {issuerId}, (ResultSetExtractor<Issuer>) rs -> {
				rs.next();
				Issuer issuer = new Issuer();
				issuer.setIssuerId(issuerId);
				issuer.setIssuerName(rs.getString("ISSUER_NAME"));
				issuer.setIssuerType(IssuerType.fromId(rs.getInt("ISSUER_TYPE_ID")));
				issuer.setSocialActivity(socialActivityDao.getSocialActivity(rs.getLong("SOCIAL_ACTIVITY_ID")));
				return issuer;
			});
		
		issu.setIssuerCertificateList(certificateDao.getIssuerCertificates(issuerId));
		issu.setIssuerDetail(issuerDetailDao.getIssuerDetailByIssuerId(issuerId));
		return issu;
	}

	@Override
	public void removeIssuer(Long issuerId) {
		String sql = "DELETE FROM ISSUER WHERE ISSUER_ID=?";
		jdbcTemplate.update(sql, issuerId);
	}
}
