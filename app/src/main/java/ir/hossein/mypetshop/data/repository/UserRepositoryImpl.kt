package ir.hossein.mypetshop.data.repository

import ir.hossein.mypetshop.data.local.dao.UserDao
import ir.hossein.mypetshop.data.model.User
import ir.hossein.mypetshop.domain.repository.UserRepository
import ir.hossein.mypetshop.ui.utils.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) : UserRepository {

    override suspend fun registerUser(user: User) { userDao.registerUser(user = user) }

    override suspend fun getUser(id: Int): User = userDao.getUser(userId = id)

    override suspend fun getUsers(): Response<Flow<List<User>>> =
        try {
            Response.Success(data = userDao.getUsers())
        } catch (e: Exception) {
            Response.Failure(message = e.stackTraceToString())
        }

    override suspend fun updateUser(user: User) {
        userDao.updateUser(user = user)
    }
}