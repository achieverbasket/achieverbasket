package com.ab.vo;

public class ClientDetail {

	private int clientid;
	private String name;
	private String desc;
	private Address address;
	/**
	 * @return the clientid
	 */
	public int getClientid() {
		return clientid;
	}
	/**
	 * @param clientid the clientid to set
	 */
	public void setClientid(int clientid) {
		this.clientid = clientid;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

}
