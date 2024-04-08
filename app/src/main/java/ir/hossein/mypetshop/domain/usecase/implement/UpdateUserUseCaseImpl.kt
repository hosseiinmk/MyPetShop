package ir.hossein.mypetshop.domain.usecase.implement

import ir.hossein.mypetshop.data.model.User
import ir.hossein.mypetshop.domain.repository.UserRepository
import ir.hossein.mypetshop.domain.usecase.UpdateUserUseCase
import javax.inject.Inject

internal class UpdateUserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : UpdateUserUseCase {

    override suspend fun invoke(user: User) {
        userRepository.updateUser(user = user)
    }
}