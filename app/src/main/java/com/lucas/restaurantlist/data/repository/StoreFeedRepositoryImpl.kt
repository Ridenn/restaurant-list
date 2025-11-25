package com.lucas.restaurantlist.data.repository

import com.lucas.restaurantlist.data.RestaurantService
import com.lucas.restaurantlist.data.model.StoreResponse
import com.lucas.restaurantlist.domain.repository.StoreFeedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class StoreFeedRepositoryImpl(
    private val remoteDataSource: RestaurantService
) : StoreFeedRepository {

    override fun getStoreFeed(latitude: Double, longitude: Double): Flow<List<StoreResponse>> = flow {
        val result = remoteDataSource.getStoreFeed(latitude, longitude)
//        localDataSource.saveLocalStoreFeed()
        emit(result)
    }.flowOn(Dispatchers.IO)
}
