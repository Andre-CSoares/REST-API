create table servico(
    id bigint not null auto_increment,
    titulo varchar(255) not null,
    data date,
    funcionario varchar(255) not null,
    tipo varchar(255) not null,
    primary key (id)
);

alter table servico
    add constraint uk_servico unique (tipo);