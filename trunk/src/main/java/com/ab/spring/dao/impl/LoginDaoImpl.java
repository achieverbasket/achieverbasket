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
import com.ab.spring.dao.LoginDao;
import com.ab.type.UserType;
import com.ab.vo.User;
import com.ab.vo.candidate.Candidate;
import com.ab.vo.login.Login;

@Repository
public class LoginDaoImpl implements LoginDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SequenceDao sequenceDao;
	
	@Autowired
	private CandidateDao candidateDao;

	@Override
	public Tuple<Boolean, String, Optional<User>> signinUser(Login login) {
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
				user.setCandidate(Candidate.defaultCandidate());
				if(user.getPassword().equals(login.getPassword())) {
					if(rs.getBoolean("ENABLED")) {
						switch (user.getUserType()) {
						case CANDIDATE:
//							String candidateSql = "SELECT CANDIDATE_ID FROM CANDIDATE_USER_MAPPING WHERE LOGIN_ID=?";
//							Long candidateId = jdbcTemplate.query(candidateSql, new Object[]{user.getUserId()}, (ResultSetExtractor<Long>) rs1 -> rs1.getLong("CANDIDATE_ID"));
//							user.setCandidate(candidateDao.getCandidate(candidateId));
							break;
						default:
							break;
						}
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
	public User getUser(Long userId) {
		String sql = "SELECT USERNAME, PASSWORD, USER_TYPE_ID, FIRST_NAME, LAST_NAME, HINT_Q, HINT_A, EMAIL, ENABLED FROM USERS WHERE USER_ID=?";
		
		ResultSetExtractor<User> rse = rs -> {
			rs.next();
			User user = new User();
			user.setUserId(userId);
			user.setUserName(rs.getString("USERNAME"));
			user.setPassword(rs.getString("PASSWORD"));
			user.setEmail(rs.getString("EMAIL"));
			user.setEnabled(rs.getBoolean("ENABLED"));
			user.setFirstName(rs.getString("FIRST_NAME"));
			user.setLastName(rs.getString("LAST_NAME"));
			user.setHintQ(rs.getString("HINT_Q"));
			user.setHintA(rs.getString("HINT_A"));
			user.setUserType(UserType.fromId(rs.getInt("USER_TYPE_ID")));
			user.setCandidate(Candidate.defaultCandidate());
			switch (user.getUserType()) {
			case CANDIDATE:
//				String candidateSql = "SELECT CANDIDATE_ID FROM CANDIDATE_USER_MAPPING WHERE LOGIN_ID=?";
//				Long candidateId = jdbcTemplate.query(candidateSql, new Object[]{user.getUserId()}, (ResultSetExtractor<Long>) rs1 -> rs1.getLong("CANDIDATE_ID"));
//				user.setCandidate(candidateDao.getCandidate(candidateId));
				break;
			default:
				break;
			}
			return user;
		};
		
		return jdbcTemplate.query(sql, new Object[]{userId}, rse);
	}

	@Override
	public TwoTuple<Boolean, String> registerUser(User user) {
		String sql = "INSERT INTO USERS (USER_ID, USERNAME, PASSWORD, USER_TYPE_ID, FIRST_NAME, LAST_NAME, HINT_Q, HINT_A, EMAIL, ENABLED, CREATED_BY, CREATED_TIME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, SYSDATE())";
		Long userId = sequenceDao.getNextVal("USERS_SEQ");
		int count =0;
		count = jdbcTemplate.update(sql, userId, user.getUserName(), user.getPassword(), user.getUserType().getUserTypeId(), user.getFirstName(), user.getLastName(), user.getHintQ(), user.getHintA(), user.getEmail(), user.isEnabled());
		user.setUserId(userId);
//		if(user.getUserType() == UserType.CANDIDATE) {
//			Candidate candidate = candidateDao.saveCandidate(user.getCandidate());
//			user.setCandidate(candidate);
//			String mappingSQL = "INSERT INTO CANDIDATE_USER_MAPPING VALUES (?, ?)";
//			jdbcTemplate.update(mappingSQL, user.getUserId(), candidate.getCandidateId());
//		}
		return count > 0 ? new TwoTuple<Boolean, String>(true, ApplicationStatusConstant.msg_account_created_success) :  new TwoTuple<Boolean, String>(false, ApplicationStatusConstant.msg_account_created_error);
	}

	@Override
	public boolean activateUser(Long userId) {
		String sql = "UPDATE USERS SET ENABLED=1 WHERE USER_ID=?";
		return jdbcTemplate.update(sql, userId) == 1;
	}

//======================Commented existing code below======================	
	
//	@Override
//	public User signinUser(Login form) throws Exception {
//		
//		// get jdbc template, get user details required for rendering dash board
//		// also get roles associated to user : candidate,issuer,client,observer etc
//		
//		jdbcTemplate.execute("select 1 from dual");
//		
//		// returing java object for now
//		User userDetailObj = new User();
//		userDetailObj.setFirstName("Swapnil");
//		userDetailObj.setLastName("Singhai");
//		userDetailObj.setEmail("Swap@a.com");
//		userDetailObj.setUserType(UserType.CANDIDATE);
//		
//		// will fill roles later
//		
//		
//		// user education details
//		
//		UserEducationForm eduForm = new UserEducationForm();
//		eduForm.setId(1);
//		eduForm.setInstituteCity("Bhopal");
//		eduForm.setInstituteName("RGTU");
//		eduForm.setPassoutYear("2010");
//		eduForm.setPercentmarks("77");
//		eduForm.setQualification("BE");
//		eduForm.setRegistrationYear("2006");
//		
//		List<UserEducationForm> userEdulist = new ArrayList<UserEducationForm>();
//		userEdulist.add(eduForm);
//		
////		userDetailObj.setUserEducationForm(userEdulist);
//		
//		
//		return userDetailObj;
//	}
//
//	@Override
//	public User getUserDetails(User form) throws Exception {
//		// get jdbc template, get user details required for rendering dash board
//				// also get roles associated to user : candidate,issuer,client,observer etc
//				
//				
//				// returing java object for now
//				User userDetailObj = new User();
//				userDetailObj.setUserId(1l);
//				userDetailObj.setFirstName("Swapnil");
//				userDetailObj.setLastName("Singhai");
//				userDetailObj.setEmail("Swap@a.com");
//				userDetailObj.setUserType(UserType.CANDIDATE);
//				
//				// will fill roles later
//				
//				
//				// user education details
//				
//				UserEducationForm eduForm = new UserEducationForm();
//				eduForm.setId(1);
//				eduForm.setInstituteCity("Bhopal");
//				eduForm.setInstituteName("RGTU");
//				eduForm.setPassoutYear("2010");
//				eduForm.setPercentmarks("77");
//				eduForm.setQualification("BE");
//				eduForm.setRegistrationYear("2006");
//				
//				UserEducationForm eduForm1 = new UserEducationForm();
//				eduForm1.setId(2);
//				eduForm1.setInstituteCity("Bhopal");
//				eduForm1.setInstituteName("RGTU");
//				eduForm1.setPassoutYear("2010");
//				eduForm1.setPercentmarks("77");
//				eduForm1.setQualification("BE");
//				eduForm1.setRegistrationYear("2006");
//				
//				List<UserEducationForm> userEdulist = new ArrayList<UserEducationForm>();
//				userEdulist.add(eduForm);userEdulist.add(eduForm1);
//				
////				userDetailObj.setUserEducationForm(userEdulist);
//				
//				
//				// user profession detail
//				UserProfessionForm proForm = new UserProfessionForm();
//				proForm.setId(1);
//				proForm.setCompanyCity("Mumbai");
//				proForm.setCompanyName("MSCI");
//				proForm.setJoiningYear("2016");
//				proForm.setDesignation("Associate");
//				proForm.setLeavingYear("2018");
//				
//				UserProfessionForm proForm1 = new UserProfessionForm();
//				proForm1.setId(2);
//				proForm1.setCompanyCity("Mumbai");
//				proForm1.setCompanyName("MSCI");
//				proForm1.setJoiningYear("2016");
//				proForm1.setDesignation("Associate");
//				proForm1.setLeavingYear("2018");
//				
//				List<UserProfessionForm> userProlist = new ArrayList<UserProfessionForm>();
//				userProlist.add(proForm);userProlist.add(proForm1);
//				
////				userDetailObj.setUserProfessionForm(userProlist);
//				
//				return userDetailObj;
//	}
//
//	@Override
//	public int registerNewUser(Registration form) throws Exception {
//		
//		// after register user will be send to two auth page, keep him inactive till then
//		return jdbcTemplate.update(ApplicationSQLStmtConstant.REGISTER_NEW_USER,new PreparedStatementSetter() {
//			@Override
//			public void setValues(PreparedStatement ps) throws SQLException {
//				ps.setString(1, "DUMMY");
//				ps.setString(2, form.getFirstname());
//				ps.setString(3, form.getLastname());
//				ps.setString(4, form.getEmail());
//				ps.setString(5, form.getPassword());
//				ps.setInt(6, 0);
//				ps.setInt(7, 0);
//				ps.setString(8, "SYSTEM");
//				ps.setString(9, "SYSTEM");
//				
//			}
//		});
//		
//	}
//
}
