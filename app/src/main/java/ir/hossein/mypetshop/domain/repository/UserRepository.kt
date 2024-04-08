package ir.hossein.mypetshop.domain.repository

import ir.hossein.mypetshop.data.model.User
import ir.hossein.mypetshop.ui.utils.Response
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun registerUser(user: User)

    suspend fun getUser(id: Int): User

    suspend fun getUsers(): Response<Flow<List<User>>>

    suspend fun updateUser(user: User)
}