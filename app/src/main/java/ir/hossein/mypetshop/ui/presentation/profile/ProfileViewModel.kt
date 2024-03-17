package ir.hossein.mypetshop.ui.presentation.profile

import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hossein.mypetshop.core.BaseViewModel
import ir.hossein.mypetshop.data.model.User
import ir.hossein.mypetshop.domain.usecase.RegisterUserUseCase
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) : BaseViewModel<ProfileUiState>(ProfileUiState()) {

    fun registerUser(user: User) {
        if (user.email.isEmpty()) return
        if (!user.email.contains("@gmail.com")) return
        baseViewModelScope {
            registerUserUseCase(user = user)
            updateState { copy(registerDialog = false) }
        }
    }
}