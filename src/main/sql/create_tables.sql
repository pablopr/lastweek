CONNECT lastweek;

DROP TABLE CATEGORY_SUBCATEGORY;
DROP TABLE CLASSIFIED_AD;
DROP TABLE PROVINCE;
DROP TABLE SUBCATEGORY;
DROP TABLE CATEGORY;
DROP TABLE USER_DATA;

CREATE TABLE USER_DATA (
  ID  BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  NAME VARCHAR(80),
  EMAIL VARCHAR(80),
  PHONE VARCHAR(20),
  STATE INTEGER NOT NULL,
  CONSTRAINT USER_MAIL_OR_PHONE_CHECK
    CHECK (EMAIL IS NOT NULL OR PHONE IS NOT NULL)
  );
 
CREATE INDEX USER_DATA_ID_INDEX ON USER_DATA (ID);
 
CREATE TABLE PROVINCE (
  ID  BIGINT NOT NULL PRIMARY KEY,
  NAME VARCHAR(30) NOT NULL UNIQUE
  );
 
CREATE INDEX PROVINCE_ID_INDEX ON PROVINCE (ID);
 
CREATE TABLE CATEGORY (
  ID  BIGINT  NULL PRIMARY KEY,
  NAME VARCHAR(30) NOT NULL UNIQUE
  );
 
CREATE INDEX CATEGORY_ID_INDEX ON CATEGORY (ID);
  
CREATE TABLE SUBCATEGORY(
  ID  BIGINT NOT NULL PRIMARY KEY,
  NAME VARCHAR(30) NOT NULL
);
  
CREATE INDEX SUBCATEGORY_ID_INDEX ON SUBCATEGORY (ID);
 
CREATE TABLE CATEGORY_SUBCATEGORY (
	CATEGORY_ID BIGINT NOT NULL,
	SUBCATEGORY_ID BIGINT NOT NULL
);

CREATE TABLE CLASSIFIED_AD (
  ID  BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  TITLE VARCHAR(80) NOT NULL,
  PRICE DOUBLE,
  DESCRIPTION VARCHAR(511),
  SOURCE_URL VARCHAR(511),
  SOURCE INTEGER NOT NULL,
  FLAG INTEGER NOT NULL DEFAULT 0,
  STATE INTEGER NOT NULL,
  CREATION_DATE TIMESTAMP NOT NULL,
  PUBLICATION_DATE TIMESTAMP NOT NULL,
  HASH_CODE VARCHAR(511) NOT NULL,
  CATEGORY_ID BIGINT NOT NULL,
  SUBCATEGORY_ID BIGINT,
  PROVINCE_ID BIGINT NOT NULL,
  USER_DATA_ID BIGINT,
  CONSTRAINT CLASSIFIED_AD_CATEGORY_ID_FK
    FOREIGN KEY(CATEGORY_ID)
    REFERENCES CATEGORY(ID),
  CONSTRAINT CLASSIFIED_AD_SUBCATEGORY_ID_FK
    FOREIGN KEY(SUBCATEGORY_ID)
    REFERENCES SUBCATEGORY(ID),
  CONSTRAINT CLASSIFIED_AD_PROVINCE_ID_FK
    FOREIGN KEY(PROVINCE_ID)
    REFERENCES PROVINCE(ID),
  CONSTRAINT CLASSIFIED_AD_USER_DATA_ID_FK
    FOREIGN KEY(USER_DATA_ID)
    REFERENCES USER_DATA(ID),
  CONSTRAINT CLASSIFIED_AD_SOURCES_CHECK
    CHECK ((SOURCE_URL IS NULL AND SOURCE = 0) OR (SOURCE_URL IS NOT NULL AND SOURCE != 0))
  );
 
CREATE INDEX CLASSIFIED_AD_ID_INDEX ON CLASSIFIED_AD (ID);
CREATE INDEX CLASSIFIED_AD_PROVINCE_INDEX ON CLASSIFIED_AD (PROVINCE_ID);
CREATE INDEX CLASSIFIED_AD_CATEGORY_INDEX ON CLASSIFIED_AD (CATEGORY_ID);
CREATE INDEX CLASSIFIED_AD_SUBCATEGORY_INDEX ON CLASSIFIED_AD (SUBCATEGORY_ID);
CREATE INDEX CLASSIFIED_AD_USER_DATA_INDEX ON CLASSIFIED_AD (USER_DATA_ID);
 
GRANT ALL PRIVILEGES ON lastweek.* TO marc@'%' IDENTIFIED BY 'marc';