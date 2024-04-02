-- use prog4db;

insert into persona (id, tipo_persona, correo, apellidos, nombre, telefono, tipo_id, password) values
('404440444', 'Admin','admin@admin.com', 'Mora', 'Jaison', '88888888', 'Fisica', 'admin');

insert into persona (id, tipo_persona, correo, apellidos, nombre, telefono, tipo_id, password) values
('402290985', 'Admin', 'admin2@admin.com', 'Prubarton', 'Testino', '555555585', 'Fisica', 'testingLikeNeva');

-- ------------------------------KEEP THIS BLOCK UNCOMMENT FOR TESTING----------------------------------------------- --
insert into
    persona (id, tipo_persona, tipo_id, nombre, apellidos, correo, telefono, password, proveedor_acceso)
values ('506320478', 'Proveedor', 'Fisica', 'Gerardo', 'Gonzalez', 'gerardo@proveedor.com', '99999991', 'pass', false);
insert into
    persona (id, tipo_persona, tipo_id, nombre, apellidos, correo, telefono, password, proveedor_acceso)
values ('901020304', 'Proveedor', 'Fisica', 'Olman', 'Sequeira', 'Olman@proveedor.com', '99999992', 'pass1', false);
insert into
    persona (id, tipo_persona, tipo_id, nombre, apellidos, correo, telefono, password, proveedor_acceso)
values ('802040608', 'Proveedor', 'Fisica', 'Steven', 'Jimenez', 'steven@proveedor.com', '99999993', 'pass2', false);
insert into
    persona (id, tipo_persona, tipo_id, nombre, apellidos, correo, telefono, password, proveedor_acceso)
values ('703060907', 'Proveedor', 'Fisica', 'Melisa', 'Victor', 'melisa@proveedor.com', '99999994', 'pass3', false);
insert into
    persona (id, tipo_persona, tipo_id, nombre, apellidos, correo, telefono, password, proveedor_acceso)
values ('405000500', 'Proveedor', 'Fisica', 'Gabriel', 'Tenorio', 'gabriel@proveedor.com', '99999995', 'pass4', false);
insert into
    persona (id, tipo_persona, tipo_id, nombre, apellidos, correo, telefono, password, proveedor_acceso)
values ('207040108', 'Proveedor', 'Fisica', 'Marta', 'Fonseca', 'marta@proveedor.com', '99999996', 'pass5', false);
-- ---------------------------------------------------------------------------------------------------------------------