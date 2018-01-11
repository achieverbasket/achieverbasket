package com.ab.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ab.constant.config.ApplicationStatusConstant;
import com.ab.dao.CandidateDao;
import com.ab.dao.IssuerDao;
import com.ab.dao.LoginDao;
import com.ab.datastructure.TwoTuple;
import com.ab.form.CustomRoleForm;
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
	public User loginUser(Login login) {
		String sql = "SELECT USER_ID, PASSWORD, USER_TYPE_ID, FIRST_NAME, LAST_NAME, HINT_Q, HINT_A, EMAIL, MOBILE_NUMBER, ENABLED FROM USERS WHERE USERNAME=?";
		System.out.println("in login"+login);
		
		ResultSetExtractor<User> rse = rs -> {
			if(rs.next()) {
				// get roles , hard coding them for now, structure of roles should be
				List<com.ab.form.CustomRoleForm> roles = new ArrayList<CustomRoleForm>();
				CustomRoleForm role = new CustomRoleForm();
				role.setName("ROLE_USER");role.setName("ROLE_ADMIN");role.setName("ROLE_ISSUER");
				roles.add(role);	
				String status = null;
				if(rs.getString("PASSWORD")!= null && rs.getString("PASSWORD").equalsIgnoreCase(login.getPassword()) ){
					if(rs.getBoolean("ENABLED")) {
						status = "Success";
					}else{
						status = "Account not activated";
					}
				}else{
					status = "Password incorrect";
				}
				
				User user = new User(login.getUsername(), rs.getString("PASSWORD"), rs.getBoolean("ENABLED"),
						true, true, 
						true, roles, status, UserType.fromId(rs.getInt("USER_TYPE_ID")), 
						rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"), rs.getString("EMAIL"), rs.getLong("MOBILE_NUMBER"), rs.getLong("USER_ID"));
				
				
				return user;
				
			} else {
				return null;
			}
		};
		return jdbcTemplate.query(sql, new Object[]{login.getUsername()}, rse);
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
		System.out.println("in regiserUser of LoginDaoImpl for: "+user);
		String sql = "INSERT INTO USERS (USER_ID, USERNAME, PASSWORD, USER_TYPE_ID, FIRST_NAME, LAST_NAME, HINT_Q, HINT_A, EMAIL, MOBILE_NUMBER, ENABLED, CREATED_BY, CREATED_TIME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, SYSDATE())";
		Long userId = sequenceDao.getNextVal("USERS_SEQ");
		int count =0;
		count = jdbcTemplate.update(sql, userId, user.getUserName(), user.getPassword(), user.getUserType().getUserTypeId(), user.getFirstName(), user.getLastName(), user.getHintQ(), user.getHintA(), user.getEmail(), user.getMobileNumber(), user.isEnabled());
		user.setUserId(userId);
		if(user.getUserType().getUserTypeId() == UserType.CANDIDATE.getUserTypeId()) {
			Candidate candidate = candidateDao.saveCandidate(Candidate.from(user));
			String mappingSQL = "INSERT INTO CANDIDATE_USER_MAPPING VALUES (?, ?, 0, SYSDATE())";
			jdbcTemplate.update(mappingSQL, user.getUserId(), candidate.getCandidateId());
		}
		else if(user.getUserType().getUserTypeId() == UserType.ISSUER.getUserTypeId()) {
			System.out.println("in register user for Issuer: "+user);
			Issuer issuer = issuerDao.saveIssuer(Issuer.from(user));
			String mappingSQL = "INSERT INTO ISSUER_USER_MAPPING VALUES (?, ?, 0, SYSDATE())";
			jdbcTemplate.update(mappingSQL, user.getUserId(), issuer.getIssuerId());
		}
		return count > 0 ? new TwoTuple<Boolean, String>(true, ApplicationStatusConstant.msg_account_created_success) :  new TwoTuple<Boolean, String>(false, ApplicationStatusConstant.msg_account_created_error);
	}

	@Override
	public boolean activateUser(Long userId) {
		String sql = "UPDATE USERS SET ENABLED=1 WHERE USER_ID=?";
		return jdbcTemplate.update(sql, userId) == 1;
	}
}
