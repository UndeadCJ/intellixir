package com.github.undeadcj.intellixir.ide.highlighting

import com.github.undeadcj.intellixir.language.ElixirLexerAdapter
import com.github.undeadcj.intellixir.language.psi.ElixirTypes
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType


class ElixirSyntaxHighlighter : SyntaxHighlighterBase() {
    val SEPARATOR: TextAttributesKey =
        createTextAttributesKey("SIMPLE_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN)
    val KEY: TextAttributesKey = createTextAttributesKey("SIMPLE_KEY", DefaultLanguageHighlighterColors.KEYWORD)
    val VALUE: TextAttributesKey = createTextAttributesKey("SIMPLE_VALUE", DefaultLanguageHighlighterColors.STRING)
    val COMMENT: TextAttributesKey =
        createTextAttributesKey("SIMPLE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)

    private val SEPARATOR_KEYS = arrayOf(SEPARATOR)
    private val KEY_KEYS = arrayOf(KEY)
    private val VALUE_KEYS = arrayOf(VALUE)
    private val COMMENT_KEYS = arrayOf(COMMENT)
    private val EMPTY_KEYS = emptyArray<TextAttributesKey>()

    override fun getHighlightingLexer() = ElixirLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        if (tokenType == ElixirTypes.SEPARATOR) {
            return SEPARATOR_KEYS
        }
        if (tokenType == ElixirTypes.KEY) {
            return KEY_KEYS
        }
        if (tokenType == ElixirTypes.VALUE) {
            return VALUE_KEYS
        }
        if (tokenType == ElixirTypes.COMMENT) {
            return COMMENT_KEYS
        }

        return EMPTY_KEYS
    }
}