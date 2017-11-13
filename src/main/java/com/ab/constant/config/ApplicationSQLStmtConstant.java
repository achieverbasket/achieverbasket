package com.ab.constant.config;

public class ApplicationSQLStmtConstant {
	
	/*register new user*/
	
	
	/* t as prefix for table
	 * v as prefix for view
	 * p as for procedure 
	 * f for function
	 * */
	
	public static final String REGISTER_NEW_USER = "insert into t_user(account_id,first_name,last_name,email,password,active,verified,created_by"
			+ ", created_on,modified_by,modified_on ) values(?,?,?,?,?,?,?,?,sysdate(),?,sysdate())";





	/*candidates sql*/
	public static final String INSERT_CANDIDATE_QUERY= "INSERT INTO MASTER_CANDIDATE"
			+ "(CANDIDATE_ID, CANDIDATE_NAME, CANDIDATE_TYPE_ID, CANDIDATE_SOCIAL_ACTIVITY_ID, "
			+ "CANDIDATE_PREFERENCE_ID,  CANDIDATE_PERSONAL_DETAIL_ID"
			+ ",CREATED_BY, CREATED_TIME, MODIFIED_BY, MODIFIED_TIME)"
			+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
	
	public static final String SELECT_CANDIDATE_QUERY="SELECT CANDIDATE_ID, CANDIDATE_NAME, CANDIDATE_TYPE_ID, "
			+ "CANDIDATE_SOCIAL_ACTIVITY_ID, CANDIDATE_PREFERENCE_ID, "
			+ "CANDIDATE_PERSONAL_DETAIL_ID, CREATED_BY, CREATED_TIME, MODIFIED_BY, MODIFIED_TIME "
			+ "FROM MASTER_CANDIDATE WHERE CANDIDATE_ID=?";
	
	

	/*certificate query*/
	public static final String INSERT_CERTIFICATE_QUERY="INSERT INTO MASTER_CERTIFICATE(CERTIFICATE_ID,CERTIFICATE_NAME,CERTIFICATE_ISSUER_ID,"
			+ "CERTIFICATE_CANDIDATE_ID,CERTIFICATE_TYPE_ID,CERTIFICATE_DESC,CERTIFICATE_ISSUE_DATE,"
			+ "CERTIFICATE_END_DATE,CERTIFICATE_VERIFIED_BY_ID,CERTIFICATE_VERIFIED_DATE,CERTIFICATE_TEMPLATE_ID,"
			+ "CERTIFICATE_FILE_PATH,CERTIFICATE_SOCIAL_ACTIVITY_ID,CERTIFICATE_PREFERENCE_ID,CREATED_BY,CREATED_TIME,"
			+ "MODIFIED_BY,MODIFIED_TIME)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	
	public static final String SELECT_CERTIFICATE_QUERY="SELECT CERTIFICATE_ID,CERTIFICATE_NAME,CERTIFICATE_ISSUER_ID,"
			+ "CERTIFICATE_CANDIDATE_ID,CERTIFICATE_TYPE_ID,CERTIFICATE_DESC,CERTIFICATE_ISSUE_DATE,"
			+ "CERTIFICATE_END_DATE,CERTIFICATE_VERIFIED_BY_ID,CERTIFICATE_VERIFIED_DATE,CERTIFICATE_TEMPLATE_ID,"
			+ "CERTIFICATE_FILE_PATH,CERTIFICATE_SOCIAL_ACTIVITY_ID,CERTIFICATE_PREFERENCE_ID "
			+ "FROM MASTER_CERTIFICATE WHERE CERTIFICATE_ID=?";
	
	public static final String SELECT_CANDIDATE_CERTIFICATES_QUERY="SELECT CERTIFICATE_ID,CERTIFICATE_NAME,CERTIFICATE_ISSUER_ID,"
			+ "CERTIFICATE_CANDIDATE_ID,CERTIFICATE_TYPE_ID,CERTIFICATE_DESC,CERTIFICATE_ISSUE_DATE,"
			+ "CERTIFICATE_END_DATE,CERTIFICATE_VERIFIED_BY_ID,CERTIFICATE_VERIFIED_DATE,CERTIFICATE_TEMPLATE_ID,"
			+ "CERTIFICATE_FILE_PATH,CERTIFICATE_SOCIAL_ACTIVITY_ID,CERTIFICATE_PREFERENCE_ID "
			+ "FROM MASTER_CERTIFICATE WHERE CERTIFICATE_CANDIDATE_ID=?";
	
	public static final String SELECT_ISSUER_CERTIFICATES_QUERY="SELECT CERTIFICATE_ID,CERTIFICATE_NAME,CERTIFICATE_ISSUER_ID,"
			+ "CERTIFICATE_CANDIDATE_ID,CERTIFICATE_TYPE_ID,CERTIFICATE_DESC,CERTIFICATE_ISSUE_DATE,"
			+ "CERTIFICATE_END_DATE,CERTIFICATE_VERIFIED_BY_ID,CERTIFICATE_VERIFIED_DATE,CERTIFICATE_TEMPLATE_ID,"
			+ "CERTIFICATE_FILE_PATH,CERTIFICATE_SOCIAL_ACTIVITY_ID,CERTIFICATE_PREFERENCE_ID "
			+ "FROM MASTER_CERTIFICATE WHERE CERTIFICATE_ISSUER_ID=?";

	/*client query*/
	public static final String INSERT_CLIENT_QUERY = "insert into master_client values(?,?,?)";
	public static final String SELECT_CLIENT_QUERY= "select * from master_client where client_id=?";



}
