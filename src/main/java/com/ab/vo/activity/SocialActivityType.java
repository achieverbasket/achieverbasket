package com.ab.vo.activity;

public enum SocialActivityType {
	PROFILE(1),
	BIRTHDAY(2),
	CERTIFICATE(3),
	STORY(4),
	OTHER(5);
	
	private int activityTypeId;
	private SocialActivityType(int activityTypeId) {
		this.activityTypeId = activityTypeId;
	}
	
	public static SocialActivityType fromId(int activityTypeId) {
		for (SocialActivityType activityType : SocialActivityType.values()) {
			if(activityType.activityTypeId == activityTypeId) {
				return activityType;
			}
		}
		return SocialActivityType.OTHER;
	}

	public int getActivityTypeId() {
		return activityTypeId;
	}
}
