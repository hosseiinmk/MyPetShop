package ir.hossein.mypetshop.data.repository

import ir.hossein.mypetshop.data.local.dao.UserDao
import ir.hossein.mypetshop.data.model.UserDTO
import ir.hossein.mypetshop.domain.repository.UserRepository
import ir.hossein.mypetshop.ui.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) : UserRepository {

    override suspend fun registerUser(user: UserDTO) { userDao.registerUser(user = user) }

    override suspend fun getUser(email: String): UserDTO = userDao.getUser(email = email)

    override suspend fun getUsers(): Flow<Response<List<UserDTO>>> = flow {
        try {
            userDao.getUsers().collect { emit(Response.Success(data = it)) }
        } catch (e: Exception) {
            emit(Response.Failure(message = e.stackTraceToString()))
        }
    }

    override suspend fun updateUser(user: UserDTO) { userDao.updateUser(user = user) }
}
