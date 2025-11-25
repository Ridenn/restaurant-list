package com.lucas.restaurantlist.domain.repository

import com.lucas.restaurantlist.data.model.StoreResponse
import kotlinx.coroutines.flow.Flow

interface StoreFeedRepository {
    fun getStoreFeed(latitude: Double, longitude: Double): Flow<List<StoreResponse>>
}