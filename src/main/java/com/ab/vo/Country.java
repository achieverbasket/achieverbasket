package com.ab.vo;

public class Country {
	
	
	public Country() {
		super();
	}

	private  long id;
	private  String name;
	private  String countryCode;
	private  int phoneCode;
	
	
	public Country(long id, String name, String countryCode, int phoneCode) {
		this.id = id;
		this.name = name;
		this.countryCode = countryCode;
		this.phoneCode = phoneCode;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public int getPhoneCode() {
		return phoneCode;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + ", countryCode=" + countryCode
				+ ", phoneCode=" + phoneCode + "]";
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
