create table user
(
    id        bigint auto_increment
        primary key,
    active    int          not null,
    email     varchar(255) null,
    last_name varchar(255) null,
    login     varchar(255) null,
    name      varchar(255) null,
    password  varchar(255) null
);

INSERT INTO basic_auth_j11.user (id, active, email, last_name, login, name, password) VALUES (1, 1, 'user@user.pl', 'kowalski', 'adam', 'Adam', '$2a$10$IaTc09fIV8P6EUxs9J16bOxwbK6YieJVqni3f9/Uz87bx906Ravve');
INSERT INTO basic_auth_j11.user (id, active, email, last_name, login, name, password) VALUES (2, 1, 'admin@admin.pl', 'Podkowa', 'bartek', 'Bartek', '$2a$10$lz9aFPAbHD2q8lYRXdtTxeyvSNs9fZaxeWBPRrOU9tPbJ0AwenjLC');
INSERT INTO basic_auth_j11.user (id, active, email, last_name, login, name, password) VALUES (3, 1, 'ania@ania.pl', 'kowalska', 'ania', 'ania', '$2a$10$BPwviaJSwufhzkSKEFJqxOw3SRnlHBLSmbkleBSSjb.xfgjkxkSTa');
INSERT INTO basic_auth_j11.user (id, active, email, last_name, login, name, password) VALUES (4, 1, 'zbyszek@zbyniu.pl', 'kowalski', 'zbyszek', 'zbyszek', '$2a$10$EunaD4.luL4.uAfG1jbq1O0A7GmG9iMdyII/8HcPHce3FDeKySa9m');