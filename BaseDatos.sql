CREATE SCHEMA banco;

CREATE TABLE banco.cliente (
	activo bool NOT NULL,
	edad int4 NOT NULL,
	tipo_documento varchar(5) NOT NULL,
	id int8 NOT NULL,
	genero varchar(10) NOT NULL,
	telefono varchar(20) NOT NULL,
	numero_documento varchar(30) NOT NULL,
	cliente_id varchar(64) NOT NULL,
	direccion varchar(100) NOT NULL,
	nombre varchar(100) NOT NULL,
	contrasena varchar(255) NULL,
	CONSTRAINT cliente_edad_check null,
	CONSTRAINT cliente_genero_check null,
	CONSTRAINT cliente_pkey null,
	CONSTRAINT ukaqqbak3e88hlbfraw6p651fbu null
);

CREATE TABLE banco.cuenta (
	activo bool NOT NULL,
	saldo_inicial numeric(38, 2) NOT NULL,
	cliente_id int8 NOT NULL,
	id int8 NOT NULL,
	moneda varchar(8) NOT NULL,
	tipo varchar(16) NOT NULL,
	numero varchar(64) NOT NULL,
	CONSTRAINT cuenta_numero_key null,
	CONSTRAINT cuenta_pkey null,
	CONSTRAINT uklu430rdwe7erytvlc637x8uvk null,
	CONSTRAINT fk4p224uogyy5hmxvn8fwa2jlug FOREIGN KEY (cliente_id) REFERENCES banco.cliente(id)
);

CREATE TABLE banco.movimiento (
	fecha date NOT NULL,
	saldo numeric(38, 2) NOT NULL,
	valor numeric(38, 2) NOT NULL,
	cuenta_id int8 NOT NULL,
	id int8 NOT NULL,
	tipo varchar(10) NOT NULL,
	CONSTRAINT movimiento_pkey null,
	CONSTRAINT movimiento_tipo_check null,
	CONSTRAINT fk4ea11fe7p3xa1kwwmdgi9f2fi FOREIGN KEY (cuenta_id) REFERENCES banco.cuenta(id)
);