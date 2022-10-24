Como já explicado no relatório, na página 14 , nesta vulnerabilidade é possivel um utilizador malicioso aceder a dados confidenciais apenas alterando o link do site. Assim como
alterar as passwords de outros utilizadores através dos seus emails como podemos ver nestas imagens "1.png", "2.png" e "3.png".
Na app insegura temos uma função de reset password, no entanto, a mesma não é suficiente pois não verifica se o email é valido nem se a pessoa em questão é o proprietário da
conta. (imagem "4.png")
Na app segura fizemos uma função reset onde o utilizador que pede para dar reset da password tem de introduzir um email válido assim como aceder ao seu email, clicar
no link que gera um token para dar reset e de seguida alterar a sua password.(imagem "5.png")
