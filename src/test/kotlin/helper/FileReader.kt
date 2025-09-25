package helper

import java.io.File
import java.nio.file.Paths


internal fun getLines(filePath: String): List<String> {
    val path = Paths.get("").toAbsolutePath()
        .toString() + filePath
    val file = File(path)

    return file.readLines()
}