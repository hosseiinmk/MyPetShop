package ir.hossein.mypetshop.domain.repository

import ir.hossein.mypetshop.data.model.Product
import ir.hossein.mypetshop.data.model.User
import ir.hossein.mypetshop.ui.utils.Response
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun addProduct(product: Product)

    suspend fun getProduct(id: Int): Product

    suspend fun getProducts(): Flow<Response<List<Product>>>

    suspend fun updateProduct(product: Product)
}