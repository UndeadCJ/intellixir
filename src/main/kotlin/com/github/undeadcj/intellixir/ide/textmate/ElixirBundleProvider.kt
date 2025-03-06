package com.github.undeadcj.intellixir.ide.textmate

import org.jetbrains.plugins.textmate.api.TextMateBundleProvider
import org.jetbrains.plugins.textmate.api.TextMateBundleProvider.PluginBundle
import com.intellij.openapi.diagnostic.Logger

import com.intellij.openapi.application.PluginPathManager


class ElixirBundleProvider : TextMateBundleProvider {
    private val logger = Logger.getInstance(ElixirBundleProvider::class.java)

    override fun getBundles(): List<PluginBundle> {
        val textmateDir = PluginPathManager.getPluginResource(this::class.java, "textmate") ?: return emptyList()
        val path = textmateDir.toPath()
        return listOf(PluginBundle("Elixir", path))
    }
}
