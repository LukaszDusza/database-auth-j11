create table role
(
    role_id bigint auto_increment
        primary key,
    role    varchar(255) null
);

INSERT INTO basic_auth_j11.role (role_id, role) VALUES (1, 'USER');
INSERT INTO basic_auth_j11.role (role_id, role) VALUES (2, 'ADMIN');