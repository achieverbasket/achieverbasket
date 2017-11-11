package com.ab.vo.preference;

public enum PreferenceStatusType {
	PRIVATE(1,"PRIVATE"),
	PROTECTED(2,"PROTECTED"),
	PUBLIC(3,"PUBLIC"),
	OTHER(9,"OTHER");
	private int preferenceStatusTypeId;
	private String preferenceStatusTypeName;
	
	private PreferenceStatusType(int preferenceStatusTypeId,String name)
	{
		this.preferenceStatusTypeId = preferenceStatusTypeId;
		this.preferenceStatusTypeName = name;
	}
	
	public static PreferenceStatusType fromId(int preferenceStatusTypeId) {
		for (PreferenceStatusType preferenceStatusType : PreferenceStatusType.values()) {
			if(preferenceStatusType.preferenceStatusTypeId == preferenceStatusTypeId) {
				return preferenceStatusType;
			}
		}
		return PreferenceStatusType.OTHER;
	}
	
	

	public String getPreferenceStatusTypeName() {
		return preferenceStatusTypeName;
	}

	public void setPreferenceStatusTypeName(String preferenceStatusTypeName) {
		this.preferenceStatusTypeName = preferenceStatusTypeName;
	}

	public int getPreferenceStatusTypeId() {
		return preferenceStatusTypeId;
	}

	public void setPreferenceStatusTypeId(int preferenceStatusTypeId) {
		this.preferenceStatusTypeId = preferenceStatusTypeId;
	}
}
