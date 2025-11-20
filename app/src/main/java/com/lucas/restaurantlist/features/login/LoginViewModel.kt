package com.lucas.restaurantlist.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucas.restaurantlist.data.model.LoginResponse
import com.lucas.restaurantlist.domain.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository
) : ViewModel() {

    sealed class LoginState {
        data class Success(val login: LoginResponse): LoginState()
        data class Error(val errorMessage: String): LoginState()
        object Loading: LoginState()
    }

    private val _getLoginState by lazy { MutableStateFlow<LoginState>(LoginState.Loading) }
    val getLoginState: StateFlow<LoginState> = _getLoginState

    fun requestLogin(email: String, password: String) = viewModelScope.launch {
        _getLoginState.value = LoginState.Loading

        try {
//            if (!validateEmail(email)) {
//                _getLoginState.value = LoginState.Error("Wrong email format.")
//            }
//
//            if (!validatePassword(password)) {
//                _getLoginState.value = LoginState.Error("Invalid password format.")
//            }

            val response = repository.requestLogin(email, password)
            _getLoginState.value = LoginState.Success(response)
        } catch (e: Exception) {
            _getLoginState.value = LoginState.Error("Login failed, try again.")
        }
    }

    private fun validateEmail(email: String): Boolean = email.contains("@")

    private fun validatePassword(password: String): Boolean = password.length > 5
}
