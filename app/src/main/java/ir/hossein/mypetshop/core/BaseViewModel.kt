package ir.hossein.mypetshop.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<T>(uiState: T) : ViewModel() {

    private val state: MutableStateFlow<T> = MutableStateFlow(uiState)

    fun state(): StateFlow<T> = state.asStateFlow()

    private fun stateValue(): T = state().value

    fun updateState(newState: T.() -> T) {
        state.value = newState(stateValue())
    }

    fun baseViewModelScope(
        dispatcher: CoroutineDispatcher = Dispatchers.Main,
        block: suspend () -> Unit
    ): Job = viewModelScope.launch(dispatcher) { block() }
}