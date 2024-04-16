package ir.hossein.mypetshop.ui.presentation.addProduct

import android.net.Uri

data class AddProductUiState(
    val image: Uri? = null,
    val name: String = "NewProduct",
    val price: String = "",
    val category: Int = -1,
    val amount: String = ""
)
