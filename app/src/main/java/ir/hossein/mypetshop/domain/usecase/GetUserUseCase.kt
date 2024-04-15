package ir.hossein.mypetshop.domain.usecase

import ir.hossein.mypetshop.domain.model.User

interface GetUserUseCase {

    suspend operator fun invoke(email: String): User
}