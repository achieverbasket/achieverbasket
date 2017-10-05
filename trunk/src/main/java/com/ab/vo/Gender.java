package com.ab.vo;

public enum Gender {
	MALE(1),
	FEMALE(2),
	OTHER(3);
	
	private int genderId;
	private Gender(int genderId) {
		this.genderId = genderId;
	}
	
	public static Gender fromId(int genderId) {
		for (Gender gender : Gender.values()) {
			if(gender.genderId == genderId) {
				return gender;
			}
		}
		return Gender.OTHER;
	}

	public int getGenderId() {
		return genderId;
	}
}
