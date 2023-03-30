package codigo;
import static codigo.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
S=["[","]","{","}",".",",","¬",":",";","-","_","#","$","'","¿","?"]+
espacio=[ ,\t,\r,\n]+
%{
    public String lexema;
%}
%%
abstract |
assert |
boolean |
break |
byte |
case |
catch |
char |
class |
continue |
default |
do |
double |
else |
enum |
exports |
extends |
final |
finally |
float |
for |
if |
implements |
import |
instanceof |
int |
interface |
long |
module |
native |
new |
package |
private |
protected |
public |
requires |
return |
short |
static |
strictfp |
super |
switch |
synchronized |
this |
throw |
throws |
transient |
try |
void |
volatile |
while {lexema=yytext(); return Reservadas;}
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"=" {return Igual;}
"+" {return Suma;}
"-" {return Resta;}
"*" {return Multiplicacion;}
"/" {return Division;}
">" {return Mayor;}
"<" {return Menor;}
"!=" {return Diferente;}
"%" {return Modular;}
"++" {return Incremento;}
"--" {return Decremento;}
"<=" {return MenorIgual;}
">=" {return MayorIgual;}
"==" {return ExactamenteIgual;}
"!" {return Negacion;}
"&&" {return AND;}
"||" {return OR;}
"^" {return XOR;}
{L}({L}|{D})* {lexema=yytext(); return Identificador;}
("(-"{D}+")")|{D}+ {lexema=yytext(); return Numero;}
 . {return ERROR;}
 {S}* {lexema=yytext(); return Signos;}