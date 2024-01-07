package model

import util.FileIconResolver
import util.FileLengthUtil
import java.io.File
import java.io.IOException
import java.nio.file.*


class DirectoryModel constructor(file: File): FileModel(file) {

    constructor(path: String): this(File(path))

    var files: ArrayList<FileModel> = initFilesList()
    val filesCount: Int get() = files.size
    override val length: Long get() = FileLengthUtil.getLength(file)

    override val iconUrl: String get() = FileIconResolver.resolveDirectoryIconPath(filesCount)

    @Throws(IOException::class)
    fun initFilesList(): ArrayList<FileModel> {
        val filesList: Array<File>? = file.listFiles()
        return if (filesList.isNullOrEmpty()){
            ArrayList()
        } else {
            ArrayList(filesList.map { file->
                if(file.isDirectory){
                    DirectoryModel(file)
                } else {
                    TypedFileModel(file)
                }
            })
        }
    }

    fun refreshList() {
        files = initFilesList()
    }

}
