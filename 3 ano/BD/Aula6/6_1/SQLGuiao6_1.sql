
/* Guiao 6	*/
-- 6.1
--a)
SELECT * FROM authors;

-- b)
SELECT au_lname, au_fname, phone 
FROM authors;

-- c)
SELECT au_lname, au_fname, phone
FROM authors
ORDER BY au_lname, au_fname;

-- d)
SELECT au_lname AS last_name, au_fname AS first_name, phone
FROM authors
ORDER BY au_lname, au_fname;

--e)
SELECT au_lname AS last_name, au_fname AS first_name, phone
FROM authors
WHERE state = 'CA' AND au_lname != 'Ringer'
ORDER BY au_lname, au_fname;

-- f)
SELECT * 
FROM publishers
WHERE pub_name LIKE '%Bo%';

-- g)
SELECT DISTINCT pub_name AS editoras  
FROM publishers, titles
WHERE type = 'Business';


-- h)
SELECT pub_name, COUNT(sales.qty) AS vendas
FROM ((sales JOIN titles ON titles.title_id = sales.title_id) JOIN publishers ON publishers.pub_id = titles.pub_id)
GROUP BY pub_name;


-- i) 
SELECT title, pub_name, ytd_sales AS Vendas 
FROM (publishers JOIN  titles ON publishers.pub_id = titles.pub_id);

-- j)
SELECT titles.title, stor_name 
FROM ((stores JOIN sales ON stores.stor_id = sales.stor_id ) JOIN titles ON sales.title_id = titles.title_id)
WHERE stores.stor_name = 'Bookbeat';

-- k)
SELECT au_fname, au_lname
FROM ((authors JOIN titleauthor ON authors.au_id = titleauthor.au_id) JOIN titles ON titleauthor.title_id = titles.title_id)
GROUP BY au_fname, au_lname
HAVING (COUNT(titles.type) > 1);

-- l)
SELECT pub_id, type, AVG(price) AS preço, count(ytd_sales) AS VENDAS
FROM titles
GROUP BY pub_id, type;


--m)
SELECT type
FROM titles
GROUP BY type
HAVING MAX(advance) > 1.5*AVG(advance);


--n)

SELECT title, au_fname+' '+au_lname AS name, sum(ytd_sales) AS money_made
FROM ((titles JOIN titleauthor ON titles.title_id = titleauthor.title_id) JOIN authors ON titleauthor.au_id = authors.au_id)
GROUP BY title, au_fname+' '+au_lname
ORDER BY title,  au_fname+' '+au_lname;


--o)

SELECT title, ytd_sales,
		(ytd_sales*price) AS faturacao,
		(ytd_sales*price*royalty/100) AS auths_revenue,
		(ytd_sales*price-ytd_sales*price*royalty/100) AS publisher_revenue
FROM titles
ORDER BY title;


--p)
SELECT title, ytd_sales, au_fname+' '+ au_lname AS author,
		(ytd_sales*price*royalty/100) as auth_revenue,
		(ytd_sales*price-ytd_sales*price*royalty/100) as publisher_revenue
FROM (titles join titleauthor ON titles.title_id = titleauthor.title_id JOIN authors ON authors.au_id = titleauthor.au_id)
ORDER BY title, price, au_fname+' '+ au_lname, ytd_sales, royalty;


--q)
SELECT stor_name, count(DISTINCT title) AS titulos
FROM stores JOIN sales ON stores.stor_id = sales.stor_id JOIN titles ON sales.title_id = titles.title_id
GROUP BY stor_name
HAVING COUNT(DISTINCT title) = (SELECT COUNT(title)
								FROM titles);
								

--r)
SELECT stor_name, SUM(qty)
FROM stores JOIN sales ON stores.stor_id = sales.stor_id
GROUP BY stor_name
HAVING SUM(qty) > (	SELECT SUM(qty)/ COUNT(DISTINCT stor_id) 
					FROM sales
					);
--s)

SELECT DISTINCT titles.title
FROM titles
WHERE titles.title NOT IN (SELECT title
						   FROM titles JOIN sales ON titles.title_id = sales.title_id JOIN stores ON stores.stor_id = sales.stor_id AND stores.stor_name = 'Bookbeat'
						  );
					
-- t)

(SELECT pub_name AS Editora, stor_name AS Loja
FROM publishers, stores
GROUP BY pub_name, stor_name)
EXCEPT
(SELECT pub_name, stor_name
FROM publishers	JOIN titles ON publishers.pub_id=titles.pub_id JOIN sales ON titles.title_id=sales.title_id JOIN stores ON sales.stor_id=stores.stor_id
GROUP BY pub_name, stor_name);


