create table LFS_INDEX
(
  INDEXUUID           VARCHAR(33),
  INDEXNAME           VARCHAR(100),
  INDEXORDER          int,
  INDEXURL            VARCHAR(2000),
  INDEXMAPPEDURL      VARCHAR(2000),
  PARENTINDEXUUID     VARCHAR(32),
  TARGET              VARCHAR(50),
  WAY                 VARCHAR(255),
  INDEXTYPE           int,
  INDEXICON           VARCHAR(255),
  INDEXLEVEL          int,
  DESCRIPTION         VARCHAR(255),
  HASCHILD            int,
  CREATETIME          date,
  INDEXICON_DISK_PATH VARCHAR(500),
  LEVEL_CODE          VARCHAR(10),
  APP_ID              VARCHAR(32)
);

create table LFS_IG
(
  GROUPUUID VARCHAR(32),
  INDEXUUID VARCHAR(33)
);

create table HEA_ACCESSLOG
(
  LOGID       VARCHAR(32),
  USER_UUID   VARCHAR(50),
  USER_NAME   VARCHAR(50),
  INDEX_UUID  VARCHAR(50),
  INDEX_NAME  VARCHAR(50),
  ACCESS_IP   VARCHAR(50),
  ACCESS_TIME VARCHAR(20),
  REMARK      VARCHAR(1000),
  ACCESS_TYPE VARCHAR(2),
  APP_ID      VARCHAR(100),
  APP_NAME    VARCHAR(200)
);

create table HEA_SYSTEM_CODE
(
  ID         VARCHAR(255) not null,
  REG_CODE   VARCHAR(255),
  REG_TYPE   VARCHAR(255),
  REG_NAME   VARCHAR(200) not null,
  REG_DESC   VARCHAR(500),
  REG_PROP   VARCHAR(50),
  REG_ORDER  int,
  REG_VALUE  VARCHAR(1000),
  PARENT_ID  VARCHAR(32),
  INDEXLEVEL int,
  HASCHILD   int,
  LEVEL_CODE VARCHAR(255),
  REG_LABEL  VARCHAR(255),
  APP_ID     VARCHAR(32)
);

create table BUSINESS_SYSTEM_CODE
(
  ID         VARCHAR(255) not null,
  PARENT_ID  VARCHAR(255),
  REG_CODE   VARCHAR(255),
  REG_TYPE   VARCHAR(255),
  REG_NAME   VARCHAR(200) not null,
  REG_PROP   VARCHAR(50),
  REG_ORDER  int,
  REG_VALUE  VARCHAR(255),
  REG_LABEL  VARCHAR(500),
  REG_DESC   VARCHAR(500),
  REG_STATUS VARCHAR(32),
  INDEXLEVEL int,
  HASCHILD   int,
  LEVEL_CODE VARCHAR(255),
  PARAM1     VARCHAR(255),
  PARAM2     VARCHAR(255),
  PARAM3     VARCHAR(255),
  PARAM4     VARCHAR(255),
  PARAM5     VARCHAR(255)
);
