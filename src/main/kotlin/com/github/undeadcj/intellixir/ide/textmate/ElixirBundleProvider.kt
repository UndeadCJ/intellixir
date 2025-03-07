package com.github.undeadcj.intellixir.ide.textmate

import org.jetbrains.plugins.textmate.api.TextMateBundleProvider
import org.jetbrains.plugins.textmate.api.TextMateBundleProvider.PluginBundle

import com.intellij.openapi.application.PluginPathManager


class ElixirBundleProvider : TextMateBundleProvider {
    private fun getElixirBundle(): PluginBundle {
        val textmateDir = PluginPathManager.getPluginResource(this::class.java, "textmate/elixir")
            ?: throw IllegalStateException("Resource 'elixir' not found for ElixirBundleProvider")

        val path = textmateDir.toPath()
        return PluginBundle("Elixir", path)
    }

    override fun getBundles(): List<PluginBundle> {
        return listOf(getElixirBundle())
    }
}
