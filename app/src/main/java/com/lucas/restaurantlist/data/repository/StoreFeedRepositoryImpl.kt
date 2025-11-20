package com.lucas.restaurantlist.data.repository

import com.lucas.restaurantlist.data.RestaurantService
import com.lucas.restaurantlist.data.model.StoreResponse
import com.lucas.restaurantlist.domain.repository.StoreFeedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StoreFeedRepositoryImpl(
    private val remoteDataSource: RestaurantService
) : StoreFeedRepository {

    override suspend fun getStoreFeed(latitude: Double, longitude: Double): List<StoreResponse> = withContext(Dispatchers.IO) {
        val result = remoteDataSource.getStoreFeed(latitude, longitude)
//        localDataSource.saveLocalStoreFeed()
        result
    }
}
