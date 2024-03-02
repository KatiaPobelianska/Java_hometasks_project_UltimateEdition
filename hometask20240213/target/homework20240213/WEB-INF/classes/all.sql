CREATE TABLE friend
(
    id        bigserial primary key,
    first_name varchar(20) not null,
    last_name  varchar(20) not null,
    birthday  date
);
DROP TABLE friend;