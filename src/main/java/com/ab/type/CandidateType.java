package com.ab.type;

public enum CandidateType {
	

	CANDIDATE(1),
	OTHER(2);
	
	private int candidateTypeId;
	private CandidateType(int candidateTypeId) {
		this.candidateTypeId = candidateTypeId;
	}
	
	public static CandidateType fromId(int candidateTypeId) {
		for (CandidateType candidateType : CandidateType.values()) {
			if(candidateType.candidateTypeId == candidateTypeId) {
				return candidateType;
			}
		}
		return CandidateType.OTHER;
	}

	public int getCandidateTypeId() {
		return candidateTypeId;
	}
}
