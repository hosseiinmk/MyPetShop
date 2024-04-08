package ir.hossein.mypetshop.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.hossein.mypetshop.domain.repository.UserRepository
import ir.hossein.mypetshop.data.repository.UserRepositoryImpl
import ir.hossein.mypetshop.domain.usecase.GetUsersUseCase
import ir.hossein.mypetshop.domain.usecase.RegisterUserUseCase
import ir.hossein.mypetshop.domain.usecase.UpdateUserUseCase
import ir.hossein.mypetshop.domain.usecase.implement.GetUsersUseCaseImpl
import ir.hossein.mypetshop.domain.usecase.implement.RegisterUserUseCaseImpl
import ir.hossein.mypetshop.domain.usecase.implement.UpdateUserUseCaseImpl
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
    internal abstract fun bindRegisterUserUseCase(
        registerUserUseCaseImpl: RegisterUserUseCaseImpl
    ): RegisterUserUseCase

    @Binds
    @Singleton
    internal abstract fun bindGetUsersUseCase(
        getUsersUseCaseImpl: GetUsersUseCaseImpl
    ): GetUsersUseCase

    @Binds
    @Singleton
    internal abstract fun bindUpdateUsersUseCase(
        updateUsersUseCaseImpl: UpdateUserUseCaseImpl
    ): UpdateUserUseCase
}