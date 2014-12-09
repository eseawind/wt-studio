create table CP_CHARTPROVIDE_DIR
(
  ID        VARCHAR2(32 CHAR) not null,
  NAME      VARCHAR2(30 CHAR),
  PARENT_ID VARCHAR2(32 CHAR)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table CP_CHARTPROVIDE_DIR
  add primary key (ID)
  using index 
  tablespace $TS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table CP_CHARTPROVIDE_DIR
  add constraint FKC01788C3217044FB foreign key (PARENT_ID)
  references CP_CHARTPROVIDE_DIR (ID);

create table CP_DATASOURCE
(
  ID          VARCHAR2(32 CHAR) not null,
  NAME        VARCHAR2(30 CHAR),
  DRIVERCLASS VARCHAR2(100 CHAR),
  JDBCURL     VARCHAR2(500 CHAR),
  USER_NAME   VARCHAR2(30 CHAR),
  PASSWORD    VARCHAR2(30 CHAR),
  DS_TYPE     VARCHAR2(10)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table CP_DATASOURCE
  add primary key (ID)
  using index 
  tablespace $TS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create table CP_SQL_DIR
(
  ID        VARCHAR2(32 CHAR) not null,
  NAME      VARCHAR2(30 CHAR),
  PARENT_ID VARCHAR2(32 CHAR)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table CP_SQL_DIR
  add primary key (ID)
  using index 
  tablespace $TS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table CP_SQL_DIR
  add constraint FK8663E48A723E740E foreign key (PARENT_ID)
  references CP_SQL_DIR (ID);


create table CP_TESTDATA
(
  EMPNO    NUMBER(4),
  ENAME    VARCHAR2(10),
  JOB      VARCHAR2(9),
  MGR      NUMBER(4),
  HIREDATE DATE,
  SAL      NUMBER(7,2),
  COMM     NUMBER(7,2),
  DEPTNO   NUMBER(2)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );





create table EXL_V2_DATABLOCK_INFO
(
  DB_ID      VARCHAR2(32) not null,
  RPT_ID     VARCHAR2(32),
  DB_NO      VARCHAR2(50),
  START_SITE VARCHAR2(50),
  END_STIE   VARCHAR2(50)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create table EXL_V2_DATASOURCE
(
  DS_ID        VARCHAR2(32) not null,
  DS_TYPE      VARCHAR2(8),
  TABLE_NAME   VARCHAR2(256),
  TABLE_REMARK VARCHAR2(256),
  SQL_CONTENT  VARCHAR2(4000)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

create table EXL_V2_DB_DS_INFO
(
  DB_DS_ID VARCHAR2(32) not null,
  DB_ID    VARCHAR2(32),
  DS_ID    VARCHAR2(32),
  ELT      VARCHAR2(255)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create table EXL_V2_PROPERTY
(
  PROP_ID          VARCHAR2(32),
  IF_LOCK          VARCHAR2(1),
  TYPE_CODE        VARCHAR2(2),
  TYPE_STYLE       VARCHAR2(50),
  CENTER           VARCHAR2(2),
  FONT             VARCHAR2(50),
  FONT_COLOR       VARCHAR2(50),
  BACKGROUND_COLOR VARCHAR2(50),
  ANNOTATION       VARCHAR2(2000),
  IF_BOLD          VARCHAR2(1),
  FONT_SIZE        VARCHAR2(2),
  WIDTH            VARCHAR2(20),
  HEIGHT           VARCHAR2(20),
  CELL_TYPE        VARCHAR2(20),
  IF_SHOW          VARCHAR2(4),
  CAPTION_TYPE     VARCHAR2(4)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );



create table EXL_V2_RPT_INFO
(
  RPT_ID      VARCHAR2(32) not null,
  RPT_NO      VARCHAR2(32),
  RPT_NAME    VARCHAR2(256),
  RPT_STATUS  VARCHAR2(8),
  CREATE_TIME DATE,
  MODIFY_TIME DATE,
  SHEET_NAME  VARCHAR2(100),
  DISK_PATH   VARCHAR2(1000)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );



create table EXL_V2_TITLE
(
  TITLE_ID        VARCHAR2(32),
  RPT_ID          VARCHAR2(32),
  BLOCK_ID        VARCHAR2(32),
  PROPERTY_ID     VARCHAR2(32),
  CONTENT         VARCHAR2(200),
  START_SITE      VARCHAR2(20),
  END_SITE        VARCHAR2(20),
  DB_TABLE        VARCHAR2(50),
  DB_FILED        VARCHAR2(50),
  IS_CELL_MAPPING VARCHAR2(10),
  IMP_TABLE       VARCHAR2(20),
  IMP_FILED       VARCHAR2(20)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );



create table EXL_V2_TMPL_INFO
(
  TMPL_ID     VARCHAR2(32) not null,
  TMPL_NO     VARCHAR2(32),
  TMPL_NAME   VARCHAR2(256),
  TMPL_CODE   VARCHAR2(8),
  TMPL_STATUS VARCHAR2(8),
  TMPL_ADDR   VARCHAR2(256),
  CREATE_TIME DATE,
  MODIFY_TIME DATE,
  REMARK      VARCHAR2(256)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );



create table EXL_V2_TMPL_RPT_INFO
(
  TMPL_RPT_ID VARCHAR2(32),
  TMPL_ID     VARCHAR2(32),
  RPT_ID      VARCHAR2(32),
  DISP_SN     VARCHAR2(8),
  SHEET_NAME  VARCHAR2(256)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create table E_A_ACCESSORY
(
  UUID                  VARCHAR2(33 CHAR) not null,
  TRANSFORMER_UUID      VARCHAR2(33 CHAR),
  EQPT_LOG_NO           VARCHAR2(32),
  ACCESSORIES_NAME      VARCHAR2(64),
  ACCESSORY_MODEL       VARCHAR2(64),
  ACCESSORY_NUM         NUMBER(5),
  MANUFACTURER          VARCHAR2(256),
  TRANSITION_RESISTANCE NUMBER(5),
  OUT_MANU_NO           VARCHAR2(64),
  OUT_MANU_DATE         DATE,
  MODIFY_DATE           DATE,
  VALID_FLAG            VARCHAR2(32)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

alter table E_A_ACCESSORY
  add constraint PK_E_A_ACCESSORY primary key (UUID)
  using index 
  tablespace $TS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create table E_A_BREAK_DISCONNECTOR
(
  BREAK_DISCONNECTOR_UUID VARCHAR2(33 CHAR) not null,
  EQPT_LEDGER_UUID        VARCHAR2(33 CHAR),
  INTERVAL_UNIT           VARCHAR2(256),
  MAIN_PARAM              VARCHAR2(4000),
  REMARK                  VARCHAR2(4000)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

alter table E_A_BREAK_DISCONNECTOR
  add constraint PK_E_A_BREAK_DISCONNECTOR primary key (BREAK_DISCONNECTOR_UUID)
  using index 
  tablespace $TS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create table E_A_BUSHING
(
  UUID             VARCHAR2(33 CHAR) not null,
  TRANSFORMER_UUID VARCHAR2(33 CHAR),
  EQPT_LOG_NO      VARCHAR2(32),
  VOLTAGE          VARCHAR2(64),
  PHASE_SEQUENCE   VARCHAR2(64),
  EQPT_MODEL       VARCHAR2(64),
  EQPT_CODE        VARCHAR2(64),
  CAPACITANCE      NUMBER(5),
  MANUFACTURER     VARCHAR2(256),
  OUT_MANU_DATE    DATE,
  MODIFY_DATE      DATE,
  VALID_FLAG       VARCHAR2(32)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );



create table E_A_EQPT_LEDGER
(
  EQPT_LEDGER_UUID      VARCHAR2(33 CHAR) not null,
  EQPT_P_NO             VARCHAR2(64),
  EQPT_NO               VARCHAR2(64),
  EQPT_NAME             VARCHAR2(256),
  OPERATE_COMPANY       VARCHAR2(256),
  OPERATE_NO            VARCHAR2(256),
  SUBSTATION            VARCHAR2(256),
  EQPT_MODEL            VARCHAR2(64),
  MANUFACTURER          VARCHAR2(256),
  FACTORY_SERIAL_NUMBER VARCHAR2(64),
  PRODUCT_CODE          VARCHAR2(256),
  RELEASE_DATE          DATE,
  RUNNING_DATE          DATE,
  EQPT_TYPE             VARCHAR2(32),
  EQPT_STATUS           VARCHAR2(32),
  EQMT_TYPE             NUMBER(5)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );



create table E_A_EQPT_LEDGER_STATUS
(
  UUID             VARCHAR2(33 CHAR) not null,
  TRANSFORMER_UUID VARCHAR2(33 CHAR),
  EQPT_LOG_NO      VARCHAR2(32),
  STATUS_DATE      DATE,
  STATUS_RECORD    VARCHAR2(4000),
  VALID_FLAG       VARCHAR2(32)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

alter table E_A_EQPT_LEDGER_STATUS
  add constraint PK_E_A_EQPT_LEDGER_STATUS primary key (UUID)
  using index 
  tablespace $TS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create table E_A_TRANSFORMER
(
  TRANSFORMER_UUID       VARCHAR2(33 CHAR) not null,
  UUID                   VARCHAR2(33 CHAR),
  INSULATION_LEVEL       NUMBER(5),
  COOL_MODE              VARCHAR2(256),
  CONN_GROUP_NO          VARCHAR2(64),
  VOLTAGE                VARCHAR2(64),
  VOLTAGE_RATIO          VARCHAR2(256),
  CAPACITANCE_RATIO      VARCHAR2(256),
  NO_LOAD_CURRENT        NUMBER(15),
  NO_LOAD_LOSS           NUMBER(15),
  RATE_CURRENT_H         NUMBER(15),
  RATE_CURRENT_M         NUMBER(15),
  RATE_CURRENT_L         NUMBER(15),
  RESISTANCE_VOLTAGE_H_M NUMBER(15),
  RESISTANCE_VOLTAGE_H_L NUMBER(15),
  RESISTANCE_VOLTAGE_M_L NUMBER(15),
  LOAD_LOSS_H_M          NUMBER(15),
  LOAD_LOSS_H_L          NUMBER(15),
  LOAD_LOSS_M_L          NUMBER(15),
  TRAN_WEIGHT_N          NUMBER(5),
  TRAN_WEIGHT_OIL        NUMBER(5),
  TANK_WEIGHT            NUMBER(5),
  BODY_WEIGHT            NUMBER(5),
  OIL_WEIGHT             NUMBER(5),
  SF6_WEIGHT             NUMBER(5),
  ALL_WEIGHT             NUMBER(5)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );



create table FLASH_MAP
(
  YD_MONTH  VARCHAR2(7),
  DQ        VARCHAR2(100),
  MONTH_RDL NUMBER,
  DQ_BM     CHAR(2)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create table HEA_ACCESSLOG
(
  LOGID       VARCHAR2(32) not null,
  USER_UUID   VARCHAR2(50),
  USER_NAME   VARCHAR2(50),
  INDEX_UUID  VARCHAR2(50),
  INDEX_NAME  VARCHAR2(50),
  ACCESS_IP   VARCHAR2(50),
  ACCESS_TIME VARCHAR2(20) default sysdate,
  REMARK      VARCHAR2(1000),
  ACCESS_TYPE VARCHAR2(2),
  APP_ID      VARCHAR2(100),
  APP_NAME    VARCHAR2(200)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

create table HEA_APPS
(
  APP_ID      VARCHAR2(32) not null,
  APP_NO      VARCHAR2(32),
  APP_NAME    VARCHAR2(256),
  APP_DESC    VARCHAR2(256),
  APP_TYPE    VARCHAR2(16),
  APP_ADDR    VARCHAR2(256),
  APP_CATALOG VARCHAR2(1000),
  DB_INFO     VARCHAR2(256),
  DB_USER     VARCHAR2(32),
  DB_PASSWORD VARCHAR2(32),
  APP_STATUS  VARCHAR2(16),
  RUN_STATUS  VARCHAR2(16)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

alter table HEA_APPS
  add constraint PK_HEA_APPS primary key (APP_ID)
  using index 
  tablespace $TS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create table HEA_SYSTEM_CODE
(
  ID         VARCHAR2(255) not null,
  REG_CODE   VARCHAR2(255),
  REG_TYPE   VARCHAR2(255),
  REG_NAME   VARCHAR2(200) not null,
  REG_DESC   VARCHAR2(500),
  REG_PROP   VARCHAR2(50),
  REG_ORDER  NUMBER(10),
  REG_VALUE  VARCHAR2(1000),
  PARENT_ID  VARCHAR2(32),
  INDEXLEVEL NUMBER(10),
  HASCHILD   NUMBER(10),
  LEVEL_CODE VARCHAR2(255),
  REG_LABEL  VARCHAR2(255),
  APP_ID     VARCHAR2(32)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table HEA_SYSTEM_CODE
  add primary key (ID)
  using index 
  tablespace $TS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table HEA_SYSTEM_CODE
  add constraint FKB73CBD229DFCF1C3 foreign key (PARENT_ID)
  references HEA_SYSTEM_CODE (ID);


create table LFS_IG
(
  GROUPUUID VARCHAR2(32 CHAR) not null,
  INDEXUUID VARCHAR2(33 CHAR) not null
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table LFS_IG
  add primary key (INDEXUUID, GROUPUUID)
  using index 
  tablespace $TS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create table LFS_INDEX
(
  INDEXUUID           VARCHAR2(33 CHAR) not null,
  INDEXNAME           VARCHAR2(100 CHAR),
  INDEXORDER          NUMBER,
  INDEXURL            VARCHAR2(2000 CHAR),
  INDEXMAPPEDURL      VARCHAR2(2000 CHAR),
  PARENTINDEXUUID     VARCHAR2(32 CHAR),
  TARGET              VARCHAR2(50 CHAR),
  WAY                 VARCHAR2(255 CHAR),
  INDEXTYPE           NUMBER(10),
  INDEXICON           VARCHAR2(255 CHAR),
  INDEXLEVEL          NUMBER(19),
  DESCRIPTION         VARCHAR2(255 CHAR),
  HASCHILD            NUMBER,
  CREATETIME          DATE,
  INDEXICON_DISK_PATH VARCHAR2(500),
  LEVEL_CODE          VARCHAR2(2000),
  APP_ID              VARCHAR2(32)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create table P_LAYOUT_DEFINITION
(
  LAYOUT_CODE    VARCHAR2(32 CHAR) not null,
  LAYOUT_NAME    VARCHAR2(256 CHAR),
  LAYOUT_CONTENT VARCHAR2(256 CHAR),
  LAYOUT_PICPATH VARCHAR2(256 CHAR),
  CREATE_DATE    DATE,
  MODIFY_DATE    DATE
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table P_LAYOUT_DEFINITION
  add primary key (LAYOUT_CODE)
  using index 
  tablespace $TS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create table P_PLACEHOLDER_GROUP
(
  PG_ID          VARCHAR2(32 CHAR) not null,
  PLACEHOLDER_ID VARCHAR2(255 CHAR),
  GROUP_ID       VARCHAR2(255 CHAR),
  TMPL_ID        VARCHAR2(255 CHAR)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table P_PLACEHOLDER_GROUP
  add primary key (PG_ID)
  using index 
  tablespace $TS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create table P_POP_INFO
(
  POP_ID      VARCHAR2(32) not null,
  SITE_ID     VARCHAR2(32),
  TOP_ID      VARCHAR2(32),
  POP_TITLE   VARCHAR2(256),
  POP_CONTENT VARCHAR2(4000),
  POP_STATUS  VARCHAR2(16),
  START_TIME  VARCHAR2(50),
  END_TIME    VARCHAR2(50),
  POP_URL     VARCHAR2(1000),
  HEIGHT      NUMBER(5),
  WIDTH       NUMBER(5)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create table P_PORTLET_PROPERTY
(
  PORTLET_ID   VARCHAR2(32 CHAR) not null,
  PORTLET_NAME VARCHAR2(256 CHAR),
  FUNC_ACTION  VARCHAR2(256 CHAR),
  PORTLET_TYPE VARCHAR2(16 CHAR),
  EDIT_ACTION  VARCHAR2(256 CHAR),
  TITLE_URL    VARCHAR2(256 CHAR),
  PORTLET_DESC VARCHAR2(1024 CHAR),
  WIDTH        NUMBER(10),
  HEIGHT       NUMBER(10),
  HTML_CODE    VARCHAR2(4000 CHAR)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table P_PORTLET_PROPERTY
  add primary key (PORTLET_ID)
  using index 
  tablespace $TS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create table P_RESOURCES
(
  RES_ID     VARCHAR2(32 CHAR) not null,
  RES_TYPE   VARCHAR2(255 CHAR),
  RES_NAME   VARCHAR2(255 CHAR),
  RES_PATH   VARCHAR2(255 CHAR),
  RES_STATUS VARCHAR2(255 CHAR),
  RES_WIDTH  NUMBER(10),
  RES_HEIGHT NUMBER(10),
  FILE_SIZE  NUMBER(19),
  SITE_ID    VARCHAR2(255 CHAR),
  SITE_NAME  VARCHAR2(255 CHAR),
  RES_CODE   VARCHAR2(255 CHAR),
  APP_ID     VARCHAR2(255)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table P_RESOURCES
  add primary key (RES_ID)
  using index 
  tablespace $TS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create table P_SITE_MANAGE
(
  SITE_ID           VARCHAR2(32 CHAR) not null,
  SITE_NO           VARCHAR2(255 CHAR),
  SITE_NAME         VARCHAR2(255 CHAR),
  P_SITE_NO         VARCHAR2(255 CHAR),
  P_SITE_NAME       VARCHAR2(255 CHAR),
  DISP_SN           NUMBER(10),
  SITE_STATUS       VARCHAR2(255 CHAR),
  CREATE_TIME       TIMESTAMP(6),
  USER_NO           VARCHAR2(255 CHAR),
  DEPT_NO           VARCHAR2(255 CHAR),
  SITE_ADDR         VARCHAR2(255 CHAR),
  LOGO_PATH         VARCHAR2(255 CHAR),
  COPYRIGHT_CONTENT VARCHAR2(255 CHAR),
  TYPE_CODE         VARCHAR2(255 CHAR),
  NAVI_ID           VARCHAR2(255 CHAR),
  APP_ID            VARCHAR2(255)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table P_SITE_MANAGE
  add primary key (SITE_ID)
  using index 
  tablespace $TS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table P_SITE_MANAGE
  add constraint FK21A8AFEECB2F2617 foreign key (P_SITE_NO)
  references P_SITE_MANAGE (SITE_ID);


create table P_SYS_PARAM
(
  CODE_ID    VARCHAR2(32 CHAR) not null,
  CODE_TYPE  VARCHAR2(255 CHAR),
  CODE_VALUE VARCHAR2(255 CHAR),
  CODE_NAME  VARCHAR2(255 CHAR),
  P_CODE     VARCHAR2(255 CHAR),
  DISP_SN    NUMBER(10),
  CONTENT1   VARCHAR2(255 CHAR),
  CONTENT2   VARCHAR2(255 CHAR),
  CONTENT3   VARCHAR2(255 CHAR),
  CONTENT4   VARCHAR2(255 CHAR),
  CONTENT5   VARCHAR2(255 CHAR)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table P_SYS_PARAM
  add primary key (CODE_ID)
  using index 
  tablespace $TS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create table P_THEME_DEFINITION
(
  THEME_CODE    VARCHAR2(32 CHAR) not null,
  THEME_NAME    VARCHAR2(256 CHAR),
  THEME_PICPATH VARCHAR2(256 CHAR),
  THEME_CONTENT VARCHAR2(256 CHAR),
  CREATE_DATE   DATE,
  MODIFY_DATE   DATE,
  THEME_PATH    VARCHAR2(255 CHAR),
  APP_ID        VARCHAR2(255)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table P_THEME_DEFINITION
  add primary key (THEME_CODE)
  using index 
  tablespace $TS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create table P_TMPL
(
  TMPL_ID         VARCHAR2(32 CHAR) not null,
  TMPL_NAME       VARCHAR2(100 CHAR),
  TMPL_ADDR       VARCHAR2(4000 CHAR),
  TMPL_STATUS     VARCHAR2(10 CHAR),
  TMPL_CODE       VARCHAR2(8 CHAR),
  TMPL_PIC_ADDR   VARCHAR2(255 CHAR),
  DISP_SN         NUMBER(10),
  CREATE_TIME     TIMESTAMP(6),
  UPDATE_TIME     TIMESTAMP(6),
  TMPL_STYLE_ADDR VARCHAR2(255 CHAR),
  APP_ID          VARCHAR2(255)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table P_TMPL
  add primary key (TMPL_ID)
  using index 
  tablespace $TS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create table P_USER_PAGE
(
  PAGE_ID     VARCHAR2(32 CHAR) not null,
  PAGE_NO     VARCHAR2(32 CHAR),
  PAGE_TITLE  VARCHAR2(256 CHAR),
  DISP_SN     NUMBER(10),
  LAYOUT_CODE VARCHAR2(32 CHAR),
  THEME_CODE  VARCHAR2(32 CHAR),
  USER_ID     VARCHAR2(256 CHAR),
  USER_NO     VARCHAR2(256 CHAR),
  USER_NAME   VARCHAR2(256 CHAR),
  IF_DEFAULT  NUMBER(10)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table P_USER_PAGE
  add primary key (PAGE_ID)
  using index 
  tablespace $TS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create table P_USER_PORTLETINFO
(
  UP_ID          VARCHAR2(32 CHAR) not null,
  PAGE_ID        VARCHAR2(255 CHAR),
  PORTLET_ID     VARCHAR2(32 CHAR),
  SHOW_NAME      VARCHAR2(256 CHAR),
  USER_ID        VARCHAR2(256 CHAR),
  USER_NO        VARCHAR2(256 CHAR),
  USER_NAME      VARCHAR2(256 CHAR),
  FUNC_ID        VARCHAR2(16 CHAR),
  FUNC_NAME      VARCHAR2(256 CHAR),
  FUNC_ACTION    VARCHAR2(256 CHAR),
  PORTLET_STATUS VARCHAR2(8 CHAR),
  SORT_NO        NUMBER(10),
  ROW_NO         NUMBER(10),
  DISP_SN        NUMBER(10),
  EDIT_ACTION    VARCHAR2(256 CHAR),
  TITLE_URL      VARCHAR2(256 CHAR)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table P_USER_PORTLETINFO
  add primary key (UP_ID)
  using index 
  tablespace $TS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table P_USER_PORTLETINFO
  add constraint FK_USER_POR_PORTLET_P_PORTLET_ foreign key (PORTLET_ID)
  references P_PORTLET_PROPERTY (PORTLET_ID);
alter table P_USER_PORTLETINFO
  add constraint FK_USER_POR_USER_PERS_USER_PAG foreign key (PAGE_ID)
  references P_USER_PAGE (PAGE_ID);
  
create table BUSINESS_SYSTEM_CODE
(
  ID         VARCHAR2(255) not null,
  PARENT_ID  VARCHAR2(255),
  REG_CODE   VARCHAR2(255),
  REG_TYPE   VARCHAR2(255),
  REG_NAME   VARCHAR2(200) not null,
  REG_DESC   VARCHAR2(500),
  REG_PROP   VARCHAR2(50),
  REG_ORDER  NUMBER(10),
  REG_VALUE  VARCHAR2(1000),
  REG_STATUS VARCHAR2(32),
  INDEXLEVEL NUMBER(10),
  HASCHILD   NUMBER(10),
  LEVEL_CODE VARCHAR2(255),
  REG_LABEL  VARCHAR2(255),
  PARAM1     VARCHAR2(255 CHAR),
  PARAM2     VARCHAR2(255 CHAR),
  PARAM3     VARCHAR2(255 CHAR),
  PARAM4     VARCHAR2(255 CHAR),
  PARAM5     VARCHAR2(255 CHAR)
)
tablespace $TS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table BUSINESS_SYSTEM_CODE
  add primary key (ID)
  using index 
  tablespace $TS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table BUSINESS_SYSTEM_CODE
  add constraint FKA4D2C1C29DFCF1C3 foreign key (PARENT_ID)
  references BUSINESS_SYSTEM_CODE (ID);  