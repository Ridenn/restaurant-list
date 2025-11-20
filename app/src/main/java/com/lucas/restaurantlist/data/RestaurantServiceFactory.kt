package com.lucas.restaurantlist.data

import com.lucas.restaurantlist.Constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestaurantServiceFactory {
    fun makeRestaurantService(): RestaurantService {
        //val okHttpClient = OkHttpClient.Builder()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            //.client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(RestaurantService::class.java)
    }
}
