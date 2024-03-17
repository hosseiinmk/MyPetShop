package ir.hossein.mypetshop.data.repository

import ir.hossein.mypetshop.data.local.dao.UserDao
import ir.hossein.mypetshop.data.model.User
import ir.hossein.mypetshop.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) : UserRepository {

    override suspend fun registerUser(user: User) { userDao.registerUser(user = user) }

    override suspend fun getUser(id: Int): User = userDao.getUser(userId = id)
}