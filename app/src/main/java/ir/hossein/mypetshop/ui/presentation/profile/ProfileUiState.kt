package ir.hossein.mypetshop.ui.presentation.profile

import ir.hossein.mypetshop.domain.model.User

data class ProfileUiState(
    val activeUser: User? = null,
    val users: List<User> = emptyList(),
    val email: String = "",
    val showDialog: Boolean = false,
    val userExists: Boolean = false,
    val loading: Boolean = false
)
