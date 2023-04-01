
-- 5.2

-- a)
SELECT nome
FROM (stocks.fornecedor LEFT OUTER JOIN stocks.encomenda ON NIF = fornecedor)
WHERE fornecedor = NULL;


--b)
SELECT codProd, nome, AVG(stocks.item.unidades) AS unidades
FROM stocks.produto JOIN stocks.item ON codigo = codProd
GROUP BY codProd, nome;

--c)
SELECT AVG(numeroProd) AS Media
FROM (	SELECT numEnc, COUNT(codProd) AS numeroProd
		FROM stocks.item
		GROUP BY numEnc) AS items;


--d)
SELECT stocks.fornecedor.nome, stocks.produto.nome, SUM(stocks.item.unidades) AS quantidade
FROM stocks.encomenda JOIN stocks.item ON numero = numEnc JOIN stocks.fornecedor ON NIF = fornecedor JOIN stocks.produto ON codigo = codProd
GROUP BY stocks.produto.nome, stocks.fornecedor.nome;

