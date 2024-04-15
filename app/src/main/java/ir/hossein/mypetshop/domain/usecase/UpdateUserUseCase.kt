package ir.hossein.mypetshop.domain.usecase

import ir.hossein.mypetshop.domain.model.User

interface UpdateUserUseCase {

    suspend operator fun invoke(user: User)
}