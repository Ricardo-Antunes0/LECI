Como já explicado no relatório, na página 8 , esta vulnerabilidade pode inserir scripts na página de forma maliciosa.
Basicamente nas imagens "1.png" e "2.png podemos ver um utilizador a usar um script do tipo 0, e qualquer outro utilizador que aceda à publicação
irá ser vitima do script como se pode ver nas imagem "3.png"

Nestas imagens foram executados scripts pequenos, no entanto, é possível executar qualquer tipo de script.
Ao utilizarmos flask já nos estamos a prevevenir contra XSS, devido ao Jinja2 que está incorporado no flask, no entanto, é possivel desativar esta funcionalidade
do Jinja2 como foi realizado no site inseguro atravéz do "| safe". (Imagem "5.png") No seguro não
é necessário introduzir nada deixando assim este modulo do Jinja2 ativado (Imagem "6.png")
