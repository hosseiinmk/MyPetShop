package ir.hossein.mypetshop.domain.usecase.userUsecase

import ir.hossein.mypetshop.domain.model.User

interface RegisterUserUseCase {

    suspend operator fun invoke(user: User)
}