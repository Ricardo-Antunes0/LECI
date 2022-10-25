## Group members
&nbsp;

| NMec   | Name             |  email                  | Contribution (%)    | Detailed contribution [1]
|:-:|:--|:--|:-:|:--|
| 98275  | Ricardo Antunes  |  ricardofantunes@ua.pt  | 20%                 | primary-grammar (20%)<br>primary-semantic-analysis (15%)<br>code-generation (20%)<br>secondary-grammar (20%)<br>secondary-semantic-analysis (20%)<br>secondary-interpretation/secondary-code-generation (20%)<br>examples (25%)<br>testing (20%)<br>other (documentation) (20%)|
| 98403  | Diogo Fontes     |  diogo.fontes@ua.pt     | 20%                 | primary-grammar (20%)<br>primary-semantic-analysis (15%)<br>code-generation (20%)<br>secondary-grammar (20%)<br>secondary-semantic-analysis (20%)<br>secondary-interpretation/secondary-code-generation (20%)<br>examples (25%)<br>testing (20%)<br>other (documentation) (20%)|
| 98502  | Hugo Domingos    |  h.domingos@ua.pt       | 20%                 | primary-grammar (20%)<br>primary-semantic-analysis (25%)<br>code-generation (20%)<br>secondary-grammar (20%)<br>secondary-semantic-analysis (20%)<br>secondary-interpretation/secondary-code-generation (20%)<br>examples (20%)<br>testing (20%)<br>other (20%)
| 98459  | Tiago Marques    |  tamarques@ua.pt        | 20%                 | primary-grammar (20%)<br>primary-semantic-analysis (25%)<br>code-generation (20%)<br>secondary-grammar (20%)<br>secondary-semantic-analysis (20%)<br>secondary-interpretation/secondary-code-generation (15%)<br>examples (15%)<br>testing (20%)<br>other (20%)
| 98393  | Diogo Santos     |  diogoejsantos@ua.pt    | 20%                 | primary-grammar (20%)<br>primary-semantic-analysis (20%)<br>code-generation (20%)<br>secondary-grammar (20%)<br>secondary-semantic-analysis (20%)<br>secondary-interpretation/secondary-code-generation (25%)<br>examples (15%)<br>testing (20%)<br>other (20%)



[1] Topics:<br>
   primary-grammar (%)<br>primary-semantic-analysis (%)<br>code-generation (%)<br>secondary-grammar (%)<br>secondary-semantic-analysis (%)<br>secondary-interpretation/secondary-code-generation (%)<br>examples (%)<br>testing (%)<br>other (20%) (explain)


## Compilation & Run

 - Compilação da linguagem:
	antlr4 -visitor Table.g4
	javac Table*.java
	antlr4 -visitor Bdex.g4
	javac Bdex*.java

		ou

	bash compile

- Compilação do ficheiro Output.java:

	javac Output.java
	java Output

- Execução da linguagem:
	antlr4-java -ea BdexMain "$1"

		ou

	bash run <pathFile>


## Working examples

1. `../examples/Euro2020.txt`

   O ficheiro "Euro2020.txt" tem como objetivo a simulação do europeu de futebol de 2020.
   As equipas são distribuidas por 6 grupos, de A a F, onde cada grupo é constituido por 4 seleções.
   É construida uma tabela com as seguintes colunas: "Seleção"(Text), "Pontos"(Int), "Golos marcados"(int) e "Golos sofridos"(int).
   No início é feita uma fase de grupos e as seleções com menos pontos são eliminadas do europeu.
   De seguida, as equipas que agora possuem menor pontuação são extraidas para uma outra tabela. As duas equipas com menor pontuação são eliminadas.
   A partir deste momento, começam os oitavos de final levando aos quartos de final e assim até à final.

   Para compilar basta fazer "bash compile" e para executar é bash run ../examples/Euro2020.txt


2. `../examples/TestNotas.txt`

   O ficheiro "TestNotas.txt" tem como objetivo a criação de uma tabela para calcular a nota final de vários alunos.
   Este é constituido por 5 colunas: "Nome"(Text), "Nmec"(Int), "Nota1"(Real), "Nota2"(Real) e "Notafinal"(Real).

   Para compilar basta fazer "bash compile" e para executar é bash run ../examples/TestNotas.txt



## Semantic error examples

1. `../examples/ErrosSemantica.txt`
   
   O ficheiro "ErrosSemantica.txt" tem como objetivo detectar os seguintes erros semânticos: 
	1. Criar uma tabela em que a coluna seja do tipo integer ou do tipo texto.
	2. Adicionar coluna sem precisar de aspas e não ser referido qual o tipo (Int, Real..).
	3. Ao invocar o método extract é necessário por columns. e não columns

