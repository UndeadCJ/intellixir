package com.github.undeadcj.intellixir.language.psi

import com.intellij.psi.tree.TokenSet


object ElixirTokenSets {
    var IDENTIFIERS: TokenSet = TokenSet.create(ElixirTypes.KEY)

    var COMMENTS: TokenSet = TokenSet.create(ElixirTypes.COMMENT)
}