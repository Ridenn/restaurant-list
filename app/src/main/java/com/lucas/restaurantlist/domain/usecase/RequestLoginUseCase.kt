package com.lucas.restaurantlist.domain.usecase

import com.lucas.restaurantlist.data.model.LoginResponse
import com.lucas.restaurantlist.domain.repository.LoginRepository

/**
 * Use case for requesting login.
 *
 * This use case encapsulates the login logic, including input validation.
 * It ensures that the email contains an "@" symbol and the password is longer than 5 characters.
 */
class RequestLoginUseCase(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(email: String, password: String): LoginResponse {
        if (!email.contains("@")) {
            throw IllegalArgumentException("Wrong email format.")
        }

        if (password.length <= 5) {
            throw IllegalArgumentException("Invalid password format.")
        }

        return repository.requestLogin(email, password)
    }
}
