create table coindesk (
      id bigint not null,
      char_name varchar(50) not null,
      code varchar(10) not null,
      updated_utc varchar(50) not null,
      currency_ch varchar(10) not null,
      currency_en varchar(10) not null,
      description varchar(255) not null,
      rate varchar(50) not null,
      rate_float decimal(19,2) not null,
      symbol varchar(10) not null,
      created_by varchar(50) not null,
      create_date datetime(6) not null,
      updated_by varchar(50) not null,
      updated_date datetime(6) not null,
      primary key (id)
) engine=InnoDB