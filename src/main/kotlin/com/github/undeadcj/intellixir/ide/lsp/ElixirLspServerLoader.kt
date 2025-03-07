package com.github.undeadcj.intellixir.ide.lsp

import com.intellij.openapi.project.Project
import com.intellij.openapi.util.SystemInfo
import java.io.File
import java.net.URI
import java.nio.file.FileSystems
import java.util.zip.ZipInputStream

class ElixirLspServerLoader(private val project: Project) {

    private val settings = ElixirServiceSettings.getInstance(project)

    init {
        ensureDirectoryExists(RELEASE_DIR)
    }

    fun load(): String {
        if (!isLspInstalled()) {
            installLatestVersion()
        }
        return findExecutable() ?: throw IllegalStateException("LSP executable not found after installation.")
    }

    private fun findExecutable(): String? =
        RELEASE_DIR.listFiles()?.find { it.name == LSP_EXECUTABLE_NAME }?.absolutePath

    private fun isLspInstalled() = findExecutable() != null

    private fun installLatestVersion() {
        val assetFile = downloadAsset(ASSET_URI, RELEASE_DIR, ASSET_NAME)
        extractZip(assetFile, RELEASE_DIR)
    }

    private fun downloadAsset(uri: URI, outputDir: File, fileName: String): File {
        val connection = uri.toURL().openConnection()
        val outputFile = File(outputDir, fileName)

        connection.getInputStream().use { input ->
            outputFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }

        return outputFile
    }

    private fun extractZip(zipFile: File, outputDir: File) {
       val buffer = ByteArray(1024)

        zipFile.inputStream().use { fis ->
            ZipInputStream(fis).use { zis ->
                generateSequence { zis.nextEntry }.forEach { entry ->
                    val newFile = File(outputDir, entry.name)

                    if (entry.isDirectory) {
                        ensureDirectoryExists(newFile)
                    } else {
                        newFile.outputStream().use { fos ->
                            generateSequence { zis.read(buffer).takeIf { it > 0 } }
                                .forEach { fos.write(buffer, 0, it) }
                        }
                        setFilePermissions(newFile)
                    }
                }
            }
        }
    }

    private fun ensureDirectoryExists(dir: File) {
        if (!dir.exists() && !dir.mkdirs()) {
            throw IllegalStateException("Failed to create directory: ${dir.path}")
        }
    }

    private fun setFilePermissions(file: File) {
        if (!file.setExecutable(true) || !file.setReadable(true) || !file.setWritable(true)) {
            throw IllegalStateException("Failed to set permissions for file: ${file.path}")
        }
    }

    companion object {
        private val FILE_SEPARATOR = FileSystems.getDefault().separator
        private val BASE_DIR = File(System.getProperty("user.home") + FILE_SEPARATOR + ".intellixir")
        private val RELEASE_DIR = File(BASE_DIR, "elixir-ls")
        private const val LSP_VERSION = "0.27.1"
        private const val ASSET_NAME = "elixir-ls-v${LSP_VERSION}.zip"
        private val ASSET_URI =
            URI("https://github.com/elixir-lsp/elixir-ls/releases/download/v${LSP_VERSION}/$ASSET_NAME")
        val LSP_EXECUTABLE_NAME: String = if (SystemInfo.isWindows) "language_server.bat" else "language_server.sh"
    }
}