package com.github.undeadcj.intellixir.ide.lsp

import com.intellij.openapi.project.Project
import com.intellij.openapi.util.SystemInfo
import java.io.File
import java.net.URI
import java.nio.file.FileSystems

class ElixirLspServerLoader(val project: Project) {
    private val settings = ElixirServiceSettings.getInstance(project)

    init {
        if (!releaseDir.exists()) {
            releaseDir.mkdirs()
        }
    }

    private fun findExecutable() =
        releaseDir.listFiles()?.find { file ->
            file.name == LSP_EXECUTABLE_NAME
        }?.path

    private fun lspIsInstalled() = findExecutable() != null

    private fun downloadAsset(): File {
        val connection = assetUri.toURL().openConnection()
        val outputFile = File(releaseDir, assetName)

        connection.getInputStream().use { input ->
            outputFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }
        return outputFile
    }

    private fun getLatest() {
        extractZip(downloadAsset(), releaseDir)
    }

    fun load(): String {
        if (!lspIsInstalled()) {
            getLatest()
        }

        return findExecutable()!!
    }

    companion object {
        private val fileSeparator = FileSystems.getDefault().separator ?: File.pathSeparator

        private val baseDir = "${System.getProperty("user.home")}${fileSeparator}.intellixir".run { File(this) }
        private val releaseDir = "${baseDir.path}${fileSeparator}elixir-ls".run { File(this) }

        private val lspVersion = "0.25.0"
        private val assetName = "elixir-ls-v${lspVersion}.zip"

        private val assetUri =
            URI("https://github.com/elixir-lsp/elixir-ls/releases/download/v${lspVersion}/$assetName")

        val LSP_EXECUTABLE_NAME: String =
            if (SystemInfo.isWindows) "language_server.bat" else "language_server.sh"
    }

    private fun extractZip(zipFile: File, outputDir: File) {
        val buffer = ByteArray(1024)

        zipFile.inputStream().use { fis ->
            java.util.zip.ZipInputStream(fis).use { zis ->
                var entry = zis.nextEntry
                while (entry != null) {
                    val newFile = File(outputDir, entry.name)
                    if (entry.isDirectory) {
                        newFile.mkdirs()
                    } else {
                        newFile.outputStream().use { fos ->
                            var len = zis.read(buffer)
                            while (len > 0) {
                                fos.write(buffer, 0, len)
                                len = zis.read(buffer)
                            }
                        }
                    }
                    setFilePermissions(newFile)
                    entry = zis.nextEntry
                }
                zis.closeEntry()
            }
        }
    }

    private fun setFilePermissions(file: File) {
        file.setExecutable(true)
        file.setReadable(true)
        file.setWritable(true)
    }
}