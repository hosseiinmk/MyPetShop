package ir.hossein.mypetshop.domain.usecase.implement

import ir.hossein.mypetshop.domain.model.User
import ir.hossein.mypetshop.domain.repository.UserRepository
import ir.hossein.mypetshop.domain.usecase.GetUserUseCase
import ir.hossein.mypetshop.domain.utils.mapToDomain
import javax.inject.Inject

class GetUserUseCaseImpl @Inject constructor(private val userRepository: UserRepository) : GetUserUseCase {

    override suspend fun invoke(email: String): User = userRepository.getUser(email = email).mapToDomain()
}