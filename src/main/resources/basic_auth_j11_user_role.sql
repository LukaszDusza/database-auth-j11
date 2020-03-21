create table user_role
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    constraint FK859n2jvi8ivhui0rl0esws6o
        foreign key (user_id) references user (id),
    constraint FKa68196081fvovjhkek5m97n3y
        foreign key (role_id) references role (role_id)
);

INSERT INTO basic_auth_j11.user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO basic_auth_j11.user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO basic_auth_j11.user_role (user_id, role_id) VALUES (3, 1);
INSERT INTO basic_auth_j11.user_role (user_id, role_id) VALUES (4, 1);
INSERT INTO basic_auth_j11.user_role (user_id, role_id) VALUES (2, 2);