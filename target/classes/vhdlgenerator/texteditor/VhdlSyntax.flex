
/* 
 * ORIGINAL VERSION SOURCE LINK
 * https://github.com/logisim-evolution/
 *
 */
 
/*
 * THIS IS MY VERSION 
 * File current version https://github.com/Var7600/VHDL_GENERATOR
 */
 
 // 
 //   lexical analyzer to identify simple VHDL SYNTAX/KEYWORDS
 //
 import java.util.List;
 import java.util.LinkedList ;
%%
%public
%class VhdlSyntax
%type Token
%unicode
%ignorecase

%{
     // a linkedList of token to highlight
    private List<Token> list_token = new LinkedList<> ();
    /**
	 * Constructor.  This must be here because JFlex does not generate a
	 * no-parameter constructor.
	 */
	public VhdlSyntax() {}
	
    /**
    * this method return a List of token to highlight
    * @return a list of token
    */
    public  List<Token> getListToken()
    {
        return this.list_token ;
    }
    
    private void addToken(int type , String text , int offset , int length)
    {
          list_token.add(new Token(type,text,offset,length));
            
    }
  
%}
LineTerminator				= (\n|\r|\r\n)
WhiteSpace				= ([ \t\f]+)
NonzeroDigit						= [1-9]
Digit							= ("0"|{NonzeroDigit})
HexDigit							= ({Digit}|[A-Fa-f])
EscapedSourceCharacter				= ("\\"{HexDigit}{HexDigit}{HexDigit}{HexDigit})
LineCommentBegin			= "--"
Comment = --.* 
Letter							= [A-Za-z]
LetterOrUnderscore				= ({Letter}|"_")
IdentifierStart					= ({LetterOrUnderscore}|"$")
IdentifierPart						= ({IdentifierStart}|{Digit}|("\\"{EscapedSourceCharacter}))
Identifier				= ({IdentifierStart}{IdentifierPart}*)

%%


    /*COMMENTS*/
 /*COMMENTS*/
{Comment} {  addToken( SymbolType.COMMENT, yytext(),zzStartRead,  yylength() ); }
	/* KEYWORDS */
"access" |
"after" |
"alias" |
"all" |
"architecture" |
"array" |
"assert" |
"attribute" |
"begin" |
"block" |
"body" |
"buffer" |
"bus" |
"case" |
"component" |
"configuration" |
"constant" |
"disconnect" |
"downto" |
"else" |
"elsif" |
"end" |
"end" |
"entity" |
"exit" |
"file" |
"for" |
"function" |
"generate" |
"generic" |
"group" |
"guarded" |
"if" |
"impure" |
"in" |
"inertial" |
"inout" |
"is" |
"label" |
"library" |
"linkage" |
"literal" |
"loop" |
"map" |
"new" |
"rising\_edge" |
"next" |
"null" |
"of" |
"on" |
"open" |
"others" |
"out" |
"package" |
"port" |
"postponed" |
"procedure" |
"process" |
"pure" |
"range" |
"record" |
"register" |
"reject" |
"report" |
"return" |
"select" |
"severity" |
"shared" |
"signal" |
"subtype" |
"then" |
"to" |
"transport" |
"type" |
"unaffected" |
"units" |
"until" |
"use" |
"variable" |
"wait" |
"when" |
"while" |
"with"  { addToken( SymbolType.KEYWORDS, yytext() , zzStartRead , yylength() )  ; }
	/* KEYWORDS 2 (JUST AN OPTIONAL SET OF KEYWORDS COLORED DIFFERENTLY) */
"'ACTIVE" |
"'ASCENDING" |
"'BASE" |
"'DELAYED" |
"'DRIVING" |
"'DRIVING_VALUE" |
"'EVENT" |
"'HIGH" |
"'IMAGE" |
"'INSTANCE_NAME" |
"'LAST_ACTIVE" |
"'LAST_EVENT" |
"'LAST_VALUE" |
"'LEFT" |
"'LEFTOF" |
"'LOW" |
"'PATH_NAME" |
"'POS" |
"'PRED" |
"'QUIET" |
"'RANGE" |
"'REVERSE_RANGE" |
"'RIGHT" |
"'RIGHTOF" |
"'SIMPLE_NAME" |
"'STABLE" |
"'SUCCESS" |
"'TRANSACTION" |
"'VAL" |
"'VALUE"    {  addToken( SymbolType.KEYWORDS, yytext(),zzStartRead   , yylength() ) ;  }
	/* DATA TYPES */
"bit" |
"bit_vector" |
"boolean" |
"integer" |
"natural" |
"positive" |
"std_logic" |
"std_logic_unsigned" |
"std_logic_vector" |
"std_logis_signed"		{ addToken(SymbolType.DATA_TYPE, yytext() ,zzStartRead , yylength()) ; }
	/* FUNCTIONS */
"'-'" |
"'0'" |
"'1'" |
"'H'" |
"'L'" |
"'U'" |
"'W'" |
"'X'" |
"'Z'" |
"false" |
"true"		{ addToken( SymbolType.FUNCTIONS, yytext() , zzStartRead  , yylength() ) ; }
	/* OPERATORS. */
"&" |
"**" |
"abs" |
"mod" |
"rem" |
"sll" |
"srl" |
"sla" |
"sra" |
"rol" |
"ror" |
"not" |
"and" |
"or" |
"nand" |
"nor" |
"xor" |
"xnor"		{ addToken( SymbolType.OPERATORS , yytext(),zzStartRead  ,  yylength() )  ; }
{Identifier} { addToken(SymbolType.IDENTIFIER, yytext(),zzStartRead  , yylength() );}
. { addToken(SymbolType.IDENTIFIER , yytext() , zzStartRead  , yylength() ); }
{LineTerminator} { /*Do nothing*/  }
{WhiteSpace} { /*Do nothing*/}
