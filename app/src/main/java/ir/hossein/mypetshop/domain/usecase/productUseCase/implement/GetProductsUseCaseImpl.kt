package ir.hossein.mypetshop.domain.usecase.productUseCase.implement

import ir.hossein.mypetshop.domain.model.Product
import ir.hossein.mypetshop.domain.repository.ProductRepository
import ir.hossein.mypetshop.domain.usecase.productUseCase.GetProductsUseCase
import ir.hossein.mypetshop.domain.utils.mapToDomain
import ir.hossein.mypetshop.ui.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductsUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository
): GetProductsUseCase {

    override suspend fun invoke(): Flow<Response<List<Product>>> = flow {
        productRepository.getProducts().collect { response ->
            when (response) {
                is Response.Success -> {
                    emit(Response.Success(data = response.data.map { it.mapToDomain() }))
                }

                is Response.Failure -> {
                    emit(Response.Failure(message = response.message))
                }
            }
        }
    }
}