create table users(
    id char(36) not null,
    name varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null,

    primary key (id)
);

alter table users
    add constraint uk_user unique (email);