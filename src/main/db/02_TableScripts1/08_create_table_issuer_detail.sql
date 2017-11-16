CREATE TABLE ISSUER_DETAIL	(
	ISSUER_DETAIL_ID INTEGER(12) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	ISSUER_ID	INTEGER(12) NOT NULL,
	ISSUER_INCEPTION_DATE DATE,
	ISSUER_END_DATE DATE,
	EMAIL VARCHAR(100),
	ADDRESS_ID INTEGER(12),
	CREATED_BY INTEGER(12) NOT NULL,
	CREATED_TIME TIMESTAMP NOT NULL,
	MODIFIED_BY INTEGER(12),
	MODIFIED_TIME TIMESTAMP
);

ALTER TABLE ISSUER_DETAIL ADD FOREIGN KEY (ISSUER_ID) REFERENCES ISSUER(ISSUER_ID);
ALTER TABLE ISSUER_DETAIL ADD FOREIGN KEY (ADDRESS_ID) REFERENCES ADDRESS(ADDRESS_ID);