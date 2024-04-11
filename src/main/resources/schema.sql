create schema prog4db;
use prog4db;

-- ------------------------------KEEP THIS BLOCK UNCOMMENT FOR TESTING-----------------------------------------------
create table cuentas (id bigint not null auto_increment, primary key (id)) engine=InnoDB;
create table factura (id bigint not null auto_increment, costo_total bigint, date datetime(6), iva float(53),
                      cliente_id varchar(255), cuenta_id bigint, info_com_id bigint, primary key (id)) engine=InnoDB;
create table informacion_comercial (id bigint not null auto_increment, canton varchar(255), distrito varchar(255),
                                    nombre varchar(255), provincia varchar(255), razon_social varchar(255),
                                    primary key (id)) engine=InnoDB;
create table persona (dtype varchar(31) not null, id varchar(255) not null, correo varchar(255), apellidos varchar(255),
                      nombre varchar(255), password varchar(64), telefono varchar(255), proveedor_acceso bit,
                      info_com_id bigint, cuenta_id bigint, primary key (id)) engine=InnoDB;
create table producto (id bigint not null, cantidad integer, costo bigint, descripcion varchar(255),
                       cuenta_id bigint, factura_id bigint, primary key (id)) engine=InnoDB;

-- ---------------------------------SESSION DATABASE TABLES SQL--------------------------------------------------------

CREATE TABLE SPRING_SESSION (
                                PRIMARY_ID CHAR(36) NOT NULL,
                                SESSION_ID CHAR(36) NOT NULL,
                                CREATION_TIME BIGINT NOT NULL,
                                LAST_ACCESS_TIME BIGINT NOT NULL,
                                MAX_INACTIVE_INTERVAL INT NOT NULL,
                                EXPIRY_TIME BIGINT NOT NULL,
                                PRINCIPAL_NAME VARCHAR(100),
                                CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

CREATE TABLE SPRING_SESSION_ATTRIBUTES (
                                           SESSION_PRIMARY_ID CHAR(36) NOT NULL,
                                           ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
                                           ATTRIBUTE_BYTES BLOB NOT NULL,
                                           CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
                                           CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;