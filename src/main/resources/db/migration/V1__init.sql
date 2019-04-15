create table user (
    id bigserial unique not null,
    name varchar(255) not null,
    cpf varchar(255) unique not null,
    birth date,
    weight float,
    uf varchar(255) not null,

    primary key(id)
);
