package ir.hossein.mypetshop.domain.usecase.userUsecase

import ir.hossein.mypetshop.domain.model.User

interface UpdateUserUseCase {

    suspend operator fun invoke(user: User)
}