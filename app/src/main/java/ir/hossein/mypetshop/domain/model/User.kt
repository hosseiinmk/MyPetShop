package ir.hossein.mypetshop.domain.model

data class User(
    val id: Int,
    val username: String,
    val name: String,
    val family: String,
    val email: String,
    val isLogged: Int
)
