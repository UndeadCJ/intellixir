package com.github.undeadcj.intellixir.ide.ui

import com.github.undeadcj.intellixir.ElixirBundle
import com.github.undeadcj.intellixir.ide.lsp.ElixirServiceSettings
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.options.UiDslUnnamedConfigurable
import com.intellij.openapi.project.Project
import com.intellij.ui.dsl.builder.Panel

class ElixirSettingsConfigurable(val project: Project) : UiDslUnnamedConfigurable.Simple(), Configurable {
    private val settings = ElixirServiceSettings.getInstance(project)

    override fun Panel.createContent() {
        group(ElixirBundle.message("elixir.settings.service.configurable.service.group")) {
        }
    }

    override fun getDisplayName() = ElixirBundle.message("elixir.settings.configurable.title")
}

