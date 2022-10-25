grammar Table;
file:
    line line* EOF
  ;
line:
    field (',' field)* NEWLINE
  ;
field:
    TEXT
  | STRING
  |
  ;
NEWLINE: '\r'?'\n';
STRING: [ \t]* '"'.*?'"' [ \t]*;
TEXT: ~[,"\r\n] ~[,\r\n]*;
