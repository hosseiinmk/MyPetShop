package ir.hossein.mypetshop.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ir.hossein.mypetshop.data.model.UserDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun registerUser(user: UserDTO)

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUser(email: String): UserDTO

    @Query("SELECT * FROM users")
    fun getUsers(): Flow<List<UserDTO>>

    @Update
    fun updateUser(user: UserDTO)
}