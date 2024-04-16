package ir.hossein.mypetshop.domain.usecase.userUsecase.implement

import ir.hossein.mypetshop.domain.model.User
import ir.hossein.mypetshop.domain.repository.UserRepository
import ir.hossein.mypetshop.domain.usecase.userUsecase.UpdateUserUseCase
import ir.hossein.mypetshop.domain.utils.mapToData
import javax.inject.Inject

internal class UpdateUserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : UpdateUserUseCase {

    override suspend fun invoke(user: User) {
        userRepository.updateUser(user = user.mapToData())
    }
}