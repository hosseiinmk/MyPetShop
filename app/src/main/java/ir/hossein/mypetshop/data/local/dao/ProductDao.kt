package ir.hossein.mypetshop.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ir.hossein.mypetshop.data.model.ProductDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert
    suspend fun addProduct(product: ProductDTO)

    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getProduct(id: Int): ProductDTO

    @Query("SELECT * FROM products")
    fun getProducts(): Flow<List<ProductDTO>>

    @Update
    fun updateProduct(product: ProductDTO)
}