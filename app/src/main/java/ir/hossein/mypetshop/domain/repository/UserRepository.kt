package ir.hossein.mypetshop.domain.repository

import ir.hossein.mypetshop.data.model.UserDTO
import ir.hossein.mypetshop.ui.utils.Response
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun registerUser(user: UserDTO)

    suspend fun getUser(email: String): UserDTO

    suspend fun getUsers(): Flow<Response<List<UserDTO>>>

    suspend fun updateUser(user: UserDTO)
}