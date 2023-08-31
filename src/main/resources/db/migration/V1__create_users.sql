create sequence user_sequence start with 1 increment by 1;

create table users(
    id BIGINT primary key,
    name varchar(255) not null,
    login varchar(255) unique,
    password varchar(255) not null,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
)