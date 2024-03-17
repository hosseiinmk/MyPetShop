package ir.hossein.mypetshop.domain.usecase

import ir.hossein.mypetshop.data.model.User

interface GetUserUseCase {

    suspend operator fun invoke(id: Int): User
}