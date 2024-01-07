package model

import util.FileIconResolver
import util.FileTypesResolver
import util.FilesStringsUtil
import java.io.File

class TypedFileModel constructor(file: File): FileModel(file) {

    constructor(path: String): this(File(path))

    val nameWithNoExtension: String = FilesStringsUtil.getFileNameWithoutExtension(name)
    val extension: String = FilesStringsUtil.getFileExtension(name)
    val type: FileType = FileTypesResolver.resolveFileType(extension)
    override val length: Long get() = file.length()

    override val iconUrl: String get() = FileIconResolver.resolveFileIconPath(type)

}
