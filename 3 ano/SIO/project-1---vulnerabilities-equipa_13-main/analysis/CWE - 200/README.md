Como já explicado no relatório, na página 13, nesta vulnerabilidade é possivel um utilizador malicioso aceder a dados confidenciais apenas alterando o id no link do site. (imagem "1.png")
Na app insegura, como é possível ver na imagem "2.png", estava a ser usado um algoritmo onde cada id coincidia com um profile de utilizador diferente, sendo o mesmo privado.
Para prevenir isto na app_sec utilizamos a função login_required do flask para assegurar que o individuo em questão estava logado no site, assim como retiramos
os ids dos utilizadores no link da página perfil como podem ver na imagem "3.png".
