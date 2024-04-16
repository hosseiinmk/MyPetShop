package ir.hossein.mypetshop.domain.usecase.userUsecase

import ir.hossein.mypetshop.domain.model.User

interface GetUserUseCase {

    suspend operator fun invoke(email: String): User
}