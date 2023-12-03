alter table usuarios add role tinyint;
update usuarios set role = 0 where id = 1;
update usuarios set role = 1 where id = 2;