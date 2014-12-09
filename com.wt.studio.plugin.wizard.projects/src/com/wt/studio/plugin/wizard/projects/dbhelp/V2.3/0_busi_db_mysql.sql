create table BUSINESS_SYSTEM_CODE
(
  id         varchar(255) not null,
  parent_id  varchar(255),
  reg_code   varchar(255),
  reg_type   varchar(255),
  reg_status varchar(32),
  reg_name   varchar(200),
  reg_desc   varchar(500),
  reg_prop   varchar(50),
  reg_value  varchar(255),
  reg_label  varchar(255),
  reg_order  int,
  indexlevel int,
  haschild   int,
  level_code varchar(255),
  param1     varchar(255),
  param2     varchar(255),
  param3     varchar(255),
  param4     varchar(255),
  param5     varchar(255)
);