package ir.hossein.mypetshop.ui.presentation.addProduct

import android.graphics.Bitmap

data class AddProductUiState(
    val name: String = "",
    val price: String = "",
    val category: Int = -1,
    val amount: String = "",
    val imageUri: Bitmap? = null,
    val imagePath: String = ""
)
