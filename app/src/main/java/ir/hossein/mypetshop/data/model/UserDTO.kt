package ir.hossein.mypetshop.data.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "users")
data class UserDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val name: String,
    val family: String,
    val email: String,
    val logged: Int,
    val thumbnail: String
)
