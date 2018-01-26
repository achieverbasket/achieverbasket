package com.ab.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ab.dao.CandidateDao;
import com.ab.dao.CandidatePersonalDetailDao;
import com.ab.dao.SocialActivityDao;
import com.ab.type.CandidateType;
import com.ab.vo.activity.SocialActivity;
import com.ab.vo.activity.SocialActivityType;
import com.ab.vo.candidate.Candidate;
import com.ab.vo.certificate.Certificate;

@Repository
public class CandidateDaoImpl implements CandidateDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SequenceDao sequenceDao;
	
	@Autowired
	private CandidatePersonalDetailDao candidatePersonalDetailDao;

	@Autowired
	private SocialActivityDao socialActivityDao;

	@Override
	public Candidate saveCandidate(Candidate candidate) {
		
		System.out.println("in saveCandidate: "+candidate);
		String sql = "INSERT INTO CANDIDATE (CANDIDATE_ID, CANDIDATE_NAME, CANDIDATE_TYPE_ID, IS_ACTIVE, SOCIAL_ACTIVITY_ID, CREATED_BY, CREATED_TIME) VALUES (?, ?, ?, ?, ?, 0, SYSDATE())";

		Long candidateId = sequenceDao.getNextVal("CANDIDATE_SEQ");
		
		SocialActivity socialActivity = socialActivityDao.saveSocialActivity(new SocialActivity(SocialActivityType.PROFILE));
		jdbcTemplate.update(sql, 
				candidateId, 
				candidate.getCandidateName(), 
				candidate.getCandidateType().getCandidateTypeId(), 
				candidate.isActive()==true?1:0, 
				socialActivity.getSocialActivityId());
		
		candidate.setCandidateId(candidateId);
		candidate.setSocialActivity(socialActivity);
		candidate.getCandidatePersonalDetail().setCandidateId(candidateId);
		
		candidatePersonalDetailDao.saveCandidatePersonalDetail(candidate.getCandidatePersonalDetail());
		return candidate;
	}

	@Override
	public void updateCandidate(Candidate candidate) {
		String sql = "UPDATE CANDIDATE (CANDIDATE_NAME=?, MODIFIED_BY=0, MODIFIED_TIME=SYSDATE() WHERE CANDIDATE_ID=?";
		jdbcTemplate.update(sql, candidate.getCandidateName(), candidate.getCandidateId());
	}

	@Override
	public Candidate getCandidate(Long candidateId) {
		String sql = "SELECT CANDIDATE_NAME, CANDIDATE_TYPE_ID, IS_ACTIVE, SOCIAL_ACTIVITY_ID, CREATED_BY, CREATED_TIME, MODIFIED_BY, MODIFIED_TIME FROM CANDIDATE WHERE CANDIDATE_ID=?";
		Candidate cand = jdbcTemplate.query(sql, new Object[] {candidateId}, (ResultSetExtractor<Candidate>) rs -> {
				rs.next();
				Candidate candidate = new Candidate();
				candidate.setCandidateId(candidateId);
				candidate.setCandidateName(rs.getString("CANDIDATE_NAME"));
				candidate.setCandidateType(CandidateType.fromId(rs.getInt("CANDIDATE_TYPE_ID")));
				candidate.setActive(rs.getBoolean("IS_ACTIVE"));
				candidate.setSocialActivity(socialActivityDao.getSocialActivity(rs.getLong("SOCIAL_ACTIVITY_ID")));
				return candidate;
			});
		
//		cand.setCandidateCertificateList(certificateDao.getCandidateCertificates(candidateId));
		cand.setCandidatePersonalDetail(candidatePersonalDetailDao.getPersonalDetailByCandidateId(candidateId));
		return cand;
	}
	
	@Override
	public Long getCandidateIdByEmailOrMobile(String emailId, Long mobileNumber) {
		
		System.out.println("email: "+emailId+" mobile: "+mobileNumber);
		
		String sql ="select C.CANDIDATE_ID from CANDIDATE C where C.CANDIDATE_ID =("+
					 "select CPD.CANDIDATE_ID from CANDIDATE_PERSONAL_DETAIL CPD WHERE CPD.CANDIDATE_ID=C.CANDIDATE_ID AND ( CPD.EMAIL=? OR CPD.MOBILE_NUMBER= ?))";
		return jdbcTemplate.query(sql, new Object[] {emailId, mobileNumber}, (ResultSetExtractor<Long>) rs -> {
			if(rs.next())
			{
				return rs.getLong("CANDIDATE_ID");
			}
			else
			{
				return null;
			}
		});
	}

	@Override
	public void removeCandidate(Long candidateId) {
		String sql = "DELETE FROM CANDIDATE WHERE CANDIDATE_ID=?";
		jdbcTemplate.update(sql, candidateId);
	}
	
	@Override
	public List<Candidate> getCandidateListByActiveFlag(boolean isActive) {
		String sql = "SELECT CANDIDATE_ID, CANDIDATE_NAME, CANDIDATE_TYPE_ID, IS_ACTIVE, SOCIAL_ACTIVITY_ID, CREATED_BY, CREATED_TIME, MODIFIED_BY, MODIFIED_TIME FROM CANDIDATE WHERE IS_ACTIVE=?";
		return jdbcTemplate.query(sql, new Object[] {isActive},  (RowMapper<Candidate>)  (rs, arg) -> {
			if(rs.next())
			{
				Candidate candidate = new Candidate();
				candidate.setCandidateId(rs.getLong("CANDIDATE_ID"));
				candidate.setCandidateName(rs.getString("CANDIDATE_NAME"));
				candidate.setCandidateType(CandidateType.fromId(rs.getInt("CANDIDATE_TYPE_ID")));
				candidate.setActive(rs.getBoolean("IS_ACTIVE"));
				candidate.setSocialActivity(socialActivityDao.getSocialActivity(rs.getLong("SOCIAL_ACTIVITY_ID")));
				return candidate;
			}
			else {
				return null;
			}
			});
	}
}
