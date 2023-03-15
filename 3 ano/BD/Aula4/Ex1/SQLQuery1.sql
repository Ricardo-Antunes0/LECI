DROP DATABASE rentacar;
CREATE DATABASE rentacar;
GO

use rentacar;

create table balcao(
	Endereço		VARCHAR(50),
	Numero	
	INT			PRIMARY KEY		NOT NULL,
	Nome			VARCHAR(50)
);

create table cliente(
	Nome			VARCHAR(50),
	Endereço		VARCHAR(50),
	Num_carta		INT			UNIQUE,
	NIF				INT			PRIMARY KEY		NOT NULL		
);

create table tipo_veiculo(
	codigo		INT		PRIMARY KEY		NOT NULL,
	Designação	VARCHAR(20), 
	A_C			BIT
);

create table veiculo(
	ano					CHAR(4),
	marca				VARCHAR(20),
	matricula			CHAR(6)		PRIMARY KEY		NOT NULL,
	codigo_tipo_veiculo INT		REFERENCES	tipo_veiculo(codigo)
);

create table aluguer(
	data	DATE,
	numero	INT		PRIMARY KEY		NOT NULL,
	duracao	TIME,
	NIF_cliente			INT			REFERENCES cliente(NIF),
	numero_balcao		INT			REFERENCES balcao(Numero),
	matricula_veiculo	CHAR(6)		REFERENCES veiculo(matricula)
);

create table ligeiro(
	numero_lugares			INT,
	portas					INT,
	combustivel				VARCHAR(10),
	codigo_tipo_veiculo		INT		PRIMARY KEY		NOT NULL	REFERENCES tipo_veiculo(codigo)
);

create table pesado(
	peso			INT,
	passageiros		INT,
	codigo_tipo_veiculo		INT PRIMARY KEY			NOT NULL	REFERENCES tipo_veiculo(Codigo)
);

create table similaridade(
	codigo_1	INT		REFERENCES tipo_veiculo(codigo)	NOT NULL,
	codigo_2	INT		REFERENCES tipo_veiculo(codigo) NOT NULL,
	PRIMARY KEY(codigo_1,codigo_2)
);
