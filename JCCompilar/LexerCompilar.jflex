package Analizadores;
import java_cup.runtime.*; 
import Analizadores.sym.*;
import java.util.List;
import java.util.ArrayList;
import Analizadores.ErrorCom;

%%

//Configuracion JFLEX
%public
%class LexerCompilar
%standalone
%line
%column
%cup

//Expresiones Regulares


/*--------------Literales---------------*/
letra               = [a-zA-Z]
digito              = [0-9]
numero              = {digito}+([.]{digito}{1,6})?

/*----------Espacios En blanco----------*/
espacio             = " "
saltoLinea          = \n|\r|\r\n
espacioblanco       = ({espacio}|{saltoLinea}| [\t\n])+

/*------------Identificador------------*/
identificador       = ({letra}|"_") ({letra}|{digito}|"_")*



/*--------- Codigo Incrustado ---------*/
%{
    private List<ErrorCom> erroresCom;

    private void error(String lexeme) {
        erroresCom.add(new ErrorCom("Lexico","Simbolo no existe en el lenguaje",String.valueOf(yyline+1),String.valueOf(yycolumn+1),lexeme));
    }    

    private void errorPrueba(String lexeme, String tipo) {
        erroresCom.add(new ErrorCom("PRUEBA",tipo,String.valueOf(yyline+1),String.valueOf(yycolumn+1),lexeme));
    }

    public List<ErrorCom> getErroresCom() {
        return erroresCom;
    }
%}

%init{
    erroresCom = new ArrayList<>();
%init}

%%


//Reglas Lexicas
<YYINITIAL> {    

    {espacioblanco}         {/*vacio*/}
    /*-------- Palabras Reservadas --------*/
    "programa"              {return new Symbol(sym.PROGRAMA,yyline+1,yycolumn+1, yytext());}
    "procedimiento"         {return new Symbol(sym.PROCEDIMIENTO,yyline+1,yycolumn+1, yytext());}
    "end"                   {return new Symbol(sym.END,yyline+1,yycolumn+1, yytext());}
    "integer"               {return new Symbol(sym.INTEGER,yyline+1,yycolumn+1, yytext());}
    "begin"                 {return new Symbol(sym.BEGIN,yyline+1,yycolumn+1, yytext());}
    {identificador}         {return new Symbol(sym.ID,yyline+1,yycolumn+1, yytext());}
    {numero}                {return new Symbol(sym.NUM,yyline+1,yycolumn+1, yytext());}
    /*------------ Operadores ------------*/   
    "("                     {return new Symbol(sym.PARI,yyline+1,yycolumn+1, yytext());}      
    ")"                     {return new Symbol(sym.PARD,yyline+1,yycolumn+1, yytext());}        
    ":"                     {return new Symbol(sym.DOSPUNT,yyline+1,yycolumn+1, yytext());}      
    ";"                     {return new Symbol(sym.PUNTCOMA,yyline+1,yycolumn+1, yytext());}      
    ","                     {return new Symbol(sym.COMA,yyline+1,yycolumn+1, yytext());}      
    "="                     {return new Symbol(sym.IGUAL,yyline+1,yycolumn+1, yytext());}      


    

}

/* Error por cualquier otro simbolo*/
[^]
		{ error(yytext()); new Symbol(sym.error,yyline,yycolumn, yytext());}
