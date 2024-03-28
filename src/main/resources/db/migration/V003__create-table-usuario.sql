create table user(
    id bigint not null auto_increment,
    nome varchar(255) not null,
    email varchar(255) not null,
    telefone varchar(20) not null,
    tipo_user varchar(50) not null,
    senha varchar(50) not null,

    primary key (id)
);

alter table user
    add constraint uk_user unique (email);