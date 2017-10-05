package com.ab.spring.dao.impl;

import java.sql.Date;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ab.spring.dao.AddressDao;
import com.ab.spring.dao.CandidatePersonalDetailDao;
import com.ab.vo.Address;
import com.ab.vo.Gender;
import com.ab.vo.candidate.CandidatePersonalDetail;
import com.ab.vo.candidate.MaritalStatus;

@Repository
public class CandidatePersonalDetailDaoImpl implements CandidatePersonalDetailDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private SequenceDao sequenceDao;
	
	@Override
	public CandidatePersonalDetail saveCandidatePersonalDetail(CandidatePersonalDetail personalDetail) {
		String sql = "INSERT INTO CANDIDATE_PERSONAL_DETAIL (PERSONAL_DETAIL_ID, CANDIDATE_ID, FIRST_NAME, MIDDLE_NAME, LAST_NAME, DOB, EMAIL, GENDER, MARITAL_STATUS, ADDRESS_ID, CREATED_BY, CREATED_TIME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, SYSDATE())";
		Long personalDetailId = sequenceDao.getNextVal("CANDIDATE_PERSONAL_DETAIL_SEQ");
		Address address = addressDao.saveAddress(personalDetail.getAddress());
		jdbcTemplate.update(sql, personalDetailId, 
				personalDetail.getCandidateId(), 
				personalDetail.getFirstName(), 
				personalDetail.getMiddleName(), 
				personalDetail.getLastName(), 
				new Date(personalDetail.getDateOfBirth().toDate().getTime()),
				personalDetail.getEmail(),
				personalDetail.getGender().getGenderId(),
				personalDetail.getMaritalStatus().getMaritalStatusId(),
				address.getAddressId()
				);
		
		personalDetail.setCandidatePersonalDetailId(personalDetailId);
		personalDetail.setAddress(address);
		return personalDetail;
	}

	@Override
	public void updateCandidatePersonalDetail(CandidatePersonalDetail personalDetail) {
		String sql = "UPDATE CANDIDATE_PERSONAL_DETAIL SET FIRST_NAME=?, MIDDLE_NAME=?, LAST_NAME=?, DOB=?, EMAIL=?, GENDER=?, MARITAL_STATUS=?, MODIFIED_BY=0, MODIFIED_TIME=SYSDATE()) WHERE PERSONAL_DETAIL_ID =?";
		jdbcTemplate.update(sql,  
				personalDetail.getFirstName(), 
				personalDetail.getMiddleName(), 
				personalDetail.getLastName(), 
				new Date(personalDetail.getDateOfBirth().toDate().getTime()),
				personalDetail.getEmail(),
				personalDetail.getGender().getGenderId(),
				personalDetail.getMaritalStatus().getMaritalStatusId(),
				personalDetail.getCandidatePersonalDetailId()
				);
	}

	@Override
	public CandidatePersonalDetail getPersonalDetailByCandidateId(Long candidateId) {
		String sql = "SELECT PERSONAL_DETAIL_ID, FIRST_NAME, MIDDLE_NAME, LAST_NAME, DOB, EMAIL, GENDER, MARITAL_STATUS, ADDRESS_ID, CREATED_BY, CREATED_TIME, MODIFIED_BY, MODIFIED_TIME FROM CANDIDATE_PERSONAL_DETAIL WHERE CANDIDATE_ID=?";
		return jdbcTemplate.query(sql, new Object[] {candidateId}, (ResultSetExtractor<CandidatePersonalDetail>) rs -> {
				rs.next();
				CandidatePersonalDetail personalDetail = new CandidatePersonalDetail();
				personalDetail.setCandidatePersonalDetailId(rs.getLong("PERSONAL_DETAIL_ID"));
				personalDetail.setCandidateId(candidateId);
				personalDetail.setFirstName(rs.getString("FIRST_NAME"));
				personalDetail.setMiddleName(rs.getString("MIDDLE_NAME"));
				personalDetail.setLastName(rs.getString("LAST_NAME"));
				personalDetail.setDateOfBirth(new DateTime(rs.getDate("DOB")));
				personalDetail.setEmail(rs.getString("EMAIL"));
				personalDetail.setMaritalStatus(MaritalStatus.fromId(rs.getInt("MARITAL_STATUS")));
				personalDetail.setGender(Gender.fromId(rs.getInt("GENDER")));
				personalDetail.setAddress(addressDao.getAddress(rs.getLong("ADDRESS_ID")));
				return personalDetail;
			});
	}

	@Override
	public CandidatePersonalDetail getPersonalDetailById(Long personalDetailId) {
		String sql = "SELECT CANDIDATE_ID, FIRST_NAME, MIDDLE_NAME, LAST_NAME, DOB, EMAIL, GENDER, MARITAL_STATUS, ADDRESS_ID, CREATED_BY, CREATED_TIME, MODIFIED_BY, MODIFIED_TIME FROM CANDIDATE_PERSONAL_DETAIL WHERE PERSONAL_DETAIL_ID=?";
		return jdbcTemplate.query(sql, new Object[] {personalDetailId}, (ResultSetExtractor<CandidatePersonalDetail>) rs -> {
				rs.next();
				CandidatePersonalDetail personalDetail = new CandidatePersonalDetail();
				personalDetail.setCandidatePersonalDetailId(personalDetailId);
				personalDetail.setCandidateId(rs.getLong("CANDIDATE_ID"));
				personalDetail.setFirstName(rs.getString("FIRST_NAME"));
				personalDetail.setMiddleName(rs.getString("MIDDLE_NAME"));
				personalDetail.setLastName(rs.getString("LAST_NAME"));
				personalDetail.setDateOfBirth(new DateTime(rs.getDate("DOB")));
				personalDetail.setEmail(rs.getString("EMAIL"));
				personalDetail.setMaritalStatus(MaritalStatus.fromId(rs.getInt("MARITAL_STATUS")));
				personalDetail.setGender(Gender.fromId(rs.getInt("GENDER")));
				personalDetail.setAddress(addressDao.getAddress(rs.getLong("ADDRESS_ID")));
				return personalDetail;
			});
	}
	
	@Override
	public void removeCandidatePersonalDetail(Long candidatePersonalDetailId) {
		String sql = "DELETE FROM CANDIDATE_PERSONAL_DETAIL WHERE PERSONAL_DETAIL_ID=?";
		jdbcTemplate.update(sql, candidatePersonalDetailId);
	}
}
