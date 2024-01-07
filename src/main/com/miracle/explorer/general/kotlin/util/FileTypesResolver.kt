package util

import model.FileType

object FileTypesResolver {

    fun resolveFileType(extension:String): FileType {
        return when(extension){
            "png", "jpg" -> FileType.IMAGE
            "mp3" -> FileType.AUDIO
            "txt" -> FileType.TEXT
            else -> FileType.ANY
        }
    }

}
