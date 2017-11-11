CREATE TABLE ADDRESS	(
	ADDRESS_ID INTEGER(12) PRIMARY KEY NOT NULL AUTO_INCREMENT,
	ADDRESS_LINE_1 VARCHAR(500),
	ADDRESS_LINE_2 VARCHAR(500),
	LANDMARK VARCHAR(100),
	CITY VARCHAR(100),
	STATE VARCHAR(100),
	COUNTRY VARCHAR(100),
	ZIPCODE VARCHAR (20),
	PHONE INTEGER(12),
	SECONDARY_PHONE INTEGER(12),
	CREATED_BY INTEGER(12) NOT NULL,
	CREATED_TIME TIMESTAMP NOT NULL,
	MODIFIED_BY INTEGER(12),
	MODIFIED_TIME TIMESTAMP
);