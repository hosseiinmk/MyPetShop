package ir.hossein.mypetshop.ui.presentation.profile

import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hossein.mypetshop.core.BaseViewModel
import ir.hossein.mypetshop.data.model.User
import ir.hossein.mypetshop.domain.usecase.GetUsersUseCase
import ir.hossein.mypetshop.domain.usecase.RegisterUserUseCase
import ir.hossein.mypetshop.domain.usecase.UpdateUserUseCase
import ir.hossein.mypetshop.ui.utils.Response
import ir.hossein.mypetshop.ui.utils.myLog
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val getUsersUseCase: GetUsersUseCase,
    private val updateUserUseCase: UpdateUserUseCase
) : BaseViewModel<ProfileUiState>(ProfileUiState()) {

    init {
        getUsers()
    }

    private fun getUsers() {
        updateState { copy(isLoading = true) }
        baseViewModelScope {
            getUsersUseCase().let { response ->
                when (response) {
                    is Response.Success -> {
                        response.data.collect { users ->
                            users.forEach { user ->
                                if (user.isLogged == 1) {
                                    updateState { copy(user = user) }
                                    return@forEach
                                }
                            }
                            if (stateValue().user == null) {
                                updateState { copy(registerDialog = true) }
                            }
                            updateState { copy(isLoading = false) }
                        }
                    }

                    is Response.Failure -> {
                        myLog(response.message)
                    }
                }
            }
        }
    }

    fun registerUser(user: User) {
        if (user.email.isEmpty()) return
        if (!user.email.contains("@gmail.com")) return
        baseViewModelScope {
            registerUserUseCase(user = user)
            updateState { copy(registerDialog = false) }
        }
    }

    fun logoutUser() {
        stateValue().user?.let { user ->
            baseViewModelScope(dispatcher = Dispatchers.IO) {
                updateUserUseCase(user = user.copy(isLogged = 0))
            }
        }
    }
}