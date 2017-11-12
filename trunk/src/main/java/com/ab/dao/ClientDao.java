package com.ab.dao;

import java.util.List;

import com.ab.vo.Client;
import com.ab.vo.certificate.Certificate;

public interface ClientDao {
	
String insertClientQuery = "insert into master_client values(?,?,?)";
	
	public void saveClientInfo(Client client) throws Exception;
	
	public Client getClientInfo(int clientId) throws Exception;

	public List<Certificate> getCandidateCertificatesList(int candidateId)
			throws Exception;
	
}
