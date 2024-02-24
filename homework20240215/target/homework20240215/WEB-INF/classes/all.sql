create table Log_entry
(
    id         bigserial primary key,
    class_name varchar(20) not null,
    full_name  varchar(20) not null,
    subject    varchar(20) not null,
    mark       int check ( mark > 0 )
);
