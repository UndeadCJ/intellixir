package com.github.undeadcj.intellixir.ide.lsp

import com.intellij.openapi.components.*
import com.intellij.openapi.project.Project

@Service(Service.Level.PROJECT)
@State(name = "ElixirServiceSettings", storages = [Storage(StoragePathMacros.WORKSPACE_FILE)])
class ElixirServiceSettings(val project: Project): SimplePersistentStateComponent<ElixirServiceState>(ElixirServiceState()) {
    var serviceMode
        get() = state.serviceMode
        set(value) {
            val changed = state.serviceMode != value
            state.serviceMode = value
            if (changed) restartElixirServerAsync(project)
        }

    companion object {
        fun getInstance(project: Project): ElixirServiceSettings = project.service()
    }
}

class ElixirServiceState: BaseState() {
    var serviceMode by enum(ElixirServiceMode.ENABLED)
    var lspServerPackageName by string("elixir-ls")
}

enum class ElixirServiceMode {
    ENABLED,
    DISABLED
}