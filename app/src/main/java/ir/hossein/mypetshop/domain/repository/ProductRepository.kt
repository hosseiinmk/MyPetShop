package ir.hossein.mypetshop.domain.repository

import ir.hossein.mypetshop.data.model.ProductDTO
import ir.hossein.mypetshop.ui.utils.Response
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun addProduct(product: ProductDTO)

    suspend fun getProduct(id: Int): ProductDTO

    suspend fun getProducts(): Flow<Response<List<ProductDTO>>>

    suspend fun updateProduct(product: ProductDTO)
}