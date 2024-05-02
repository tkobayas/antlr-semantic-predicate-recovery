grammar Interpreter;

conditionalOrExpression : relationalRestriction ( OR relationalRestriction )* ;

relationalRestriction  : primary ( orRestriction )* ;

orRestriction : singleRestriction (OR annotation? singleRestriction)* ;

singleRestriction : operator primary ;

operator : EQUALS
         | {  _input.LT(1).getText().equals("customOperator") }? ID  // check custom operators using java util method
         ;

primary : INT
        | ID
        | '(' ID ')'
        | ID '(' ID ')'
        ;

annotation : AT ID ;

OR     :  '||';
ID     :  [a-zA-Z]+;
INT    :  '0'..'9'+;
EQUALS :  '==';
AT     :  '@';

WS     :  [ \t\r\n]+ -> skip;
