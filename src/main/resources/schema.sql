/*create schema prog4db;
use prog4db;*/

-- insert into admin (id, correo, apellidos, nombre, telefono, tipo_id, admin_pass) values
-- ('404440444', 'admin@admin.com', 'Mora', 'Jaison', '88888888', 'Fisica', 'admin');

-- ------------------------------KEEP THIS BLOCK UNCOMMENT FOR TESTING----------------------------------------------- --
create table admin
(id varchar(255) not null, correo varchar(255), apellidos varchar(255), nombre varchar(255), telefono varchar(255),
 tipo_id enum ('Fisica','Juridica'), admin_pass varchar(64) not null, primary key (id));
create table clientes
(id varchar(255) not null, correo varchar(255), apellidos varchar(255), nombre varchar(255), telefono varchar(255),
 tipo_id enum ('Fisica','Juridica'), cuenta_id bigint, primary key (id));
create table cuentas (id bigint not null auto_increment, proveedor_id varchar(255), primary key (id));
create table proveedores
(id varchar(255) not null, correo varchar(255), apellidos varchar(255), nombre varchar(255), telefono varchar(255),
 tipo_id enum ('Fisica','Juridica'), proveedor_pass varchar(64) not null, proveedor_acceso bit, proveedor_username varchar(255),
 primary key (id));

-- ---------------------------------------------------------------------------------------------------------------------
/*alter table cuentas drop index UK_5ynxb6n8ls4h5c7yvnfibtnfh;
alter table cuentas add constraint UK_5ynxb6n8ls4h5c7yvnfibtnfh unique (proveedor_id);
alter table clientes add constraint CUENTA_ID_FK foreign key (cuenta_id) references cuentas (id);
alter table cuentas add constraint PROVEEDOR_ID_FK foreign key (proveedor_id) references proveedores (id);*/