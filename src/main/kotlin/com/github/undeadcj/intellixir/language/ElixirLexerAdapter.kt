package com.github.undeadcj.intellixir.language

import com.intellij.lexer.FlexAdapter

class ElixirLexerAdapter : FlexAdapter(ElixirLexer(null))