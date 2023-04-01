
CREATE SCHEMA stocks;
GO

-- a)
CREATE TABLE stocks.Tipo_fornecedor(
	codigo	INT NOT NULL PRIMARY KEY,
	Designacao VARCHAR(20),
	UNIQUE(codigo)
);

CREATE TABLE stocks.fornecedor(
	NIF			CHAR(9) NOT NULL  PRIMARY KEY,
	nome		VARCHAR(20),
	fax			INT NOT NULL,
	endereco	VARCHAR(30),
	condpag		VARCHAR(40) NOT NULL,
	tipo		INT REFERENCES stocks.tipo_fornecedor(codigo)
);

CREATE TABLE stocks.produto(
	codigo		INT NOT NULL PRIMARY KEY,
	nome		VARCHAR(45) NOT NULL,
	preco		SMALLMONEY NOT NULL,
	IVA			FLOAT,
	unidades	INT NOT NULL,
	UNIQUE(codigo)
);

CREATE TABLE stocks.encomenda(
	numero		INT NOT NULL PRIMARY KEY,
	data		DATE NOT NULL,
	fornecedor	CHAR(9) REFERENCES stocks.fornecedor(NIF)
);

CREATE TABLE stocks.item(
	numEnc		INT REFERENCES stocks.encomenda(numero),
	codProd		INT REFERENCES stocks.produto(codigo),
	unidades	INT,
	PRIMARY KEY(numEnc, codProd)
);