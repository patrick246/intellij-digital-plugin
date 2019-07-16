package de.patrick246.intellij.digitalplugin.language;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.lexer.FlexLexer;
import de.patrick246.intellij.digitalplugin.language.psi.DATypes;


%%
%class DALexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{ return;
%eof}
%ignorecase


CLRF=(\r|\n|\r\n)
WHITE_SPACE=[\ \t]
TEXT=[^\n]+
ALIAS_FIRST_CHAR=[a-zA-Z_]
ALIAS_NEXT=[a-zA-Z0-9_]*
REGNUMBER=(R([0-9]|10|11|12))|BP|SP|RA
HEXNUMBER=(0x)?[0-9a-fA-F]+
STRING=\"([^\"\\]|\\.)*\"
CHAR=\'.\'

%state AFTER_INST
%state AFTER_DIRECTIVE
%state IN_LABEL
%state AFTER_REG_DIRECTIVE
%state IN_COMMENT
%state EXPECT_EXPR
%%

<YYINITIAL> "NOP"|"BRK"|"RETI"|"LEAVE"|"LEAVEI"|"ENTERI"     {yybegin(YYINITIAL); return DATypes.SIMPLEINSTRUCTION;}

<YYINITIAL> "MOV"|"ADD"|"ADC"|"SUB"|"SBC"|"AND"|"OR"|"EOR"|"MUL"|"CMP" { yybegin(AFTER_INST); return DATypes.REGREGINSTRUCTION;}

<YYINITIAL> "LDI"|"ADDI"|"ADCI"|"SUBI"|"SBCI"|"ANDI"|"ORI"|"EORI"|"MULI"|"CPI"|"LDS"|"RCALL"|"IN" {yybegin(AFTER_INST); return DATypes.REGIMMINSTRUCTION;}

<YYINITIAL> "LSL"|"LSR"|"ROL"|"ROR"|"ASR"|"SWAP"|"SWAPN"|"RRET"|"POP"|"DEC"|"PUSH"|"INC" {yybegin(AFTER_INST); return DATypes.REGINSTRUCTION;}

<YYINITIAL> "STS"|"OUT" {yybegin(AFTER_INST); return DATypes.IMMREGINSTRUCTION;}

<YYINITIAL> "ST"|"OUTR" {yybegin(AFTER_INST); return DATypes.INDREGREGINSTRUCTION;}

<YYINITIAL> "INR"|"LD" {yybegin(AFTER_INST); return DATypes.REGINDREGINSTRUCTION;}

<YYINITIAL> "STD" {yybegin(AFTER_INST); return DATypes.INDCALCREGINSTRUCTION;}

<YYINITIAL> "LDD" {yybegin(AFTER_INST); return DATypes.REGINDCALCINSTRUCTION;}

<YYINITIAL> "BRCS"|"BREQ"|"BRMI"|"BRCC"|"BRNE"|"BRPL"|"JMP"|"CALL"|"ENTER"|"_SCALL" {yybegin(AFTER_INST); return DATypes.IMMINSTRUCTION;}

<YYINITIAL> "RET" {yybegin(AFTER_INST); return DATypes.IMMOPTINSTRUCTION;}

<YYINITIAL> "reg" {yybegin(AFTER_REG_DIRECTIVE); return DATypes.ALIASREGISTERDIRECTIVE;}

<YYINITIAL> "const" {yybegin(AFTER_REG_DIRECTIVE); return DATypes.ALIASEXPRDIRECTIVE;}

<YYINITIAL> "long"|"word" {yybegin(AFTER_DIRECTIVE); return DATypes.ALIASDIRECTIVE;}

<YYINITIAL> "include" {yybegin(AFTER_DIRECTIVE); return DATypes.STRINGDIRECTIVE;}

<YYINITIAL> "data" {yybegin(AFTER_DIRECTIVE); return DATypes.ALIASMULTIVALUEDIRECTIVE;}

<YYINITIAL> "org"|"dorg" {yybegin(AFTER_DIRECTIVE); return DATypes.ADDRESSDIRECTIVE;}

<YYINITIAL> {ALIAS_FIRST_CHAR}{ALIAS_NEXT} {yybegin(IN_LABEL); return DATypes.LABELDEFINITION;}

<IN_LABEL> "\:" {yybegin(YYINITIAL); return DATypes.COLON;}

<AFTER_DIRECTIVE> {STRING} {yybegin(AFTER_DIRECTIVE); return DATypes.STRINGTEXT;}

<AFTER_INST,AFTER_DIRECTIVE,AFTER_REG_DIRECTIVE> {REGNUMBER} {yybegin(AFTER_INST); return DATypes.REGNUMBER;}

<AFTER_REG_DIRECTIVE> {ALIAS_FIRST_CHAR}{ALIAS_NEXT} {yybegin(AFTER_REG_DIRECTIVE); return DATypes.ALIASDEFINITIONTOKEN;}

";" {yybegin(IN_COMMENT); return DATypes.COMMENTCHAR;}

<IN_COMMENT> {TEXT} {yybegin(YYINITIAL); return DATypes.COMMENTTEXT;}

{WHITE_SPACE} {return TokenType.WHITE_SPACE;}

<AFTER_INST,AFTER_DIRECTIVE> {ALIAS_FIRST_CHAR}{ALIAS_NEXT} {return DATypes.ALIAS;}

<AFTER_INST> "," { return DATypes.COMMA;}

{HEXNUMBER} {yybegin(AFTER_INST); return DATypes.HEXNUM;}

{CLRF} {yybegin(YYINITIAL); return DATypes.NEWLINE;}

[^] {return TokenType.BAD_CHARACTER;}
