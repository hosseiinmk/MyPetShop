package ir.hossein.mypetshop.domain.usecase

import ir.hossein.mypetshop.data.model.User

interface RegisterUserUseCase {

    suspend operator fun invoke(user: User)
}