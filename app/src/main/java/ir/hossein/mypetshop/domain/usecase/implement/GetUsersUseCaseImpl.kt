package ir.hossein.mypetshop.domain.usecase.implement

import ir.hossein.mypetshop.data.model.User
import ir.hossein.mypetshop.domain.repository.UserRepository
import ir.hossein.mypetshop.domain.usecase.GetUsersUseCase
import ir.hossein.mypetshop.ui.utils.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetUsersUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : GetUsersUseCase {

    override suspend operator fun invoke(): Response<Flow<List<User>>> = userRepository.getUsers()
}