drop table if exists user;

create table user (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  email varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  created_at datetime NOT NULL,
  updated_at datetime,
  del_flg tinyint(1) DEFAULT 0 NOT NULL,
  primary key(id)
);

insert into user values (1, 'johnsmisth@test.com', 'password', '2023-12-05 10:10:10+09:00', NULL, 0)