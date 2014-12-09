--/********************************以下是同业对标报表报送系统相关表结构*********************************/--

--同业对标子标主题域表:TYDB_BUSINESS_SUBJECT  	已转移到LFS_INDEX
--同业对标组织机构表: TYDB_BASIC_DATA   		已转移到 U2_DEPARTMENT

--同业对标子标维表
CREATE TABLE TYDB_BENCHMARK_INDEX  (
   ID          			NUMBER NOT NULL,
   INDEX_ID             VARCHAR2(6)  NOT NULL,		--五位编码值
   SUBJ_ID              VARCHAR2(4),				--引用LFS_INDEX.	INDEXUUID
   SORT_NUM             NUMBER,						--排序号
   INDEX_NAME           VARCHAR2(255),				--子标名称
   PARENT_ID            VARCHAR2(4),				--父子标编号
   MEASURE_UNIT         VARCHAR2(30),				--度量单位
   DATA_SCALE           VARCHAR2(30),				--数据精确度
   SUBMIT_PERIOD        VARCHAR2(30),				--报送周期
   PROPORTION           NUMBER,						--计算权重
   IS_SUB_INDEX         VARCHAR2(1),				--是否为分公司对标指标
   USING_STATU          VARCHAR2(1),				--使用状态 1代表启用 0代表禁用
   IS_FOCUS				VARCHAR2(1),				--是否为本期关注指标 1代表是 0代表否
   CREATE_DATE          DATE,						--创建时间
   MODIFIED_DATE        DATE,						--修改时间

   CONSTRAINT PK_TYDB_BENCHMARK_INDEX PRIMARY KEY (ID)
);



--同业对标基础数据表
CREATE TABLE TYDB_BASIC_DATA  (
   ID                   NUMBER NOT NULL,	
   SUBMIT_UNIT          VARCHAR2(100), 			--上报单位
   REP_TIME             VARCHAR2(30),			--报表对应时间
   SUBMIT_PERSON        VARCHAR2(255),			--填报人
   WRITE_TIME           VARCHAR2(30),			--填报日期	
   SUBMIT_TIME          DATE,					--上报时间
   SG_INDEX_ID          NUMBER,					--国网体系指标序号
   INDEX_ID             VARCHAR2(4),			--指标编码
   ORG_ID               VARCHAR2(4),			--单位编码
   INDEX_VALUE          NUMBER,					--指标值(本期)
   INDEX_VALUE_CUMULATIVE NUMBER,				--指标值(累计)
   INDEX_COMMENT        VARCHAR2(500),			--备注

   CONSTRAINT PK_TYDB_BASIC_DATA PRIMARY KEY (ID)
);


--同业对标指标数据对比表
CREATE TABLE TYDB_INDEX_DATA_COMPARE  (
   ID                   NUMBER NOT NULL,
   PER_TIME             VARCHAR2(30),			--报表对应时间
   ORG_ID               VARCHAR2(4),			--组织构构id
   INDEX_ID             VARCHAR2(4),			--指标编码
   INDEX_VALUE          NUMBER,					--指标值

   CONSTRAINT PK_TYDB_INDEX_DATA_COMPARE PRIMARY KEY (ID)
);


--同业对标指标评阶表
CREATE TABLE TYDB_INDEX_EVALUATION  (
   ID                   NUMBER,
   EVALUATION_TIME      VARCHAR2(30),			--评价时间
   ORG_ID               VARCHAR2(4),			--组织机构id
   INDEX_ID             VARCHAR2(4),			--对标指标维表id
   NORMAL_VALUE         NUMBER,					--正态值
   CONSTRAINT PK_TYDB_INDEX_EVALUATION PRIMARY KEY (ID)
);


--同业对标子标维表修改的日志表
CREATE TABLE TYDB_BENCHMARK_INDEX2  (
   ID          			NUMBER NOT NULL,
   INDEX_ID             VARCHAR2(6)  NOT NULL,		--四位编码值
   SUBJ_ID              VARCHAR2(4),				--引用LFS_INDEX.	INDEXUUID
   SORT_NUM             NUMBER,						--排序号
   INDEX_NAME           VARCHAR2(255),				--子标名称
   PARENT_ID            VARCHAR2(4),				--父子标编号
   MEASURE_UNIT         VARCHAR2(30),				--度量单位
   DATA_SCALE           VARCHAR2(30),				--数据精确度
   SUBMIT_PERIOD        VARCHAR2(30),				--报送周期
   PROPORTION           NUMBER,						--计算权重
   IS_SUB_INDEX         NVARCHAR2(1),				--是否为分公司对标指标
   USING_STATU          VARCHAR2(1),				--使用状态
   CREATE_DATE          DATE,						--创建时间
   MODIFIED_DATE        DATE,						--MODIFIED_DATE

    CONSTRAINT TYDB_BENCHMARK_INDEX2 PRIMARY KEY (ID)
);


