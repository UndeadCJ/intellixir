package com.github.undeadcj.intellixir

import com.intellij.DynamicBundle
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.PropertyKey

@NonNls
private const val ELIXIR_BUNDLE = "messages.ElixirBundle"

object ElixirBundle : DynamicBundle(ELIXIR_BUNDLE) {

    @JvmStatic
    fun message(@PropertyKey(resourceBundle = ELIXIR_BUNDLE) key: String, vararg params: Any) =
        getMessage(key, *params)

    @Suppress("unused")
    @JvmStatic
    fun messagePointer(@PropertyKey(resourceBundle = ELIXIR_BUNDLE) key: String, vararg params: Any) =
        getLazyMessage(key, *params)
}
