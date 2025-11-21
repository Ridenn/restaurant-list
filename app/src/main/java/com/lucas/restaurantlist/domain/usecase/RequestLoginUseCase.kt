package com.lucas.restaurantlist.domain.usecase

import com.lucas.restaurantlist.data.model.LoginResponse
import com.lucas.restaurantlist.domain.repository.LoginRepository

class RequestLoginUseCase(private val repository: LoginRepository) {
    suspend operator fun invoke(email: String, password: String): LoginResponse {
        return repository.requestLogin(email, password)
    }
}
