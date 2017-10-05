package com.ab.type;

public enum IssuerType {
	ACADEMIC(1),
	SPORT(2),
	PROFESSIONAL(3),
	MEDIA(4),
	MARATHON(5),
	EXTRA_CURRICULAM(6),
	TRAINING_INSTITUTE(7),
	OTHER(99);

	int issuerTypeId;
	
	private IssuerType(int issuerTypeId)
	{
		this.issuerTypeId = issuerTypeId;
	}
	
	public static IssuerType fromId(int issuerTypeId)
	{
		for(IssuerType issuerType: IssuerType.values())
		{
			if(issuerType.issuerTypeId==issuerTypeId)
			{
				return issuerType;
			}
		}
		return IssuerType.OTHER;
	}
	
	public int getIssuerTypeId()
	{
		return issuerTypeId;
	}
}
