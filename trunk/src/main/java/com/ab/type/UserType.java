package com.ab.type;

public enum UserType {
	CANDIDATE(1),
	CLIENT(2),
	ISSUER(3),
	OTHER(9);
	
	private int userTypeId;
	private UserType(int userTypeId) {
		this.userTypeId = userTypeId;
	}
	
	public static UserType fromId(int userTypeId) {
		for (UserType userType : UserType.values()) {
			if(userType.userTypeId == userTypeId) {
				return userType;
			}
		}
		return UserType.OTHER;
	}

	public int getUserTypeId() {
		return userTypeId;
	}
}
