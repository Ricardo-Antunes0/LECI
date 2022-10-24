Como já explicado no relatório, na página 11 , esta vulnerabilidade pode entrar em contas de utilizadores ou até mesmo aceder aos dados sql do site através de sql-injection. 
Basicamente na imagem "1.png" podemos ver um utilizador a usar sql-injection com um email existente com comentário, entrando na conta de um utilizador 
sem autorização (imagem "2.png").
Para o site inseguro estavamos a usar o from_statement do flask_sql ficando assim vulneráveis a sql-injection, imagem "3.png" ,para a resolução do problema usamos 
sql-alchemy que já tem módulos contra sql-injection. (Imagem "4.png")
