grammar LawXAI;
prog:	clauses ;
clauses: clause (NEWLINE clause)* NEWLINE?;
       
clause:	head LARROW body? ':' reasonings? ';';

reasonings: ' '* string ' '* (':' ' '* string ' '*)*;

body: d_atom (COMMA d_atom)*;

d_atom: not_d_atom (OR not_d_atom)*;
not_d_atom: atom | '[' c_atom ']';

c_atom: not_c_atom (AND not_c_atom)*;
not_c_atom: atom | '[' d_atom ']';

head: atom;
atom: predicate '(' argument ')' ('{' term '}')?;
predicate: '~'? (MATH|VARIABLE|CONSTANT);
argument: g_term (COMMA g_term)*;



g_term: term | d_term | c_term;

d_term: not_d_term (OR not_d_term)+;
not_d_term: term | '[' c_term ']';

c_term: not_c_term (OR not_c_term)+;
not_c_term: term | '[' d_term ']';

term: constant | variable | inner_atom | mathTerm;

mathTerm: plusTerm | minusTerm | timesTerm | divideTerm | smallerTerm | biggerTerm;
plusTerm: (constant|variable) PLUS (constant|variable);
minusTerm:(constant|variable) MINUS (constant|variable);
timesTerm:(constant|variable) TIMES (constant|variable);
divideTerm:(constant|variable) DIVIDE (constant|variable);

smallerTerm: (constant|variable) '~'? SMALLER | '~'? SMALLER (constant|variable);
biggerTerm: (constant|variable) '~'? BIGGER | '~'? BIGGER (constant|variable);

constant: CONSTANT;
variable: VARIABLE;
inner_atom: atom; 

string: (CONSTANT | VARIABLE | MATH)+;

LARROW: [ ]*[<][-][ ]*;
NEWLINE: [\r\n]+;
COMMA: [ ]*[,][ ]*;
OR: [ ]*[\\][/][ ]*;
AND: [ ]*[/][\\][ ]*;
MATH: [+]|[-]|[*]|[/]|[<]|[>];
PLUS: [+];
MINUS: [-];
TIMES: [*];
DIVIDE: [/];
SMALLER: [ ]*[<][ ]*;
BIGGER: [ ]*[>][ ]*;
VARIABLE: ([\p{Lu}]|[#])([\p{L} #0-9_]|[.])*;
CONSTANT: ([\p{Ll}]|[§]|[0-9])([\p{L} 0-9_]|[.]|[/])*;