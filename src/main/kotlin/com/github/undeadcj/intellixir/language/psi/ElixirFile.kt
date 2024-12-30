package com.github.undeadcj.intellixir.language.psi

import com.github.undeadcj.intellixir.language.ElixirFileType
import com.github.undeadcj.intellixir.language.ElixirLanguage
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider

class ElixirFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, ElixirLanguage) {
    override fun getFileType() = ElixirFileType
    override fun toString() = "Elixir File"
}
