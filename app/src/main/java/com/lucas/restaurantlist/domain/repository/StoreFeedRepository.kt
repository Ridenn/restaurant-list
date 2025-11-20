package com.lucas.restaurantlist.domain.repository

import com.lucas.restaurantlist.data.model.StoreResponse

interface StoreFeedRepository {
    suspend fun getStoreFeed(latitude: Double, longitude: Double): List<StoreResponse>
}