package ir.hossein.mypetshop.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ir.hossein.mypetshop.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun registerUser(user: User)

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUser(email: String): User

    @Query("SELECT * FROM users")
    fun getUsers(): Flow<List<User>>

    @Update
    fun updateUser(user: User)
}