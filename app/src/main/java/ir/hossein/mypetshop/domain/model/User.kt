package ir.hossein.mypetshop.domain.model

import androidx.annotation.Keep
import ir.hossein.mypetshop.ui.utils.Constant

@Keep
data class User(
    val id: Int = 0,
    val username: String,
    val name: String,
    val family: String,
    val email: String,
    val logged: Int,
    val thumbnail: String
) {
    companion object {
        val default = User(
            username = "NewUser",
            name = "",
            family = "",
            email = "",
            logged = 1,
            thumbnail = Constant.PROFILE_IMAGE
        )
    }
}
