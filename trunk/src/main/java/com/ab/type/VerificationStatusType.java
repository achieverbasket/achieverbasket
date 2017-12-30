package com.ab.type;

public enum VerificationStatusType {
	VER_NOT_REQUESTED(1),
	VER_REQUESTED(2),
	VER_PENDING(3),
	VERIFIED(4),
	VER_REQ_REJECTED(5),
	VER_QUERY_RAISED(6),
	OTHER(9);
	
	private int verificationStatusTypeId;
	
	private VerificationStatusType(int verificationStatusTypeId) {
		this.verificationStatusTypeId = verificationStatusTypeId;
	}
	
	public static VerificationStatusType fromId(int verificationStatusTypeId) {
		for (VerificationStatusType verificationStatusType : VerificationStatusType.values()) {
			if(verificationStatusType.verificationStatusTypeId == verificationStatusTypeId) {
				return verificationStatusType;
			}
		}
		return VerificationStatusType.OTHER;
	}

	public int getVerificationStatusTypeId() {
		return verificationStatusTypeId;
	}
}
