package com.lucas.restaurantlist.domain.repository

import com.lucas.restaurantlist.data.model.LoginResponse

interface LoginRepository {
    suspend fun requestLogin(email: String, password: String): LoginResponse
}