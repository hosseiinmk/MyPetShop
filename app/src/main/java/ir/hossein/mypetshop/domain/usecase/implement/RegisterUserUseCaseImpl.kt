package ir.hossein.mypetshop.domain.usecase.implement

import ir.hossein.mypetshop.data.model.User
import ir.hossein.mypetshop.domain.repository.UserRepository
import ir.hossein.mypetshop.domain.usecase.RegisterUserUseCase
import javax.inject.Inject

class RegisterUserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : RegisterUserUseCase {

    override suspend fun invoke(user: User) { userRepository.registerUser(user = user) }
}