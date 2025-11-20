package com.lucas.restaurantlist.data

import com.lucas.restaurantlist.data.model.LoginResponse
import com.lucas.restaurantlist.data.model.StoreDetailsResponse
import com.lucas.restaurantlist.data.model.StoreResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestaurantService {
    /**
     * Returns the Store feed per location provided.
     */
    @GET("v1/feed")
    suspend fun getStoreFeed(
        @Query("lat") latitude: Double,
        @Query("lng") longitude: Double
    ): List<StoreResponse>

    /**
     * Returns a detailed specification for the Store.
     */
    @GET("v1/stores/{id}")
    suspend fun getStoreDetails(
        @Path("id") storeId: String
    ): StoreDetailsResponse

    @GET("v1/auth/token")
    suspend fun requestLogin(
        @Query("email") email: String,
        @Query("password") password: String
    ): LoginResponse
}