package ir.hossein.mypetshop.ui.utils

import android.app.Application
import android.content.ContentValues
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import javax.inject.Inject

class SaveImageToStorage @Inject constructor(
    private val application: Application
) {

    operator fun invoke(imageUri: Uri, imageName: String) {
        val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        } else {
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        }
        application.contentResolver.let { contentResolver ->
            ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, "myImage")
            }.let { contentValues ->
                contentResolver.insert(collection, contentValues)
            }
        }
        ContentValues().apply {
            put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis())
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.DISPLAY_NAME, imageName)
        }.let { contentValues ->
            try {
                application.contentResolver.let { contentResolver ->
                    contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)?.let { contentUri ->
                        contentResolver.openOutputStream(contentUri)?.use {  outputStream ->
                            contentResolver.openInputStream(imageUri).use { inputStream ->
                                val buffer = ByteArray(1024)
                                var bytesRead: Int
                                while (inputStream?.read(buffer).also { bytesRead = it ?: -1 } != -1) {
                                    outputStream.write(buffer, 0, bytesRead)
                                }
                            }
                        }
                    } ?: log("CONTENT_URI_NULL_POINT_EXCEPTION")
                }
            } catch (e: Exception) {
                log(e.stackTraceToString())
            }
        }
    }
}