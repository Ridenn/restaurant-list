package com.lucas.restaurantlist.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("is_password_secure")
    val isPasswordSecure: Boolean
)
