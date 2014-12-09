--/*****************应用框架表结构整理:袁明敏,by:2010-11-08***********************/--
-- Create  sequence for Hibernate
create sequence HIBERNATE_SEQUENCE
minvalue 1
maxvalue 999999999999999999999999999
start with 21
increment by 1
cache 20;

--系统日志表，代码级，所有代码运行信息将保存到此表
CREATE TABLE HEA_SYSTEMLOG
(
  LOGTIME TIMESTAMP (6), 
  NUM NUMBER(38), 
  PRIORITY VARCHAR2(5), 
  CLASSNAME VARCHAR2(500), 
  LINE NUMBER, 
  MSG VARCHAR2(4000), 
  THROW VARCHAR2(4000)
);
CREATE UNIQUE INDEX "HEA_SYSTEMLOG_INDEX" ON "HEA_SYSTEMLOG" ("LOGTIME", "NUM");


--系统常量表
create table EDC_SYSTEM_CODE
(
  ID        NUMBER,
  REG_CODE  VARCHAR2(50 CHAR) not null,
  REG_TYPE  VARCHAR2(50 CHAR) not null,
  REG_NAME  VARCHAR2(200 CHAR) not null,
  REG_DESC  VARCHAR2(500 CHAR),
  REG_PROP  VARCHAR2(50 CHAR),
  REG_ORDER NUMBER,
  REG_VALUE VARCHAR2(1000 CHAR)
)


--RBAC资源表
create table LFS_INDEX
(
  INDEXUUID           VARCHAR2(32 CHAR),			
  INDEXNAME           VARCHAR2(100 CHAR),
  INDEXORDER          NUMBER,						--资源排序号
  INDEXURL            VARCHAR2(2000 CHAR),			--资源展现url
  INDEXMAPPEDURL      VARCHAR2(2000 CHAR),			--资源重写url或添加参数的url
  PARENTINDEXUUID     VARCHAR2(32 CHAR),			--父项资源id
  TARGET              VARCHAR2(50 CHAR),			--资源跳转目标
  WAY                 VARCHAR2(255 CHAR),			--是否禁用, 0 禁用  1启用
  INDEXTYPE           NUMBER(10),					--资源类型, 0是指标  1是控件  2是站点   3是菜单  04(文件模板)类别  5管理指标
  INDEXICON           VARCHAR2(255 CHAR),			--资源图标全http路径
  INDEXLEVEL          NUMBER(19),					--资源层级数
  DESCRIPTION         VARCHAR2(255 CHAR),			--资源描述
  HASCHILD            NUMBER,						--是否有子级资源
  CREATETIME          DATE,							--资源建立时间
  INDEXICON_DISK_PATH VARCHAR2(500)					--资源图标存储的服务器磁盘绝对路径
)





--组织机构表
create table U2_DEPARTMENT
(
  UUID       VARCHAR2(32 CHAR) not null,
  DEPT_NAME  VARCHAR2(50 CHAR),						--部门名称
  ORDERNUM   NUMBER(10),							--排序号
  PARENTUUID VARCHAR2(255 CHAR),					--父组织机构id
  DEPT_LEVEL NUMBER,								--组织机构层级结构
  HASCHILD   NUMBER									--是否有下级部门
)

--用户表
create table U2_USER
(
  UUID                  VARCHAR2(32 CHAR) not null,	
  ID                    VARCHAR2(255 CHAR),				--登陆用户账号
  MOBILE                VARCHAR2(255 CHAR),				--用户手机号
  UNAME                  VARCHAR2(255 CHAR),			--用户名称
  UPASSWORD              VARCHAR2(255 CHAR),			--用户密码
  PRIMARYDEPARTMENTUUID VARCHAR2(255 CHAR)				--用户所属的部门(组织机构)编号
)



--###############################数据中心Excel导入功能表相关#####################################
--数据库登陆用户表(用于用户登陆配置Excel指标和外键值)
create table EDC_DB_USER_CONFIG
(
  ID               NUMBER not null,
  DATA_SOURCE_NAME VARCHAR2(100),
  USER_NAME        VARCHAR2(50),
  USER_COMMENT     VARCHAR2(200),
  USER_PASSWORD    VARCHAR2(200),
  DATA_SOURCE_URL  VARCHAR2(200)
)

--Excel数据导入日志表
create table EDC_IMPORT_LOGS
(
  ID            NUMBER(16) not null,
  IMPORT_TYPE   VARCHAR2(4),
  IMPORT_STATUS VARCHAR2(4),
  IMPORT_DESC   VARCHAR2(500),
  USER_ID       VARCHAR2(50),
  IMPORT_TIME   VARCHAR2(50),
  DATAFILE_URL  VARCHAR2(1000)
)


--模板文件里的数据块表

create table EDC_TEMPLATE_DATA_BLOCK
(
  ID                     NUMBER(16) not null,
  DATA_BLOCK_NAME        VARCHAR2(150) not null,			--数据块名称
  TABLE_CODE			 VARCHAR2(35),						--数据块映射的数据库表名
  DATA_BLOCK_TYPE        CHAR(2) not null,					--数据块所属的类型：10，Excel；20，XML；30，TXT；
  TEMPLATE_FILE_NAME     VARCHAR2(200) not null,			--数据块所属的模板文件名(用于查询的冗余字段)
  WORK_SHEET             VARCHAR2(200),						--数据块所在的sheet表
  TITLE_KEY              VARCHAR2(200),						--标题关键字
  TITLE_AREA               VARCHAR2(20),					--标题关键字范围
  START_COORDINATE       VARCHAR2(20),						--数据块起始坐标
  END_CONDITION          VARCHAR2(20),						--解析终止条件：10、以连续空行数终止（默认）；20、以指定记录数终止；30、以指定字符标识终止；40、读取sheet末端   例如:10:2 表示以连续空两行结束解析; 20:2 表示以2条记录结束，30:strFlag 表示以指定的字符结束 40:end 读取到sheet末端
  FILTER_RULE            VARCHAR2(500),						--数据过滤规则：导入时需过滤的数据，由正则表达式定义；
  DATA_BLOCK_COMMENT     VARCHAR2(1000),					--数据块备注信息
  DATA_BLOCK_STATUS      CHAR(2) not null,					--数据块启用状态： 10激活  20锁定
  TITLE_KEY_HOLDER_VALUE VARCHAR2(2000)						--数据块标题里的文字占位符,值内容Hand：手工输入， sql：sql语句查询聚合值 ，cell：Excel单元格的值
)

--(Excel)模板文件
create table EDC_TEMPLATE_FILE
(
  ID           NUMBER(16) not null,
  FILE_SIZE    LONG,
  FILE_TYPE    VARCHAR2(30) not null,			--10为Excel数据文件   20为XML    30为TXT   40为Excel模版文件
  FILE_PATH    VARCHAR2(200) not null,			--上传后的全路径URL地址
  FILE_NAME    VARCHAR2(200),					--原始文件名称
  UPLOAD_DATE  NUMBER,
  USER_ID      VARCHAR2(60),
  USER_NAME    VARCHAR2(50),
  IS_PUBLISH   VARCHAR2(4) not null,			--01 模板文件草稿状态  02 发布状态 
  UPDATED_DATE NUMBER,
  REMARK       VARCHAR2(1000)					--备注
)



--模板数据块反复导入记录的删除条件配置表
create table EDC_TMPL_DATABLOCK_CONDIT
(
  ID            NUMBER not null,
  TABLE_CODE    VARCHAR2(30),		--数据块的表名（用于查询的冗余字段)
  COLUMN_CODE   VARCHAR2(20),		--表的字段
  BY_DATA_VALUE VARCHAR2(2000),		--如以sql开头,计算聚合值， 如果以cell开头计算单元格的值，如果以buss开头，取业务字段的key值，注: 业务字段与值在系统启动时全部保存map里
  REMARK        VARCHAR2(2000)		--条件备注
)



--用户导入时上传的原始(Excel)数据表
create table EDC_ORIGINAL_DATA_FILE
(
	ID 				NUMBER(16) not null,
	TMPL_ID 		NUMBER NOT NULL,		--文件关联的模板,引用模板文件id
	USER_ID 		VARCHAR2(32),			--上传人id
	DISK_PATH 		VARCHAR2(500),			--原始文件服务器物理存放路径
	HTTP_PATH 		VARCHAR2(500),			--原始文件http下载路径
	IMPORT_TIME 	DATE,					--导入的时间
	FILE_TYPE   	CHAR(2) not null		--数据文件所属的类型：10，Excel；20，XML；30，TXT；
)


--数据块与数据库表字段的映射配置
create table EDC_TMPL_DATABLOCK_MAPPING
(
  ID                   NUMBER(16) not null,
  ELEMENT_NAME         VARCHAR2(50) not null,				--映射的元素名称,如："对标指标"或"年同比值"等
  TMPL_BLOCK_NAME      VARCHAR2(150) not null,				--数据块定义的名称(用于查询的冗余字段)，例如：山西省公司安全管理数据块或资产经营数据块
  TABLE_CODE           VARCHAR2(150) not null,				--映射的表名称(用于查询的冗余字段)
  COLUMN_CODE          VARCHAR2(150) not null,				--excel字段列映射的数据库表列字段
  COLUMN_NAME          VARCHAR2(150),						--列名称
  COLUMN_COMMENT       VARCHAR2(200),						--列注释说明信息
  IMPORT_MULTIPLE_RATE FLOAT default 1,						--导入数据为number型时，乘以倍率后，再插入到数据库
  MAPPING_TYPE         VARCHAR2(4) default 10 not null,		--映射类型,10，列数据映射；20，单元格映射；30，主从表映射；40，业务字段映射
  MAPPING_KEY          VARCHAR2(200)						--映射关键字，存储内容取决于MAPPING_TYPE：映射内容为10时，此字段记录列序号；映射内容为20时，此字段记录单元格内容坐标；映射内容为30时，此字段记录主表主键ID；映射内容为40时，此字段记录业务map的key值，如从登陆用户session里取用户的USERID

)

--(单步)审核记录表
create table EDC_CHECKED_INFO
(
  ID              NUMBER,
  USER_ID         VARCHAR2(50) not null,
  ISCHECKED       VARCHAR2(4) not null,
  CHECKED_TIME    VARCHAR2(20) not null,
  CHECKED_COMMENT VARCHAR2(1000)
)

--数据块映射和数据块中间表
create table EDC_MID_DATABLOCK_MAPPING
(
  DATABLOCK_ID NUMBER,
  MAPPING_ID   NUMBER
)

--模板文件与模板文件类别中间表
create table EDC_MID_INDEX_TMPLFILE
(
  TMPL_FILE_ID	NUMBER,						--引用EDC_TEMPLATE_FILE.ID
  INDEXUUID 	VARCHAR2(32)				--引用LFS_INDEX.INDEXUUID
)

--数据块与数据块条件中间表
create table EDC_MID_CONDIT_DATABLOCK
(
  CONDIT_ID    NUMBER,						--引用EDC_TMPL_DATABLOCK_CONDIT.ID
  DATABLOCK_ID NUMBER						--引用EDC_TEMPLATE_DATA_BLOCK.ID
)

--模板文件和数据块中间表
create table EDC_MID_TMPLFILE_DATABLOCK
(
  DATABLOCK_ID NUMBER(19) not null,			--引用EDC_TEMPLATE_DATA_BLOCK.ID
  TMPL_FILE_ID NUMBER(19) not null			--引用EDC_TEMPLATE_FILE.ID
)








--系统初使化数据

--##资源表
insert into LFS_INDEX (INDEXUUID, INDEXNAME, INDEXORDER, INDEXURL, INDEXMAPPEDURL, PARENTINDEXUUID, TARGET, WAY, INDEXTYPE, INDEXICON, INDEXLEVEL, DESCRIPTION, HASCHILD, CREATETIME, INDEXICON_DISK_PATH, LEVEL_CODE)
values ('hea_rootpower', '权限管理', -2, null, null, 'hea_root', null, '1', 0, null, 2, null, 1, to_date('02-12-2010', 'dd-mm-yyyy'), null, '1000010000');
insert into LFS_INDEX (INDEXUUID, INDEXNAME, INDEXORDER, INDEXURL, INDEXMAPPEDURL, PARENTINDEXUUID, TARGET, WAY, INDEXTYPE, INDEXICON, INDEXLEVEL, DESCRIPTION, HASCHILD, CREATETIME, INDEXICON_DISK_PATH, LEVEL_CODE)
values ('hea_rootdept', '组织机构管理', 0, null, null, 'hea_rootpower', null, '1', 0, null, 3, null, 0, to_date('03-12-2010', 'dd-mm-yyyy'), null, '100001000010000');
insert into LFS_INDEX (INDEXUUID, INDEXNAME, INDEXORDER, INDEXURL, INDEXMAPPEDURL, PARENTINDEXUUID, TARGET, WAY, INDEXTYPE, INDEXICON, INDEXLEVEL, DESCRIPTION, HASCHILD, CREATETIME, INDEXICON_DISK_PATH, LEVEL_CODE)
values ('hea_rootgroup', '角色管理', 0, null, null, 'hea_rootpower', null, '1', 0, null, 3, null, 0, to_date('04-12-2010', 'dd-mm-yyyy'), null, '100001000010001');
insert into LFS_INDEX (INDEXUUID, INDEXNAME, INDEXORDER, INDEXURL, INDEXMAPPEDURL, PARENTINDEXUUID, TARGET, WAY, INDEXTYPE, INDEXICON, INDEXLEVEL, DESCRIPTION, HASCHILD, CREATETIME, INDEXICON_DISK_PATH, LEVEL_CODE)
values ('hea_root', '系统结构', 0, null, null, null, '_blank', '1', 0, null, 1, null, 1, to_date('01-12-2010', 'dd-mm-yyyy'), null, '10000');
insert into LFS_INDEX (INDEXUUID, INDEXNAME, INDEXORDER, INDEXURL, INDEXMAPPEDURL, PARENTINDEXUUID, TARGET, WAY, INDEXTYPE, INDEXICON, INDEXLEVEL, DESCRIPTION, HASCHILD, CREATETIME, INDEXICON_DISK_PATH, LEVEL_CODE)
values ('hea_rootindex', '资源管理', 0, 'heaIndexAction.hea?action=display'||'&'||'indexuuid=hea_rootindex', null, 'hea_rootpower', null, '1', 0, null, 3, null, 1, to_date('05-12-2010', 'dd-mm-yyyy'), null, '100001000010002');
insert into LFS_INDEX (INDEXUUID, INDEXNAME, INDEXORDER, INDEXURL, INDEXMAPPEDURL, PARENTINDEXUUID, TARGET, WAY, INDEXTYPE, INDEXICON, INDEXLEVEL, DESCRIPTION, HASCHILD, CREATETIME, INDEXICON_DISK_PATH, LEVEL_CODE)
values ('hea_rootui', '界面定制', 0, null, null, 'hea_root', null, '1', 0, null, 2, null, 0, to_date('05-12-2010', 'dd-mm-yyyy'), null, '1000010001');
insert into LFS_INDEX (INDEXUUID, INDEXNAME, INDEXORDER, INDEXURL, INDEXMAPPEDURL, PARENTINDEXUUID, TARGET, WAY, INDEXTYPE, INDEXICON, INDEXLEVEL, DESCRIPTION, HASCHILD, CREATETIME, INDEXICON_DISK_PATH, LEVEL_CODE)
values ('hea_rootdc', '数据导入', -1, null, null, 'hea_root', null, '1', 0, null, 2, null, 1, to_date('06-12-2010', 'dd-mm-yyyy'), null, '1000010002');
insert into LFS_INDEX (INDEXUUID, INDEXNAME, INDEXORDER, INDEXURL, INDEXMAPPEDURL, PARENTINDEXUUID, TARGET, WAY, INDEXTYPE, INDEXICON, INDEXLEVEL, DESCRIPTION, HASCHILD, CREATETIME, INDEXICON_DISK_PATH, LEVEL_CODE)
values ('hea_rootedc', '模板定制', -2, null, null, 'hea_rootdc', null, '1', 0, null, 3, null, 1, to_date('07-12-2010', 'dd-mm-yyyy'), null, '100001000010003');
insert into LFS_INDEX (INDEXUUID, INDEXNAME, INDEXORDER, INDEXURL, INDEXMAPPEDURL, PARENTINDEXUUID, TARGET, WAY, INDEXTYPE, INDEXICON, INDEXLEVEL, DESCRIPTION, HASCHILD, CREATETIME, INDEXICON_DISK_PATH, LEVEL_CODE)
values ('hea_rootuser', '用户管理', -2, null, null, 'hea_rootpower', null, '1', 0, null, 3, null, 0, to_date('08-12-2010', 'dd-mm-yyyy'), null, '100001000010003');
insert into LFS_INDEX (INDEXUUID, INDEXNAME, INDEXORDER, INDEXURL, INDEXMAPPEDURL, PARENTINDEXUUID, TARGET, WAY, INDEXTYPE, INDEXICON, INDEXLEVEL, DESCRIPTION, HASCHILD, CREATETIME, INDEXICON_DISK_PATH, LEVEL_CODE)
values ('hea_rootedc_datablock', '数据块配置', 1, null, null, 'hea_rootedc', null, '1', 0, null, 4, null, 0, to_date('06-12-2010', 'dd-mm-yyyy'), null, '1000010000100031000');
insert into LFS_INDEX (INDEXUUID, INDEXNAME, INDEXORDER, INDEXURL, INDEXMAPPEDURL, PARENTINDEXUUID, TARGET, WAY, INDEXTYPE, INDEXICON, INDEXLEVEL, DESCRIPTION, HASCHILD, CREATETIME, INDEXICON_DISK_PATH, LEVEL_CODE)
values ('hea_rootedc_datacondit', '数据块条件配置', 2, null, null, 'hea_rootedc', null, '1', 0, null, 4, null, 0, to_date('09-12-2010', 'dd-mm-yyyy'), null, '10000100001000310001');
insert into LFS_INDEX (INDEXUUID, INDEXNAME, INDEXORDER, INDEXURL, INDEXMAPPEDURL, PARENTINDEXUUID, TARGET, WAY, INDEXTYPE, INDEXICON, INDEXLEVEL, DESCRIPTION, HASCHILD, CREATETIME, INDEXICON_DISK_PATH, LEVEL_CODE)
values ('hea_rootedc_bind_conditandblock', '数据块和条件绑定', 3, null, null, 'hea_rootedc', null, '1', 0, null, 4, null, 0, to_date('10-12-2010', 'dd-mm-yyyy'), null, '10000100001000310002');
insert into LFS_INDEX (INDEXUUID, INDEXNAME, INDEXORDER, INDEXURL, INDEXMAPPEDURL, PARENTINDEXUUID, TARGET, WAY, INDEXTYPE, INDEXICON, INDEXLEVEL, DESCRIPTION, HASCHILD, CREATETIME, INDEXICON_DISK_PATH, LEVEL_CODE)
values ('test', 'test', null, null, null, 'hea_rootindex', null, '1', 0, null, 4, null, 0, to_date('11-12-2010', 'dd-mm-yyyy'), null, '10000100001000210000');
commit;




--#系统常量表
insert into EDC_SYSTEM_CODE (ID, REG_CODE, REG_TYPE, REG_NAME, REG_DESC, REG_PROP, REG_ORDER, REG_VALUE)
values (1,'10', 'PARSE_MODE', '逐行解析 ', '解析模版时以行的方式读取', null, '1', null);
insert into EDC_SYSTEM_CODE (ID, REG_CODE, REG_TYPE, REG_NAME, REG_DESC, REG_PROP, REG_ORDER, REG_VALUE)
values (2,'20', 'PARSE_MODE', '逐列解析', '解析模版时以列的方式读取', null, '2', null);
insert into EDC_SYSTEM_CODE (ID, REG_CODE, REG_TYPE, REG_NAME, REG_DESC, REG_PROP, REG_ORDER, REG_VALUE)
values (3,'10', 'TEMPLATE_TYPE', '模版类型', 'Excel类型', null, '3', null);
insert into EDC_SYSTEM_CODE (ID, REG_CODE, REG_TYPE, REG_NAME, REG_DESC, REG_PROP, REG_ORDER, REG_VALUE)
values (4,'20', 'TEMPLATE_TYPE', '模版类型', 'XML模版类型', null, '4', null);
insert into EDC_SYSTEM_CODE (ID, REG_CODE, REG_TYPE, REG_NAME, REG_DESC, REG_PROP, REG_ORDER, REG_VALUE)
values (5,'30', 'TEMPLATE_TYPE', '模版类型', 'Text文本文件类型', null, '5', null);
insert into EDC_SYSTEM_CODE (ID, REG_CODE, REG_TYPE, REG_NAME, REG_DESC, REG_PROP, REG_ORDER, REG_VALUE)
values (6,'VARCHAR2', 'ORACLE_TYPE', '字符类型', 'oracle数据字符类型', '1000', '6', null);
insert into EDC_SYSTEM_CODE (ID, REG_CODE, REG_TYPE, REG_NAME, REG_DESC, REG_PROP, REG_ORDER, REG_VALUE)
values (7,'TIMESTAMP', 'ORACLE_TYPE', '日期类型', 'oracle数据日期类型', null, '7', null);
insert into EDC_SYSTEM_CODE (ID, REG_CODE, REG_TYPE, REG_NAME, REG_DESC, REG_PROP, REG_ORDER, REG_VALUE)
values (8,'NUMBER', 'ORACLE_TYPE', '数值类型', 'oracle数据数值类型', null, '8', null);
insert into EDC_SYSTEM_CODE (ID, REG_CODE, REG_TYPE, REG_NAME, REG_DESC, REG_PROP, REG_ORDER, REG_VALUE)
values (9,'10', 'TEMPLATE_STATUS', '模版状态', '激活状态', null, '9', null);
insert into EDC_SYSTEM_CODE (ID, REG_CODE, REG_TYPE, REG_NAME, REG_DESC, REG_PROP, REG_ORDER, REG_VALUE)
values (10,'20', 'TEMPLATE_STATUS', '模版状态', '禁用状态', null, '10', null);
insert into EDC_SYSTEM_CODE (ID, REG_CODE, REG_TYPE, REG_NAME, REG_DESC, REG_PROP, REG_ORDER, REG_VALUE)
values (11,'10', 'END_CONDITION', '以连续空行数终止(默认)', '解析终止条件', null, '11', null);
insert into EDC_SYSTEM_CODE (ID, REG_CODE, REG_TYPE, REG_NAME, REG_DESC, REG_PROP, REG_ORDER, REG_VALUE)
values (12,'20', 'END_CONDITION', '以指定记录数终止', '解析终止条件', null, '12', null);
insert into EDC_SYSTEM_CODE (ID, REG_CODE, REG_TYPE, REG_NAME, REG_DESC, REG_PROP, REG_ORDER, REG_VALUE)
values (13,'30', 'END_CONDITION', '以指定字符标识终止', '解析终止条件', null, '13', null);
insert into EDC_SYSTEM_CODE (ID, REG_CODE, REG_TYPE, REG_NAME, REG_DESC, REG_PROP, REG_ORDER, REG_VALUE)
values (14,'40', 'END_CONDITION', '以指定的空行数终止', '解析终止条件', null, '14', null);
insert into EDC_SYSTEM_CODE (ID, REG_CODE, REG_TYPE, REG_NAME, REG_DESC, REG_PROP, REG_ORDER, REG_VALUE)
values (15,'10', 'IMPORT_METHOD', '文件导入', '数据导入方式', null, '15', null);
insert into EDC_SYSTEM_CODE (ID, REG_CODE, REG_TYPE, REG_NAME, REG_DESC, REG_PROP, REG_ORDER, REG_VALUE)
values (16,'20', 'IMPORT_METHOD', '手工导入', '数据导入方式', null, '16', null);
insert into EDC_SYSTEM_CODE (ID, REG_CODE, REG_TYPE, REG_NAME, REG_DESC, REG_PROP, REG_ORDER, REG_VALUE)
values (17,'10', 'MAPPING_TYPE', '列数据映射', '映射类型', null, '17', null);
insert into EDC_SYSTEM_CODE (ID, REG_CODE, REG_TYPE, REG_NAME, REG_DESC, REG_PROP, REG_ORDER, REG_VALUE)
values (18,'20', 'MAPPING_TYPE', '单元格映射', '映射类型', null, '18', null);
insert into EDC_SYSTEM_CODE (ID, REG_CODE, REG_TYPE, REG_NAME, REG_DESC, REG_PROP, REG_ORDER, REG_VALUE)
values (19,'30', 'MAPPING_TYPE', '主从表映射', '映射类型', null, '19', null);
commit;

