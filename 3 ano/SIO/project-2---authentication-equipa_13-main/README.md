# project-2---authentication-equipa_13
project-2---authentication-equipa_13 created by GitHub Classroom
## Autores
> Frederico Vieira, nmec: 98518

> Ricardo Antunes, nmec: 98275

> Tiago Coelho, nmec: 98385

> Vladyslav Mysnyk, nmec: 97548

## Descrição de Projeto
Este projeto tem como finalidade a aplicação dos conceitos dados em aula, tais como criptografia, chaves, smartcards, mecanismos de autenticação e protocolos. Para tal, foi nos pedido a criação de uma UAP (User Authentication Protocol), aplicação com web interface iniciada pelo usuário caso pretenda fazer login no site. Na eventualidade do mesmo não a iniciar, este é advertido caso tente logar-se.
A UAP baseia-se num protocolo de autenticação do usuário que usa mecanismos de E-CHAP ("Encrypted Challenge Handshake Autentication Protocol") de forma a garantir a mútua autenticação entre o usuário e o servidor, criando uma ligação segura e encriptada, capaz de obter uma probabilidade (1/2)^N de um impostor ter sucesso. Escolhemos um N de valor 10, sendo N o número de trocas HTTP, porque se fizermos um meio elevado a 10, podemos constar que o valor seria 0,0009765625, sendo esta uma probabilidade muita baixa de ter sucesso para qualquer impostor.
Em relação ao E-CHAP, a UAP começa por enviar o username para o servidor, este verifica a sua existência na plataforma, se este existir, o servidor cria o primeiro challenge e envia para o cliente. Este após receber, cria o segundo challenge e calcula o primeiro hash(BLAKE2b) com o primeiro bit da password e os dois challenges. De seguida, o cliente envia para o servidor e o mesmo verifica se a resposta está correta. Caso esteja correta, o servidor cria o challenge 3, calcula o hash com challenge 2 e 3 mais o segundo bit e envia para o cliente para este verificar se está correto, este procedimento ocorre entre servidor e cliente até o N ser 10, ou até alguma resposta estar incorreta. No caso de estar incorreto, os mesmos irão começar a enviar respostas random até N ser 10.
No final, estando o login correto, será armazenado no cliente o seu username e password encriptada com a chave pedida no ato do login, e o mesmo será redirecionado para o site com o login efetuado.


## Procedimento
> Para utilizar esta aplicação, é necessário seguir os seguintes passos:
1. Instalar os requirements usando o seguinte código:
- pip install -r requirements.txt
2. Ir ao diretório app_auth:
- python3 main.py
- pass: pempass 
- python3 app_server.py
> Para o cliente:
1. Ir ao diretório uap:
- python3 main.py
