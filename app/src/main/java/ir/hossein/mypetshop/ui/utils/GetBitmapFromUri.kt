package ir.hossein.mypetshop.ui.utils

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import javax.inject.Inject

class GetBitmapFromUri @Inject constructor(
    private val application: Application
) {

    operator fun invoke(imageUri: Uri): Bitmap {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            application.contentResolver.openInputStream(imageUri).use { inputStream ->
                return BitmapFactory.decodeStream(inputStream)
            }
        } else {
            ImageDecoder.createSource(application.contentResolver, imageUri).let {
                return ImageDecoder.decodeBitmap(it)
            }
        }
    }
}