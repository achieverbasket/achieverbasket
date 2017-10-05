package com.ab.vo.candidate;

import org.joda.time.DateTime;

import com.ab.vo.Address;
import com.ab.vo.Gender;
import com.ab.vo.preference.PreferenceStatusType;

public class CandidatePersonalDetail {
	private Long candidatePersonalDetailId;
	private Long candidateId;
	private PreferenceStatusType preferenceStatusType;
	private String firstName;
	private String middleName;
	private String lastName;
	private DateTime dateOfBirth;
	private Address address;
	private Gender gender;
	private String email;
	
	private MaritalStatus maritalStatus;

	public Long getCandidatePersonalDetailId() {
		return candidatePersonalDetailId;
	}

	public void setCandidatePersonalDetailId(Long candidatePersonalDetailId) {
		this.candidatePersonalDetailId = candidatePersonalDetailId;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public DateTime getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(DateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public PreferenceStatusType getPreferenceStatusType() {
		return preferenceStatusType;
	}

	public void setPreferenceStatusType(PreferenceStatusType preferenceStatusType) {
		this.preferenceStatusType = preferenceStatusType;
	}

}
