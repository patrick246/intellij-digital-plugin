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

CLRF=(\r|\n|\r\n)
WHITE_SPACE=[\ \t]
TEXT=[^\n]+
FIRST_REGISTER_ALIAS=[a-zA-Z]
REGISTER_ALIAS=[a-zA-Z0-9]*
REGNUMBER=(R[0-9]|11|12)|BP|SP|RA

%state AFTER_INST
%state IN_COMMENT
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

<YYINITIAL> "BRCS"|"BREQ"|"BRMI"|"BRCC"|"BRNE"|"BRPL"|"JMP"|"RET"|"CALL"|"ENTER"|"_SCALL" {yybegin(AFTER_INST); return DATypes.IMMINSTRUCTION;}

";" {yybegin(IN_COMMENT); return DATypes.COMMENTCHAR;}

<IN_COMMENT> {TEXT} {yybegin(YYINITIAL); return DATypes.TEXT;}

<AFTER_INST> {WHITE_SPACE} {yybegin(AFTER_INST); return TokenType.WHITE_SPACE;}

<AFTER_INST> {REGNUMBER} {yybegin(AFTER_INST); return DATypes.REGNUMBER;}

<AFTER_INST> {FIRST_REGISTER_ALIAS}{REGISTER_ALIAS} {yybegin(AFTER_INST); return DATypes.REGISTERALIAS;}

<AFTER_INST> "," { return DATypes.COMMA;}

{CLRF} {yybegin(YYINITIAL); return DATypes.NEWLINE;}

[^] {return TokenType.BAD_CHARACTER;}
