insert into users (id, name, surname, cell_number)  values (1, 'Deslan', 'Pillay', '+27731832705');
insert into users (id, name, surname, cell_number)  values (2, 'Deshan', 'Pillay', '+27731832705');
insert into users (id, name, surname, cell_number)  values (3, 'Danny', 'Pillay', '+27731832705');


insert into role (role, id) values ('ADMIN', 1);
insert into role (role, id) values ('USER', 2);
insert into role (role, id) values ('STAFF', 3);

insert into user_role (user_id, role_id) values (1, 1);
insert into user_role (user_id, role_id) values (2, 2);
insert into user_role (user_id, role_id) values (3, 3);

