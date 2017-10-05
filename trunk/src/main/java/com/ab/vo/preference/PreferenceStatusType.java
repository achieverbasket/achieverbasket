package com.ab.vo.preference;

public enum PreferenceStatusType {
	PRIVATE(1),
	PROTECTED(2),
	PUBLIC(3),
	OTHER(9);
	private int preferenceStatusTypeId;
	private PreferenceStatusType(int preferenceStatusTypeId)
	{
		this.preferenceStatusTypeId = preferenceStatusTypeId;
	}
	
	public static PreferenceStatusType fromId(int preferenceStatusTypeId) {
		for (PreferenceStatusType preferenceStatusType : PreferenceStatusType.values()) {
			if(preferenceStatusType.preferenceStatusTypeId == preferenceStatusTypeId) {
				return preferenceStatusType;
			}
		}
		return PreferenceStatusType.OTHER;
	}
	
	public Integer getPreferenceStatusValue()
	{
		return preferenceStatusTypeId;
	}
}
