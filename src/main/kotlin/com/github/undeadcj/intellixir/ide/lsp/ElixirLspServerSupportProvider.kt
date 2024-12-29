package com.github.undeadcj.intellixir.ide.lsp

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServerManager
import com.intellij.platform.lsp.api.LspServerSupportProvider

class ElixirLspServerSupportProvider : LspServerSupportProvider {
    override fun fileOpened(
        project: Project,
        file: VirtualFile,
        serverStarter: LspServerSupportProvider.LspServerStarter
    ) {
        serverStarter.ensureServerStarted(ElixirLspServerDescriptor(project))
    }
}


fun restartElixirServerAsync(project: Project) {
    ApplicationManager.getApplication().invokeLater(Runnable {
        LspServerManager.getInstance(project).stopAndRestartIfNeeded(ElixirLspServerSupportProvider::class.java)
    }, project.disposed)
}