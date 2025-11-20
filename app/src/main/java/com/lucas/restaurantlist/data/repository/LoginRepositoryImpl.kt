package com.lucas.restaurantlist.data.repository

import com.lucas.restaurantlist.data.RestaurantService
import com.lucas.restaurantlist.data.model.LoginResponse
import com.lucas.restaurantlist.domain.repository.LoginRepository
import com.lucas.restaurantlist.features.login.SessionManagerPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepositoryImpl(
    private val remoteDataSource: RestaurantService,
    private val sharedPrefs: SessionManagerPreferences
) : LoginRepository {

    override suspend fun requestLogin(email: String, password: String): LoginResponse = withContext(Dispatchers.IO) {
        val result = remoteDataSource.requestLogin(email, password)

        if (result.token.isNotEmpty()) {
            sharedPrefs.saveLoginToken(result.token)
        }
        result
    }
}
