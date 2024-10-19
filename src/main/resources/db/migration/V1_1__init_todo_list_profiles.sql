create table profiles
(
    profiles_id serial      not null,
    name        varchar(32) not null,
    surname     varchar(32) not null,
    email       varchar(32) not null,
    username    varchar(36) not null,
    primary key (profiles_id),
    unique (email, username)
);