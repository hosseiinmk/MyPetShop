package ir.hossein.mypetshop.data.repository

import ir.hossein.mypetshop.data.local.dao.ProductDao
import ir.hossein.mypetshop.data.model.ProductDTO
import ir.hossein.mypetshop.domain.repository.ProductRepository
import ir.hossein.mypetshop.ui.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao
) : ProductRepository {

    override suspend fun addProduct(product: ProductDTO) {
        productDao.addProduct(product = product)
    }

    override suspend fun getProduct(id: Int): ProductDTO = productDao.getProduct(id = id)

    override suspend fun getProducts(): Flow<Response<List<ProductDTO>>> = flow {
        try {
            productDao.getProducts().collect { emit(Response.Success(data = it)) }
        } catch (e: Exception) {
            emit(Response.Failure(message = e.stackTraceToString()))
        }
    }

    override suspend fun updateProduct(product: ProductDTO) {
        productDao.updateProduct(product = product)
    }
}
