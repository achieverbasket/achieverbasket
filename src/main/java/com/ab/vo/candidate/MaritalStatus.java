package com.ab.vo.candidate;

public enum MaritalStatus {
	SINGLE(1),
	MARRIED(2),
	OTHER(3);
	
	private int maritalStatusId;
	private MaritalStatus(int maritalStatusId) {
		this.maritalStatusId = maritalStatusId;
	}
	
	public static MaritalStatus fromId(int maritalStatusId) {
		for (MaritalStatus maritalStatus : MaritalStatus.values()) {
			if(maritalStatus.maritalStatusId == maritalStatusId) {
				return maritalStatus;
			}
		}
		return MaritalStatus.OTHER;
	}

	public int getMaritalStatusId() {
		return maritalStatusId;
	}
}
