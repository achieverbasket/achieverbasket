CREATE TABLE CANDIDATE	(	
	CANDIDATE_ID	INTEGER(12) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	CANDIDATE_NAME	VARCHAR(100),
	CANDIDATE_TYPE_ID INTEGER(12),
	IS_ACTIVE boolean not null default 0,
	SOCIAL_ACTIVITY_ID INTEGER(12), -- TODO modify this to become NOT NULL
	CREATED_BY INTEGER(12) NOT NULL,
	CREATED_TIME TIMESTAMP NOT NULL,
	MODIFIED_BY INTEGER(12),
	MODIFIED_TIME TIMESTAMP
);

ALTER TABLE CANDIDATE ADD FOREIGN KEY (SOCIAL_ACTIVITY_ID) REFERENCES SOCIAL_ACTIVITY(SOCIAL_ACTIVITY_ID);