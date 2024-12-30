package com.github.undeadcj.intellixir.language

import com.github.undeadcj.intellixir.language.parser.ElixirParser
import com.github.undeadcj.intellixir.language.psi.ElixirFile
import com.github.undeadcj.intellixir.language.psi.ElixirTokenSets
import com.github.undeadcj.intellixir.language.psi.ElixirTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

class ElixirParserDefinition : ParserDefinition {
    override fun createLexer(project: Project?) = ElixirLexerAdapter()

    override fun createParser(project: Project?) = ElixirParser()

    override fun getFileNodeType(): IFileElementType {
        return IFileElementType(ElixirLanguage)
    }

    override fun getCommentTokens(): TokenSet {
        return ElixirTokenSets.COMMENTS
    }

    override fun getStringLiteralElements(): TokenSet {
        return TokenSet.EMPTY
    }

    override fun createElement(node: ASTNode?): PsiElement {
        return ElixirTypes.Factory.createElement(node)
    }

    override fun createFile(viewProvider: FileViewProvider): PsiFile {
        return ElixirFile(viewProvider)
    }
}