package com.ab.dao.impl;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ab.dao.IssuerDetailDao;
import com.ab.dao.AddressDao;
import com.ab.vo.Address;
import com.ab.vo.issuer.IssuerDetail;


	@Repository
	public class IssuerDetailDaoImpl implements IssuerDetailDao{
		
		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		@Autowired
		private AddressDao addressDao;
		
		@Autowired
		private SequenceDao sequenceDao;

		final static Logger logger = Logger.getLogger(IssuerDetailDaoImpl.class);
		
		@Override
		public IssuerDetail saveIssuerDetail(IssuerDetail issuerDetail){
			String sql = "INSERT INTO ISSUER_DETAIL (ISSUER_DETAIL_ID, ISSUER_ID, ISSUER_INCEPTION_DATE, ISSUER_END_DATE, EMAIL, MOBILE_NUMBER, ADDRESS_ID, CREATED_BY, CREATED_TIME) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, 0, SYSDATE())";
			Long issuerDetailId = sequenceDao.getNextVal("ISSUER_DETAIL_SEQ");
			Address address = addressDao.saveAddress(issuerDetail.getAddress());
			jdbcTemplate.update(sql, issuerDetailId, 
					issuerDetail.getIssuerId(), 
					issuerDetail.getIssuerInceptionDate().toDate().getTime(), 
					issuerDetail.getIssuerEndDate().toDate().getTime(), 
					address.getAddressId(),
					issuerDetail.getEmailId(),
					issuerDetail.getMobileNumber()
					);
			
			issuerDetail.setIssuerDetailId(issuerDetailId);
			issuerDetail.setAddress(address);
			return issuerDetail;
		}

		@Override
		public void updateIssuerDetail(IssuerDetail issuerDetail){
			String sql = "UPDATE ISSUER_DETAIL SET ISSUER_INCEPTION_DATE=?, ISSUER_END_DATE=?, EMAIL=?, MOBILE_NUMBER=?, MODIFIED_BY=0, MODIFIED_TIME=SYSDATE()) WHERE ISSUER_DETAIL_ID =?";
			jdbcTemplate.update(sql,  
					issuerDetail.getIssuerId(), 
					issuerDetail.getIssuerInceptionDate().toDate().getTime(), 
					issuerDetail.getIssuerEndDate().toDate().getTime(), 
					issuerDetail.getEmailId(),
					issuerDetail.getMobileNumber(),
					issuerDetail.getIssuerDetailId()
					);
		}
		
		@Override
		public IssuerDetail getIssuerDetail(Long issuerDetailId){
			String sql = "SELECT ISSUER_DETAIL_ID, ISSUER_ID, ISSUER_INCEPTION_DATE, ISSUER_END_DATE, EMAIL, MOBILE_NUMBER, ADDRESS_ID, CREATED_BY, CREATED_TIME, MODIFIED_BY, MODIFIED_TIME FROM ISSUER_DETAIL WHERE ISSUER_DETAIL_ID=?";
			return jdbcTemplate.query(sql, new Object[] {issuerDetailId}, (ResultSetExtractor<IssuerDetail>) rs -> {
					rs.next();
					IssuerDetail issuerDetail = new IssuerDetail();
					issuerDetail.setIssuerDetailId(rs.getLong("ISSUER_DETAIL_ID"));
					issuerDetail.setIssuerId(rs.getLong("ISSUER_ID"));
					issuerDetail.setIssuerInceptionDate(new DateTime(rs.getDate("INCEPTION_DATE")));
					issuerDetail.setIssuerEndDate(new DateTime(rs.getDate("END_DATE")));
					issuerDetail.setAddress(addressDao.getAddress(rs.getLong("ADDRESS_ID")));
					issuerDetail.setEmailId(rs.getString("EMAIL"));
					issuerDetail.setMobileNumber(rs.getLong("MOBILE_NUMBER"));
					return issuerDetail;
				});
		}

		@Override
		public IssuerDetail getIssuerDetailByIssuerId(Long issuerId){
			String sql = "SELECT ISSUER_DETAIL_ID, ISSUER_ID, ISSUER_INCEPTION_DATE, ISSUER_END_DATE, EMAIL, MOBILE_NUMBER, ADDRESS_ID, CREATED_BY, CREATED_TIME, MODIFIED_BY, MODIFIED_TIME FROM ISSUER_DETAIL WHERE ISSUER_ID=?";
			return jdbcTemplate.query(sql, new Object[] {issuerId}, (ResultSetExtractor<IssuerDetail>) rs -> {
				if(rs.next()){
					IssuerDetail issuerDetail = new IssuerDetail();
					issuerDetail.setIssuerDetailId(rs.getLong("ISSUER_DETAIL_ID"));
					issuerDetail.setIssuerId(rs.getLong("ISSUER_ID"));
					issuerDetail.setIssuerInceptionDate(new DateTime(rs.getDate("INCEPTION_DATE")));
					issuerDetail.setIssuerEndDate(new DateTime(rs.getDate("END_DATE")));
					issuerDetail.setAddress(addressDao.getAddress(rs.getLong("ADDRESS_ID")));
					issuerDetail.setEmailId(rs.getString("EMAIL"));
					issuerDetail.setMobileNumber(rs.getLong("MOBILE_NUMBER"));
					return issuerDetail;
				}else{
					return null;// swapnil modified this code
				}
				
			});
		}
		
		@Override
		public void removeIssuerDetail(Long issuerDetailId){
			String sql = "DELETE FROM ISSUER_DETAIL WHERE ISSUER_DETAIL_ID=?";
			jdbcTemplate.update(sql, issuerDetailId);
		}
	

}
