package ir.hossein.mypetshop.domain.usecase.userUsecase.implement

import ir.hossein.mypetshop.domain.model.User
import ir.hossein.mypetshop.domain.repository.UserRepository
import ir.hossein.mypetshop.domain.usecase.userUsecase.RegisterUserUseCase
import ir.hossein.mypetshop.domain.utils.mapToData
import javax.inject.Inject

internal class RegisterUserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : RegisterUserUseCase {

    override suspend fun invoke(user: User) { userRepository.registerUser(user = user.mapToData()) }
}