package ir.hossein.mypetshop.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.hossein.mypetshop.data.repository.ProductRepositoryImpl
import ir.hossein.mypetshop.domain.repository.ProductRepository
import ir.hossein.mypetshop.domain.usecase.productUseCase.AddProductUseCase
import ir.hossein.mypetshop.domain.usecase.productUseCase.implement.AddProductUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ProductModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindAddProductUseCase(
        addProductUseCaseImpl: AddProductUseCaseImpl
    ): AddProductUseCase
}