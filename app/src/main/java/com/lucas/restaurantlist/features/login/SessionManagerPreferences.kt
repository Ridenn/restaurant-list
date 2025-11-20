package com.lucas.restaurantlist.features.login

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SessionManagerPreferences(context: Context) {

    private val preferences: SharedPreferences = context.getSharedPreferences(
        prefsKey,
        Context.MODE_PRIVATE
    )

    fun saveLoginToken(token: String) {
        preferences.edit { putString(prefsName, token) }
    }

    fun getLoginToken() = preferences.getString(prefsName, null)

    companion object {
        const val prefsKey = "AUTH_KEY"
        const val prefsName = "LOGIN_TOKEN"
    }

}