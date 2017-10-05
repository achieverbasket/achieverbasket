package com.ab.vo;

import java.util.List;

import com.ab.type.ClientType;
import com.ab.vo.login.Login;

public class Client {

	private int clientId;
	private ClientDetail clientDetail;
	private ClientType clientType;
	private Login login;
	private List<Integer> certificateIdList;//A client can have multiple certificates assigned to him
	private List<Integer> candidateIdList;//A client can have multiple candidates assigned to him

	/**
	 * @return the clientId
	 */
	public int getClientId() {
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	/**
	 * @return the clientDetail
	 */
	public ClientDetail getClientDetail() {
		return clientDetail;
	}
	/**
	 * @param clientDetail the clientDetail to set
	 */
	public void setClientDetail(ClientDetail clientDetail) {
		this.clientDetail = clientDetail;
	}
	/**
	 * @return the clientType
	 */
	public ClientType getClientType() {
		return clientType;
	}
	/**
	 * @param clientType the clientType to set
	 */
	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}
	/**
	 * @return the login
	 */
	public Login getLogin() {
		return login;
	}
	/**
	 * @param login the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}
	
	/**
	 * @return the certificateIdList
	 */
	public List<Integer> getCertificateIdList() {
		return certificateIdList;
	}
	/**
	 * @param certificateIdList the certificateIdList to set
	 */
	public void setCertificateIdList(List<Integer> certificateIdList) {
		this.certificateIdList = certificateIdList;
	}
	/**
	 * @return the candidateIdList
	 */
	public List<Integer> getCandidateIdList() {
		return candidateIdList;
	}
	/**
	 * @param candidateIdList the candidateIdList to set
	 */
	public void setCandidateIdList(List<Integer> candidateIdList) {
		this.candidateIdList = candidateIdList;
	}

}
