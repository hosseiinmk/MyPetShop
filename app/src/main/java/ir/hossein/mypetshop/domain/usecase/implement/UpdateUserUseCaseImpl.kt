package ir.hossein.mypetshop.domain.usecase.implement

import ir.hossein.mypetshop.domain.model.User
import ir.hossein.mypetshop.domain.repository.UserRepository
import ir.hossein.mypetshop.domain.usecase.UpdateUserUseCase
import ir.hossein.mypetshop.domain.utils.mapToData
import ir.hossein.mypetshop.ui.utils.log
import javax.inject.Inject

internal class UpdateUserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : UpdateUserUseCase {

    override suspend fun invoke(user: User) {
        userRepository.updateUser(user = user.mapToData())
    }
}