package com.github.undeadcj.intellixir.ide.highlighting

import com.github.undeadcj.intellixir.language.ElixirLexerAdapter
import com.github.undeadcj.intellixir.language.psi.ElixirTypes
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType


class ElixirSyntaxHighlighter : SyntaxHighlighterBase() {
    val COMMENT: TextAttributesKey =
        createTextAttributesKey("ELIXIR_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)

    val DOCSTRING: TextAttributesKey =
        createTextAttributesKey("ELIXIR_DOCSTRING", DefaultLanguageHighlighterColors.DOC_COMMENT)

    val KEYWORD: TextAttributesKey =
        createTextAttributesKey("ELIXIR_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)

    val ATOM: TextAttributesKey =
        createTextAttributesKey("ELIXIR_ATOM", DefaultLanguageHighlighterColors.INSTANCE_FIELD)

    val IDENTIFIER: TextAttributesKey =
        createTextAttributesKey("ELIXIR_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER)

    val NUMBER: TextAttributesKey =
        createTextAttributesKey("ELIXIR_NUMBER", DefaultLanguageHighlighterColors.NUMBER)

    val STRING: TextAttributesKey =
        createTextAttributesKey("ELIXIR_STRING", DefaultLanguageHighlighterColors.STRING)


    private val COMMENT_KEYS = arrayOf(COMMENT)
    private val DOCSTRING_KEYS = arrayOf(DOCSTRING)
    private val KEYWORD_KEYS = arrayOf(KEYWORD)
    private val ATOM_KEYS = arrayOf(ATOM)
    private val IDENTIFIER_KEYS = arrayOf(IDENTIFIER)
    private val NUMBER_KEYS = arrayOf(NUMBER)
    private val STRING_KEYS = arrayOf(STRING)
    private val EMPTY_KEYS = emptyArray<TextAttributesKey>()

    override fun getHighlightingLexer() = ElixirLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        if (tokenType == ElixirTypes.COMMENT) {
            return COMMENT_KEYS
        }
        if (tokenType == ElixirTypes.DOCSTRING) {
            return DOCSTRING_KEYS
        }
        if (tokenType == ElixirTypes.KEYWORD) {
            return KEYWORD_KEYS
        }
        if (tokenType == ElixirTypes.ATOM) {
            return ATOM_KEYS
        }
        if (tokenType == ElixirTypes.IDENTIFIER) {
            return IDENTIFIER_KEYS
        }
        if (tokenType == ElixirTypes.INTEGER) {
            return NUMBER_KEYS
        }
        if (tokenType == ElixirTypes.STRING) {
            return STRING_KEYS
        }

        return EMPTY_KEYS
    }
}