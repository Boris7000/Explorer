package util

import java.io.File

object FileLengthUtil {

    fun getLength(file: File):Long{
        return if(file.isDirectory){
           getFilesListLength(file.listFiles())
        } else {
            file.length()
        }
    }

    fun getFilesListLength(files: Array<File>?):Long{
        return if (!files.isNullOrEmpty()){
            var length: Long = 0
            for (file in files) {
                length += if(file.isFile) {
                    file.length()
                } else {
                    getLength(file)
                }
            }
            length
        } else {
            0
        }
    }
}
