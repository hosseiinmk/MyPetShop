package ir.hossein.mypetshop.domain.model

import android.net.Uri
import androidx.annotation.Keep

@Keep
data class Product(
    val id: Int = 0,
    val name: String,
    val price: Int,
    val category: Int,
    val amount: Int,
    val image: String
) {
     companion object {
         val default = Product(
             id = 0,
             name = "NewProduct",
             price = 0,
             category = -1,
             amount = 0,
             image = ""
         )
     }
}
