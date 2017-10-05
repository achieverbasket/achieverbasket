package com.ab.vo.verification;

import java.io.Serializable;

import org.joda.time.DateTime;

public class Verification implements Serializable{
	private static final long serialVersionUID = -3119670890920570688L;
	private int verificationId;
	private int verifiedById;
	private DateTime verifiedOnTime;
	private byte[] verificationSeal;

	public int getVerificationId() {
		return verificationId;
	}

	public void setVerificationId(int verificationId) {
		this.verificationId = verificationId;
	}

	public int getVerifiedById() {
		return verifiedById;
	}

	public void setVerifiedById(int verifiedById) {
		this.verifiedById = verifiedById;
	}

	public DateTime getVerifiedOnTime() {
		return verifiedOnTime;
	}

	public void setVerifiedOnTime(DateTime verifiedOnTime) {
		this.verifiedOnTime = verifiedOnTime;
	}

	public byte[] getVerificationSeal() {
		return verificationSeal;
	}

	public void setVerificationSeal(byte[] verificationSeal) {
		this.verificationSeal = verificationSeal;
	}	
}
