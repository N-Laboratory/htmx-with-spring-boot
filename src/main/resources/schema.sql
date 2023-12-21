drop table if exists user;

create table user (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  name varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  created_at datetime NOT NULL,
  updated_at datetime,
  del_flg tinyint(1) DEFAULT 0 NOT NULL,
  primary key(id)
);

insert into user values (1, 'John Smith', 'johnsmisth@test.com', '2023-12-05 10:10:10+09:00', NULL, 0)