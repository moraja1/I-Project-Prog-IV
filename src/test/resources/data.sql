-- use prog4db;

insert into admin (id, correo, apellidos, nombre, telefono, tipo_id, admin_pass) values
('404440444', 'admin@admin.com', 'Mora', 'Jaison', '88888888', 'Fisica', 'admin');

insert into admin (id, correo, apellidos, nombre, telefono, tipo_id, admin_pass) values
('402290985', 'admin2@admin.com', 'Prubarton', 'Testino', '555555585', 'Fisica', 'testingLikeNeva');

-- ------------------------------KEEP THIS BLOCK UNCOMMENT FOR TESTING----------------------------------------------- --
insert into
proveedores (id, tipo_id, nombre, apellidos, correo, telefono, proveedor_username, proveedor_pass, proveedor_acceso)
values ('506320478', 'Fisica', 'Gerardo', 'Gonzalez', 'gerardo@proveedor.com', '99999991', 'gerar', 'pass', false);
insert into
proveedores (id, tipo_id, nombre, apellidos, correo, telefono, proveedor_username, proveedor_pass, proveedor_acceso)
values ('901020304', 'Fisica', 'Olman', 'Sequeira', 'Olman@proveedor.com', '99999992', 'olman', 'pass1', false);
insert into
proveedores (id, tipo_id, nombre, apellidos, correo, telefono, proveedor_username, proveedor_pass, proveedor_acceso)
values ('802040608', 'Fisica', 'Steven', 'Jimenez', 'steven@proveedor.com', '99999993', 'steve', 'pass2', false);
insert into
proveedores (id, tipo_id, nombre, apellidos, correo, telefono, proveedor_username, proveedor_pass, proveedor_acceso)
values ('703060907', 'Fisica', 'Melisa', 'Victor', 'melisa@proveedor.com', '99999994', 'melis', 'pass3', false);
insert into
proveedores (id, tipo_id, nombre, apellidos, correo, telefono, proveedor_username, proveedor_pass, proveedor_acceso)
values ('405000500', 'Fisica', 'Gabriel', 'Tenorio', 'gabriel@proveedor.com', '99999995', 'gabriel', 'pass4', false);
insert into
proveedores (id, tipo_id, nombre, apellidos, correo, telefono, proveedor_username, proveedor_pass, proveedor_acceso)
values ('207040108', 'Fisica', 'Marta', 'Fonseca', 'marta@proveedor.com', '99999996', 'marta', 'pass5', false);
-- ---------------------------------------------------------------------------------------------------------------------