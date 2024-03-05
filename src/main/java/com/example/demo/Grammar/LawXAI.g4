grammar LawXAI;
prog:	clauses ;
clauses: clause (NEWLINE clause)* NEWLINE?;
       
clause:	head LARROW body? COLON reasonings? SEMICOLON;

reasonings: string (COLON string)*;

body: d_atom (COMMA d_atom)*;

d_atom: not_d_atom (OR not_d_atom)*;
not_d_atom: atom | LBRACKET c_atom RBRACKET;

c_atom: not_c_atom (AND not_c_atom)*;
not_c_atom: atom | LBRACKET d_atom RBRACKET;

head: atom;
atom: predicate LPAR argument RPAR (LTUBORG term RTUBORG)?;
predicate: NEGATED? (math|VARIABLE|CONSTANT);
argument: g_term (COMMA g_term)*;



g_term: term | d_term | c_term;

d_term: not_d_term (OR not_d_term)+;
not_d_term: term | LBRACKET c_term RBRACKET;

c_term: not_c_term (AND not_c_term)+;
not_c_term: term | LBRACKET d_term RBRACKET;

term: constant | variable | inner_atom | mathTerm;

mathTerm: plusTerm | minusTerm | timesTerm | divideTerm | smallerTerm | biggerTerm | equalTerm;
plusTerm: (constant|variable) PLUS (constant|variable);
minusTerm:(constant|variable) MINUS (constant|variable);
timesTerm:(constant|variable) TIMES (constant|variable);
divideTerm:(constant|variable) DIVIDE (constant|variable);
equalTerm:(constant|variable) NEGATED? EQUAL | NEGATED? EQUAL (constant|variable);


smallerTerm: (constant|variable) NEGATED? SMALLER | NEGATED? SMALLER (constant|variable);
biggerTerm: (constant|variable) NEGATED? BIGGER | NEGATED? BIGGER (constant|variable);

constant: CONSTANT;
variable: VARIABLE;
inner_atom: atom; 

string: (CONSTANT | VARIABLE | math | LPAR | RPAR | LBRACKET | RBRACKET | LTUBORG | RTUBORG | RAISED)+;
math: PLUS | MINUS | TIMES | DIVIDE | EQUAL | SMALLER | BIGGER;

LARROW: [ ]*[<][-][ ]*;
NEWLINE: [\r\n]+;
COMMA: [ ]*[,][ ]*;
OR: [ ]*[\\][/][ ]*;
AND: [ ]*[/][\\][ ]*;
PLUS: [ ]*[+][ ]*;
MINUS: [ ]*[-][ ]*;
TIMES: [ ]*[*][ ]*;
DIVIDE: [ ]*[/][ ]*;
EQUAL: [ ]*[=][ ]*;
SMALLER: [ ]*[<][ ]*;
BIGGER: [ ]*[>][ ]*;
COLON: [ ]*[:][ ]*;
SEMICOLON: [ ]*[;][ ]*;
LPAR: [ ]*[(][ ]*;
RPAR: [ ]*[)][ ]*;
LBRACKET: [ ]*[[][ ]*;
RBRACKET: [ ]*[\]][ ]*;
LTUBORG: [ ]*[{][ ]*;
RTUBORG: [ ]*[}][ ]*;
RAISED: [ ]*[\\^][ ]*;
NEGATED: [~];
VARIABLE: ([\p{Lu}]|[#])([\p{L} #0-9_]|[.]|[§])*([\p{L}#0-9_]|[.]|[§])*;
CONSTANT: ([\p{Ll}]|[§]|[0-9])([\p{L} 0-9_]|[.]|[/]|[§])*([\p{L}#0-9_]|[.]|[§])*;