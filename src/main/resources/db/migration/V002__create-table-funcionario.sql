create table funcionario(
	id bigint not null auto_increment,
    nome varchar(255) not null,
    email varchar(255) not null,
    telefone varchar(20) not null,

    primary key (id)
);

alter table funcionario
add constraint uk_cliente unique (email);