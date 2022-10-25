grammar Bdex;

main:
    stat* EOF
  ;

stat:
    add_line      # StatAddLine
  | add_column    # StatAddColumn
  | remove_column # StatRemoveColumn
  | remove_line   # StatRemoveLine
  | put_value     # StatPutValue
  | print         # StatPrint
  | declaration   # StatDeclare
  | assign        # StatAssign
  | iterate       # StatIterate
  | increment     # StatIncrement
  | save_table    # StatSaveTable
  | load_table    # StatLoadTable
  ;

table returns[String tableVar, Type eType]:
    create_table  # CreateTable
  | extract_table # ExtractTable
  | join_tables   # JoinTables
  | VAR           # VariableTable
  ;

create_table returns[String tableVar]: // Cria uma tabela
    'create_table' '[' columns ']'                    # CreateTableNoLine
  | 'create_table' '[' columns ']' '[' line=expr ']'  # CreateTableLine
  | 'create_table' '[' columns ']' ':' '[' values ']' # CreateTableValues
  ;

add_line: // Adiciona uma linha de uma tabela
     'add_line' tablename=VAR index=expr? '[' values ']' ';' # AddLineValues
  |  'add_line' tablename=VAR index=expr? ';'                # AddLineIndexOmission
  ;

add_column: // Adiciona Coluna a tabela
    'add_column' tablename=VAR where=('after' | 'before') '[' place=column ']' '[' to=column ']' ';'                # AddColumnOmissionPlace
  | 'add_column' tablename=VAR where=('after' | 'before') '[' place=column ']' '[' to=column ']' '[' values ']' ';' # AddColumnValuesPlace
  | 'add_column' tablename=VAR where=('first' | 'last')?  '[' to=column ']' ';'                                     # AddColumnOmissionFL
  | 'add_column' tablename=VAR where=('first' | 'last')?  '[' to=column ']'    '[' values ']' ';'                   # AddColumnValuesFL
  ;

remove_column: //Remove a coluna/s
    'remove_column' tablename=VAR '[' column ']' ';' 
  ;

remove_line: // Remove uma linha de uma tabela
    'remove_line' tablename=VAR index=expr? ';'
  ;

put_value returns[Type eType, String varName]: //puts value, if the a value is already there substitui
    'put_value' tablename=VAR '[' column ',' expr ']' ':' '[' expr ']' ';'
  ;

extract_table returns[String tableVar]: // Extrai uma linha/coluna de uma tabela existente com critérios
    'extract' 'from' reftable=VAR 'columns.' '[' columns ']'                                                     # ExtractTableColumns
  | 'extract' 'from' reftable=VAR 'columns' 'interval.' '[' column ',' column ']'                                # ExtractTableColumnsInterval
  | 'extract' 'from' reftable=VAR 'line.' '[' values ']'                                                         # ExtractTableLines
  | 'extract' 'from' reftable=VAR 'line.' '[' expr ',' expr ']'                                                  # ExtractTableLineBreak
  | 'extract' 'from' reftable=VAR 'columns.' '[' columns ']' 'line.' '[' expr ',' expr ']'                       # ExtractTableBothColumns
  | 'extract' 'from' reftable=VAR 'columns' 'interval.' '[' column ',' column ']'  'line.' '[' expr ',' expr ']' # ExtractTableBothInterval
  | 'extract' 'from' reftable=VAR 'contains' '[' values ']'                                                      # ExtractTableContains
  ;

join_tables returns[String tableVar]: // Junta tabelas
    'join' table (',' table)+ 
  ;

iterator returns[String varName]: // Itera sobre uma tabela, completa, por linha ou por coluna???
    'iterator' tablename=VAR ';'                              # IteratorTable
  | 'iterator' tablename=VAR 'line' index=expr ';'            # IteratorLine
  | 'iterator' tablename=VAR 'column' '['method=column']' ';' # IteratorColumn
  ;

iterate: // Itera sobre uma tabela, completa, por linha ou por coluna???
    VAR '.next' ';'
  ;  

load_table: // Lê uma tabela de um ficheiro CSV
    'load' tablename=VAR filename=TEXT ';'
  ;

save_table: // Guarda uma tabela num ficheiro CSV
    'save' tablename=VAR filename=TEXT ';'
  ;

columns returns[String varName]:
    column (',' column)*
  ;

column returns[Type eType, String varName]:
    TEXT '(' type=('Int'|'Real'|'Text') ')'
  ; 

print: //Print de uma variavel
    'print' VAR ('('it=INTEGER ')')? ';' # PrintVar
  | 'print' table ';'                    # PrintTable
  | 'print' expr ';'                     # PrintExpr
  | 'print' assign                       # PrintAssign
  | 'print' iterate                      # PrintIterate
  ;

assign returns[Type eType, String varName]: // Atribui uma tabela ou valor a uma variável
    tipo VAR '=' table ';'                    # AssignDeclarationTable
  | tipo VAR '=' expr  ';'                    # AssignDeclarationExpr
  | tipo VAR '=' put_value                    # AssignDeclarationPut
  | tipo VAR '=' iterator                     # AssignDeclarationIterator
  | tipo VAR '=' iterate                      # AssignDeclarationIterate
  | tipo VAR '=' '(' expr (',' expr)* ')' ';' # AssignDeclarationTupple
  | VAR '=' table ';'                         # AssignTable
  | VAR '=' expr ';'                          # AssignExpr
  | VAR '=' put_value                         # AssignPut
  | VAR '=' iterator                          # AssignIterator
  | VAR '=' iterate                           # AssignIterate
  ;

declaration returns[Type eType, String varName]:
    tipo VAR ';'
  ;  

tipo returns[Type res]:
    'Int'  {$res = new IntegerType();}
  | 'Real' {$res = new RealType();}
  | 'Text' {$res = new TextType();}
  | 'Table' {$res = new TableType();}
  | 'Iterator' {$res = new IteratorType();}
  | 'Tupple' {$res = new TuppleType();}
  | 'Boolean' {$res = new BooleanType();}
  | 'Object' {$res = new ObjectType();}
  ;

values returns[String varName]:
    expr (',' expr)* 
  ;

expr returns[Type eType, String varName]:
    '(' expr ')'                                # ExprParentesis
  | input                                       # ExprInput
  |  op=('+' | '-') expr 	                    # ExprUnary
  | increment                                   # ExprIncrement
  | expr op=('*' | '/' | '%') expr              # ExprMultDivMod
  | expr op=('+' | '-') expr 	                # ExprAddSub
  | expr op=('=='|'!=') expr                    # ExprBin
  | expr op=('and' | 'or') expr                 # ExprBin
  | expr op=('<' | '<=' | '>' | '>=') expr      # ExprBin
  | VAR ('('it=INTEGER ')')?'.concat(' expr ')' # ExprConcat
  | REAL                                        # ExprReal
  | INTEGER             	                    # ExprInteger
  | VAR	('('it=INTEGER ')')?		            # ExprVar
  | TEXT                                        # ExprText
  | BOOLEAN                                     # ExprBoolean
  ;

input returns[Type eType, String varName]:
    'inputInt'  # InputInt
  | 'inputReal' # InputReal
  | 'inputText' # InputText
  ;

increment returns[String varName]:
    op=('++' | '--') VAR # PreIncrement
  | VAR op=('++' | '--') # PosIncrement
  ;

BOOLEAN: 'true' | 'false';
TEXT: '"'.*?'"';
INTEGER: [0-9]+;
REAL: ([0-9]*?'.'[0-9]+) |  ([0-9]+ ('.'[0-9]*)? );
VAR: LETTER (LETTER|DIGIT)*;
fragment LETTER: [a-zA-Z_];
fragment DIGIT: [0-9];

BLOCK_COMMENTS: '#' .*? '#' -> skip;
LINE_COMMENT: '//' .*? '\n' -> skip;
NEWLINE: [\r\n] -> skip;
WS: [ \t]+ -> skip;
ERROR: .;
