package model

import util.FilesStringsUtil
import java.io.File


abstract class FileModel constructor(val file: File){

    constructor(path: String): this(File(path))

    val path: String get() = file.absolutePath
    val parentPath: String get() = file.parent
    val name: String get() = file.name
    val lastModified: Long get() = file.lastModified()
    val lastModifiedFormat: String get() = FilesStringsUtil.getFormatTime(lastModified)
    abstract val length: Long
    abstract val iconUrl: String

}
