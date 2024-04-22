package ir.hossein.mypetshop.domain.model

import androidx.annotation.Keep

@Keep
data class Product(
    val id: Int = 0,
    val name: String,
    val price: Int,
    val category: Int,
    val amount: Int,
    val imagePath: String
) {
     companion object {
         val default = Product(
             id = 0,
             name = "",
             price = 0,
             category = -1,
             amount = 0,
             imagePath = ""
         )
     }
}
