package ir.hossein.mypetshop.domain.usecase.productUseCase

import ir.hossein.mypetshop.domain.model.Product

interface AddProductUseCase {

    suspend operator fun invoke(product: Product)
}