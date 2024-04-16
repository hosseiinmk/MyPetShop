package ir.hossein.mypetshop.domain.usecase.productUseCase.implement

import ir.hossein.mypetshop.domain.model.Product
import ir.hossein.mypetshop.domain.repository.ProductRepository
import ir.hossein.mypetshop.domain.usecase.productUseCase.AddProductUseCase
import ir.hossein.mypetshop.domain.utils.mapToData
import javax.inject.Inject

class AddProductUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository
): AddProductUseCase {

    override suspend fun invoke(product: Product) {
        productRepository.addProduct(product = product.mapToData())
    }
}