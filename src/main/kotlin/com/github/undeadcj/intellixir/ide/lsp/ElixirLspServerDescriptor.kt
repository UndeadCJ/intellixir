package com.github.undeadcj.intellixir.ide.lsp

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor


class ElixirLspServerDescriptor(project: Project) : ProjectWideLspServerDescriptor(project, "Elixir") {
    val lspServerPath = ElixirLspServerLoader(project).load()

    override fun isSupportedFile(file: VirtualFile): Boolean {
        return file.extension == "ex" || file.extension == "exs" || file.extension == "heex"
    }

    override fun createCommandLine() = GeneralCommandLine(lspServerPath, "--stdio")
}