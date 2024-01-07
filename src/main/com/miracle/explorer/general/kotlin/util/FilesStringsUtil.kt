package util

import java.text.SimpleDateFormat
import java.util.*

object FilesStringsUtil {

    private const val KB = 1024.0
    private const val MB = 1024.0 * 1024.0
    private const val GB = 1024.0 * 1024.0 * 1024.0

    fun getFormatSize(fileLength: Long): String {
        return if (fileLength < KB) {
            String.format("%d Byte", fileLength)
        } else if (fileLength < MB) {
            String.format("%.0f KB", fileLength / KB)
        } else if (fileLength < GB) {
            String.format("%.2f MB", fileLength / MB)
        } else {
            String.format("%.2f GB", fileLength / GB)
        }
    }

    fun getFormatTime(time: Long): String {
        val data = Date(time)
        val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        return sdf.format(data)
    }

    fun getFileNameWithoutExtension(name: String): String{
        return name.substring(0, name.lastIndexOf("."))
    }

    fun getFileExtension(name: String): String{
        return name.substring(name.lastIndexOf(".") + 1).lowercase(Locale.getDefault())
    }

}
