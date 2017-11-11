package com.ab.spring.dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ab.constant.config.ApplicationStatusConstant;
import com.ab.datastructure.Tuple;
import com.ab.datastructure.TwoTuple;
import com.ab.spring.dao.CandidateDao;
import com.ab.spring.dao.IssuerDao;
import com.ab.spring.dao.LoginDao;
import com.ab.type.UserType;
import com.ab.vo.User;
import com.ab.vo.candidate.Candidate;
import com.ab.vo.issuer.Issuer;
import com.ab.vo.login.Login;

@Repository
public class LoginDaoImpl implements LoginDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SequenceDao sequenceDao;
	
	@Autowired
	private CandidateDao candidateDao;

	@Autowired
	private IssuerDao issuerDao;

	@Override
	public Tuple<Boolean, String, Optional<User>> loginUser(Login login) {
		String sql = "SELECT USER_ID, PASSWORD, USER_TYPE_ID, FIRST_NAME, LAST_NAME, HINT_Q, HINT_A, EMAIL, ENABLED FROM USERS WHERE USERNAME=?";
		
		ResultSetExtractor<Tuple<Boolean, String, Optional<User>>> rse = rs -> {
			if(rs.next()) {
				User user = new User();
				user.setUserId(rs.getLong("USER_ID"));
				user.setUserName(login.getUserName());
				user.setPassword(rs.getString("PASSWORD"));
				user.setEmail(rs.getString("EMAIL"));
				user.setEnabled(rs.getBoolean("ENABLED"));
				user.setFirstName(rs.getString("FIRST_NAME"));
				user.setLastName(rs.getString("LAST_NAME"));
				user.setHintQ(rs.getString("HINT_Q"));
				user.setHintA(rs.getString("HINT_A"));
				user.setUserType(UserType.fromId(rs.getInt("USER_TYPE_ID")));
				if(user.getPassword().equals(login.getPassword())) {
					if(rs.getBoolean("ENABLED")) {
						return new Tuple<Boolean, String, Optional<User>>(true, "Success.", Optional.of(user));
					} else {
						return new Tuple<Boolean, String, Optional<User>>(false, "Account not activated.", Optional.of(user));
					}
				} else {
					return new Tuple<Boolean, String, Optional<User>>(false, "Password incorrect.", Optional.of(user));
				}
			} else {
				return new Tuple<Boolean, String, Optional<User>>(false, "Username not found.", Optional.empty());
			}
			
		};
		
		return jdbcTemplate.query(sql, new Object[]{login.getUserName()}, rse);
	}

	@Override
	public Candidate getCandidate(Long userId) {
		String candidateSql = "SELECT CANDIDATE_ID FROM CANDIDATE_USER_MAPPING WHERE USER_ID=?";
		Long candidateId = jdbcTemplate.query(candidateSql, new Object[]{userId}, (ResultSetExtractor<Long>) rs1 -> { rs1.next(); return rs1.getLong("CANDIDATE_ID");});
		return candidateDao.getCandidate(candidateId);
	}

	@Override
	public Issuer getIssuer(Long userId) {
		String issuerSql = "SELECT ISSUER_ID FROM ISSUER_USER_MAPPING WHERE USER_ID=?";
		Long issuerId = jdbcTemplate.query(issuerSql, new Object[]{userId}, (ResultSetExtractor<Long>) rs1 -> {rs1.next(); return rs1.getLong("ISSUER_ID");});
		return issuerDao.getIssuer(issuerId);
	}

	@Override
	public TwoTuple<Boolean, String> registerUser(User user) {
		String sql = "INSERT INTO USERS (USER_ID, USERNAME, PASSWORD, USER_TYPE_ID, FIRST_NAME, LAST_NAME, HINT_Q, HINT_A, EMAIL, ENABLED, CREATED_BY, CREATED_TIME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, SYSDATE())";
		Long userId = sequenceDao.getNextVal("USERS_SEQ");
		int count =0;
		count = jdbcTemplate.update(sql, userId, user.getUserName(), user.getPassword(), user.getUserType().getUserTypeId(), user.getFirstName(), user.getLastName(), user.getHintQ(), user.getHintA(), user.getEmail(), user.isEnabled());
		user.setUserId(userId);
		if(user.getUserType() == UserType.CANDIDATE) {
			Candidate candidate = candidateDao.saveCandidate(Candidate.from(user));
			String mappingSQL = "INSERT INTO CANDIDATE_USER_MAPPING VALUES (?, ?, 0, SYSDATE())";
			jdbcTemplate.update(mappingSQL, user.getUserId(), candidate.getCandidateId());
		}
		return count > 0 ? new TwoTuple<Boolean, String>(true, ApplicationStatusConstant.msg_account_created_success) :  new TwoTuple<Boolean, String>(false, ApplicationStatusConstant.msg_account_created_error);
	}

	@Override
	public boolean activateUser(Long userId) {
		String sql = "UPDATE USERS SET ENABLED=1 WHERE USER_ID=?";
		return jdbcTemplate.update(sql, userId) == 1;
	}
}
