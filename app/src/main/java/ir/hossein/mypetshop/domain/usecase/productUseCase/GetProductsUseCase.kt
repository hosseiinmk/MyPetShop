package ir.hossein.mypetshop.domain.usecase.productUseCase

import ir.hossein.mypetshop.domain.model.Product
import ir.hossein.mypetshop.ui.utils.Response
import kotlinx.coroutines.flow.Flow

interface GetProductsUseCase {

    suspend operator fun invoke(): Flow<Response<List<Product>>>
}