package com.ab.spring.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ab.constant.config.ApplicationSQLStmtConstant;
import com.ab.spring.dao.ClientDao;
import com.ab.vo.Client;
import com.ab.vo.certificate.Certificate;

@Repository
public class ClientDaoImpl implements ClientDao{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public void saveClientInfo(Client client) throws Exception
	{
		jdbcTemplate.update(ApplicationSQLStmtConstant.INSERT_CLIENT_QUERY, new Object[]{client.getClientId()});
	}
	
	public Client getClientInfo(int clientId) throws Exception
	{
		
		return jdbcTemplate.query(ApplicationSQLStmtConstant.SELECT_CLIENT_QUERY, new Object[] { clientId },
				new ResultSetExtractor<Client>() {

					@Override
					public Client extractData(ResultSet rs) throws SQLException, DataAccessException {
						Client client = new Client();
						client.setClientId(rs.getInt(1));
						return client;
					}

				});
	}		

	public List<Certificate> getCandidateCertificatesList(int candidateId)
	{
		return null;//call CertificateDAO for each certificate belongs to client
	}
	
}
