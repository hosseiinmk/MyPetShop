package ir.hossein.mypetshop.ui.presentation.profile

import ir.hossein.mypetshop.data.model.User

data class ProfileUiState(
    val user: User? = null,
    val email: String = "",
    val registerDialog: Boolean = false,
    val isLoading: Boolean = false
)
