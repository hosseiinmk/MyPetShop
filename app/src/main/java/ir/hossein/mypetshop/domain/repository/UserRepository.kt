package ir.hossein.mypetshop.domain.repository

import ir.hossein.mypetshop.data.model.User

interface UserRepository {

    suspend fun registerUser(user: User)

    suspend fun getUser(id: Int): User
}