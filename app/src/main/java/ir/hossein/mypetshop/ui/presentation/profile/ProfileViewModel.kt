package ir.hossein.mypetshop.ui.presentation.profile

import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hossein.mypetshop.core.BaseViewModel
import ir.hossein.mypetshop.domain.model.User
import ir.hossein.mypetshop.domain.usecase.GetUserUseCase
import ir.hossein.mypetshop.domain.usecase.GetUsersUseCase
import ir.hossein.mypetshop.domain.usecase.RegisterUserUseCase
import ir.hossein.mypetshop.domain.usecase.UpdateUserUseCase
import ir.hossein.mypetshop.ui.utils.Response
import ir.hossein.mypetshop.ui.utils.log
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val getUsersUseCase: GetUsersUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase
) : BaseViewModel<ProfileUiState>(ProfileUiState()) {

    init {
        getUsers()
    }

    private fun getUsers() {
        updateState { copy(loading = true) }
        baseViewModelScope(dispatcher = Dispatchers.IO) {
            getUsersUseCase().collect { response ->
                when (response) {
                    is Response.Success -> {
                        if (response.data.isNotEmpty()) {
                            updateState { copy(users = response.data) }
                            response.data.forEach { user ->
                                if (user.logged == 1) {
                                    updateState { copy(activeUser = user) }
                                    return@forEach
                                }
                            }
                        }
                        stateValue().activeUser?.let {
                            updateState { copy(showDialog = false) }
                        } ?: updateState { copy(showDialog = true) }
                        updateState { copy(loading = false) }
                    }

                    is Response.Failure -> {
                        log(response.message)
                    }
                }
            }
        }
    }

    fun registerUser(user: User) {
        if (user.email.isEmpty()) return
        if (!user.email.contains("@gmail.com")) return
        if (stateValue().userExists) return
        if (stateValue().users.isNotEmpty()) {
            for (savedUser in stateValue().users) {
                if (user.email == savedUser.email) {
                    updateState { copy(userExists = true) }
                    return
                }
            }
        }
        baseViewModelScope(dispatcher = Dispatchers.IO) {
            registerUserUseCase(user = user)
            updateState { copy(showDialog = false) }
        }
    }

    fun loginUser(email: String) {
        baseViewModelScope(dispatcher = Dispatchers.IO) {
            stateValue().users.forEach(action = { user ->
                if (user.email == email) {
                    getUserUseCase(email = email).let {
                        updateUserUseCase(user = it.copy(logged = 1))
                        updateState { copy(activeUser = it) }
                    }
                }
            })
        }
    }

    fun logoutUser() {
        baseViewModelScope(dispatcher = Dispatchers.IO) {
            updateUserUseCase(user = stateValue().activeUser!!.copy(logged = 0))
        }
    }
}