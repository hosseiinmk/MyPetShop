package ir.hossein.mypetshop.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.hossein.mypetshop.domain.repository.UserRepository
import ir.hossein.mypetshop.data.repository.UserRepositoryImpl
import ir.hossein.mypetshop.domain.usecase.RegisterUserUseCase
import ir.hossein.mypetshop.domain.usecase.implement.RegisterUserUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindRegisterUserUseCase(
        registerUserUseCaseImpl: RegisterUserUseCaseImpl
    ): RegisterUserUseCase
}