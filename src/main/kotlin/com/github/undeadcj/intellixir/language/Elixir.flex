package com.github.undeadcj.intellixir.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.github.undeadcj.intellixir.language.psi.ElixirTypes;
import com.intellij.psi.TokenType;

%%

%class ElixirLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{
    return;
%eof}
%eofval{
    return null;
%eofval}

%{
%}

// Basic Definitions
LINE_TERMINATOR = \r|\n|\r\n
WHITESPACE = {LINE_TERMINATOR} | [ \t\f]
COMMENT = "#"[^\r\n]*
SIGIL = "~"[A-Z]

// Docstrings
DOC_OPEN="@doc"
MODULE_DOC_OPEN="@moduledoc"
TRIPLE_QUOTE=\"\"\"
DOCSTRING = {DOC_OPEN}[ \t]({SIGIL})?{TRIPLE_QUOTE}([^\"]|\"\"{TRIPLE_QUOTE}|\"{1,2}[^\"])*{TRIPLE_QUOTE}
MODULE_DOCSTRING = {MODULE_DOC_OPEN}[ \t]({SIGIL})?{TRIPLE_QUOTE}([^\"]|\"\"{TRIPLE_QUOTE}|\"{1,2}[^\"])*{TRIPLE_QUOTE}

// Literals
ATOM = :[a-zA-Z_][a-zA-Z0-9_]*
STRING = \"([^\"\\]|\\.)*\"
IDENTIFIER = [a-z][a-zA-Z0-9_]*
INTEGER = [0-9]+

// Keywords
KEYWORDS =
  "def" | 
  "defmodule" | 
  "defp" | 
  "do" | 
  "end" | 
  "if" | 
  "else" | 
  "case" | 
  "when" | 
  "fn" | 
  "true" | 
  "false" | 
  "nil" |
  "and" |
  "or" |
  "not" |
  "in" |
  "for" |
  "with" |
  "receive" |
  "try" |
  "catch" |
  "raise" |
  "alias" |
  "use" |
  "import" |
  "require";

KEYWORD = ({WHITESPACE}|[,(])?{KEYWORDS}({WHITESPACE}|[,)])

%%

<YYINITIAL> {
    // Docstrings
    {DOCSTRING}   { return ElixirTypes.DOCSTRING; }
    {MODULE_DOCSTRING}   { return ElixirTypes.DOCSTRING; }

    // Keywords
    {KEYWORD}   { return ElixirTypes.KEYWORD; }

    // Literals
    {ATOM}        { return ElixirTypes.ATOM; }
    {STRING} {return ElixirTypes.STRING; }

    // Identifiers
    {IDENTIFIER} {return ElixirTypes.IDENTIFIER; }

    // Numbers
    {INTEGER} {return ElixirTypes.INTEGER; }
}

// Comments
{COMMENT}           { return ElixirTypes.COMMENT; }

// Whitespaces e caracteres inválidos
{WHITESPACE}        { return TokenType.WHITE_SPACE; }
[^]                 { return TokenType.BAD_CHARACTER; }