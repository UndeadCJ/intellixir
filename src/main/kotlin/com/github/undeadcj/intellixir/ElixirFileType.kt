package com.github.undeadcj.intellixir

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

object ElixirFileType : LanguageFileType(ElixirLanguage) {

    override fun getName(): String = "Elixir File"

    override fun getDescription(): String = "Elixir language file"

    override fun getDefaultExtension(): String = "ex"

    override fun getIcon(): Icon = ElixirIcons.FILE
}