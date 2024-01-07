package util

import model.FileType

object FileIconResolver {

    fun resolveFileIconPath(fileType: FileType): String {
        return when(fileType){
            FileType.IMAGE -> "/icons/image_file.png"
            FileType.AUDIO -> "/icons/audio_file.png"
            FileType.TEXT -> "/icons/text_file.png"
            FileType.ANY -> "/icons/file.png"
        }
    }

    fun resolveDirectoryIconPath(size: Int): String {
        return when(size){
            0 -> "/icons/empty_folder.png"
            else -> "/icons/not_empty_folder.png"
        }
    }

}
