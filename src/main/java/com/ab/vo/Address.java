package com.ab.vo;

import com.ab.vo.preference.PreferenceStatusType;

public class Address {
	private Long addressId;
	private String addressLine1;
	private String addressLine2;
	private String landmark;
	private String city;
	private String state;
	private String country;
	private String zipcode;
	private Long phonePrimary;
	private Long phoneSecondary;
	private PreferenceStatusType preferenceStatusType;

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Long getPhonePrimary() {
		return phonePrimary;
	}

	public void setPhonePrimary(Long phonePrimary) {
		this.phonePrimary = phonePrimary;
	}

	public Long getPhoneSecondary() {
		return phoneSecondary;
	}

	public void setPhoneSecondary(Long phoneSecondary) {
		this.phoneSecondary = phoneSecondary;
	}

	public PreferenceStatusType getPreferenceStatusType() {
		return preferenceStatusType;
	}

	public void setPreferenceStatusType(PreferenceStatusType preferenceStatusType) {
		this.preferenceStatusType = preferenceStatusType;
	}
}
