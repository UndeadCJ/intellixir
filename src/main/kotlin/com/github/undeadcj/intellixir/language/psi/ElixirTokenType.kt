package com.github.undeadcj.intellixir.language.psi

import com.github.undeadcj.intellixir.language.ElixirLanguage
import com.intellij.psi.tree.IElementType

class ElixirTokenType(debugName: String) : IElementType(debugName, ElixirLanguage) {
    override fun toString(): String {
        return "ElixirTokenType." + super.toString()
    }
}