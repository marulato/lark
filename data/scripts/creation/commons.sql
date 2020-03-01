CREATE TABLE CM_MASTER_CODE
(
    CODE_TYPE   VARCHAR(64),
    VALUE       VARCHAR(32),
    DESCRIPTION VARCHAR(256),
    ACTIVE      CHAR(1)     NOT NULL,
    CREATED_AT  DATETIME    NOT NULL,
    CREATED_BY  VARCHAR(32) NOT NULL,
    UPDATED_AT  DATETIME    NOT NULL,
    UPDATED_BY  VARCHAR(32) NOT NULL
);