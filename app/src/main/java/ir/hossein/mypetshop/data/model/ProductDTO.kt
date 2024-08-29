package ir.hossein.mypetshop.data.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "products")
data class ProductDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val price: Int,
    val category: Int,
    val amount: Int,
    val imagePath: String
)
